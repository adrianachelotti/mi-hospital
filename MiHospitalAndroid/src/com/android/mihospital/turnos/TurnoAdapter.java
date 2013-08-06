package com.android.mihospital.turnos;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.Utils;

public class TurnoAdapter extends ArrayAdapter<Turno> {

	private  ArrayList<Turno> turnos;

	public TurnoAdapter(Context context, int textViewResourceId, ArrayList<Turno> turnos) {
		super(context, textViewResourceId, turnos);
		this.turnos = turnos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.turno_item, null);
		}

		Turno turno = turnos.get(position);
		if (turno != null) {
			TextView horario = (TextView) v.findViewById(R.id.horario);
			TextView especialidad = (TextView) v.findViewById(R.id.especialidad);
			
			if (horario != null) {
				horario.setText(turno.getDia());
				horario.setHeight(100);
			}
			if(especialidad!=null){
				especialidad.setText(turno.getEspecialidad());
			}			
		}
		return v;
	}
}
