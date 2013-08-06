package com.android.mihospital.dominio;


import org.kobjects.base64.Base64;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.mihospital.ws.paciente.PacienteResult;

/**
 * Clase que modela un paciente
 * Grupo 2
 * @author Adriana
 *
 */
public class Paciente implements Parcelable{


	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Paciente createFromParcel(Parcel in) {
			return new Paciente(in);
		}

		public Paciente[] newArray(int size) {
			return new Paciente[size];
		}
	};
	private boolean valido; 

	public Paciente(Parcel in) {
		this.readFromParcel(in);
	}

	public Paciente(Integer dni, String clave){
		this.numeroDocumento =dni;
		this.clave = clave;
	}

	public Paciente(PacienteResult pacienteResult){
		this.setId(pacienteResult.getId());
		this.setNombre(pacienteResult.getNombre());
		this.setApellido(pacienteResult.getApellido());
		this.setClave(pacienteResult.getClave());
		this.setTipoDocumento(pacienteResult.getTipoDocumento());
		this.setNumeroDocumento(pacienteResult.getNumeroDocumento());
		this.setAfiliado(pacienteResult.getAfiliado());
		this.setObraSocial(pacienteResult.getObraSocial());
		this.setMail(pacienteResult.getMail());
		this.setTelefono(pacienteResult.getTelefono());
		this.setDireccion(pacienteResult.getDireccion());
		this.setLocalidad(pacienteResult.getLocalidad());
		this.setProvincia(pacienteResult.getProvincia());
		this.setSexo(pacienteResult.getSexo());
		this.setFechaNacimiento(pacienteResult.getFechaNacimiento());
		this.setNacionalidad(pacienteResult.getNacionalidad());
		this.setTotalMensajesNuevos(pacienteResult.getTotalMensajesNuevos());
		this.setTotalTurnosCancelados(pacienteResult.getTotalTurnosCancelados());
		if (pacienteResult.getTzfoto() != null)
			foto = Base64.decode(pacienteResult.getTzfoto());
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	private Integer id;
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private Integer numeroDocumento;
	private String nacionalidad;
	private byte[] foto;
	private Integer afiliado;
	private String telefono;
	private String mail;
	private String clave;
	private String obraSocial;
	private String pais;

	private String direccion;
	private String provincia;
	private String localidad;
	private String fechaNacimiento;
	private String sexo;
	private Integer totalMensajesNuevos ;
	private Integer totalTurnosCancelados ;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public Integer getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Integer afiliado) {
		this.afiliado = afiliado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int arg1) {
		if(id!=null) 
			dest.writeInt( id);
		dest.writeString(nombre);
		dest.writeString(apellido);
		dest.writeInt(numeroDocumento);
		dest.writeString(nacionalidad);
		dest.writeString(telefono);
		dest.writeInt(afiliado);
		dest.writeString(obraSocial);
		dest.writeString(mail);
		dest.writeString(clave);
		dest.writeString(direccion);
		dest.writeString(provincia);
		dest.writeString(localidad);
		dest.writeString(sexo);
		dest.writeString(fechaNacimiento);
		dest.writeInt(totalMensajesNuevos);
		dest.writeInt(totalTurnosCancelados);
		if (foto != null) {
			dest.writeString("fotoCargada");
			dest.writeInt(foto.length);
			dest.writeByteArray(foto);
		} else {
			dest.writeString("fotoNoCargada");
		}
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		nombre= in.readString();
		apellido= in.readString();
		numeroDocumento= in.readInt();
		nacionalidad= in.readString();
		telefono= in.readString();
		afiliado= in.readInt();
		obraSocial = in.readString();
		mail= in.readString();
		clave= in.readString();
		direccion= in.readString();
		provincia= in.readString();
		localidad= in.readString();
		sexo= in.readString();
		fechaNacimiento= in.readString();
		totalMensajesNuevos= in.readInt();
		totalTurnosCancelados=in.readInt();
		String hayFotoCargada = in.readString();
		if (hayFotoCargada.equalsIgnoreCase("fotoCargada")) {
			this.foto = new byte[in.readInt()];
			in.readByteArray(foto);
		}
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
