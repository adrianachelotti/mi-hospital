package com.android.mihospital.mensajes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.mihospital.utils.Utils;

public class MessagePoolingReciever extends BroadcastReceiver {

	public MessagePoolingReciever(){
		
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		if(MensajesActivity.getCurrent()!=null)
			MensajesActivity.getCurrent().traerMensajes(Utils.NO_LEIDO);
	}
}