package com.android.mihospital.ws.turnos;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.android.mihospital.client.SystemPC_IP;
import com.android.mihospital.ws.ServiceResult;

public class BuscarTurnosServiceClient {

	String NAMESPACE = "http://turnos.ws";
	String URL = SystemPC_IP.ip_pc + "/services/TurnoService?wsdl";
	String SOAP_ACTION_TURNO_RESERVA = "http://turnos.ws/reservarTurno";
	String SOAP_ACTION_TURNO_CANCELAR = "http://turnos.ws/cancelarTurno";
	String SOAP_ACTION_TURNO_HISTORIAL = "http://turnos.ws/obtenerHistorialDeTurnos";
	String SOAP_ACTION_TURNOS_DISPONIBLES = "http://turnos.ws/obtenerTurnosDisponibles";
	String METHOD_NAME_TURNOS = "obtenerTurnosDisponibles";
	String METHOD_NAME_RESERVA_TURNOS = "reservarTurno";
	String METHOD_NAME_CANCELAR_TURNOS = "cancelarTurno";
	String METHOD_NAME_HISTORIAL_TURNOS = "obtenerHistorialDeTurnos";

	@SuppressWarnings("finally")
	public ListadoDeTurnosResult buscarTurnosDisponibles(Integer idPaciente,
			Integer idMedico, Integer idSucursal, String date) {

		ListadoDeTurnosResult busquedaResult = new ListadoDeTurnosResult();
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_TURNOS);
		TurnoRequest turnoRequest = new TurnoRequest();
		turnoRequest.setIdMedico(idMedico);
		turnoRequest.setIdPaciente(idPaciente);
		turnoRequest.setIdSucursal(idSucursal);
		turnoRequest.setFecha(date);
		PropertyInfo pTurnoeRequest = new PropertyInfo();
		pTurnoeRequest.setName("turnoRequest");
		pTurnoeRequest.setValue(turnoRequest);
		pTurnoeRequest.setType(TurnoRequest.class);
		request.addProperty(pTurnoeRequest);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.debug = true;

		try {
			httpTransport.call(SOAP_ACTION_TURNOS_DISPONIBLES, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarBusquedaTurnosResult(result, busquedaResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return busquedaResult;
		}
	}

	public ListadoDeTurnosResult buscarHistorialDeTurnos(Integer idPaciente) {

		ListadoDeTurnosResult busquedaResult = new ListadoDeTurnosResult();
		SoapObject request = new SoapObject(NAMESPACE,
				METHOD_NAME_HISTORIAL_TURNOS);

		PropertyInfo idPacienteProperty = new PropertyInfo();
		idPacienteProperty.setName("idPaciente");
		idPacienteProperty.setValue(idPaciente);
		idPacienteProperty.setType(Integer.class);
		request.addProperty(idPacienteProperty);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);

		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.debug = true;

		try {
			httpTransport.call(SOAP_ACTION_TURNO_HISTORIAL, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarBusquedaTurnosResult(result, busquedaResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return busquedaResult;
		}
	}

	@SuppressWarnings("finally")
	public ServiceResult reservarTurno(Integer idTurno, Integer idPaciente) {

		ServiceResult serviceResult = new ServiceResult();
		SoapObject request = new SoapObject(NAMESPACE,
				METHOD_NAME_RESERVA_TURNOS);

		PropertyInfo idTurnoProperty = new PropertyInfo();
		idTurnoProperty.setName("idTurno");
		idTurnoProperty.setValue(idTurno);
		idTurnoProperty.setType(Integer.class);
		request.addProperty(idTurnoProperty);

		PropertyInfo idPacienteProperty = new PropertyInfo();
		idPacienteProperty.setName("idPaciente");
		idPacienteProperty.setValue(idPaciente);
		idPacienteProperty.setType(Integer.class);
		request.addProperty(idPacienteProperty);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.debug = true;

		try {
			httpTransport.call(SOAP_ACTION_TURNO_RESERVA, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			reservarTurnoResult(result, serviceResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return serviceResult;
		}

	}

	@SuppressWarnings("finally")
	public ServiceResult cancelarTurno(Integer idTurno) {

		ServiceResult serviceResult = new ServiceResult();
		SoapObject request = new SoapObject(NAMESPACE,
				METHOD_NAME_CANCELAR_TURNOS);

		PropertyInfo idTurnoProperty = new PropertyInfo();
		idTurnoProperty.setName("idTurno");
		idTurnoProperty.setValue(idTurno);
		idTurnoProperty.setType(Integer.class);
		request.addProperty(idTurnoProperty);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.debug = true;

		try {
			httpTransport.call(SOAP_ACTION_TURNO_CANCELAR, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			reservarTurnoResult(result, serviceResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return serviceResult;
		}

	}
	
	private void reservarTurnoResult(SoapObject result,
			ServiceResult serviceResult) {
		serviceResult.setProperty(0, (result.getProperty(0) != null) ? result
				.getProperty(0).toString() : null);
		serviceResult.setProperty(1, (result.getProperty(1) != null) ? result
				.getProperty(1).toString() : null);

	}

	private void cargarBusquedaTurnosResult(SoapObject result,
			ListadoDeTurnosResult busquedaTurnoResult) {
		busquedaTurnoResult.setProperty(0,
				(result.getProperty(0) != null) ? result.getProperty(0)
						.toString() : null);
		busquedaTurnoResult.setProperty(1,
				(result.getProperty(1) != null) ? result.getProperty(1)
						.toString() : null);
		busquedaTurnoResult.setProperty(2,
				(result.getProperty(2) != null) ? result.getProperty(2)
						.toString() : null);
	}

}
