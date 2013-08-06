package com.android.mihospital.turnos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;

import android.annotation.SuppressLint;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.consultamedico.BusquedaMedicoActivity;

import com.android.mihospital.dominio.Medico;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.login.HomeActivity;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.DateUtil;
import com.android.mihospital.utils.MedicosParser;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.utils.SucursalesList;
import com.android.mihospital.ws.medico.BusquedaMedicoResult;
import com.android.mihospital.ws.medico.MedicoResult;
import com.android.mihospital.ws.turnos.ListadoDeTurnosResult;

@SuppressLint("NewApi")
public class NuevoTurnoActivity extends Activity {
	String resultadosSerializados;
	private ArrayList<Medico> medicos;
	Spinner historialMedicos;
	Spinner sucursales;
	Medico medicoSeleccionado;
	Integer idSucursalSeleccionada;
	String fechaSeleccionada;
	TextView horarioAtencion;
	TextView duracionTurno;
	private Paciente paciente;
	private ProgressDialog progressDialog;
	private Context context;
	private BusquedaMedicosTask mAuthTask = null;
	private Button buscarButton;
	private BusquedaTurnosTask busquedaTurnosTask;
	private CalendarView calendario;
	private Button botonSiguiente;
	private Button botonAnterior;
	private Date diaElegido;
	private HashMap<Integer, Integer> posicionesSucursales;
	String mensajeValidacion = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_turno);

		paciente = getIntent().getParcelableExtra("pacienteLogeado");
		if (paciente != null) {
			PacienteLogeadoSession.setPacienteLogeado(paciente);
		} else {
			paciente = PacienteLogeadoSession.getPacientLogeado();
		}
		context = this;

		resultadosSerializados = getIntent()
				.getStringExtra("resultadoBusqueda");
		cargarMedicos();

		cargarHistorialMedicos();
		cargarSucursales();
		cargarDuracionTurno();
		cargarCalendario();
		agregarBotonBuscar();
		cargarPosicionesIdSucursales();

	}

	private void cargarPosicionesIdSucursales() {
		if (medicoSeleccionado != null) {
			posicionesSucursales = SucursalesList
					.obtenerPosiciones(medicoSeleccionado.getSucursales());
		}
	}

	private void agregarBotonBuscar() {
		buscarButton = (Button) findViewById(R.id.buscarTurno);
		buscarButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (fechaValida()) {
					busquedaTurnosTask = new BusquedaTurnosTask();
					busquedaTurnosTask.execute((Void) null);
					progressDialog = new ProgressDialog(context);
					progressDialog.setTitle("Por favor espere");
					progressDialog.setMessage("Buscando turnos disponibles");
					progressDialog.show();
				} else {
					Toast toast = Toast.makeText(NuevoTurnoActivity.this,
							mensajeValidacion, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();

				}

			}
		});

	}

	private void cargarCalendario() {
		botonAnterior = (Button) findViewById(R.id.anterior);
		botonAnterior.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				Date diaCalendario = new Date(calendario.getDate());
				int mes = diaCalendario.getMonth();
				if (mes > 0) {
					mes = mes - 1;
					diaCalendario.setMonth(mes);
					calendario.setDate(diaCalendario.getTime());
				} else {
					mes = 11;
					int year = diaCalendario.getYear() - 1;
					diaCalendario.setYear(year);
					diaCalendario.setMonth(mes);
					calendario.setDate(diaCalendario.getTime());
				}

			}
		});
		botonSiguiente = (Button) findViewById(R.id.siguiente);
		botonSiguiente.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View arg0) {
				Date diaCalendario = new Date(calendario.getDate());

				int mes = diaCalendario.getMonth();
				if (mes < 11) {
					mes = mes + 1;

					diaCalendario.setMonth(mes);
					calendario.setDate(diaCalendario.getTime());
				} else {
					mes = 0;

					int year = diaCalendario.getYear() + 1;
					diaCalendario.setYear(year);
					diaCalendario.setMonth(mes);

					calendario.setDate(diaCalendario.getTime());
				}

			}
		});
		calendario = (CalendarView) findViewById(R.id.calendarView1);
		calendario.setOnDateChangeListener(new OnDateChangeListener() {

			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				diaElegido = new Date(view.getDate());
				fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
				// Toast.makeText(getBaseContext(),"Dia seleccionado es\n\n"
				// +dayOfMonth+" : "+(month+1)+" : "+year ,
				// Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void cargarDuracionTurno() {
		if (medicoSeleccionado != null) {
			duracionTurno = (TextView) findViewById(R.id.duracionTurnosDinamico);
			duracionTurno.setText(medicoSeleccionado.getDuracionTurno()
					.toString() + " min.");
		}

	}

	private void cargarHorarioDeAtencion(int indiceSucursal) {
		if (medicoSeleccionado != null) {
			String horarios = MedicosParser.extraerHorarios(
					medicoSeleccionado.getHorariosLugaresAtencion(),
					indiceSucursal);
			horarioAtencion = (TextView) findViewById(R.id.horarioAtencionDinamico);
			horarioAtencion.setText(horarios);
		}

	}

	private void cargarSucursales() {
		if (medicoSeleccionado != null) {
			sucursales = (Spinner) findViewById(R.id.sucursalMedico);

			ArrayAdapter<String> sucursalAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_dropdown_item,
					SucursalesList.obtenerAbreviaturas(medicoSeleccionado
							.getSucursales()));
			sucursales.setAdapter(sucursalAdapter);
			sucursales.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					cargarHorarioDeAtencion(posicionesSucursales.get(arg2));
					idSucursalSeleccionada = SucursalesList.mapaDeSucursales
							.get(SucursalesList.abrevSucursales[posicionesSucursales
									.get(arg2)]);

				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});
		}

	}

	private void cargarHistorialMedicos() {
		historialMedicos = (Spinner) findViewById(R.id.historialMedicos);
		ArrayAdapter<Medico> adapter = new ArrayAdapter<Medico>(this,
				android.R.layout.simple_spinner_dropdown_item, medicos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		historialMedicos.setAdapter(adapter);
		historialMedicos
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						medicoSeleccionado = medicos.get(arg2);
						cargarSucursales();
						cargarPosicionesIdSucursales();
						cargarHorarioDeAtencion(posicionesSucursales.get(0));
						cargarDuracionTurno();

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo_turno, menu);
		return true;
	}

	public void cargarMedicos() {
		this.medicos = (ArrayList<Medico>) MedicosParser
				.parsearMedicos(this.resultadosSerializados);
	}

	public void iniciarBusquedaMedicos(View view) {

		mAuthTask = new BusquedaMedicosTask(paciente.getId());
		mAuthTask.execute((Void) null);
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Preparando la búsqueda");
		progressDialog.show();

	}

	// TODO
	private boolean fechaValida() {

		Date hoy = Calendar.getInstance().getTime();
		hoy = DateUtil.obtenerHoraMinima(hoy);

		if (diaElegido == null) {
			mensajeValidacion = "Elija una fecha";
			return false;
		}
		if (diaElegido.before(hoy)) {
			mensajeValidacion = "Debe seleccionar una fecha posterior o igual a la actual";
			return false;
		}
		String diaAtencion = (String) horarioAtencion.getText();

		if (!diaAtencion.contains(DateUtil.obtenerNombreDelDia(diaElegido))) {
			mensajeValidacion = "El día ingresado no corresponde con los días de atención del médico.";
			return false;
		}

		if (diaElegido.after(DateUtil.obtenerDiaLimite())) {
			mensajeValidacion = "No se permite sacar turnos con más de 6 meses de anticipación";
			return false;
		}

		// validacion 1 posterior al dia de hoy
		// validacion 2 dia de atencion

		return true;
	}

	public class BusquedaMedicosTask extends AsyncTask<Void, Void, Boolean> {

		String mensajeErrorLogin;
		String resultadoBusqueda;
		Integer idPaciente;

		public BusquedaMedicosTask(Integer idPaciente) {
			super();
			this.idPaciente = idPaciente;

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

				// LLamar al servicio
				Intent intent = new Intent(getApplicationContext(),
						BusquedaMedicoActivity.class);
				intent.putExtra("resultadoBusqueda", resultadosSerializados);
				intent.putExtra("pacienteLogeado", paciente);
				startActivity(intent);
				finish();

			} else {
				Toast toast = Toast.makeText(NuevoTurnoActivity.this,
						mensajeErrorLogin, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent login = new Intent(getApplicationContext(),
						HomeActivity.class);
				login.putExtra("pacienteLogeado", paciente);
				startActivity(login);
				finish();
			}

		}

	}

	public class BusquedaTurnosTask extends AsyncTask<Void, Void, Boolean> {

		String resultadoTurnos;
		String mensaje;

		public BusquedaTurnosTask() {
			super();

		}

		@Override
		protected Boolean doInBackground(Void... params) {

			ListadoDeTurnosResult resultado = ServiceProvider.getClient()
					.obtenerTurnosDisponibles(paciente.getId(),
							medicoSeleccionado.getId(), idSucursalSeleccionada,
							fechaSeleccionada);

			MedicoResult medicoRes = ServiceProvider.getClient()
					.obtenerMedicoById(
							medicoSeleccionado.getId());
			if(medicoRes.getResultado().equals("OK"))
				medicoSeleccionado = new Medico(medicoRes);
			
			if (resultado.getResultado() == null) {
				mensaje = "Error en la conexión.";
				return false;

			}

			if (resultado.getResultado().equals("NO_DATA_FOUND")) {
				mensaje = "No existen turnos disponibles para el día, médico y sucursal seleccionados.";
				return false;

			}
			if (resultado.getResultado().equals("OK") && resultado.getMensaje()!=null & !TextUtils.isEmpty(resultado.getMensaje())) {
				String turnosReservados= resultado.getMensaje().replace('#', ',');
				mensaje= "Usted ya tiene los siguientes turnos: "+ turnosReservados; 
			}else{
			
				mensaje = resultado.getMensaje();
			}
			resultadoTurnos = resultado.getTurnos();
			
			return true;

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();
			busquedaTurnosTask = null;
			if (success) {
				if (mensaje!=null & !TextUtils.isEmpty(mensaje)){
				Toast toast = Toast.makeText(NuevoTurnoActivity.this, mensaje,
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				}
				Intent intent = new Intent(getApplicationContext(),
						ListadoDeTurnosDisponiblesActivity.class);
				intent.putExtra("resultadoTurno", resultadoTurnos);
				intent.putExtra("resultadoBusqueda", resultadosSerializados);
				intent.putExtra("pacienteLogeado", paciente);
				intent.putExtra("idSucursal",
						NuevoTurnoActivity.this.idSucursalSeleccionada
								.intValue());
				intent.putExtra("medicoConsultado",
						NuevoTurnoActivity.this.medicoSeleccionado);
				startActivity(intent);
			} else {
				Toast toast = Toast.makeText(NuevoTurnoActivity.this, mensaje,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();

			}

		}

	}

}
