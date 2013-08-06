package com.android.mihospital.loginandregister;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Medico;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.turnos.Turno;
import com.android.mihospital.utils.CalendarAPI;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.utils.SucursalesList;
import com.android.mihospital.utils.Utils;
import com.android.mihospital.ws.ServiceResult;

public class DetalleTurnoSolicitadoActivity extends Activity {

	private Paciente paciente;
	private Medico medicoAConsultar;
	public Turno turnoARevisar;
	private int idSucursal;
	private String especialidad;
	private String nombreMedico;
	private Button cancelar;
	private Context context; 
	private ProgressDialog progressDialog;
	private CancelarTurnoTask cancelarTurnoTask;
	private CalendarAPI sdkManager;
	private Dialog levelDialog = null;
	private Calendar beginTime = Calendar.getInstance();
	private Calendar endTime = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_turno_solicitado);
		sdkManager = CalendarAPI.getAPI();
		sdkManager.setContext(getApplicationContext());

		turnoARevisar = getIntent().getParcelableExtra("turnoARevisar");
		idSucursal = getIntent().getIntExtra("idSucursal", idSucursal);
		nombreMedico= getIntent().getStringExtra("nombre");
		especialidad= getIntent().getStringExtra("especialidad");
		paciente = getIntent().getParcelableExtra("pacienteLogeado");
		if (paciente != null) {
			PacienteLogeadoSession.setPacienteLogeado(paciente);
		} else {
			paciente = PacienteLogeadoSession.getPacientLogeado();
		}
		ImageView medicoImagen = (ImageView) findViewById(R.id.imagenMedico);
				
		context = this;
		TextView nombreCompletoTextView = (TextView) findViewById(R.id.nombreCompletoMedico);
		TextView especialidadTextView = (TextView) findViewById(R.id.especialidadDinamico);
		TextView sucursalTextView = (TextView) findViewById(R.id.direccionDinamica);
		TextView horario = (TextView) findViewById(R.id.horarioAtencionDinamico);
		nombreCompletoTextView.setText(nombreMedico);
		especialidadTextView.setText(especialidad);
		sucursalTextView.setText(SucursalesList.sucursales[idSucursal]);
		horario.setText(turnoARevisar.getDia());
		cancelar = (Button) findViewById(R.id.cancelButton);
		cancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				cancelarTurno();
			}
		});
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date=null;
		Date now=new Date();
		try {
			date = format.parse(turnoARevisar.getDia());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long diff = date.getTime() - now.getTime() ;        
        long diffHours = diff / (60 * 60 * 1000);
        if(diffHours<=48){
        	cancelar.setEnabled(false);
        }
	}

	protected void cancelarTurno() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				DetalleTurnoSolicitadoActivity.this);

		// Setting Dialog Title
		alertDialog.setTitle("Cancelacion del turno");

		// Setting Dialog Message
		alertDialog.setMessage("Esta usted seguro?");

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Si",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						cancelarTurnoTask = new CancelarTurnoTask();
						cancelarTurnoTask.execute((Void) null);
						progressDialog = new ProgressDialog(context);
						progressDialog.setTitle("Por favor espere");
						progressDialog.setMessage("Cancelando el turno.");
						progressDialog.show();

					}

				});
		alertDialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		alertDialog.show();
	}
	
	
	public void setAlarm(View v){
		agregarAlarma();
	}
	@SuppressLint("NewApi")
	public void agregarAlarma() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				this);

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
					}
				});
		AlertDialog dialog = alertDialog.create();
		dialog.show();

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
							Date date = format.parse(turnoARevisar.getDia());
							beginTime.setTime(date);
							endTime.setTime(date);
							endTime.add(Calendar.MINUTE, turnoARevisar.getDuracionTurnos());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
						sdkManager.setAlertOnDevice(context, getTittle(), getDetails(), beginTime, endTime, minutes, SucursalesList.sucursales[idSucursal]);					
					}
				});
		levelDialog = builder.create();
		levelDialog.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_turno_solicitado, menu);
		return true;
	}

	public class CancelarTurnoTask extends AsyncTask<Void, Void, Boolean> {
		String mensaje;

		@Override
		protected Boolean doInBackground(Void... params) {

			ServiceResult resultado = ServiceProvider.getClient()
					.cancelarTurno(
							DetalleTurnoSolicitadoActivity.this.turnoARevisar.getId());

			if (resultado.getResultado() == null) {
				mensaje = "Error en la conexi—n.";
				return false;
			}
			if (resultado.getResultado().equals("OK")) {
				mensaje = "Turno Cancelado";
			} else {
				mensaje = resultado.getMensaje();
				return false;
			}
			return true;

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();

			if (success) {
				Toast toast = Toast.makeText(DetalleTurnoSolicitadoActivity.this,
						mensaje, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				DetalleTurnoSolicitadoActivity.this.finish();
			} else {
				Toast toast = Toast.makeText(DetalleTurnoSolicitadoActivity.this,
						mensaje, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
		}
	}
	private String getDetails() {
		return especialidad + ": "
				+ nombreMedico;
	}

	private String getTittle() {
		return "Turno con: " + nombreMedico;
	}
}
