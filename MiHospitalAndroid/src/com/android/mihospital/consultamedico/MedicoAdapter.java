package com.android.mihospital.consultamedico;

import java.util.ArrayList;

import com.android.mihospital.dominio.Medico;
import com.android.mihospital.loginandregister.R;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class MedicoAdapter extends ArrayAdapter<Medico> {

	private  ArrayList<Medico> medicos;

	public MedicoAdapter(Context context, int textViewResourceId, ArrayList<Medico> users) {
		super(context, textViewResourceId, users);
		this.medicos = users;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.listitem, null);
		}

		Medico medico = medicos.get(position);
		if (medico != null) {
			TextView username = (TextView) v.findViewById(R.id.username);
			TextView email = (TextView) v.findViewById(R.id.especialidad);
			TextView direccionSucursal =(TextView) v.findViewById(R.id.sucursal);
			if (username != null) {
				username.setText(medico.getNombre() + " " + medico.getApellido());
			}

			if(email != null) {
				email.setText(medico.getEspecialidad());
			}
			if (direccionSucursal!=null) {
				direccionSucursal.setText(medico.getSucursales());
			}
		}
		return v;
	}
}
