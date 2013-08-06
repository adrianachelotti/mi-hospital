package com.android.mihospital.ws.medico;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;


public class MedicoResult implements KvmSerializable{

	
	
	private String mensaje;
	private String resultado;
	private Integer id;
	private String apellido;	
	private Integer duracionTurno;
	private String especialidad;
	private String foto;
	private String horariosLugaresAtencion;
	private String mail;
	private String nombre;	
	private String sucursales;

	public Object getProperty(int index) {
		switch (index){
		case 0:
			return mensaje;
		case 1:
			return resultado;		
		case 2:		
			return apellido;		
		case 3:
			return duracionTurno;
		case 4:
			return especialidad;
		case 5:
			return foto;		
		case 6:
			return horariosLugaresAtencion;
		case 7:
			return id;
		case 8:
			return mail;
		case 9:
			return nombre;
		case 10:
			return sucursales;
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 11;
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
			info.name = "apeliido";
			break;
		case 3:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "duracionTurno";
			break;
		case 4:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "especialidad";
			break;
		case 5:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "foto";
			break;
		case 6:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "horariosLugaresAtencion";
			break;
		case 7:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "id";
			break;
		case 8:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "mail";
			break;
		case 9:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "nombre";
			break;
		case 10:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "sucursales";
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
			apellido = (String) value;
			break;
		case 3:
			duracionTurno = (Integer) value;
			break;
		case 4:
			especialidad = (String) value;
			break;		
		case 5:
			foto = (String) value;
			break;
		case 6:
			horariosLugaresAtencion = (String) value;
			break;			
		case 7:
			id = (Integer) value;
			break;
		case 8:
			mail = (String) value;
			break;
		case 9:
			nombre = (String) value;
			break;
		case 10:
			sucursales = (String) value;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDuracionTurno() {
		return duracionTurno;
	}

	public void setDuracionTurno(Integer duracionTurno) {
		this.duracionTurno = duracionTurno;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getHorariosLugaresAtencion() {
		return horariosLugaresAtencion;
	}

	public void setHorariosLugaresAtencion(String horariosLugaresAtencion) {
		this.horariosLugaresAtencion = horariosLugaresAtencion;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSucursales() {
		return sucursales;
	}

	public void setSucursales(String sucursales) {
		this.sucursales = sucursales;
	}

	
}
