package com.android.mihospital.ws;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class ServiceResult implements KvmSerializable{

	private String mensaje = null;
	private String resultado = null;
	
	public Object getProperty(int index) {
		switch (index){
		case 0:
			return mensaje;
		case 1:
			return resultado;
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 18;
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index){
		case 0:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "mensaje";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "resultado";
			break;
		default:
			break;
		}
	}

	public void setProperty(int index, Object value) {
		
		switch (index){
		case 0:
			mensaje = (String) value;
			break;
		case 1:
			resultado = (String) value;
			break;
		default:
			break;
		}
		
	}

	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
