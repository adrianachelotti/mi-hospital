package com.android.mihospital.consultamedico;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.PacienteLogeadoSession;
import com.android.mihospital.utils.SucursalesList;

public class SelectRoute extends Activity {

	private List<String> direcciones;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_route);
		direcciones = new ArrayList<String>();
		agregarSucursales();
		
		RadioGroup radioGroup= (RadioGroup) findViewById(R.id.RadioGroupSucursales);
		radioGroup.removeAllViews();
		int id=0;
		for(String dir : direcciones){

			RadioButton radioButton = new RadioButton(getBaseContext());
            radioButton.setText(dir);
            radioButton.setId(id);
            if(dir.equals(direcciones.get(0)))
            	radioButton.setChecked(true);
            radioGroup.addView(radioButton);
            id++;
		}
	}
	private void agregarSucursales() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			
			String params = (String) extras.get("sucursales");
			String[] sucursalesId = params.split(";");
			for (int i = 0; i < sucursalesId.length; i++)
				direcciones.add((SucursalesList.getSucursales()[Integer
						.valueOf(sucursalesId[i])]));
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_route, menu);
		return true;
	}

	public void onCalculateRoute(View view) {
		Intent mapActivity = new Intent(getApplicationContext(),MapActivity.class);
		
		RadioGroup origen= (RadioGroup) findViewById(R.id.RadioGroupOrigen);
		int index = origen.indexOfChild(findViewById(origen.getCheckedRadioButtonId()));
		String desde="";
		if(index==1){
			try {
				desde=URLEncoder.encode(PacienteLogeadoSession.getPacientLogeado().getDireccion()+","+PacienteLogeadoSession.getPacientLogeado().getLocalidad(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				
			}
		}
		RadioGroup destino= (RadioGroup) findViewById(R.id.RadioGroupSucursales);

		index = destino.indexOfChild(findViewById(destino.getCheckedRadioButtonId()));
		
		String hasta="";
		hasta = getSucursal(index);
		
		RadioGroup modo= (RadioGroup) findViewById(R.id.RadioGroupModo);

		index = modo.indexOfChild(findViewById(modo.getCheckedRadioButtonId()));
		
		String selectedMode=(index==0)? "driving" : "walking";
		
		String params=String.format("%s;%s;%s",  desde, hasta,selectedMode );
		

		mapActivity.putExtra("QueryParams", params);
		
		startActivity(mapActivity);
	}

	private String getSucursal(int index) {		
		try {
			return URLEncoder.encode(direcciones.get(index), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		return "";
	}
}
