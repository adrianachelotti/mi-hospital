package com.android.mihospital.loginandregister;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.mihospital.dominio.Estudio;
import com.android.mihospital.utils.TipoEstudioUtils;

public class EstudioAdapter extends ArrayAdapter<Estudio> {

	private List<Estudio> estudios;

	public EstudioAdapter(Context context, int textViewResourceId,
			List<Estudio> listItems) {
		super(context, textViewResourceId, listItems);
		this.estudios = listItems;
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.estudio_item, null);
		}
		
		Estudio estudio = estudios.get(position);
		TextView asunto = (TextView) v.findViewById(R.id.textViewEstudio);
		asunto.setText(TipoEstudioUtils.getTipoEstudio()[estudio.getTipoDeEstudio()-1]);
		TextView fecha = (TextView) v.findViewById(R.id.textViewFechaEstudio);
		fecha.setText(estudio.getFecha());
		TextView departamento = (TextView) v.findViewById(R.id.textViewDepartamento);
		departamento.setText(TipoEstudioUtils.getDepartamentos()[estudio.getIdDepartamento()-1]);
		return v;
	}
}