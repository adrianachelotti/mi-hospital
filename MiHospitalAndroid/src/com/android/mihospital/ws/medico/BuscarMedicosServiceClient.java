package com.android.mihospital.ws.medico;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.text.TextUtils;

import com.android.mihospital.client.SystemPC_IP;
import com.android.mihospital.dominio.FiltroBusquedaMedico;

public class BuscarMedicosServiceClient {

	String NAMESPACE = "http://medico.ws";
	String URL = SystemPC_IP.ip_pc +"/services/MedicoService?wsdl";  
	String SOAP_ACTION_MEDICOS = "http://medico.ws/obtenerMedicos";
	String SOAP_ACTION_MEDICOS_FRECUENTES = "http://medico.ws/obtenerMedicosMasFrecuentes";
	String METHOD_NAME_MEDICOS = "obtenerMedicos";
	String METHOD_NAME_MEDICOS_FRECUENTES = "obtenerMedicosMasFrecuentes";



	@SuppressWarnings("finally")
	public BusquedaMedicoResult buscarMedicos(FiltroBusquedaMedico filtro){

		BusquedaMedicoResult busquedaResult = new BusquedaMedicoResult();
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MEDICOS);
		BusquedaMedicoRequest busquedaRequest = new  BusquedaMedicoRequest();
		busquedaRequest.setApellido((filtro.getApellido()!=null && !TextUtils.isEmpty(filtro.getApellido()))?filtro.getApellido() : null);
		busquedaRequest.setNombre((filtro.getNombre()!=null && !TextUtils.isEmpty(filtro.getNombre()))?filtro.getNombre() : null);
		busquedaRequest.setDias((filtro.getDias()!=null && !TextUtils.isEmpty(filtro.getDias()))?filtro.getDias() : null);
		busquedaRequest.setEspecialidad((filtro.getEspecialidad()!=null && !TextUtils.isEmpty(filtro.getEspecialidad()))?filtro.getEspecialidad() : null);
		busquedaRequest.setSucursal((filtro.getSucursal().compareTo(Integer.valueOf(0))==0)? null:filtro.getSucursal());
		

		PropertyInfo pPacienteRequest = new PropertyInfo();
		pPacienteRequest.setName("medico");
		pPacienteRequest.setValue(busquedaRequest);
		pPacienteRequest.setType(BusquedaMedicoRequest.class);
		request.addProperty(pPacienteRequest);		
		
	

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.dotNet = true;
		

		envelope.setOutputSoapObject(request);  
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(URL);  
		httpTransport.debug = true;

		try {
			httpTransport.call(SOAP_ACTION_MEDICOS, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarBusquedaMedicosResult(result, busquedaResult);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return busquedaResult;
		}

		

	}




	@SuppressWarnings("finally")
	public BusquedaMedicoResult buscarMedicosMasFrecuentes(Integer idPaciente){

		BusquedaMedicoResult busquedaResult = new BusquedaMedicoResult();


		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_MEDICOS_FRECUENTES);
		
		PropertyInfo paciente = new PropertyInfo();
		paciente.setName("paciente");
		paciente.setValue(idPaciente);
		paciente.setType(Integer.class);
		request.addProperty(paciente);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);  


		HttpTransportSE httpTransport = new HttpTransportSE(URL);  
		httpTransport.debug = true;

		try {
			httpTransport.call(SOAP_ACTION_MEDICOS_FRECUENTES, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarBusquedaMedicosResult(result, busquedaResult);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return busquedaResult;
		}


	}

	private void cargarBusquedaMedicosResult(SoapObject result,
			BusquedaMedicoResult busquedaMedicoResult) {
		busquedaMedicoResult.setProperty(0, (result.getProperty(0)!=null)?result.getProperty(0).toString():null);
		busquedaMedicoResult.setProperty(1,(result.getProperty(1)!=null)?result.getProperty(1).toString():null);
		busquedaMedicoResult.setProperty(2,(result.getProperty(2)!=null)?result.getProperty(2).toString():null);
	}



}
