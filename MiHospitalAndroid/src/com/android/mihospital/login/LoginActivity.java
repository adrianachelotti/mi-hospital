package com.android.mihospital.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.registracion.RegisterActivity;
import com.android.mihospital.ws.paciente.PacienteResult;

public class LoginActivity extends Activity {

	private Paciente pacienteLogeado;
	private ProgressDialog progressDialog;
	private Context context;

	private Integer numeroDocumentoInteger;

	private String clave;
	private String numeroDocumento;
	private EditText nroDocumentoPacienteView;
	private EditText clavePacienteView;
	private TextView mLoginStatusMessageView;

	private UserLoginTask mAuthTask = null;

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		context=this;
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);
		nroDocumentoPacienteView = (EditText) findViewById(R.id.nroDocumentoPaciente);
		clavePacienteView = (EditText) findViewById(R.id.clavePaciente);
		agregarLinkRegistrarPaciente();
		agregarBottonLoginPaciente();
	}

	private void agregarLinkRegistrarPaciente() {
		TextView registracionLink = (TextView) findViewById(R.id.link_to_register);
		registracionLink.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent registroActivity = new Intent(getApplicationContext(),RegisterActivity.class);
				startActivity(registroActivity);
			}
		});
	}

	private void agregarBottonLoginPaciente() {
		Button ingresoLogin = (Button) findViewById(R.id.btnLogin);
		ingresoLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				esValidoElLogin();
			}

			private boolean esValidoElLogin() {
				// Reset errores.
				nroDocumentoPacienteView.setError(null);
				clavePacienteView.setError(null);

				// Recupero los valores ingresados
				numeroDocumento = nroDocumentoPacienteView.getText().toString();
				clave = clavePacienteView.getText().toString();

				boolean cancel = false;
				View focusView = null;

				// Chequeamos si la clave es válida
				if (TextUtils.isEmpty(clave)) {
					clavePacienteView.setError(getString(R.string.campo_obligatorio));
					focusView = clavePacienteView;
					cancel = true;
				} else if (clave.length() < 4) {
					clavePacienteView.setError(getString(R.string.clave_invalida));
					focusView = clavePacienteView;
					cancel = true;
				}

				// Chequeamos si el documento es valido
				if (TextUtils.isEmpty(numeroDocumento)) {
					nroDocumentoPacienteView.setError(getString(R.string.campo_obligatorio));
					focusView = nroDocumentoPacienteView;
					cancel = true;
				} else if (!esNumero(numeroDocumento)) {
					nroDocumentoPacienteView.setError(getString(R.string.nro_documento_invalido));
					focusView = nroDocumentoPacienteView;
					cancel = true;
				}

				if (cancel) {
					// Se muestra el mensaje de error
					focusView.requestFocus();
				} else {
					mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
					//					showProgress(true);
					pacienteLogeado= new Paciente(numeroDocumentoInteger,clave);
					mAuthTask = new UserLoginTask();
					mAuthTask.execute((Void) null);
					progressDialog = new ProgressDialog(context);	
					progressDialog.setTitle("Por favor espere");
					progressDialog.setMessage("Validando paciente");
					progressDialog.show();
		

				}
				return !cancel;

			}

		});
	}

	protected boolean esNumero(String numeroDocumento) {

		try {
			numeroDocumentoInteger = Integer.parseInt(numeroDocumento);

			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}

	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		private  String mensajeErrorLogin = "DNI o Clave incorrecta.";
		private static final String LOGGIN_CORRECTO = "Usted ha iniciado session";

		@Override
		protected Boolean doInBackground(Void... params) {
			
			PacienteResult pacienteResult =ServiceProvider.getClient().validar(pacienteLogeado);

			if (pacienteResult.getResultado()==null){
				mensajeErrorLogin = "Error en la conexión.";
				return false;
			
			}
			if(pacienteResult.getResultado()!=null && pacienteResult.getResultado().equalsIgnoreCase("OK")){
				pacienteLogeado = new Paciente(pacienteResult);
				return true;
			}else{
				if(pacienteResult.getMensaje()!=null)
					mensajeErrorLogin = pacienteResult.getMensaje();
				return false;
			}
			
				
			
			
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();		
			mAuthTask = null;


			if (success) {

				Toast toast = Toast.makeText(LoginActivity.this,LOGGIN_CORRECTO, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
				homeActivity.putExtra("pacienteLogeado", pacienteLogeado); 
				
				String mensaje ="";
				if (pacienteLogeado.getTotalMensajesNuevos() != null
						&& (pacienteLogeado.getTotalMensajesNuevos().compareTo(0) > 0)) {
					mensaje = "Tiene " + pacienteLogeado.getTotalMensajesNuevos()
							+ " mensaje(s) nuevo(s).";
					
				}
				if (pacienteLogeado.getTotalTurnosCancelados() != null
						&& (pacienteLogeado.getTotalTurnosCancelados().compareTo(0) > 0)) {
					if(mensaje.length()>0)
						mensaje+="\n";
					mensaje += "Tiene " + pacienteLogeado.getTotalTurnosCancelados()
							+ " turno(s) cancelado(s).";
				}
				if(mensaje.length()>0){
					homeActivity.putExtra("mensaje", mensaje);				
				}
				startActivity(homeActivity);
				finish();
			} 
			else {
				Toast toast = Toast.makeText(LoginActivity.this,mensajeErrorLogin, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent login = new Intent(getApplicationContext(),LoginActivity.class);
				
				startActivity(login);
				finish();
			}
			
//			super.onPostExecute(success);
		}

//		@Override
//		protected void onCancelled() {
//			progressDialog.dismiss();
//			mAuthTask = null;
//			Toast toast = Toast.makeText(LoginActivity.this,DNI_O_CLAVE_INCORRECTA, Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//			toast.show();
//
//		}

	}
}