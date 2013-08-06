package com.android.mihospital.ws.paciente;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;


public class PacienteResult implements KvmSerializable{

	private String mensaje = null;
	private String resultado = null;
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
	private Integer totalMensajesNuevos = null;
	private Integer totalTurnosCancelados = null;
	private String tzfoto=null;
	

	public Object getProperty(int index) {
		switch (index){
		case 0:
			return mensaje;
		case 1:
			return resultado;
		case 2:
			return afiliado;
		case 3:
			return apellido;
		case 4:
			return clave;
		case 5:
			return direccion;
		case 6:
			return fechaNacimiento;
		case 7:
			return id;
		case 8:
			return localidad;
		case 9:
			return mail;
		case 10:
			return nacionalidad;
		case 11:
			return nombre;
		case 12:
			return numeroDocumento;
		case 13:
			return obraSocial;
		case 14:
			return provincia;
		case 15:
			return sexo;
		case 16:
			return telefono;
		case 17:
			return tipoDocumento;
		case 18:
			return totalMensajesNuevos;
		case 19:
			return totalTurnosCancelados;
		case 20:
			return tzfoto;	
		default:
			return null;
		}
	}

	public int getPropertyCount() {
		return 21;
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
			info.type = PropertyInfo.INTEGER_CLASS ;
			info.name = "afiliado";
			break;
		case 3:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "apellido";
			break;
		case 4:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "clave";
			break;
		case 5:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "direccion";
			break;
		case 6:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "fechaNacimiento";
			break;
		case 7:
			info.type = PropertyInfo.INTEGER_CLASS ;
			info.name = "id";
			break;
		case 8:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "localidad";
			break;
		case 9:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "mail";
			break;
		case 10:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "nacionalidad";
			break;
		case 11:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "nombre";
			break;
		case 12:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "numeroDocumento";
			break;
		case 13:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "obraSocial";
			break;
		case 14:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "provincia";
			break;
		case 15:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "sexo";
			break;
		case 16:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "telefono";
			break;
		case 17:
			info.type = PropertyInfo.STRING_CLASS ;
			info.name = "tipoDocumento";
			break;
		case 18:
			info.type = PropertyInfo.INTEGER_CLASS ;
			info.name = "totalMensajesNuevos";			
			break;
		case 19:
			info.type = PropertyInfo.INTEGER_CLASS ;
			info.name = "totalTurnosCancelados";			
			break;
		case 20:
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
			mensaje = (String) value;
			break;
		case 1:
			resultado = (String) value;
			break;
		case 2:
			afiliado = (Integer) value;
			break;
		case 3:
			apellido = (String) value;
			break;
		case 4:
			clave = (String) value;
			break;
		case 5:
			direccion = (String) value;
			break;		
		case 6:
			fechaNacimiento = (String) value;
			break;
		case 7:
			id = (Integer) value;
			break;
		case 8:
			localidad = (String) value;
			break;
		case 9:
			mail = (String) value;
			break;
		case 10:
			nacionalidad = (String) value;
			break;
		case 11:
			nombre = (String) value;
			break;			
		case 12:
			numeroDocumento = (Integer) value;
			break;
		case 13:
			obraSocial = (String) value;
			break;
		case 14:
			provincia = (String) value;
			break;
		case 15:
			sexo = (String) value;
			break;
		case 16:
			telefono = (String) value;
			break;
		case 17:
			tipoDocumento = (String) value;
			break;
		case 18:
			totalMensajesNuevos = (Integer) value;
			break;
		case 19:
			totalTurnosCancelados = (Integer) value;
			break;
		case 20:
			tzfoto = (String) value;
			break;
		default:
			break;
		}
		
	}

	public Integer getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Integer afiliado) {
		this.afiliado = afiliado;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalMensajesNuevos() {
		return totalMensajesNuevos;
	}

	public void setTotalMensajesNuevos(Integer totalMensajesNuevos) {
		this.totalMensajesNuevos = totalMensajesNuevos;
	}

	public Integer getTotalTurnosCancelados() {
		return totalTurnosCancelados;
	}

	public void setTotalTurnosCancelados(Integer totalTurnosCancelados) {
		this.totalTurnosCancelados = totalTurnosCancelados;
	}

	public String getTzfoto() {
		return tzfoto;
	}

	public void setTzfoto(String tzfoto) {
		this.tzfoto = tzfoto;
	}
		
	
	
}
