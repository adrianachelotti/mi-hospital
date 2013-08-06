package com.android.mihospital.ws.paciente;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class PacienteRequest implements KvmSerializable{

	private Integer afiliado = null;
	private String apellido = null;
	private String clave = null;
	private String direccion = null;
	private String fechaNacimiento = null;
	private Integer id = null;
	private String localidad = null;
	private String mail = null;
	private String nacionalidad = null;
	private String nombre = null;
	private Integer numeroDocumento = null;
	private String obraSocial = null;
	private String provincia = null;
	private String sexo = null;
	private String telefono = null;
	private String tipoDocumento = null;
	private String tzFoto = null;

	public Object getProperty(int index) {
		switch (index){
		case 0:
			return afiliado;
		case 1:
			return apellido;
		case 2:
			return clave;
		case 3:
			return direccion;
		case 4:
			return fechaNacimiento;
		case 5:
			return id;
		case 6:
			return localidad;
		case 7:
			return mail;
		case 8:
			return nacionalidad;
		case 9:
			return nombre;
		case 10:
			return numeroDocumento;
		case 11:
			return obraSocial;
		case 12:
			return provincia;
		case 13:
			return sexo;
		case 14:
			return telefono;
		case 15:
			return tipoDocumento;
		case 16:
			return tzFoto;
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 17;
	}

	@SuppressWarnings("rawtypes")
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index){
		case 0:
			info.type = PropertyInfo.INTEGER_CLASS ;
			info.name = "afiliado";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "apellido";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "clave";
			break;
		case 3:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "direccion";
			break;
		case 4:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "fechaNacimiento";
			break;
		case 5:
			info.type = PropertyInfo.INTEGER_CLASS ;
			info.name = "id";
			break;
		case 6:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "localidad";
			break;
		case 7:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "mail";
			break;
		case 8:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "nacionalidad";
			break;
		case 9:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "nombre";
			break;
		case 10:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "numeroDocumento";
			break;
		case 11:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "obraSocial";
			break;
		case 12:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "provincia";
			break;
		case 13:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "sexo";
			break;
		case 14:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "telefono";
			break;
		case 15:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "tipoDocumento";
			break;
		case 16:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "tzfoto";
			break;
		default:
			break;
		}
	}

	public void setProperty(int index, Object value) {
		
		switch (index){
		case 0:
			afiliado = (Integer) value;
			break;
		case 1:
			apellido = (String) value;
			break;
		case 2:
			clave = (String) value;
			break;
		case 3:
			direccion = (String) value;
			break;		
		case 4:
			fechaNacimiento = (String) value;
			break;
		case 5:
			id = (Integer) value;
			break;
		case 6:
			localidad = (String) value;
			break;
		case 7:
			mail = (String) value;
			break;
		case 8:
			nacionalidad = (String) value;
			break;
		case 9:
			nombre = (String) value;
			break;			
		case 10:
			numeroDocumento = (Integer) value;
			break;
		case 11:
			obraSocial = (String) value;
			break;
		case 12:
			provincia = (String) value;
			break;
		case 13:
			sexo = (String) value;
			break;
		case 14:
			telefono = (String) value;
			break;
		case 15:
			tipoDocumento = (String) value;
			break;
		case 16:
			tzFoto = (String) value;
			break;
		default:
			break;
		}
	}

	public void setAfiliado(Integer afiliado) {
		this.afiliado = afiliado;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getTzFoto() {
		return tzFoto;
	}

	public void setTzFoto(String tzFoto) {
		this.tzFoto = tzFoto;
	}		
}
