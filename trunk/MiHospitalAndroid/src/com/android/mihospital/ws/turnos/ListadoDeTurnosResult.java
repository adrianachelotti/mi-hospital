package com.android.mihospital.ws.turnos;


import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class ListadoDeTurnosResult implements KvmSerializable {
	
	private String mensaje = null;
	private String resultado = null;
	private String turnos = null; 
	
	

	
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
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPropertyCount() {
		return 3;
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
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "turnos";
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
		case 2:
			turnos = (String) value;
			break;
		default:
			break;
		}
		
	}

	public String getTurnos() {
		return turnos;
	}

	public void setTurnos(String turnos) {
		this.turnos = turnos;
	}

}
