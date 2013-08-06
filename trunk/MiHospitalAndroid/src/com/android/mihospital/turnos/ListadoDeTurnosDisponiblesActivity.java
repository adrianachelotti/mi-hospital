package com.android.mihospital.turnos;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



import com.android.mihospital.dominio.Medico;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.PacienteLogeadoSession;


public class ListadoDeTurnosDisponiblesActivity extends Activity {
	private static final String SEPARADOR_TURNOS = "#";
	private ProgressDialog progressDialog;
	
	private Paciente paciente;
	private ArrayList<Turno> turnos;
	private Integer idTurnoAReservar;
	private String resultadoTurnos;
	private Medico medicoConsultado;
	public Turno turnoAReservar;
	private String resultadosSerializados;
	private int idSucursalSeleccioada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_turnos_search);
		resultadosSerializados = getIntent().getStringExtra("resultadoBusqueda");
		resultadoTurnos = getIntent().getStringExtra("resultadoTurno");
		medicoConsultado = getIntent().getParcelableExtra("medicoConsultado");
		paciente = getIntent().getParcelableExtra("pacienteLogeado");
		idSucursalSeleccioada = getIntent().getIntExtra("idSucursal",idSucursalSeleccioada);
		if (paciente!=null){
			PacienteLogeadoSession.setPacienteLogeado(paciente);
		}else{
			paciente = PacienteLogeadoSession.getPacientLogeado();
		}
		
		cargarTurnos();
		agregarResultadoDeLaBusqueda();
	}

	private void cargarTurnos() {
		turnos = new ArrayList<Turno>();
		String[] turnosDisponibles = resultadoTurnos.split(SEPARADOR_TURNOS);
		for (int i=0; i< turnosDisponibles.length; i++){
			String[] turnoDisponible = turnosDisponibles[i].split(";") ;
			Turno turno = new Turno(Integer.parseInt(turnoDisponible[0]), turnoDisponible[1],turnoDisponible[2]);
			turnos.add(turno);

		}
	}


	private void agregarResultadoDeLaBusqueda() {
		ListView listView = (ListView) findViewById(R.id.listTurno);
		listView.setAdapter(new TurnoAdapter(this,
				android.R.layout.simple_list_item_1, turnos));
		listView.setOnItemClickListener(new OnItemClickListener() {


			

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListadoDeTurnosDisponiblesActivity.this.turnoAReservar = turnos.get(position);
				ListadoDeTurnosDisponiblesActivity.this.idTurnoAReservar = turnos.get(position).getId();
				Intent intent = new Intent(getApplicationContext(), DetalleTurnoActivity.class);
				intent.putExtra("resultadoTurno", resultadoTurnos);
				intent.putExtra("resultadoBusqueda",resultadosSerializados);
				intent.putExtra("pacienteLogeado",	paciente);
				intent.putExtra("idSucursal", ListadoDeTurnosDisponiblesActivity.this.idSucursalSeleccioada);
				intent.putExtra("turnoAReservar",ListadoDeTurnosDisponiblesActivity.this.turnoAReservar);
				intent.putExtra("medicoConsultado",ListadoDeTurnosDisponiblesActivity.this.medicoConsultado);
				startActivity(intent);

			
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.turno_search, menu);
		return true;
	}

}
