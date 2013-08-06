package com.android.mihospital.consultapaciente;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.login.HomeActivity;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.utils.ProvinciasUtils;
import com.android.mihospital.utils.Utils;
import com.android.mihospital.ws.ServiceResult;

public class DetallePacienteActivity extends Activity {

	private static final int R_COLOR = 173;
	private static final int G_COLOR = 216;
	private static final int B_COLOR = 230;

	private String[] provincias = ProvinciasUtils.getProvincias();
	private static final String DATOS_ERRONEOS = "Corrija los datos";
	private EditText nombreApellidoEText;
	private EditText localidadEText;
	private EditText nacionalidadEText;
	private EditText sexoEText;
	private EditText fechaNacimientoEText;
	private EditText domicilioEText;
	private EditText telefonoEText;
	private EditText emailEText;
	private EditText dniEText;
	private EditText claveEText;
	private EditText afiliadoEText;
	private EditText obraSocialEText;
	private Spinner proviciaSpinner;
	private ProgressDialog progressDialog;
	private Context context;
	private Paciente paciente;
	private GuardarPacienteTask guardarPacienteTask;
	private Uri currImageURI;
	private byte[] imagenPaciente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacion_personal_activity2);
		paciente = getIntent().getParcelableExtra("pacienteLogeado2");
		context = this;
		cargarPaciente();
		ImageView pacienteImagen = (ImageView) findViewById(R.id.imagenPaciente);
		if (paciente.getFoto() != null) {
			byte[] blob = paciente.getFoto();

			Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);

			pacienteImagen.setImageBitmap(Bitmap.createScaledBitmap(bmp, 100,
					100, true));

		}
		// agregarVolverAlHome();

		agregarGuardarPaciente();

	}

	private void agregarGuardarPaciente() {
		Button guardar = (Button) findViewById(R.id.buttonModificar);
		guardar.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("static-access")
			public void onClick(View v) {

				if (ingresoDeDatosValidos()) {
					guardarPaciente();
					guardarPacienteTask = new GuardarPacienteTask();
					guardarPacienteTask.execute((Void) null);
					progressDialog = new ProgressDialog(context);
					progressDialog.show(context, "Por favor espere",
							"Modificando al paciente");
				} else {
					Toast toast = Toast.makeText(DetallePacienteActivity.this,
							DATOS_ERRONEOS, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}
			}

			private boolean ingresoDeDatosValidos() {
				boolean esValido = true;
				String pass = claveEText.getText().toString();
				String telefono = telefonoEText.getText().toString();
				String mail = emailEText.getText().toString();
				String direccion = domicilioEText.getText().toString();
				String localidad = localidadEText.getText().toString();

				View focusView = null;

				if (TextUtils.isEmpty(pass)) {
					esValido = false;
					claveEText.setError("Complete la clave");
					focusView = claveEText;
				} else {
					if (pass.length() <= 3) {
						esValido = false;
						claveEText
								.setError("La clave debe tener al menos 4 caracteres");
						focusView = claveEText;
					}
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

				if (TextUtils.isEmpty(mail)) {
					esValido = false;
					emailEText.setError("Complete el mail");
					focusView = emailEText;
				} else {
					if (!Utils.isValidEmail(mail)) {
						esValido = false;
						emailEText.setError("Formato de mail incorrecto");
						focusView = emailEText;
					}
				}

				if (!esValido) {
					// Se muestra el mensaje de error
					focusView.requestFocus();

				}
				return esValido;
			}

		});
	}

	/*
	 * private void agregarVolverAlHome() { Button volver = (Button)
	 * findViewById(R.id.linkVolver); volver.setOnClickListener(new
	 * View.OnClickListener() {
	 * 
	 * public void onClick(View v) { Intent homeActivity = new
	 * Intent(getApplicationContext(),HomeActivity.class);
	 * homeActivity.putExtra("pacienteLogeado",paciente);
	 * 
	 * startActivity(homeActivity); finish();
	 * 
	 * }
	 * 
	 * }); }
	 */

	private void guardarPaciente() {

		domicilioEText = (EditText) findViewById(R.id.direccion_editText);
		paciente.setDireccion(domicilioEText.getText().toString());
		telefonoEText = (EditText) findViewById(R.id.telefono_editText);
		paciente.setTelefono(telefonoEText.getText().toString());
		emailEText = (EditText) findViewById(R.id.email_editText);
		paciente.setMail(emailEText.getText().toString());
		obraSocialEText = (EditText) findViewById(R.id.obrasocial_editText);
		paciente.setObraSocial(obraSocialEText.getText().toString());
		localidadEText = (EditText) findViewById(R.id.localidad_editText);
		paciente.setLocalidad(localidadEText.getText().toString());
		proviciaSpinner = (Spinner) findViewById(R.id.spinner1);
		paciente.setProvincia(proviciaSpinner.getSelectedItem().toString());
		paciente.setFoto(imagenPaciente);

	}

	private int obtenerIndiceProvincia() {
		int posicion = 0;
		for (String pcia : provincias) {
			if (pcia.equals(paciente.getProvincia())) {
				return posicion;
			}
			posicion++;
		}
		return 0;
	}

	private void cargarPaciente() {
		nombreApellidoEText = (EditText) findViewById(R.id.nombre_editText);
		nombreApellidoEText.setText(paciente.getNombre() + " "
				+ paciente.getApellido());
		grisarCampoNoEditable(nombreApellidoEText);
		domicilioEText = (EditText) findViewById(R.id.direccion_editText);
		domicilioEText.setText(paciente.getDireccion());
		telefonoEText = (EditText) findViewById(R.id.telefono_editText);
		telefonoEText.setText(paciente.getTelefono());
		emailEText = (EditText) findViewById(R.id.email_editText);
		emailEText.setText(paciente.getMail());
		dniEText = (EditText) findViewById(R.id.dni_editText);
		dniEText.setText(paciente.getNumeroDocumento().toString());
		nacionalidadEText = (EditText) findViewById(R.id.nacionalidad_editText);
		nacionalidadEText.setText(paciente.getNacionalidad());
		grisarCampoNoEditable(nacionalidadEText);
		// provisorio ver de hacerlo radio button
		sexoEText = (EditText) findViewById(R.id.sexo_editText);
		sexoEText.setText(paciente.getSexo());
		grisarCampoNoEditable(sexoEText);

		fechaNacimientoEText = (EditText) findViewById(R.id.fecha_nacimmiento_editText);
		fechaNacimientoEText.setText(paciente.getFechaNacimiento());
		grisarCampoNoEditable(fechaNacimientoEText);

		grisarCampoNoEditable(dniEText);
		claveEText = (EditText) findViewById(R.id.clave_editText);
		claveEText.setText(paciente.getClave());
		afiliadoEText = (EditText) findViewById(R.id.afiliado_editText);
		afiliadoEText.setText(paciente.getAfiliado().toString());
		grisarCampoNoEditable(afiliadoEText);
		obraSocialEText = (EditText) findViewById(R.id.obrasocial_editText);
		obraSocialEText.setText(paciente.getObraSocial());
		grisarCampoNoEditable(obraSocialEText);
		localidadEText = (EditText) findViewById(R.id.localidad_editText);
		localidadEText.setText(paciente.getLocalidad());

		proviciaSpinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.prov_arrays,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		proviciaSpinner.setAdapter(adapter);
		proviciaSpinner.setSelection(obtenerIndiceProvincia());

	}

	private void grisarCampoNoEditable(EditText text) {
		// text.setBackgroundColor(Color.rgb(R_COLOR, G_COLOR, B_COLOR));
		text.setTextColor(Color.parseColor("#424242"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.informacion_personal, menu);
		return true;
	}

	public void seleccionarImagen(View view) {

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				1);

	}

	// To handle when an image is selected from the browser
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				// currImageURI is the global variable I’m using to hold the
				// content:
				currImageURI = data.getData();
				System.out.println("Current image Path is ----->"
						+ getRealPathFromURI(currImageURI));

				Bitmap bitmapOrg = BitmapFactory
						.decodeFile(getRealPathFromURI(currImageURI));
				ByteArrayOutputStream bao = new ByteArrayOutputStream();

				// Resize the image
				double width = bitmapOrg.getWidth();
				double height = bitmapOrg.getHeight();
				double ratio = 400 / width;
				int newheight = (int) (ratio * height);

				bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 400,
						newheight, true);

				bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 95, bao);
				imagenPaciente = bao.toByteArray();

				ImageView pacienteImagen = (ImageView) findViewById(R.id.imagenPaciente);
				pacienteImagen.setImageBitmap(Bitmap.createScaledBitmap(
						bitmapOrg, 100, 100, true));

			}
		}
	}

	// Convert the image URI to the direct file system path of the image file
	public String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		android.database.Cursor cursor = managedQuery(contentUri, proj, // Which
																		// columns
																		// to
																		// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	private class GuardarPacienteTask extends AsyncTask<Void, Void, Boolean> {
		private String mensajeDeErrorModificacion = "No puedo realizarse la modificacion.";
		private static final String MODIFICACION_CORRECTA = "Modificación realizada.";

		@Override
		protected Boolean doInBackground(Void... params) {

			ServiceResult serviceResult = ServiceProvider.getClient()
					.actualizarPaciente(paciente);
			if (serviceResult.getResultado() == null) {
				mensajeDeErrorModificacion = "Error en la conexión.";
				return false;

			}

			if (serviceResult.getResultado() != null
					&& serviceResult.getResultado().equalsIgnoreCase("OK")) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			guardarPacienteTask = null;
			progressDialog.dismiss();

			if (success) {
				PacienteLogeadoSession.setPacienteLogeado(paciente);
				Toast toast = Toast.makeText(DetallePacienteActivity.this,
						MODIFICACION_CORRECTA, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				finish();
			} else {
				Toast toast = Toast.makeText(DetallePacienteActivity.this,
						mensajeDeErrorModificacion, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent informacionPersonalActivity = new Intent(
						getApplicationContext(), DetallePacienteActivity.class);
				informacionPersonalActivity.putExtra("pacienteLogeado2",
						paciente);
				startActivity(informacionPersonalActivity);
				finish();
			}
			super.onPostExecute(success);
		}

		@Override
		protected void onCancelled() {
			guardarPacienteTask = null;
			progressDialog.dismiss();

		}

	}

}
