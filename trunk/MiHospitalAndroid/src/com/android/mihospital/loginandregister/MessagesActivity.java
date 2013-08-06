package com.android.mihospital.loginandregister;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import com.android.mihospital.consultamedico.MedicoAdapter;
import com.android.mihospital.dominio.Medico;
import com.android.mihospital.mensajes.MensajesActivity;
import com.android.mihospital.utils.MedicosParser;



import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class MessagesActivity extends Activity {

	private BusquedaMedicosRecientesTask mAuthTask = null;
	private int idPaciente;
	private ProgressDialog progressDialog;
	private Context context;
	private String resultadoMedicos;
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messages);
		idPaciente= Integer.valueOf(getIntent().getStringExtra("pacienteLogeado"));
		context = this;
		resultadoMedicos = getIntent().getStringExtra("resultadoBusqueda");
		
		
		getMedicos();
		agregarResultadoDeLaBusqueda();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.messages, menu);
		return true;
	}
	
	
		
	public void cargarHistorialMedicos(){
			
		mAuthTask = new BusquedaMedicosRecientesTask(idPaciente);
		mAuthTask.execute((Void) null);
		progressDialog = new ProgressDialog(context);	
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Preparando la búsqueda");
		progressDialog.show();

		}
	
	public void getMedicos() {
		this.medicos.addAll((ArrayList<Medico>) MedicosParser.parsearMedicos(this.resultadoMedicos));
	}
	
	private void agregarResultadoDeLaBusqueda() {
		ListView listView = (ListView) findViewById(R.id.listMedicos);
		listView.setAdapter(new MedicoAdapter(this,
				android.R.layout.simple_list_item_1, medicos));
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int idMedicoAConsultar = medicos.get(position).getId();
				Intent mensajesConMedico = new Intent(context,MensajesActivity.class);
				mensajesConMedico.putExtra("idPacienteLogueado", Integer.toString(idPaciente));
				mensajesConMedico.putExtra("idMedicoAMensajear", Integer.toString(idMedicoAConsultar));
				startActivity(mensajesConMedico);

			}
		});
	}
	
	

}
