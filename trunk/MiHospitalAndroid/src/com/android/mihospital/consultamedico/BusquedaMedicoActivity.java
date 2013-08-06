package com.android.mihospital.consultamedico;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Medico;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.MedicosParser;
import com.android.mihospital.ws.medico.MedicoResult;

public class BusquedaMedicoActivity extends Activity {

	
	String resultadosSerializados;
	int idMedicoAConsultar;
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	private ProgressDialog progressDialog;
	private Context context;
	private ObtenerMedicoByIdTask mAuthTask = null;
	private Medico medicoAConsultar;
	private TextView cantidadResultados;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medic_search);
		resultadosSerializados = getIntent()
				.getStringExtra("resultadoBusqueda");

		if(!getIntent().getBooleanExtra("soloMisMedicos",false)){
			getMedicos();
		}else{
			Button filtrosButton = (Button) findViewById(R.id.elegirFiltros);
			filtrosButton.setVisibility(View.INVISIBLE);
		}
		cantidadResultados =(TextView) findViewById(R.id.resultados);
		cantidadResultados.setText( medicos.size() + " Resultados");
		agregarFiltrarBusqueda();
		agregarResultadoDeLaBusqueda();
		context = this;

	}

	private void agregarFiltrarBusqueda() {
		Button filtrosButton = (Button) findViewById(R.id.elegirFiltros);
		filtrosButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent filtroActivity = new Intent(getApplicationContext(),
						FiltroBusquedaMedicosActivity.class);
				filtroActivity.putExtra("resultadoBusqueda",
						resultadosSerializados);
				startActivity(filtroActivity);
				finish();
			}

		});

	}

	public void volverHome(View view) {
		/*Button filtrosButton = (Button) findViewById(R.id.volverHome);
		filtrosButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent filtroActivity = new Intent(getApplicationContext(),
						HomeActivity.class);
				startActivity(filtroActivity);
				finish();
			}

		});*/

	}

	private void agregarResultadoDeLaBusqueda() {
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new MedicoAdapter(this,
				android.R.layout.simple_list_item_1, medicos));
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				BusquedaMedicoActivity.this.idMedicoAConsultar = medicos.get(
						position).getId();

				mAuthTask = new ObtenerMedicoByIdTask();
				mAuthTask.execute((Void) null);
				progressDialog = new ProgressDialog(context);
				progressDialog.setTitle("Por favor espere");
				progressDialog.setMessage("Buscando medico");
				progressDialog.show();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.medic_search, menu);
		return true;
	}

	public class ObtenerMedicoByIdTask extends AsyncTask<Void, Void, Boolean> {

		String mensajeErrorLogin;
		String resultadoBusqueda;

		@Override
		protected Boolean doInBackground(Void... params) {

			MedicoResult resultado = ServiceProvider.getClient()
					.obtenerMedicoById(
							BusquedaMedicoActivity.this.idMedicoAConsultar);
			medicoAConsultar = new Medico(resultado);

			if (resultado.getResultado() == null) {
				mensajeErrorLogin = "Error en la conexión.";
				return false;
			}
			return true;

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();
			mAuthTask = null;

			if (success) {
				Intent intent = new Intent(getApplicationContext(),
						DetalleMedicoActivity.class);
				intent.putExtra("medicoAConsultar", medicoAConsultar);
				intent.putExtra("resultadoBusqueda", resultadosSerializados);
				startActivity(intent);
			} else {
				Toast toast = Toast.makeText(BusquedaMedicoActivity.this,
						mensajeErrorLogin, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent resultadoBusquedaIntent = new Intent(
						getApplicationContext(), BusquedaMedicoActivity.class);
				resultadoBusquedaIntent.putExtra("resultadoBusqueda",
						resultadosSerializados);
				startActivity(resultadoBusquedaIntent);
				finish();
			}

		}
	}

	public void getMedicos() {
		this.medicos = (ArrayList<Medico>) MedicosParser.parsearMedicos(this.resultadosSerializados);

		
	}
}
