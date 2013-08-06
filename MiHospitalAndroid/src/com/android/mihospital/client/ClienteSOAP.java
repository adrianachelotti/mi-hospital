package com.android.mihospital.client;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.PropertyInfo;

import com.android.mihospital.dominio.FiltroBusquedaMedico;
import com.android.mihospital.dominio.Mensaje;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.ws.GeneralSoapService;
import com.android.mihospital.ws.LoginServiceClient;
import com.android.mihospital.ws.ServiceResult;
import com.android.mihospital.ws.medico.BuscarMedicosServiceClient;
import com.android.mihospital.ws.medico.BusquedaMedicoResult;
import com.android.mihospital.ws.medico.MedicoResult;
import com.android.mihospital.ws.medico.ObtenerMedicoByIdServiceClient;
import com.android.mihospital.ws.mensaje.ListaDeMensajeResult;
import com.android.mihospital.ws.mensaje.MensajesServiceClient;
import com.android.mihospital.ws.paciente.PacienteResult;
import com.android.mihospital.ws.paciente.PacienteServiceClient;
import com.android.mihospital.ws.turnos.BuscarTurnosServiceClient;
import com.android.mihospital.ws.turnos.ListadoDeTurnosResult;

public class ClienteSOAP implements HospistalClient {

	private static final String ESTUDIO_URL = SystemPC_IP.ip_pc
			+ "/services/EstudioMedicoService?wsdl";
	private static final String ESTUDIO_NAMESPACE = "http://estudios.ws";
	private static final String DETALLE_ESTUDIO = "obtenerDetalleEstudio";
	private static final String DETALLE_ESTUDIO_ACTION = ESTUDIO_NAMESPACE + "/"
			+ DETALLE_ESTUDIO;
	private static final String LISTA_ESTUDIOS = "obtenerEstudios";
	private static final String LISTA_ESTUDIOS_ACTION = ESTUDIO_NAMESPACE + "/"
			+ LISTA_ESTUDIOS;

	private static final String RECETA_URL = SystemPC_IP.ip_pc
			+ "/services/MedicamentoService?wsdl";
	private static final String RECETA_NAMESPACE = "http://medicamentos.ws";
	private static final String DETALLE_RECETA = "obtenerDetalleMedicamento";
	private static final String DETALLE_RECETA_ACTION = RECETA_NAMESPACE + "/"
			+ DETALLE_RECETA;
	private static final String LISTA_RECETAS ="obtenerMedicamentos";
	private static final String LISTA_RECETAS_ACTION = RECETA_NAMESPACE + "/"
			+ LISTA_RECETAS;

	private LoginServiceClient servicioLogin;
	private PacienteServiceClient servicioPaciente;
	private ObtenerMedicoByIdServiceClient servicioObtenerMedico;
	private BuscarMedicosServiceClient servicioBusquedaMedicos;
	private BuscarTurnosServiceClient servicioBusquedaTurnos;
	private MensajesServiceClient servicioMensajes;
	private GeneralSoapService generalSoapCaller;
	public PacienteResult validar(Paciente paciente) {
		initLoginService();
		return servicioLogin.validar(paciente);
	}

	private void initLoginService() {
		if (servicioLogin == null)
			servicioLogin = new LoginServiceClient();
	}

	public ServiceResult actualizarPaciente(Paciente paciente) {
		initPacienteService();
		return servicioPaciente.actualizarPaciente(paciente);
	}

	public ServiceResult registrarPaciente(Paciente paciente) {
		initPacienteService();
		return servicioPaciente.registrarPaciente(paciente);
	}

	public PacienteResult obtenerPaciente(Paciente paciente) {
		initPacienteService();
		return servicioPaciente.obtenerPaciente(paciente);
	}

	private void initPacienteService() {
		if (servicioPaciente == null) {
			servicioPaciente = new PacienteServiceClient();
		}
	}
	
	private void initMensajesService() {
		if (servicioMensajes == null) {
			servicioMensajes = new MensajesServiceClient();
		}
	}
	public BusquedaMedicoResult buscarMedicos(String apellido, String nombre,
			String especialidad, Integer sucursal, String dias) {
		initBuscarMedicoService();
		FiltroBusquedaMedico filtro = new FiltroBusquedaMedico();
		filtro.setApellido(apellido);
		filtro.setNombre(nombre);
		filtro.setEspecialidad(especialidad);
		filtro.setSucursal(sucursal);
		filtro.setDias(dias);
		return servicioBusquedaMedicos.buscarMedicos(filtro);
	}

	public BusquedaMedicoResult buscarMedicosMasFrecuentes(Integer idPaciente) {
		initBuscarMedicoService();

		return servicioBusquedaMedicos.buscarMedicosMasFrecuentes(idPaciente);
	}

