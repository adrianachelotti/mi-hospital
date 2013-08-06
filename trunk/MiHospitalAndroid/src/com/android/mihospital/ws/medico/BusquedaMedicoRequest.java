package com.android.mihospital.ws.medico;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class BusquedaMedicoRequest implements KvmSerializable{


	private String apellido = null;
	private String nombre = null;
	private String dias =null;
	private String especialidad = null;
	private Integer sucursal = null;
	
	public Object getProperty(int index) {
		switch (index){
		case 0:
			return apellido;
		case 1:
			return dias;
		case 2:
			return especialidad;
		case 3:
			return nombre;
		case 4:
			return sucursal;
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 5;
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index){
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "apellido";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "dias";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "especialidad";
			break;
		case 3:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "nombre";
			break;
		case 4:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "sucursal";
			break;
				default:
			break;
		}
	}

	public void setProperty(int index, Object value) {
		
		switch (index){
		case 0:
			apellido = (String) value;
			break;
		case 1:
			dias = (String) value;
			break;
		case 2:
			especialidad = (String) value;
			break;
		case 3:
			nombre = (String) value;
			break;		
		case 4:
			sucursal = (Integer) value;
			break;
		default:
			break;
		}
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

		
		
}
