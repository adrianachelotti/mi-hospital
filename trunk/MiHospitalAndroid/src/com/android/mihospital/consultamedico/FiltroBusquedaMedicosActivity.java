package com.android.mihospital.consultamedico;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.EspecialidadesUtil;
import com.android.mihospital.ws.medico.BusquedaMedicoResult;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FiltroBusquedaMedicosActivity extends Activity {
	private static final String COMPLETE_LOS_FILTROS = "Complete los filtros";
	private AutoCompleteTextView especialidadAutoText;
	private Spinner sucursal;
	private EditText nombre;
	private EditText apellido;
	private CheckBox lunesCheck;
	private CheckBox martesCheck;
	private CheckBox miercolesCheck;
	private CheckBox juevesCheck;
	private CheckBox viernesCheck;
	private CheckBox sabadoCheck;
	private CheckBox domingoCheck;
	private ProgressDialog progressDialog;
	private Context context;
	private BusquedaMedicosTask mAuthTask = null;
	String resultadosSerializados;
	Paciente pacienteLogeado;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filtros_busqueda_medico_activity);
		resultadosSerializados = getIntent().getStringExtra("resultadoBusqueda");		
		agregarAutoCompletable();
		//agregarBotonVolver();
		agregarBotonBuscar();
		context= this;
		
	}
        
	private void agregarAutoCompletable() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,EspecialidadesUtil.getEspecilidades());
		AutoCompleteTextView acTextView = (AutoCompleteTextView)findViewById(R.id.especialidad);
		acTextView.setThreshold(3);
		acTextView.setAdapter(adapter);

		
	}

	private void agregarBotonBuscar() {
		Button volverButton = (Button) findViewById(R.id.buttonBuscar);
		volverButton.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {
				
				if( filtrosCompletadoValidos()){		
					mAuthTask = new BusquedaMedicosTask();
					mAuthTask.execute((Void) null);
					progressDialog =ProgressDialog.show(context, "Por favor espere", "Preparando la búsqueda");
				
				}else{
					Toast toast = Toast.makeText(FiltroBusquedaMedicosActivity.this,COMPLETE_LOS_FILTROS, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				
				}
					

			}

			private boolean filtrosCompletadoValidos() {
				especialidadAutoText = (AutoCompleteTextView)findViewById(R.id.especialidad);
				sucursal = (Spinner)findViewById(R.id.sucursal);
				nombre = (EditText)findViewById(R.id.nombre_editText);
				apellido =(EditText)findViewById(R.id.apellido_editText);
				lunesCheck = (CheckBox)findViewById(R.id.lunes);
				martesCheck= (CheckBox)findViewById(R.id.martes);
				miercolesCheck= (CheckBox)findViewById(R.id.miercoles);
				juevesCheck= (CheckBox)findViewById(R.id.jueves);
				viernesCheck= (CheckBox)findViewById(R.id.viernes);
				sabadoCheck= (CheckBox)findViewById(R.id.sabado);
				domingoCheck= (CheckBox)findViewById(R.id.domingo);
				boolean valido = true;
				String especialidadElegida =especialidadAutoText.getText().toString();
				if (TextUtils.isEmpty(especialidadElegida)){
					if ((TextUtils.isEmpty(nombre.getText().toString()))&&(TextUtils.isEmpty(apellido.getText().toString()))){
						valido= false;
					}
					if (!lunesCheck.isChecked() &&!martesCheck.isChecked()&&!miercolesCheck.isChecked()&&!juevesCheck.isChecked()&&!viernesCheck.isChecked()&&!sabadoCheck.isChecked()&&!domingoCheck.isChecked()){
						if (TextUtils.isEmpty(sucursal.getSelectedItem().toString())){
							valido = false;
						}
					}
				}
				
				
				
				return valido;
			}

		});

	}
/*	private void agregarBotonVolver() {
		Button volverButton = (Button) findViewById(R.id.linkVolver);
		volverButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent busquedaMedicosActivity = new Intent(
						getApplicationContext(),
						BusquedaMedicoActivity.class);
				busquedaMedicosActivity.putExtra("resultadoBusqueda",resultadosSerializados);				
				startActivity(busquedaMedicosActivity);
				finish();

			}

		});

	}*/
	
	public class BusquedaMedicosTask extends AsyncTask<Void, Void, Boolean> {

		String mensajeErrorBusquedaMedico ;
		
		
		@Override
		protected Boolean doInBackground(Void... params) {
			String nombre = FiltroBusquedaMedicosActivity.this.nombre.getText().toString();
			String apellido = FiltroBusquedaMedicosActivity.this.apellido.getText().toString();
			String especialidad= FiltroBusquedaMedicosActivity.this.especialidadAutoText.getText().toString();
			String diasAtencion = FiltroBusquedaMedicosActivity.this.obtenerDia();
			Integer sucursal = FiltroBusquedaMedicosActivity.this.sucursal.getSelectedItemPosition();
			
			BusquedaMedicoResult resultado =ServiceProvider.getClient().buscarMedicos(apellido,nombre,especialidad,sucursal,diasAtencion);
			
			
			if (resultado.getResultado()==null){
				mensajeErrorBusquedaMedico = "Error en la conexión.";
				return false;
			
			}
			FiltroBusquedaMedicosActivity.this.resultadosSerializados = resultado.getMedicos();
						
			if (!resultado.getResultado().equalsIgnoreCase("OK")){
				mensajeErrorBusquedaMedico = resultado.getMensaje();
				FiltroBusquedaMedicosActivity.this.resultadosSerializados = "";
				
			}
			
			return true;
			
				
			
			
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();		
			mAuthTask = null;


			if (success) {

				Intent medicoActivity = new Intent(
						getApplicationContext(),
						BusquedaMedicoActivity.class);
				if (TextUtils.isEmpty(FiltroBusquedaMedicosActivity.this.resultadosSerializados)){
					Toast toast = Toast.makeText(FiltroBusquedaMedicosActivity.this,mensajeErrorBusquedaMedico, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}
				medicoActivity.putExtra("resultadoBusqueda",FiltroBusquedaMedicosActivity.this.resultadosSerializados);				
				startActivity(medicoActivity);
				finish();
			
			} 
			else {
							
				Toast toast = Toast.makeText(FiltroBusquedaMedicosActivity.this,mensajeErrorBusquedaMedico, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent login = new Intent(getApplicationContext(),FiltroBusquedaMedicosActivity.class);
				startActivity(login);
				finish();
			}
			
			
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medic_search, menu);
		return true;
	}
	
	public String obtenerDia(){
		String filtroDia ="";
		if (domingoCheck.isChecked()) filtroDia = filtroDia.concat( "domingo;");
		if (lunesCheck.isChecked())  filtroDia = filtroDia.concat( "lunes;");
		if (martesCheck.isChecked())  filtroDia = filtroDia.concat( "martes;");
		if (miercolesCheck.isChecked())  filtroDia = filtroDia.concat( "miercoles;");
		if (juevesCheck.isChecked())  filtroDia = filtroDia.concat( "jueves;");
		if (viernesCheck.isChecked())  filtroDia = filtroDia.concat( "viernes;");
		if (sabadoCheck.isChecked())  filtroDia = filtroDia.concat( "sabado;");
		if (filtroDia.endsWith(";")){
			filtroDia= filtroDia.substring(0, filtroDia.length()-1);
		}
		return filtroDia;
	}

}
