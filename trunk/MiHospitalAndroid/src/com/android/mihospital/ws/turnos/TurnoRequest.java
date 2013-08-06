package com.android.mihospital.ws.turnos;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class TurnoRequest implements KvmSerializable{


	private String fecha =null;
	private Integer idMedico = null;
	private Integer idPaciente =null;
	private Integer idSucursal = null;
	
	public Object getProperty(int index) {
		switch (index){
		case 0:
			return fecha;
		case 1:
			return idMedico; 
		case 2:
			return idPaciente;
		case 3:
			return idSucursal;
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 4;
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index){
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "fecha";
			break;
		case 1:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "idMedico";
			break;
		case 2:
			info.type = PropertyInfo.INTEGER_CLASS ;
			info.name = "idPaciente";
			break;
		case 3:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "idSucursal";
			break;
		default:
			break;
		}
	}

	public void setProperty(int index, Object value) {
		
		switch (index){
		case 0:
			fecha = (String) value;
			break;
		case 1:
			idMedico = (Integer) value;
			break;
		case 2:
			idPaciente = (Integer) value;
			break;
		case 3:
			idSucursal = (Integer) value;
			break;		
		default:
			break;
		}
	}

	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
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

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer sucursal) {
		this.idSucursal = sucursal;
	}

		
}
