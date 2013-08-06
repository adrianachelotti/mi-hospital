package com.android.mihospital.mensajes;

import java.text.SimpleDateFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mihospital.dominio.Mensaje;
import com.android.mihospital.loginandregister.R;
import com.android.mihospital.utils.Utils;

public class MensajeAdapter extends ArrayAdapter<Mensaje> {

	private List<Mensaje> mensajes;

	public MensajeAdapter(Context context, int textViewResourceId,
			List<Mensaje> listItems) {
		super(context, textViewResourceId, listItems);
		this.mensajes = listItems;
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.mensaje_item, null);
		}

		Mensaje mensaje = mensajes.get(position);
		TextView asunto = (TextView) v.findViewById(R.id.textViewSubject);
		asunto.setText(mensaje.getAsunto());
		TextView fecha = (TextView) v.findViewById(R.id.messageDate);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		fecha.setText(format.format(mensaje.getFecha()));

		if (mensaje != null) {
			TextView text = (TextView) v.findViewById(R.id.messageViewText);
			text.setText(mensaje.getMensaje());
			if (mensaje.getEmisor().equals("P")) {
				ImageView btn = (ImageView) v.findViewById(R.id.imageViewRead);
				btn.setVisibility(View.INVISIBLE);
				btn = (ImageView) v.findViewById(R.id.imageViewUnRead);
				btn.setVisibility(View.INVISIBLE);
				v.setBackgroundColor(Color.BLACK);
				//v.setBackgroundResource(R.drawable.ic_msg1);
			} else {
				//v.setBackgroundResource(R.drawable.ic_msg2);
				v.setBackgroundColor(Color.parseColor("#660000"));

				if (mensaje.getEstado().equals(Utils.LEIDO)) {
					ImageView btn = (ImageView) v
							.findViewById(R.id.imageViewUnRead);
					btn.setVisibility(View.INVISIBLE);
					btn = (ImageView) v.findViewById(R.id.imageViewRead);
					btn.setVisibility(View.VISIBLE);

				} else {
					ImageView btn = (ImageView) v
							.findViewById(R.id.imageViewRead);
					btn.setVisibility(View.INVISIBLE);
					btn = (ImageView) v.findViewById(R.id.imageViewUnRead);
					btn.setVisibility(View.VISIBLE);
				}
			}

		}
		return v;
	}
}