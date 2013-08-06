package com.android.mihospital.turnos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.loginandregister.DetalleTurnoSolicitadoActivity;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.utils.Utils;
import com.android.mihospital.ws.turnos.ListadoDeTurnosResult;

public class HistorialDeTurnosActivity extends Activity {
	private static final String SEPARADOR_TURNOS = "#";
	
	private Paciente paciente;
	private ArrayList<Turno> turnos;
	private ArrayList<Turno> turnosPasados;
	private String resultadoTurnos;
	private Context context;
	protected Turno turnoARevisar;

	protected Integer idTurnoARevisar;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historial_search);
		context = this;
		
		resultadoTurnos = getIntent().getStringExtra("resultadoTurno");
	
		paciente = getIntent().getParcelableExtra("pacienteLogeado");
	
		if (paciente!=null){
			PacienteLogeadoSession.setPacienteLogeado(paciente);
		}else{
			paciente = PacienteLogeadoSession.getPacientLogeado();
		}
		
		ActualizarTurnosTask mAuthTask = new ActualizarTurnosTask();
		mAuthTask.execute((Void) null);
		//cargarTurnos();
		//agregarResultadoDeLaBusqueda();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		ActualizarTurnosTask mAuthTask = new ActualizarTurnosTask();
		mAuthTask.execute((Void) null);
	}

	private void cargarTurnos() {
		turnos = new ArrayList<Turno>();
		turnosPasados= new ArrayList<Turno>();
		if(resultadoTurnos!=null){
		String[] turnosDisponibles = resultadoTurnos.split(SEPARADOR_TURNOS);
		for (int i=0; i< turnosDisponibles.length; i++){
			String[] turnoDisponible = turnosDisponibles[i].split(";") ;
			Turno turno = new Turno(Integer.parseInt(turnoDisponible[5]), turnoDisponible[6],turnoDisponible[7]);
			turno.setNombreMedico(turnoDisponible[1] + " " +turnoDisponible[2]);
			turno.setEspecialidad(turnoDisponible[3]);
			turno.setSucursal(Integer.parseInt(turnoDisponible[4]));
			turno.setEstado(turnoDisponible[8]);
			if(turno.getEstado().equals(Utils.CANCELADO_PACIENTE))
				continue;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date=null;
			try {
				date = format.parse(turno.getDia());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (date.before(new Date()))
				turnosPasados.add(turno);
			else
				turnos.add(turno);

		}
		}
	}


	private void agregarResultadoDeLaBusqueda() {
		ListView listView = (ListView) findViewById(R.id.listTurno);
		listView.setAdapter(new HistorialTurnoAdapter(this,
				android.R.layout.simple_list_item_1, turnos));
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				HistorialDeTurnosActivity.this.turnoARevisar = turnos.get(position);
				if(turnoARevisar.getEstado().equals(Utils.CANCELADO_MEDICO))
					return;
				HistorialDeTurnosActivity.this.idTurnoARevisar = turnos.get(position).getId();
				Intent intent = new Intent(getApplicationContext(), DetalleTurnoSolicitadoActivity.class);
				intent.putExtra("resultadoTurno", resultadoTurnos);
				intent.putExtra("pacienteLogeado",	paciente);
				intent.putExtra("especialidad",	turnoARevisar.getEspecialidad());
				intent.putExtra("nombre",	turnoARevisar.getNombreMedico());
				intent.putExtra("idSucursal", turnoARevisar.getSucursal());
				intent.putExtra("turnoARevisar",HistorialDeTurnosActivity.this.turnoARevisar);
				startActivity(intent);

			}
		});
		ListView listView2 = (ListView) findViewById(R.id.listTurno2);
		listView2.setAdapter(new HistorialTurnoAdapter(this,
				android.R.layout.simple_list_item_1, turnosPasados));
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.turno_search, menu);
		return true;
	}

	public class ActualizarTurnosTask extends AsyncTask<Void, Void, Boolean> {

		public ActualizarTurnosTask() {
			super();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			ListadoDeTurnosResult resultado = ServiceProvider.getClient()
					.buscarHistorialDeTurnos(paciente.getId());

			if (resultado.getResultado() == null) {
				return false;
			}

			if (resultado.getResultado().equals("NO_DATA_FOUND")) {
				return true;
			}

			resultadoTurnos = resultado.getTurnos();
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (success) {
				cargarTurnos();
				agregarResultadoDeLaBusqueda();
			} else {
				Toast.makeText(context.getApplicationContext(),
						"No se pudo obtener los turnos", Toast.LENGTH_SHORT)
						.show();
			}
		}

	}
}
