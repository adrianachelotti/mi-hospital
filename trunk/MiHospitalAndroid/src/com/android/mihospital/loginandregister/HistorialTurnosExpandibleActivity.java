package com.android.mihospital.loginandregister;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.turnos.Turno;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.utils.Utils;
import com.android.mihospital.ws.turnos.ListadoDeTurnosResult;

@SuppressLint("SimpleDateFormat")
public class HistorialTurnosExpandibleActivity extends Activity {
	private static final String SEPARADOR_TURNOS = "#";

	private Paciente paciente;
	private ArrayList<Turno> turnos;
	private ArrayList<Turno> turnosPasados;
	private String resultadoTurnos;
	private Context context;
	protected Turno turnoARevisar;

	protected Integer idTurnoARevisar;

	private ExpandableListView mExpandableList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historial_turnos_expandible);

		mExpandableList = (ExpandableListView) findViewById(R.id.expandableListView1);

		context = this;

		resultadoTurnos = getIntent().getStringExtra("resultadoTurno");

		paciente = getIntent().getParcelableExtra("pacienteLogeado");

		if (paciente != null) {
			PacienteLogeadoSession.setPacienteLogeado(paciente);
		} else {
			paciente = PacienteLogeadoSession.getPacientLogeado();
		}

		ActualizarTurnosTask mAuthTask = new ActualizarTurnosTask();
		mAuthTask.execute((Void) null);

		// populate();
	}

	@Override
	protected void onRestart() {
		super.onRestart();

		ActualizarTurnosTask mAuthTask = new ActualizarTurnosTask();
		mAuthTask.execute((Void) null);
	}

	private void populate() {
		ArrayList<Parent> arrayParents = new ArrayList<Parent>();
		turnos = new ArrayList<Turno>();
		turnosPasados = new ArrayList<Turno>();

		Parent parent = new Parent();
		parent.setTitle("Pendientes");

		Parent parent2 = new Parent();
		parent2.setTitle("Anteriores");

		if (resultadoTurnos != null) {
			String[] turnosDisponibles = resultadoTurnos
					.split(SEPARADOR_TURNOS);
			for (int i = 0; i < turnosDisponibles.length; i++) {
				String[] turnoDisponible = turnosDisponibles[i].split(";");
				Turno turno = new Turno(Integer.parseInt(turnoDisponible[5]),
						turnoDisponible[6], turnoDisponible[7]);
				turno.setNombreMedico(turnoDisponible[1] + " "
						+ turnoDisponible[2]);
				turno.setEspecialidad(turnoDisponible[3]);
				turno.setSucursal(Integer.parseInt(turnoDisponible[4]));
				turno.setEstado(turnoDisponible[8]);
				if (turno.getEstado().equals(Utils.CANCELADO_PACIENTE))
					continue;
				SimpleDateFormat format = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm");
				Date date = null;
				try {
					date = format.parse(turno.getDia());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (date.before(new Date()))
					turnosPasados.add(turno);
				else
					turnos.add(turno);

			}
		}
		parent.setArrayChildren(turnos);
		parent2.setArrayChildren(turnosPasados);
		arrayParents.add(parent);
		arrayParents.add(parent2);
		// sets the adapter that provides data to the list.
		mExpandableList.setAdapter(new ExpandableTurnosAdapter(this,
				arrayParents));
		mExpandableList.expandGroup(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.historial_turnos_expandible, menu);
		return true;
	}

	public class Parent {
		private String mTitle;
		private ArrayList<Turno> mArrayChildren;

		public String getTitle() {
			return mTitle;
		}

		public void setTitle(String mTitle) {
			this.mTitle = mTitle;
		}

		public ArrayList<Turno> getArrayChildren() {
			return mArrayChildren;
		}

		public void setArrayChildren(ArrayList<Turno> mArrayChildren) {
			this.mArrayChildren = mArrayChildren;
		}
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
				populate();
				agregarResultadoDeLaBusqueda();
			} else {
				Toast.makeText(context.getApplicationContext(),
						"No se pudo obtener los turnos", Toast.LENGTH_SHORT)
						.show();
			}
		}

	}

	private void agregarResultadoDeLaBusqueda() {
		mExpandableList
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
					public boolean onChildClick(
							ExpandableListView expandableListView, View view,
							int groupPosition, int childPosition, long id) {

						if (groupPosition == 1)
							return true;
						HistorialTurnosExpandibleActivity.this.turnoARevisar = turnos
								.get(childPosition);
						if (turnoARevisar.getEstado().equals(
								Utils.CANCELADO_MEDICO))
							return true;
						HistorialTurnosExpandibleActivity.this.idTurnoARevisar = turnos
								.get(childPosition).getId();
						Intent intent = new Intent(getApplicationContext(),
								DetalleTurnoSolicitadoActivity.class);
						intent.putExtra("resultadoTurno", resultadoTurnos);
						intent.putExtra("pacienteLogeado", paciente);
						intent.putExtra("especialidad",
								turnoARevisar.getEspecialidad());
						intent.putExtra("nombre",
								turnoARevisar.getNombreMedico());
						intent.putExtra("idSucursal",
								turnoARevisar.getSucursal());
						intent.putExtra(
								"turnoARevisar",
								HistorialTurnosExpandibleActivity.this.turnoARevisar);
						startActivity(intent);
						return true;
					}
				});
	}
}
