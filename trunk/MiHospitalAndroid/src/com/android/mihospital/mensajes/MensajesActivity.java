package com.android.mihospital.mensajes;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.dominio.Mensaje;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.Utils;
import com.android.mihospital.ws.ServiceResult;
import com.android.mihospital.ws.mensaje.ListaDeMensajeResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MensajesActivity extends Activity {

	private final static int INTERVAL = 1000  * 5; // 2 minutes

	// LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
	List<Mensaje> listItems = new ArrayList<Mensaje>();

	Set<Integer> mensajesKeys = new HashSet<Integer>();
	private ProgressDialog progressDialog;
	private Context context;
	// DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
	ArrayAdapter<Mensaje> adapter;

	private EditText message;

	private EditText subject;

	private String idMedico;

	private String idPaciente;
	private EnviarMensajeTask enviarMensajeTask;
	private String mensajesJson;
	private static Handler handler;
	static boolean isAlive;

	private static MensajesActivity current = null;

	public static MensajesActivity getCurrent() {
		return current;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isAlive = false;
		// Create the same intent, and thus a matching IntentSender, for
		// the one that was scheduled.
		Intent intent = new Intent(MensajesActivity.this,
				MessagePoolingReciever.class);
		PendingIntent sender = PendingIntent.getBroadcast(
				MensajesActivity.this, 0, intent, 0);
		// And cancel the alarm.
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(sender);
		current = null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mensajes);
		context = this;
		current = this;
		isAlive = true;
		ListView list = (ListView) findViewById(R.id.listView1);
		// Show the Up button in the action bar.
		setupActionBar();
		adapter = new MensajeAdapter(this, android.R.layout.simple_list_item_1,
				listItems);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!listItems.get(position).getEstado().equals(Utils.LEIDO)
						&& !listItems.get(position).getEmisor()
								.equals(Utils.EMISOR_PACIENTE)) {
					new MarcarMensajeTask(listItems.get(position)).execute();
				}
			}
		});
		message = (EditText) findViewById(R.id.messageViewText);
		subject = (EditText) findViewById(R.id.editTextAsunto);

		idMedico = getIntent().getStringExtra("idMedicoAMensajear");
		idPaciente = getIntent().getStringExtra("idPacienteLogueado");

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {

				if (isAlive) {
					adapter.notifyDataSetChanged();
				}
			}

		};
		traerMensajes(null);
		startMessagePooling();
	}

	public void traerMensajes(String estado) {
		new TraerMensajeTask(estado).execute();
	}

	public void startMessagePooling() {
		Intent intent = new Intent(MensajesActivity.this,
				MessagePoolingReciever.class);
		PendingIntent sender = PendingIntent.getBroadcast(
				MensajesActivity.this, 0, intent, 0);

	    PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, 0);
	    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
	        + INTERVAL, pendingIntent);    
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mensajes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressLint("NewApi")
	public void addItems(View v) {
		String text = message.getText().toString();
		if (!text.isEmpty()) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setTitle("Por favor espere");
			progressDialog.setMessage("Enviando mensaje");
			progressDialog.show();

			Mensaje msg = new Mensaje();
			String asunto = subject.getText().toString();
			if (asunto.isEmpty())
				asunto = "(sin asunto)";
			msg.setAsunto(asunto);
			msg.setFecha(new Date());
			msg.setMensaje(text);
			msg.setEmisor(Utils.EMISOR_PACIENTE);
			msg.setIdPaciente(Integer.valueOf(idPaciente));
			msg.setIdMedico(Integer.valueOf(idMedico));
			enviarMensajeTask = new EnviarMensajeTask(msg);
			enviarMensajeTask.execute((Void) null);
		} else {
			Toast.makeText(this.getApplicationContext(),
					"No se puede enviar un mensaje sin contenido",
					Toast.LENGTH_SHORT).show();
		}
	}

	public class EnviarMensajeTask extends AsyncTask<Void, Void, Boolean> {

		Mensaje msg;

		public EnviarMensajeTask(Mensaje msg) {
			super();
			this.msg = msg;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			ServiceResult result = ServiceProvider.getClient().enviarMensaje(
					msg);
			return (result.getResultado().equals("OK"));

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();
			enviarMensajeTask = null;
			if (success) {
				msg.setEmisor(Utils.EMISOR_PACIENTE);
				listItems.add(msg);
				message.setText("");
				subject.setText("");
				adapter.notifyDataSetChanged();
			} else {
				Toast.makeText(context.getApplicationContext(),
						"No se pudo enviar el mensaje", Toast.LENGTH_SHORT)
						.show();
			}
		}

	}

	public class MarcarMensajeTask extends AsyncTask<Void, Void, Boolean> {

		Mensaje msg;

		public MarcarMensajeTask(Mensaje msg) {
			super();
			this.msg = msg;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			ServiceResult resultado = ServiceProvider.getClient()
					.marcarMensajeComoLeido(msg.getIdMensaje());

			return (resultado.getResultado().equals("OK"));

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (success) {
				msg.setEstado(Utils.LEIDO);
				adapter.notifyDataSetChanged();
			}
		}

	}

	public class TraerMensajeTask extends AsyncTask<Void, Void, Boolean> {

		String estado=null;
		public TraerMensajeTask(String estado) {
			super();
			this.estado=estado;
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			Type listType = new TypeToken<List<Mensaje>>() {
			}.getType();
			Gson gson = new Gson();
			ListaDeMensajeResult resultado = ServiceProvider.getClient()
					.traerMensajesDelPaciente(Integer.valueOf(idPaciente),
							Integer.valueOf(idMedico), estado);
			String mensajesJson = resultado.getMensajeEnviados();

			List<Mensaje> list2 = gson.fromJson(mensajesJson, listType);
			if (list2 != null && list2.size() > 0) {
				for (Mensaje m : list2) {
					if ((!mensajesKeys.contains(m.getIdMensaje())
							&& !m.getEmisor().equals(Utils.EMISOR_PACIENTE))||(estado==null)) {
						listItems.add(m);
						mensajesKeys.add(m.getIdMensaje());
					}
				}

			}
			handler.sendEmptyMessage(0);
			startMessagePooling();
			return true;

		}

	}

}