	private void initBuscarMedicoService() {
		if (servicioBusquedaMedicos == null)
			servicioBusquedaMedicos = new BuscarMedicosServiceClient();
	}

	private void initObtenerMedicoById() {
		if (servicioObtenerMedico == null)
			servicioObtenerMedico = new ObtenerMedicoByIdServiceClient();
	}

	public MedicoResult obtenerMedicoById(int id) {
		initObtenerMedicoById();
		return servicioObtenerMedico.buscarMedicoById(id);

	}

	public ListadoDeTurnosResult obtenerTurnosDisponibles(Integer idPaciente,
			Integer idMedico, Integer idSucursal, String date) {
		initBuscarTurnosService();
		return servicioBusquedaTurnos.buscarTurnosDisponibles(idPaciente,
				idMedico, idSucursal, date);
	}

	private void initBuscarTurnosService() {
		if (servicioBusquedaTurnos == null)
			servicioBusquedaTurnos = new BuscarTurnosServiceClient();
	}

	public ServiceResult reservarTurno(Integer idTurno, Integer idPaciente) {
		initBuscarTurnosService();
		return servicioBusquedaTurnos.reservarTurno(idTurno, idPaciente);

	}

	public ListadoDeTurnosResult buscarHistorialDeTurnos(Integer idPaciente) {
		initBuscarTurnosService();
		return servicioBusquedaTurnos.buscarHistorialDeTurnos(idPaciente);

	}

	public ServiceResult marcarMensajeComoLeido(Integer idMensaje) {
		initMensajesService();
		return servicioMensajes.marcarMensajeComoLeido(idMensaje);
	}

	public ServiceResult enviarMensaje(Mensaje mensaje) {
		initMensajesService();
		return servicioMensajes.enviarMensajeAlMedico(mensaje);
	}

	public ListaDeMensajeResult traerMensajesDelPaciente(Integer idPaciente,
			Integer idMedico,
			String estado) {
		initMensajesService();
		return servicioMensajes.traerMensajesDelPaciente(idPaciente,idMedico,estado);
	}

	public ServiceResult cancelarTurno(Integer id) {
		initBuscarTurnosService();
		return servicioBusquedaTurnos.cancelarTurno(id);
	}

	private void initGeneralSoapCaller(){
		if(generalSoapCaller==null)
			generalSoapCaller=new GeneralSoapService();
	}
	
	public ServiceResult obtenerEstudios(Integer idPaciente) {
		initGeneralSoapCaller();

		PropertyInfo pId = new PropertyInfo();
		pId.setName("idPaciente");
		pId.setValue(idPaciente);
		pId.setType(Integer.class);

		List<PropertyInfo> propertyList = new ArrayList<PropertyInfo>();
		propertyList.add(pId);
		return generalSoapCaller.makeSoapRequest(ESTUDIO_URL,ESTUDIO_NAMESPACE,LISTA_ESTUDIOS,LISTA_ESTUDIOS_ACTION,propertyList);

	}
	
	public ServiceResult obtenerDetalleEstudio(Integer id) {
		initGeneralSoapCaller();
		
		PropertyInfo pId = new PropertyInfo();
		pId.setName("idEstudio");
		pId.setValue(id);
		pId.setType(Integer.class);

		List<PropertyInfo> propertyList = new ArrayList<PropertyInfo>();
		propertyList.add(pId);
		return generalSoapCaller.makeSoapRequest(ESTUDIO_URL,ESTUDIO_NAMESPACE,DETALLE_ESTUDIO,DETALLE_ESTUDIO_ACTION,propertyList);
	}

	public ServiceResult obtenerRecetas(Integer idPaciente) {
		initGeneralSoapCaller();

		PropertyInfo pId = new PropertyInfo();
		pId.setName("idPaciente");
		pId.setValue(idPaciente);
		pId.setType(Integer.class);

		List<PropertyInfo> propertyList = new ArrayList<PropertyInfo>();
		propertyList.add(pId);
		return generalSoapCaller.makeSoapRequest(RECETA_URL,RECETA_NAMESPACE,LISTA_RECETAS,LISTA_RECETAS_ACTION,propertyList);

	}
	
	public ServiceResult obtenerDetalleReceta(Integer id) {
		initGeneralSoapCaller();

		PropertyInfo pId = new PropertyInfo();
		pId.setName("idReceta");
		pId.setValue(id);
		pId.setType(Integer.class);

		List<PropertyInfo> propertyList = new ArrayList<PropertyInfo>();
		propertyList.add(pId);
		return generalSoapCaller.makeSoapRequest(RECETA_URL,RECETA_NAMESPACE,DETALLE_RECETA,DETALLE_RECETA_ACTION,propertyList);
	}

}
