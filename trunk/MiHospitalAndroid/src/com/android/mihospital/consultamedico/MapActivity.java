package com.android.mihospital.consultamedico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.mihospital.dominio.Bounds;
import com.android.mihospital.dominio.MapRequestResult;
import com.android.mihospital.dominio.Mensaje;
import com.android.mihospital.dominio.Step;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.LatLngDeserializer;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This shows how to place markers on a map.
 */
public class MapActivity extends FragmentActivity implements
		OnMarkerClickListener, OnInfoWindowClickListener,
		LocationListener {

	private static final int LINE_WIDTH = 4;
	private Bounds limits;
	private LatLng currentPos;
	private MapRequestResult maprequest;
	private ProgressDialog progressDialog;
	/** Demonstrates customizing the info window and/or its contents. */

	private GoogleMap mMap;

	private String origin;
	private String hasta;
	private String mode;
	private static final String[] DESDE = new String[] { "Mi casa" };
	private static final String[] HASTA = new String[] { "Sucursal 1",
			"Sucursal 2", "Sucursal 3" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_activity);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			LinearLayout frame = (LinearLayout) findViewById(R.id.floatingBox);
			frame.setVisibility(View.INVISIBLE);
			
			String params = (String) extras.get("QueryParams");
			String[] list=params.split(";");
			origin=list[0];
			origin=origin.replace("%2C", ",");
			origin=origin.replace("%2B", "+");
			hasta=list[1];
			hasta=hasta.replace("%2C", ",");
			hasta=hasta.replace("%2B", "+");
			mode=list[2];
		}
		setupAutocomplete(R.id.desde, DESDE,origin);
		setupAutocomplete(R.id.hasta, HASTA,hasta);

		getCurrentLocation();

		setMode();

		setUpMapIfNeeded();

		onResetMap(null);
	}

	private void setMode() {
		if(mode.length()==0){
			RadioButton bAuto = (RadioButton) findViewById(R.id.radio0);
			if (bAuto.isChecked())
				mode = "driving";
			else
				mode = "walking";
		}
	}

	private void getCurrentLocation() {
		// get Your Current Location
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			onLocationChanged(location);
		}
	}

	private void getMapRoute() {
		getOrigin();
		getHasta();
		setMode();
		if (origin.length() != 0 && hasta.length() != 0) {
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://maps.googleapis.com/maps/api/directions/json?origin="
							+ origin + "&destination=" + hasta
							+ "&region=arg&sensor=false&mode=" + mode);

			try {
				StringBuilder builder = new StringBuilder();
				HttpResponse response = httpclient.execute(httppost);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					String contentJson = builder.toString();
					Gson gson = new GsonBuilder()
							.registerTypeAdapter(LatLng.class,
									new LatLngDeserializer())
							.setFieldNamingPolicy(
									FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
							.create();
					maprequest = gson.fromJson(contentJson,
							MapRequestResult.class);
					if (!maprequest.getRoutes().isEmpty())
						limits = maprequest.getRoutes().get(0).getBounds();

				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("static-access")
	private void getOrigin() {
		AutoCompleteTextView desdeACV = (AutoCompleteTextView) findViewById(R.id.desde);
		String textDesde = desdeACV.getText().toString();
		try {
			if (textDesde.equals(this.DESDE[0]))
				origin = URLEncoder.encode(PacienteLogeadoSession.getPacientLogeado().getDireccion()+","+PacienteLogeadoSession.getPacientLogeado().getLocalidad(), "UTF-8");
			else if (textDesde.length() == 0 && currentPos != null)
				origin = String.format(Locale.US,"%f,%f", currentPos.latitude,
						currentPos.longitude);
			else
				origin = URLEncoder.encode(textDesde, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	private void getHasta() {
		AutoCompleteTextView hastaACV = (AutoCompleteTextView) findViewById(R.id.hasta);
		String textDesde = hastaACV.getText().toString();
		try {
			if (textDesde.equals(this.HASTA[0]))
				hasta = URLEncoder.encode("Av Cabildo 800", "UTF-8");
			if (textDesde.equals(this.HASTA[1]))
				hasta = URLEncoder.encode("Av. Santa Fe 4000", "UTF-8");
			if (textDesde.equals(this.HASTA[2]))
				hasta = URLEncoder.encode(
						"Av. San Martin 650,Buenos Aires, Capital", "UTF-8");
			else
				hasta = URLEncoder.encode(textDesde, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void setupAutocomplete(int fieldId, final String[] values,String value) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, values);
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(fieldId);
		textView.setAdapter(adapter);
		textView.setText(value);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {

		mMap.setMyLocationEnabled(true);

		// Set listeners for marker events. See the bottom of this class for
		// their behavior.
		mMap.setOnMarkerClickListener(this);
		mMap.setOnInfoWindowClickListener(this);

		// Pan to see all markers in view.
		// Cannot zoom to bounds until the map has a size.
		final View mapView = getSupportFragmentManager().findFragmentById(
				R.id.map).getView();
		if (mapView.getViewTreeObserver().isAlive()) {
			mapView.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {
					
						// We use the new method when supported
						@SuppressLint("NewApi")
						// We check which build version we are using.
						public void onGlobalLayout() {

							if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
								mapView.getViewTreeObserver()
										.removeOnGlobalLayoutListener(this);
							} else {
								mapView.getViewTreeObserver()
										.removeOnGlobalLayoutListener(this);
							}

							if (limits != null) {
								LatLngBounds bounds = new LatLngBounds.Builder()
										.include(limits.getNortheast())
										.include(limits.getSouthwest()).build();

								mMap.moveCamera(CameraUpdateFactory
										.newLatLngBounds(bounds, 50));
							}else
							if(currentPos!=null){
								mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPos));
								mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
							}
						}
					});
		}
	}

	private void addMarkersToMap() {
		if (maprequest != null && !maprequest.getRoutes().isEmpty()) {

			mMap.addMarker(new MarkerOptions()
					.position(
							maprequest.getRoutes().get(0).getLegs().get(0)
									.getStartLocation())
					.title("Origen")
					.snippet(
							maprequest.getRoutes().get(0).getLegs().get(0)
									.getStartAddress())
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

			mMap.addMarker(new MarkerOptions()
					.position(
							maprequest.getRoutes().get(0).getLegs().get(0)
									.getEndLocation())
					.title("Destino")
					.snippet(
							maprequest.getRoutes().get(0).getLegs().get(0)
									.getEndAddress())
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

			List<LatLng> points = new ArrayList<LatLng>();
			for (Step step : maprequest.getRoutes().get(0).getLegs().get(0)
					.getSteps()) {
				points.addAll(decodePoly(step.getPolyline().getPoints()));
				// points.add(step.getStartLocation());
				// points.add(step.getEndLocation());
			}// Uses a colored icon.
			float[] prevHSV = new float[3];
			Color.colorToHSV(Color.BLUE, prevHSV);
			mMap.addPolyline((new PolylineOptions()).addAll(points)
					.color(Color.HSVToColor(90, prevHSV)).width(LINE_WIDTH));
		} else {
			Toast toast = Toast.makeText(this,"No se encontro camino", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.show();
			
		}
	}

	private boolean checkReady() {
		if (mMap == null) {
			Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT)
					.show();
			return false;
		}
		return true;
	}

	/** Called when the Clear button is clicked. */
	public void onClearMap(View view) {
		if (!checkReady()) {
			return;
		}
		mMap.clear();
	}

	/** Called when the Reset button is clicked. */
	public void onResetMap(View view) {
		if (!checkReady()) {
			return;
		}
		getHasta();
		if(this.hasta.length()>0){
			progressDialog = new ProgressDialog(this);
			progressDialog.setTitle("Por favor espere");
			progressDialog.setMessage("Calculando ruta");
			progressDialog.show();
	
			new UpdateMapContentTask().execute((Void) null);
		}else{
			AutoCompleteTextView hastaACV = (AutoCompleteTextView) findViewById(R.id.hasta);
			hastaACV.setError(getString(R.string.campo_obligatorio));
			hastaACV.requestFocus();
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UpdateMapContentTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {

			// Clear the map because we don't want duplicates of the markers.
			getMapRoute();

			return true;

		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();
			progressDialog = null;
			mMap.clear();
			if (limits != null) {
				LatLngBounds bounds = new LatLngBounds.Builder()
						.include(limits.getNortheast())
						.include(limits.getSouthwest()).build();

				mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
			}
			addMarkersToMap();
		}
	}

	public boolean onMarkerClick(final Marker marker) {
		return false;
	}

	public void onLocationChanged(Location location) {
		currentPos = new LatLng(location.getLatitude(), location.getLongitude());
	}

	public void onProviderDisabled(String provider) {

	}

	public void onProviderEnabled(String provider) {

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}
		return poly;
	}

	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public class ObtenerRutaTask extends AsyncTask<Void, Void, Boolean> {


		public ObtenerRutaTask(Mensaje msg) {
			super();
		}

		@Override
		protected Boolean doInBackground(Void... params) {


			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			if (success) {
				
			}
		}

	}
}
