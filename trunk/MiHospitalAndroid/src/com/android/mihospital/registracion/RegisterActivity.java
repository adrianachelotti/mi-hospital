package com.android.mihospital.registracion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.login.LoginActivity;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.ObrasSocialesUtil;
import com.android.mihospital.utils.Utils;
import com.android.mihospital.ws.ServiceResult;

public class RegisterActivity extends Activity {

	
	private EditText afiliadoEText;
	private EditText apellidoEText;
	private EditText claveEText;
	private EditText domicilioEText;
	private TextView fechaEText;
	private EditText mailEText;
	private EditText nombreEText;
	private EditText numeroDocumentoEText;
	private EditText telefonoEText;
	private AutoCompleteTextView obraSocialAutoText;
	private EditText paisEText;
	private EditText localidadEText;
	private EditText sexoEText;
	private Spinner proviciaSpinner;

	private Paciente paciente;

	private RegistrarPacienteTask registrarPacienteTask;
	private ProgressDialog progressDialog;
	
	private Context context;
	
	private static final String DATOS_ERRONEOS = "Corrija los datos";

	protected static final CharSequence PACIENTE_REGISTRADO = "Paciente registrado";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.register);
		
		agregarAutoCompletable();

		//agregarVolverAlLogin();

		agregarRegistrarBoton();
		context = this;
	}

	private void agregarAutoCompletable() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,ObrasSocialesUtil.getObrasSociales());
		AutoCompleteTextView acTextView = (AutoCompleteTextView)findViewById(R.id.obraSocial);
		acTextView.setThreshold(3);
		acTextView.setAdapter(adapter);

		
	}

	private void agregarRegistrarBoton() {
		Button registrarButton = (Button) findViewById(R.id.btnRegister);
		registrarButton.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("static-access")
			public void onClick(View v) {

				if (ingresoDatosValido()) {

					guardarPaciente();
					registrarPacienteTask = new RegistrarPacienteTask();
					registrarPacienteTask.execute((Void) null);
					progressDialog = new ProgressDialog(context);	
					progressDialog.show(context, "Por favor espere", "Registrando al paciente");
				} 
				else 
				{
					Toast toast = Toast.makeText(RegisterActivity.this, DATOS_ERRONEOS,	Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}
			}
		});
	}

	private boolean ingresoDatosValido() {
		boolean esValido = true;
		nombreEText = (EditText) findViewById(R.id.nombre_editText);
		String nombre = nombreEText.getText().toString();
		apellidoEText = (EditText) findViewById(R.id.apellido_editText);
		String apellido = apellidoEText.getText().toString();
		claveEText =  (EditText) findViewById(R.id.clave_editText);
		String pass = claveEText.getText().toString();
		numeroDocumentoEText = (EditText) findViewById(R.id.dni_editText);
		afiliadoEText = (EditText) findViewById(R.id.afiliado_editText);
		obraSocialAutoText = (AutoCompleteTextView) findViewById(R.id.obraSocial);
		String obraSocial = obraSocialAutoText.getText().toString();
		telefonoEText = (EditText) findViewById(R.id.telefono_editText);
		String telefono = telefonoEText.getText().toString();
		domicilioEText = (EditText) findViewById(R.id.direccion_editText);
		String direccion = domicilioEText.getText().toString();
		mailEText = (EditText) findViewById(R.id.email_editText);
		String mail = mailEText.getText().toString();
		paisEText = (EditText) findViewById(R.id.nacionalidad_editText);
		String nacionalidad = paisEText.getText().toString();
		localidadEText = (EditText) findViewById(R.id.localidad_editText);
		String localidad = localidadEText.getText().toString();
		sexoEText = (EditText) findViewById(R.id.sexo_editText);
		String sexo = sexoEText.getText().toString();
		fechaEText = (EditText) findViewById(R.id.fecha_nacimmiento_editText);
		String fecha = fechaEText.getText().toString();
		
		proviciaSpinner = (Spinner) findViewById(R.id.spinner1);
		
		View focusView = null;

		try{
			Integer.parseInt(numeroDocumentoEText.getText().toString());
		}catch(NumberFormatException e){
			esValido = false;
			numeroDocumentoEText.setError("Complete el numero de documento");
			focusView = numeroDocumentoEText;
		}
		
		try{
			Integer.parseInt(afiliadoEText.getText().toString());
		}catch(NumberFormatException e){
			esValido = false;
			afiliadoEText.setError("Complete el numero de afiliado");
			focusView = afiliadoEText;
		}
		
		if (TextUtils.isEmpty(nombre)) {
			esValido = false;
			nombreEText.setError("Complete el nombre");
			focusView = nombreEText;
		}
		if (TextUtils.isEmpty(apellido)) {
			esValido = false;
			apellidoEText.setError("Complete el apellido");
			focusView = apellidoEText;
		}
		if (TextUtils.isEmpty(pass)) {
			esValido = false;
			claveEText.setError("Complete la clave");
			focusView = claveEText;
		}else{
			if(pass.length()<=4){
				esValido = false;
				claveEText.setError("La clave debe tener más de 4 caracteres");
				focusView = claveEText;
			}
		}
		
		if (TextUtils.isEmpty(obraSocial)) {
			esValido = false;
			obraSocialAutoText.setError("Complete la obra social");
			focusView = obraSocialAutoText;
		}
		if (TextUtils.isEmpty(telefono)) {
			esValido = false;
			telefonoEText.setError("Complete el telefono");
			focusView = telefonoEText;
		}
		if (TextUtils.isEmpty(direccion)) {
			esValido = false;
			domicilioEText.setError("Complete la direccion");
			focusView = domicilioEText;
		}
		if (TextUtils.isEmpty(localidad)) {
			esValido = false;
			localidadEText.setError("Complete la localidad");
			focusView = localidadEText;
		}
		if (TextUtils.isEmpty(sexo)) {
			esValido = false;
			sexoEText.setError("Complete el sexo");
			focusView = sexoEText;
		}
		if (TextUtils.isEmpty(fecha)) {
			esValido = false;
			fechaEText.setError("Complete la fecha");
			focusView = fechaEText;
		}else{
			if (!Utils.esFormatoFechaDeNacimientoValido(fecha)) {
				esValido = false;
				fechaEText.setError("Formato de fecha incorrecto (dd/mm/aaaa)");
				focusView = fechaEText;
			}
		}
				
		if (TextUtils.isEmpty(nacionalidad)) {
			esValido = false;
			paisEText.setError("Complete la nacionalidad");
			focusView = paisEText;
		}
		
		if (TextUtils.isEmpty(mail)) {
			esValido = false;
			mailEText.setError("Complete el mail");
			focusView = mailEText;
		}else{
			if (!Utils.isValidEmail(mail)) {
				esValido = false;
				mailEText.setError("Formato de mail incorrecto");
				focusView = mailEText;
			}
		}
		
				
		if (!esValido) {
			// Se muestra el mensaje de error
			focusView.requestFocus();

		}
		
		return esValido;

	}


	
	
	private void guardarPaciente() {
		
		paciente = new Paciente(Integer.parseInt(numeroDocumentoEText.getText().toString()),claveEText.getText().toString());
		
		paciente.setNombre(nombreEText.getText().toString());
		paciente.setApellido(apellidoEText.getText().toString());
		paciente.setDireccion(domicilioEText.getText().toString());
		paciente.setTelefono(telefonoEText.getText().toString());
		paciente.setMail(mailEText.getText().toString());
		paciente.setObraSocial(obraSocialAutoText.getText().toString());
		paciente.setAfiliado(Integer.parseInt(afiliadoEText.getText().toString()));
		paciente.setNacionalidad(paisEText.getText().toString());
		paciente.setLocalidad(localidadEText.getText().toString());
		paciente.setSexo(sexoEText.getText().toString());
		paciente.setFechaNacimiento(fechaEText.getText().toString());
		paciente.setProvincia(proviciaSpinner.getSelectedItem().toString());
		
	}




	private class RegistrarPacienteTask extends AsyncTask<Void, Void, Boolean> {

		private   String mensajeDeError = "No puedo realizarse. Verifique los datos";
		private static final String MODIFICACION_CORRECTA = "Se ha registrado correctamente!";
		

		@Override
		protected Boolean doInBackground(Void... params) {
			
			ServiceResult serviceResult = ServiceProvider.getClient().registrarPaciente(paciente);
			
			if (serviceResult.getResultado()==null){
				mensajeDeError= "Error en la conexión.";
				return false;
			
			}

			if (serviceResult.getResultado() != null && serviceResult.getResultado().equalsIgnoreCase("OK")) {
				return true;
			} else {
				if(serviceResult.getMensaje()!=null)
				mensajeDeError = serviceResult.getMensaje();
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			registrarPacienteTask = null;
			progressDialog.dismiss();
			if (success) {
				Toast toast = Toast.makeText(RegisterActivity.this,	MODIFICACION_CORRECTA, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent homeActivity = new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(homeActivity);
				finish();
			} else {
				Toast toast = Toast.makeText(RegisterActivity.this,	mensajeDeError, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent informacionPersonalActivity = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(informacionPersonalActivity);
				finish();
			}
			super.onPostExecute(success);
		}

		@Override
		protected void onCancelled() {
			registrarPacienteTask = null;
		}

	}
}