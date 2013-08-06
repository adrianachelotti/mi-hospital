package com.android.mihospital.turnos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Medico;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.login.HomeActivity;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.CalendarAPI;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.utils.SucursalesList;
import com.android.mihospital.utils.Utils;
import com.android.mihospital.ws.ServiceResult;

@SuppressLint("NewApi")
public class DetalleTurnoActivity extends Activity {

	private CalendarAPI sdkManager;
	private ProgressDialog progressDialog;
	private Context context;
	private Paciente paciente;
	private Medico medicoAConsultar;
	public Turno turnoAReservar;
	public int idSucursal;
	private Button confirmar;
	private ReservarTurnoTask reservaTask;
	private Dialog levelDialog = null;
	private Calendar beginTime = Calendar.getInstance();
	private Calendar endTime = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_turno_detail);

		sdkManager = CalendarAPI.getAPI();
		sdkManager.setContext(getApplicationContext());

		medicoAConsultar = getIntent().getParcelableExtra("medicoConsultado");
		turnoAReservar = getIntent().getParcelableExtra("turnoAReservar");
		idSucursal = getIntent().getIntExtra("idSucursal", idSucursal);
		paciente = getIntent().getParcelableExtra("pacienteLogeado");
		if (paciente != null) {
			PacienteLogeadoSession.setPacienteLogeado(paciente);
		} else {
			paciente = PacienteLogeadoSession.getPacientLogeado();
		}
		ImageView medicoImagen = (ImageView) findViewById(R.id.imagenMedico);
		if (medicoAConsultar.getFoto() != null) {
			byte[] blob = medicoAConsultar.getFoto();

			Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);

			medicoImagen.setImageBitmap(Bitmap.createScaledBitmap(bmp, 100,
					100, true));

		}
		context = this;
		TextView nombreCompletoTextView = (TextView) findViewById(R.id.nombreCompletoMedico);
		TextView duracionTextView = (TextView) findViewById(R.id.duracionTurnosDinamico);
		TextView sucursalTextView = (TextView) findViewById(R.id.direccionDinamica);
		TextView horario = (TextView) findViewById(R.id.horarioAtencionDinamico);
		nombreCompletoTextView.setText(medicoAConsultar.getNombre() + " "
				+ medicoAConsultar.getApellido());
		duracionTextView.setText(medicoAConsultar.getDuracionTurno().toString()
				+ " min.");
		sucursalTextView.setText(SucursalesList.sucursales[idSucursal]);
		horario.setText(turnoAReservar.getDia());
		confirmar = (Button) findViewById(R.id.confirmar);
		confirmar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				agregarConfirmacion();
			}
		});
	}

	public void agregarConfirmacion() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				DetalleTurnoActivity.this);

		// Setting Dialog Title
		alertDialog.setTitle("Confirmación del turno");

		// Setting Dialog Message
		alertDialog.setMessage("Está usted seguro?");

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Si",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						reservaTask = new ReservarTurnoTask();
						reservaTask.execute((Void) null);
						progressDialog = new ProgressDialog(context);
						progressDialog.setTitle("Por favor espere");
						progressDialog.setMessage("Reservando el turno.");
						progressDialog.show();

					}

				});
		alertDialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// irAlHome();
						dialog.cancel();
					}
				});
		alertDialog.show();
	}

	@SuppressLint("NewApi")
	public void agregarAlarma() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				DetalleTurnoActivity.this);

		// Setting Dialog Title
		alertDialog.setTitle("");

		// Setting Dialog Message
		alertDialog.setMessage("Desea crear recordatorio");

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Si",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						createOptionsDialog();

					}

				});
		alertDialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						irAlHome();
					}
				});
		AlertDialog dialog = alertDialog.create();
		dialog.show();

	}

	private void irAlHome() {
		Intent homeActivity = new Intent(getApplicationContext(),
				HomeActivity.class);
		homeActivity.putExtra("pacienteLogeado", paciente);
		startActivity(homeActivity);
		finish();
	}

	public class ReservarTurnoTask extends AsyncTask<Void, Void, Boolean> {
		String mensaje;

		@Override
		protected Boolean doInBackground(Void... params) {

			ServiceResult resultado = ServiceProvider.getClient()
					.reservarTurno(
							DetalleTurnoActivity.this.turnoAReservar.getId(),
							DetalleTurnoActivity.this.paciente.getId());

			if (resultado.getResultado() == null) {
				mensaje = "Error en la conexión.";
				return false;
			}
			if (resultado.getResultado().equals("OK")) {
				mensaje = "Turno Reservado";
			} else {
				mensaje = resultado.getMensaje();
			}
			return true;

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();
			reservaTask = null;

			if (success) {
				Toast toast = Toast.makeText(DetalleTurnoActivity.this,
						mensaje, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				agregarAlarma();
				
			} else {
				Toast toast = Toast.makeText(DetalleTurnoActivity.this,
						mensaje, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

			}
		}
	}

	@SuppressLint("NewApi")
	private void createOptionsDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Seleccione anticipación");
		builder.setSingleChoiceItems(Utils.anticipacionItems, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						int minutes = 10;
						switch (item) {
						case 0:
							minutes = 60;
							break;
						case 1:
							minutes = 60 * 24;
							break;
						case 2:
							break;
						}

						levelDialog.dismiss();
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
						try {
							Date date = format.parse(turnoAReservar.getDia());
							beginTime.setTime(date);
							endTime.setTime(date);
							endTime.add(Calendar.MINUTE, medicoAConsultar.getDuracionTurno());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						sdkManager.setAlertOnDevice(getApplicationContext(), getTittle(), getDetails(), beginTime, endTime, minutes, SucursalesList.sucursales[idSucursal]);
						irAlHome();
					}
				});
		levelDialog = builder.create();
		levelDialog.show();
	}

	private String getDetails() {
		return medicoAConsultar.getEspecialidad() + ": "
				+ medicoAConsultar.getNombre() + " "
				+ medicoAConsultar.getApellido();
	}

	private String getTittle() {
		return "Turno con: " + medicoAConsultar.getNombre()
				+ " " + medicoAConsultar.getApellido();
	}
}
