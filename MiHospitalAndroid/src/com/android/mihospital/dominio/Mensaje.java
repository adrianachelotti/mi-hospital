package com.android.mihospital.dominio;

import java.util.Date;

public class Mensaje {

	public Mensaje() {
		super();
		this.setEstado("");
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIdPaciente() {
		return IdPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		IdPaciente = idPaciente;
	}

	public Integer getIdMedico() {
		return IdMedico;
	}

	public void setIdMedico(Integer idMedico) {
		IdMedico = idMedico;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Integer id) {
		idMensaje = id;
	}

	private Integer idMensaje;
	private String asunto;
	private Date fecha;
	private String mensaje;
	private String estado;
	private Integer IdPaciente;
	private Integer IdMedico;
	private String emisor;
	public void setEmisor(String string) {
		emisor=string;
	}
	
	public String getEmisor(){
		return emisor;
	}
}
