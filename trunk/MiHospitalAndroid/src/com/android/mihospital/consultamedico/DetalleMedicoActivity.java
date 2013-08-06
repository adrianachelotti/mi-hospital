package com.android.mihospital.consultamedico;



import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mihospital.dominio.Medico;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.turnos.NuevoTurnoActivity;
import com.android.mihospital.utils.SucursalesList;

public class DetalleMedicoActivity extends Activity {

	private static final String SEPARADOR_SUCURSALES = ";";
	private static final String SEPARADOR_DIAS = "#";
	private Medico medico;
	private String resultadoBusqueda;
	private String medicoAReservarTurno;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medic_detail);
		setupActionBar();
		setupBotonMap();

		medico = getIntent().getParcelableExtra("medicoAConsultar");
		resultadoBusqueda = getIntent().getStringExtra("resultadoBusqueda");
		medicoAReservarTurno = extraerSubConsulta();
		
		ImageView medicoImagen = (ImageView) findViewById(R.id.imagenMedico);
		if (medico.getFoto() != null) {
			byte[] blob = medico.getFoto();

			Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
			
			medicoImagen.setImageBitmap(Bitmap.createScaledBitmap(bmp, 100, 100, true));

		}
		TextView nombreMedico = (TextView) findViewById(R.id.nombreMedico);
		TextView especialidadMedico = (TextView) findViewById(R.id.especialidadDinamica);
		TextView emailMedico = (TextView) findViewById(R.id.direccionDinamica);
		
		nombreMedico.setText(medico.getNombre() + " " + medico.getApellido());
		especialidadMedico.setText(medico.getEspecialidad());
		emailMedico.setText(medico.getMail());
		 
		agregarHorarios();

		agregarDuracionTurnos();
		agregarSolicitarTurno();
	}

	//
	private String extraerSubConsulta() {
		String resultados[] = resultadoBusqueda.split("#");
		for (int i = 0; i < resultados.length; i++) {
			String[] detalleMedico=resultados[i].split(";");
			if (detalleMedico[0].equals(medico.getId().toString())){
				return resultados[i];
			}
		}
		return "";
	}

	private void agregarSolicitarTurno() {
		Button botonSolicitarTurno = (Button) findViewById(R.id.solicitarTurno);
		botonSolicitarTurno.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), NuevoTurnoActivity.class);
				
				resultadoBusqueda = medico.getId()+";"+medico.getNombre()+";"+medico.getApellido()+";"+medico.getEspecialidad()+";"+medico.getSucursales()+ ";"+medico.getDuracionTurno()+"%"+ medico.getHorariosLugaresAtencion();
				intent.putExtra("resultadoBusqueda", medicoAReservarTurno);
//				intent.putExtra("pacienteLogeado",	paciente);
				startActivity(intent);	
				finish();
				
			}
		});
		
	}

	private void agregarHorarios() {
		TextView horarioMedico = (TextView) findViewById(R.id.horarioAtencionDinamico);
		String horarios = "";
		String[] dias = medico.getHorariosLugaresAtencion().split(
				SEPARADOR_DIAS);
		for (int i = 0; i < dias.length; i++) {
			String[] componentes = dias[i].split(SEPARADOR_SUCURSALES);
			String horario = String.format("- %s, de %s:00 a %s:00 en %s",
					componentes[0], componentes[1], componentes[2],
					SucursalesList.getSucursales()[Integer
							.valueOf(componentes[3])]);
			horarios += (horario) + "\n";
		}
		horarios=horarios.substring(0, horarios.length()-1);
		horarioMedico.setText(horarios);
	}

	private void agregarDuracionTurnos() {
		TextView sucursales = (TextView) findViewById(R.id.duracionTurnosDinamico);
		sucursales.setText(Integer.toString(medico.getDuracionTurno()) + " minutos");
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	private void setupBotonMap() {
		Button volverButton = (Button) findViewById(R.id.buttonMap);
		volverButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent mapActivity = new Intent(getApplicationContext(),
						SelectRoute.class);
				mapActivity
				.putExtra("sucursales", medico.getSucursales());
				startActivity(mapActivity);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.medic_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void volver(View view) {
		Intent resultadoBusquedaIntent = new Intent(getApplicationContext(),
				BusquedaMedicoActivity.class);
		resultadoBusquedaIntent
				.putExtra("resultadoBusqueda", resultadoBusqueda);
		startActivity(resultadoBusquedaIntent);
		finish();
	}

}
