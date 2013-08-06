package com.android.mihospital.dominio;

public class Estudio {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public int getTipoDeEstudio() {
		return tipoDeEstudio;
	}

	public void setTipoDeEstudio(int tipoDeEstudio) {
		this.tipoDeEstudio = tipoDeEstudio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	int id;
	int idDepartamento;
	int tipoDeEstudio;
	String fecha;
	String descripcion;
}
