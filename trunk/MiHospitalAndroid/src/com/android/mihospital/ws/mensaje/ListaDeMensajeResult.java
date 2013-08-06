package com.android.mihospital.ws.mensaje;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class ListaDeMensajeResult implements KvmSerializable {

	private String mensaje = null;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	private String mensajeEnviados = null;

	public String getMensajeEnviados() {
		return mensajeEnviados;
	}

	public void setMensajeEnviados(String mensajeEnviados) {
		this.mensajeEnviados = mensajeEnviados;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	private String resultado = null;

	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return this.getMensaje();
		case 1:
			return this.getResultado();
		case 2:
			return this.getMensajeEnviados();
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 3;
	}

	public void getPropertyInfo(int index,
			@SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
		switch (index) {
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "mensaje";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "resultado";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "mensajeEnviados";
			break;
		default:
			break;
		}
	}

	public void setProperty(int index, Object value) {
		switch (index) {
		case 0:
			mensaje = (String) value;
			break;
		case 1:
			resultado = (String) value;
			break;
		case 2:
			mensajeEnviados = (String) value;
			break;
		default:
			break;
		}
	}

}
