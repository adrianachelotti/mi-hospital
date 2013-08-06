package com.android.mihospital.ws.medico;


import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class BusquedaMedicoResult implements KvmSerializable {
	
	private String mensaje = null;
	private String resultado = null;
	private String medicos = null; 
	
	

	
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
			info.name = "medicos";
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
			medicos = (String) value;
			break;
		default:
			break;
		}
		
	}

	public String getMedicos() {
		return medicos;
	}

	public void setMedicos(String medicos) {
		this.medicos = medicos;
	}

}
