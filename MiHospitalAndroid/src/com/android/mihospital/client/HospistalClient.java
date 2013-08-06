package com.android.mihospital.client;

import com.android.mihospital.dominio.Mensaje;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.ws.ServiceResult;
import com.android.mihospital.ws.medico.BusquedaMedicoResult;
import com.android.mihospital.ws.medico.MedicoResult;
import com.android.mihospital.ws.mensaje.ListaDeMensajeResult;
import com.android.mihospital.ws.paciente.PacienteResult;
import com.android.mihospital.ws.turnos.ListadoDeTurnosResult;

public interface HospistalClient {
	
	public BusquedaMedicoResult buscarMedicos(String apellido, String nombre, String especialidad, Integer sucursal, String dias);
	
	public BusquedaMedicoResult buscarMedicosMasFrecuentes(Integer idPaciente);
	
	public PacienteResult validar(Paciente paciente);
	
	public ServiceResult actualizarPaciente(Paciente paciente);
	
	public ServiceResult registrarPaciente(Paciente paciente);
	
	public PacienteResult obtenerPaciente(Paciente paciente);
	
	public MedicoResult obtenerMedicoById(int idMedico); 
	
	public ListadoDeTurnosResult obtenerTurnosDisponibles(Integer idPaciente, Integer idMedico, Integer idSucursal, String date);
	
	public ServiceResult reservarTurno(Integer idTurno, Integer idPaciente);
	
	public ListadoDeTurnosResult buscarHistorialDeTurnos(Integer idPaciente);
	
	public ServiceResult marcarMensajeComoLeido(Integer id);
	
	public ServiceResult enviarMensaje(Mensaje mensaje);
	
	public ListaDeMensajeResult traerMensajesDelPaciente(Integer idPaciente,
			Integer integer, String estado);

	public ServiceResult cancelarTurno(Integer id);

	public ServiceResult obtenerDetalleEstudio(Integer id);

	public ServiceResult obtenerDetalleReceta(Integer id);

	ServiceResult obtenerRecetas(Integer idPaciente);

	ServiceResult obtenerEstudios(Integer idPaciente);
	
}
