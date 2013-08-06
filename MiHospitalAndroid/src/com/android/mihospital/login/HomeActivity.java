package com.android.mihospital.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.consultamedico.BusquedaMedicoActivity;
import com.android.mihospital.consultapaciente.DetallePacienteActivity;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.loginandregister.HistoriaClinicaActivity;
import com.android.mihospital.loginandregister.HistorialTurnosExpandibleActivity;
import com.android.mihospital.loginandregister.MessagesActivity;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.mensajes.MensajesActivity;
import com.android.mihospital.turnos.NuevoTurnoActivity;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.ws.medico.BusquedaMedicoResult;
import com.android.mihospital.ws.turnos.ListadoDeTurnosResult;

public class HomeActivity extends Activity {

	private static final String TURNOS_ICON = "TURNOS";
	private static final String CARTILLA_ICON = "CARTILLA";
	private static final String MENSAJES_ICON = "MENSAJES";
	private ProgressDialog progressDialog;
	private Context context;
	private BusquedaMedicosTask mAuthTask = null;

	private BusquedaHistorialDeTurnosTask busquedaTurnosTask;

	private Paciente paciente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		paciente = getIntent().getParcelableExtra("pacienteLogeado");
		if (paciente != null) {
			PacienteLogeadoSession.setPacienteLogeado(paciente);
		} else {
			paciente = PacienteLogeadoSession.getPacientLogeado();
		}
		context = this;

		String mensaje = getIntent().getStringExtra("mensaje");
		agregarIconoInformacionPaciente();
		agregarBotonSalir();
		if (mensaje != null && mensaje.length()>0) {
			getIntent().putExtra("mensaje","");
			AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
			builder1.setMessage(mensaje);
			builder1.setCancelable(true);
			builder1.setNeutralButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});

			AlertDialog alert11 = builder1.create();
			alert11.show();
		}

	}

	public void agregarIconoHistorial(View view) {
		busquedaTurnosTask = new BusquedaHistorialDeTurnosTask();
		busquedaTurnosTask.execute((Void) null);
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Preparando la búsqueda");
		progressDialog.show();

	}

	private void agregarBotonSalir() {
		Button salirButton = (Button) findViewById(R.id.salir_button);
		salirButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent login = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(login);

				finish();

			}

		});
	}

	private void agregarIconoInformacionPaciente() {
		ImageButton infoPersonalButton = (ImageButton) findViewById(R.id.info_personal_button);
		infoPersonalButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent informacionPersonalActivity = new Intent(
						getApplicationContext(), DetallePacienteActivity.class);
				informacionPersonalActivity.putExtra("pacienteLogeado2",
						PacienteLogeadoSession.getPacientLogeado());
				startActivity(informacionPersonalActivity);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void iniciarBusquedaMedicos(View view) {

		mAuthTask = new BusquedaMedicosTask(paciente.getId(), CARTILLA_ICON);
		mAuthTask.execute((Void) null);
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Preparando la búsqueda");
		progressDialog.show();

	}

	public void iniciarMensajes(View view) {

		mAuthTask = new BusquedaMedicosTask(paciente.getId(), MENSAJES_ICON);
		mAuthTask.execute((Void) null);
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Preparando la búsqueda");
		progressDialog.show();

	}

	public void iniciarHistoriaClinica(View view) {

		Intent clinHis = new Intent(getApplicationContext(),
				HistoriaClinicaActivity.class);
		clinHis.putExtra("idPaciente", paciente.getId());
		startActivity(clinHis);
	}

	public void cargarHistorialMedicos(View view) {

		mAuthTask = new BusquedaMedicosTask(paciente.getId(), TURNOS_ICON);
		mAuthTask.execute((Void) null);
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Preparando la búsqueda");
		progressDialog.show();

	}

	public void verListadoMisMedicos(View view) {

		Intent login = new Intent(getApplicationContext(),
				MensajesActivity.class);
		startActivity(login);

	}

	public void iniciarNuevoTurno(View view) {

		Intent login = new Intent(getApplicationContext(),
				NuevoTurnoActivity.class);
		startActivity(login);

	}

	public class BusquedaHistorialDeTurnosTask extends
			AsyncTask<Void, Void, Boolean> {

		String resultadoTurnos;
		String mensaje;

		public BusquedaHistorialDeTurnosTask() {
			super();

		}

		@Override
		protected Boolean doInBackground(Void... params) {

			ListadoDeTurnosResult resultado = ServiceProvider.getClient()
					.buscarHistorialDeTurnos(paciente.getId());

			if (resultado.getResultado() == null) {
				mensaje = "Error en la conexión.";
				return false;

			}

			if (resultado.getResultado().equals("NO_DATA_FOUND")) {
				mensaje = "No existen turnos.";
				return false;

			}

			resultadoTurnos = resultado.getTurnos();

			return true;

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();
			busquedaTurnosTask = null;
			if (success) {

				Intent intent = new Intent(getApplicationContext(),
						HistorialTurnosExpandibleActivity.class);
				intent.putExtra("resultadoTurno", resultadoTurnos);
				intent.putExtra("pacienteLogeado", getIntent()
						.getParcelableExtra("pacienteLogeado"));
				startActivity(intent);
			} else {
				Toast toast = Toast.makeText(HomeActivity.this, mensaje,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

			}

		}

	}

	public class BusquedaMedicosTask extends AsyncTask<Void, Void, Boolean> {

		String mensajeErrorLogin;
		String resultadoBusqueda;
		Integer idPaciente;
		String iconoHome;

		public BusquedaMedicosTask(Integer idPaciente, String icono) {
			super();
			this.idPaciente = idPaciente;
			this.iconoHome = icono;

		}

		@Override
		protected Boolean doInBackground(Void... params) {

			BusquedaMedicoResult resultado = ServiceProvider.getClient()
					.buscarMedicosMasFrecuentes(this.idPaciente);
			resultadoBusqueda = resultado.getMedicos();

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

				if (MENSAJES_ICON.equals(this.iconoHome)) {
					if (TextUtils.isEmpty(resultadoBusqueda)) {
						Toast toast = Toast
								.makeText(
										HomeActivity.this,
										"Debe antenderse con algún médico antes por lo menos",
										Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					} else {

						Intent intent = new Intent(getApplicationContext(),
								MessagesActivity.class);
						intent.putExtra("resultadoBusqueda", resultadoBusqueda);
						intent.putExtra("pacienteLogeado", paciente.getId()
								.toString());
						startActivity(intent);
					}
				} else if (CARTILLA_ICON.equals(this.iconoHome)) {
					// LLamar al servicio
					Intent intent = new Intent(getApplicationContext(),
							BusquedaMedicoActivity.class);
					intent.putExtra("resultadoBusqueda", resultadoBusqueda);
					intent.putExtra("pacienteLogeado", paciente);
					startActivity(intent);
				} else {
					if (TextUtils.isEmpty(resultadoBusqueda)) {
						Toast toast = Toast
								.makeText(
										HomeActivity.this,
										"No puede solicitar un turno rápido dado que nunca solicitó un turno. Utilice la opción Cartilla Médica.",
										Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					} else {
						Intent intent = new Intent(getApplicationContext(),
								NuevoTurnoActivity.class);
						intent.putExtra("resultadoBusqueda", resultadoBusqueda);
						intent.putExtra("pacienteLogeado", paciente);
						startActivity(intent);
					}
				}
			} else {
				Toast toast = Toast.makeText(HomeActivity.this,
						mensajeErrorLogin, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent login = new Intent(getApplicationContext(),
						HomeActivity.class);
				startActivity(login);
				finish();
			}

		}

	}

}
