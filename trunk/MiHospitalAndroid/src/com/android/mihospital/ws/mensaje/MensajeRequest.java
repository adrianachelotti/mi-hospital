package com.android.mihospital.ws.mensaje;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class MensajeRequest implements KvmSerializable {

	private String asunto = null;
	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	private Integer idMedico = null;
	private Integer idPaciente = null;
	private String mensaje = null;
	
	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return asunto;
		case 1:
			return idMedico;
		case 2:
			return idPaciente;
		case 3:
			return mensaje;
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 4;
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index) {
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "asunto";
			break;
		case 1:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "idMedico";
			break;
		case 2:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "idPaciente";
			break;
		case 3:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "mensaje";
			break;
		default:
			break;
		}
	}

	public void setProperty(int index, Object value) {

		switch (index) {
		case 0:
			asunto = (String) value;
			break;
		case 1:
			idMedico = (Integer) value;
			break;
		case 2:
			idPaciente = (Integer) value;
			break;
		case 3:
			mensaje = (String) value;
			break;
		default:
			break;
		}
	}

	public String getFecha() {
		return asunto;
	}

	public void setFecha(String fecha) {
		this.asunto = fecha;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
