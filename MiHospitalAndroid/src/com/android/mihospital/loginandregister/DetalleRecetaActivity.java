package com.android.mihospital.loginandregister;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Receta;
import com.android.mihospital.ws.ServiceResult;
import com.google.gson.Gson;

public class DetalleRecetaActivity extends Activity {

	private TextView medicamento;
	private TextView fecha;
	private TextView posologia;
	private TextView detalle;
	private TextView medico;
	private Context context;
	
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_receta);
		context=this;
		medicamento = (TextView) findViewById(R.id.textViewMedicamentoDinamica);
		posologia = (TextView) findViewById(R.id.textViewPosologiaDinamica);
		fecha = (TextView) findViewById(R.id.textViewFechaRecetaDinamica);
		detalle = (TextView) findViewById(R.id.textViewFormatoDinamica);
		medico = (TextView) findViewById(R.id.textViewMedicoDinamica);
		Integer id = getIntent().getIntExtra("idReceta", -1);
		
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Por favor espere");
		progressDialog.setMessage("Buscando datos");
		progressDialog.show();
		
		new DetalleRecetaTask(id).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_receta, menu);
		return true;
	}

	public class DetalleRecetaTask extends AsyncTask<Void, Void, Boolean> {

		Integer id;
		String content;

		public DetalleRecetaTask(Integer idReceta) {
			super();
			this.id = idReceta;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			ServiceResult result = ServiceProvider.getClient()
					.obtenerDetalleReceta(id);
			content = result.getMensaje();
			return (result.getResultado().equals("OK"));

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			
			progressDialog.dismiss();
			
			if (success) {
				Gson gson = new Gson();
				Receta m = gson.fromJson(content, Receta.class);
				
				medicamento.setText(m.getMedicamento().getMedicamento());
				posologia.setText(m.getIndicaciones());
				fecha.setText(m.getFecha());
				detalle.setText(m.getDetalle());
				medico.setText(m.getMedico().getApellido()+", "+m.getMedico().getNombre());
			} else {
				Toast.makeText(context.getApplicationContext(),
						"No se pudo el detalle del medicamento recetado", Toast.LENGTH_SHORT)
						.show();
			}
		}

	}
}
