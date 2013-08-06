package com.android.mihospital.dominio;

import android.os.Parcel;
import android.os.Parcelable;


public class FiltroBusquedaMedico  implements Parcelable{

	String nombre =null;
	String apellido=null ;
	String especialidad =null;
	Integer sucursal =null;
	String dias= null;
	
	

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public FiltroBusquedaMedico createFromParcel(Parcel in) {
			return new FiltroBusquedaMedico(in);
		}

		public FiltroBusquedaMedico[] newArray(int size) {
			return new FiltroBusquedaMedico[size];
		}
	};
	
	public FiltroBusquedaMedico(){
		
	}
	public  FiltroBusquedaMedico(Parcel in) {
		this.readFromParcel(in);
	}
	

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nombre);
		dest.writeString(apellido);
		dest.writeString(especialidad);
		dest.writeInt(sucursal);
		dest.writeString(dias);		
	}
	
	private void readFromParcel(Parcel in) {
	
		nombre= in.readString();
		apellido= in.readString();
		especialidad = in.readString();
		sucursal =  in.readInt();
		dias = in.readString();
		
	}

	public int describeContents() {
			return 0;
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

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

}
