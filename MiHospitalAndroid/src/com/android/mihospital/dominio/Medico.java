package com.android.mihospital.dominio;

import org.kobjects.base64.Base64;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.mihospital.ws.medico.MedicoResult;

public class Medico implements Parcelable {

	private Integer id;
	private String nombre;
	private String apellido;

	private String especialidad;

	private String mail;

	private byte[] foto;
	private String horariosLugaresAtencion;
	private Integer duracionTurno;
	private String sucursales;
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Medico createFromParcel(Parcel in) {
			return new Medico(in);
		}

		public Medico[] newArray(int size) {
			return new Medico[size];
		}
	};

	public Medico(Parcel in) {
		this.readFromParcel(in);
	}

	public Medico(Integer id, String nombre, String apellido) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Medico(MedicoResult medicoResult) {
		id = medicoResult.getId();
		nombre = medicoResult.getNombre();
		apellido = medicoResult.getApellido();
		especialidad = medicoResult.getEspecialidad();

		mail = medicoResult.getMail();
		if (medicoResult.getFoto() != null)
			foto = Base64.decode(medicoResult.getFoto());
		horariosLugaresAtencion = medicoResult.getHorariosLugaresAtencion();
		duracionTurno = medicoResult.getDuracionTurno();
		sucursales = medicoResult.getSucursales();

	}

	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeInt(id);
		dest.writeString(nombre);
		dest.writeString(apellido);
		dest.writeString(especialidad);
		dest.writeString(mail);
		dest.writeString(horariosLugaresAtencion);
		dest.writeInt(duracionTurno);
		if (foto != null) {
			dest.writeString("fotoCargada");
			dest.writeInt(foto.length);
			dest.writeByteArray(foto);
		} else {
			dest.writeString("fotoNoCargada");
		}

		dest.writeString(sucursales);

	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		nombre = in.readString();
		apellido = in.readString();
		especialidad = in.readString();
		mail = in.readString();
		horariosLugaresAtencion = in.readString();
		duracionTurno = in.readInt();
		String hayFotoCargada = in.readString();
		if (hayFotoCargada.equalsIgnoreCase("fotoCargada")) {
			this.foto = new byte[in.readInt()];
			in.readByteArray(foto);
		}
		sucursales = in.readString();

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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int describeContents() {
		return 0;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getHorariosLugaresAtencion() {
		return horariosLugaresAtencion;
	}

	public void setHorariosLugaresAtencion(String horariosLugaresAtencion) {
		this.horariosLugaresAtencion = horariosLugaresAtencion;
	}

	public Integer getDuracionTurno() {
		return duracionTurno;
	}

	public void setDuracionTurno(Integer duracionTurno) {
		this.duracionTurno = duracionTurno;
	}

	public String getSucursales() {
		return sucursales;
	}

	public void setSucursales(String sucursales) {
		this.sucursales = sucursales;
	}

	public void setId(int id) {
		this.id = id;
	}
	
public String toString(){
	return apellido + " " + nombre;
	
}
}
