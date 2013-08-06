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
import com.android.mihospital.utils.SucursalesList;
import com.android.mihospital.utils.Utils;

public class HistorialTurnoAdapter extends ArrayAdapter<Turno> {

	private ArrayList<Turno> turnos;

	public HistorialTurnoAdapter(Context context, int textViewResourceId,
			ArrayList<Turno> turnos) {
		super(context, textViewResourceId, turnos);
		this.turnos = turnos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.historial_turno_item, null);
		}

		Turno turno = turnos.get(position);
		if (turno != null) {
			TextView nombeCompleto = (TextView) v
					.findViewById(R.id.nombreMedico);
			TextView especialidad = (TextView) v
					.findViewById(R.id.especialidad);
			TextView diaFecha = (TextView) v.findViewById(R.id.fechaHorario);
			TextView sucursal = (TextView) v.findViewById(R.id.sucursal);

			if (nombeCompleto != null) {
				nombeCompleto.setText(turno.getNombreMedico());

			}
			if (especialidad != null) {
				especialidad.setText(turno.getEspecialidad());
			}

			if (diaFecha != null) {
				diaFecha.setText(turno.getDia());
			}
			if (sucursal != null) {
				sucursal.setText(SucursalesList.sucursales[turno.getSucursal()]);
			}

			TextView cancelado = (TextView) v
					.findViewById(R.id.textViewCancelado);
			if (turno.getEstado().equals(Utils.CANCELADO_MEDICO)) {

				cancelado.setVisibility(View.VISIBLE);
			} else {
				cancelado.setVisibility(View.GONE);
			}

		}
		return v;
	}
}
