package com.android.mihospital.ws.mensaje;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.android.mihospital.client.SystemPC_IP;
import com.android.mihospital.dominio.Mensaje;
import com.android.mihospital.ws.ServiceResult;

public class MensajesServiceClient {
	private static final String URL = SystemPC_IP.ip_pc
			+ "/services/MensajeService?wsdl";
	private static final String NAMESPACE = "http://mensajeria.ws";

	String METHOD_NAME_ENVIAR_MENSAJE = "enviarMensajeAlMedico";
	String SOAP_ACTION_ENVIAR_MENSAJE = NAMESPACE + "/"
			+ METHOD_NAME_ENVIAR_MENSAJE;
	String METHOD_NAME_TRAER_MENSAJE = "traerMensajesDelPaciente";
	String SOAP_ACTION_TRAER_MENSAJE = NAMESPACE + "/"
			+ METHOD_NAME_TRAER_MENSAJE;
	String METHOD_NAME_MARCAR_MENSAJE = "marcarMensajeComoLeido";
	String SOAP_ACTION_MARCAR_MENSAJE = NAMESPACE + "/"
			+ METHOD_NAME_MARCAR_MENSAJE;

	public ServiceResult marcarMensajeComoLeido(Integer idMensaje) {

		ServiceResult requestResult = new ServiceResult();
		SoapObject request = new SoapObject(NAMESPACE,
				METHOD_NAME_MARCAR_MENSAJE);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.debug = true;

		PropertyInfo mensaje = new PropertyInfo();
		mensaje.setName("idMensaje");
		mensaje.setValue(idMensaje);
		mensaje.setType(Integer.class);

		request.addProperty(mensaje);
		try {
			httpTransport.call(SOAP_ACTION_MARCAR_MENSAJE, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarResultado(result, requestResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestResult;
	}

	private void cargarResultado(SoapObject result, ServiceResult requestResult) {
		requestResult.setProperty(0, (result.getProperty(0) != null) ? result
				.getProperty(0).toString() : null);
		requestResult.setProperty(1, (result.getProperty(1) != null) ? result
				.getProperty(1).toString() : null);
	}

	public ServiceResult enviarMensajeAlMedico(Mensaje mensaje) {

		ServiceResult requestResult = new ServiceResult();
		SoapObject request = new SoapObject(NAMESPACE,
				METHOD_NAME_ENVIAR_MENSAJE);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.debug = true;

		MensajeRequest mensajeRequest = new MensajeRequest();
		mensajeRequest.setIdMedico(mensaje.getIdMedico());
		mensajeRequest.setIdPaciente(mensaje.getIdPaciente());
		mensajeRequest.setMensaje(mensaje.getMensaje());
		mensajeRequest.setAsunto(mensaje.getAsunto());
		PropertyInfo pMensajeRequest = new PropertyInfo();
		pMensajeRequest.setName("mensaje");
		pMensajeRequest.setValue(mensajeRequest);
		pMensajeRequest.setType(MensajeRequest.class);
		request.addProperty(pMensajeRequest);
		
		try {
			httpTransport.call(SOAP_ACTION_ENVIAR_MENSAJE, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarResultado(result, requestResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestResult;
	}

	public ListaDeMensajeResult traerMensajesDelPaciente(Integer idPaciente,
			Integer idMedico, String estado) {
		ListaDeMensajeResult requestResult = new ListaDeMensajeResult();
		SoapObject request = new SoapObject(NAMESPACE,
				METHOD_NAME_TRAER_MENSAJE);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		PropertyInfo idPacienteProperty = new PropertyInfo();
		idPacienteProperty.setName("idPaciente");
		idPacienteProperty.setValue(idPaciente);
		idPacienteProperty.setType(Integer.class);
		request.addProperty(idPacienteProperty);

		if (idMedico != null) {
			PropertyInfo idMedicoProperty = new PropertyInfo();
			idMedicoProperty.setName("idMedico");
			idMedicoProperty.setValue(idMedico);
			idMedicoProperty.setType(Integer.class);
			request.addProperty(idMedicoProperty);
		}
		if (estado != null) {
			PropertyInfo estadoProperty = new PropertyInfo();
			estadoProperty.setName("estado");
			estadoProperty.setValue(estado);
			estadoProperty.setType(String.class);
			request.addProperty(estadoProperty);
		}
		envelope.setOutputSoapObject(request);
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.debug = true;
		try {
			httpTransport.call(SOAP_ACTION_TRAER_MENSAJE, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarResultadoMensajes(result, requestResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestResult;
	}

	private void cargarResultadoMensajes(SoapObject result,
			ListaDeMensajeResult requestResult) {
		requestResult.setProperty(0, (result.getProperty(0) != null) ? result
				.getProperty(0).toString() : null);
		requestResult.setProperty(1, (result.getProperty(1) != null) ? result
				.getProperty(1).toString() : null);
		requestResult.setProperty(2, (result.getProperty(2) != null) ? result
				.getProperty(2).toString() : null);
	}
}
