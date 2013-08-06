package com.android.mihospital.loginandregister;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.mihospital.dominio.Receta;

public class RecetaAdapter extends ArrayAdapter<Receta> {

	private List<Receta> medicamentos;

	public RecetaAdapter(Context context, int textViewResourceId,
			List<Receta> listItems) {
		super(context, textViewResourceId, listItems);
		this.medicamentos = listItems;
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.medicamento_item, null);
		}

		Receta medicamento = medicamentos.get(position);
		TextView asunto = (TextView) v.findViewById(R.id.textViewMedicamento);
		asunto.setText(medicamento.getMedicamento().getMedicamento());
		TextView fecha = (TextView) v.findViewById(R.id.textViewFecha);
		fecha.setText(medicamento.getFecha());

		return v;
	}
}