package com.android.mihospital.turnos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Turno implements Parcelable {

	Integer id;
	String dia;
	String diaFin;
	String nombreMedico ;
	String especialidad;
	Integer sucursal;
	Integer duracionTurnos;
	String estado;
	

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Turno createFromParcel(Parcel in) {
			return new Turno(in);
		}

		public Turno[] newArray(int size) {
			return new Turno[size];
		}
	};

	public Turno(Integer id, String dia, String diaFin){
		this.dia = dia;
		this.id = id;
		this.diaFin = diaFin;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date=null;
		Date dateFin=null;
		try {
			date = format.parse(dia);
			dateFin = format.parse(diaFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long diff = dateFin.getTime() - date.getTime();        
        long diffMinutes = diff / (60 * 1000);   
		this.duracionTurnos = (int) diffMinutes;
	}
	public Turno(Parcel in) {
		this.readFromParcel(in);
	}

	private void readFromParcel(Parcel in) {
		id = in.readInt();
		dia = in.readString();
		diaFin=in.readString();
		duracionTurnos = in.readInt();
		estado = in.readString();
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeInt(id);
		arg0.writeString(dia);
		arg0.writeString(diaFin);
		arg0.writeInt(duracionTurnos);
		arg0.writeString(estado);

	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDiaFin() {
		return diaFin;
	}
	public void setDiaFin(String diaFin) {
		this.diaFin = diaFin;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
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
	public Integer getDuracionTurnos() {
		return duracionTurnos;
	}
	public void setDuracionTurnos(Integer duracionTurnos) {
		this.duracionTurnos = duracionTurnos;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado=estado;
	}
}
