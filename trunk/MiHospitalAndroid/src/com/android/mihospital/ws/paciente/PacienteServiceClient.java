package com.android.mihospital.ws.paciente;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.android.mihospital.client.SystemPC_IP;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.ws.ServiceResult;

public class PacienteServiceClient {
	
	private static final String URL = SystemPC_IP.ip_pc +"/services/PacienteService?wsdl";  
	private static final String NAMESPACE = "http://paciente.ws";

	@SuppressWarnings("finally")
	public ServiceResult actualizarPaciente(Paciente paciente){
		ServiceResult serviceResult = new ServiceResult();
		String SOAP_ACTION = "http://paciente.ws/actualizarPaciente"; 
		String METHOD_NAME = "actualizarPaciente";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
		
		PacienteRequest pacienteRequest = new PacienteRequest();
						
		pacienteRequest.setObraSocial(paciente.getObraSocial());
		pacienteRequest.setId(paciente.getId());
		pacienteRequest.setClave(paciente.getClave());
		pacienteRequest.setMail(paciente.getMail());
		pacienteRequest.setTelefono(paciente.getTelefono());
		pacienteRequest.setDireccion(paciente.getDireccion());
		pacienteRequest.setLocalidad(paciente.getLocalidad());
		pacienteRequest.setProvincia(paciente.getProvincia());
		if(paciente.getFoto()!=null)
		pacienteRequest.setTzFoto(Base64.encode(paciente.getFoto()));
		
		PropertyInfo pPacienteRequest = new PropertyInfo();
		pPacienteRequest.setName("paciente");
		pPacienteRequest.setValue(pacienteRequest);
		pPacienteRequest.setType(PacienteRequest.class);
		request.addProperty(pPacienteRequest);
					
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);  
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(URL);  
		httpTransport.debug = true;
		
		try {
			httpTransport.call(SOAP_ACTION, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();
			
			serviceResult.setMensaje((result.getProperty(0)!=null)?result.getProperty(0).toString():null);
			serviceResult.setResultado((result.getProperty(1)!=null)?result.getProperty(1).toString():null);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}finally{
			return serviceResult;
		}
	}
	
	@SuppressWarnings("finally")
	public ServiceResult registrarPaciente(Paciente paciente){
		ServiceResult serviceResult = new ServiceResult();
		String SOAP_ACTION = "http://paciente.ws/registrarPaciente"; 
		String METHOD_NAME = "registrarPaciente";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
		
		PacienteRequest pacienteRequest = new PacienteRequest();
		
		pacienteRequest.setId(paciente.getId());
		pacienteRequest.setTipoDocumento(paciente.getTipoDocumento());
		pacienteRequest.setNumeroDocumento(paciente.getNumeroDocumento());
		pacienteRequest.setNombre(paciente.getNombre());
		pacienteRequest.setApellido(paciente.getApellido());
		pacienteRequest.setClave(paciente.getClave());
		pacienteRequest.setMail(paciente.getMail());
		pacienteRequest.setTelefono(paciente.getTelefono());
		pacienteRequest.setDireccion(paciente.getDireccion());
		pacienteRequest.setLocalidad(paciente.getLocalidad());
		pacienteRequest.setProvincia(paciente.getProvincia());
		pacienteRequest.setObraSocial(paciente.getObraSocial());
		pacienteRequest.setAfiliado(paciente.getAfiliado());
		pacienteRequest.setSexo(paciente.getSexo());
		pacienteRequest.setFechaNacimiento(paciente.getFechaNacimiento());
		pacienteRequest.setNacionalidad(paciente.getNacionalidad());

		PropertyInfo pPacienteRequest = new PropertyInfo();
		pPacienteRequest.setName("paciente");
		pPacienteRequest.setValue(pacienteRequest);
		pPacienteRequest.setType(PacienteRequest.class);
		request.addProperty(pPacienteRequest);
				
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request); 
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(URL);  
		httpTransport.debug = true;
		
		try {
			httpTransport.call(SOAP_ACTION, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();
			
			serviceResult.setMensaje((result.getProperty(0)!=null)?result.getProperty(0).toString():null);
			serviceResult.setResultado((result.getProperty(1)!=null)?result.getProperty(1).toString():null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			return serviceResult;
		}
	} 
	
		
	@SuppressWarnings("finally")
	public PacienteResult obtenerPaciente(Paciente paciente){

		PacienteResult pacienteResult = new PacienteResult();
		String NAMESPACE = "http://paciente.ws";
		String URL = "http://10.0.2.2:8080/miHospital/services/PacienteService?wsdl";  
		String SOAP_ACTION = "http://paciente.ws/obtenerPaciente"; 
		String METHOD_NAME = "obtenerPaciente";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
				
		PropertyInfo pTipoDocumento = new PropertyInfo();
		pTipoDocumento.setName("tipoDocumento");
		pTipoDocumento.setValue("DNI");
		pTipoDocumento.setType(String.class);
		request.addProperty(pTipoDocumento);

		PropertyInfo pNumeroDocumento = new PropertyInfo();
		pNumeroDocumento.setName("numeroDocumento");
		pNumeroDocumento.setValue(paciente.getNumeroDocumento() );
		pNumeroDocumento.setType(Integer.class);
		request.addProperty(pNumeroDocumento);
		
		PropertyInfo pClave = new PropertyInfo();
		pClave.setName("clave");
		pClave.setValue(paciente.getClave());
		pClave.setType(String.class);
		request.addProperty(pClave);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);  
		HttpTransportSE httpTransport = new HttpTransportSE(URL);  
		httpTransport.debug = true;
		
		try {
			httpTransport.call(SOAP_ACTION, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();
			
			cargarPacienteResult(result, pacienteResult);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return pacienteResult;
		}
		
	}

	private void cargarPacienteResult(SoapObject result,PacienteResult pacienteResult) {
		pacienteResult.setProperty(0, (result.getProperty(0)!=null)?result.getProperty(0).toString():null);
		pacienteResult.setProperty(1,(result.getProperty(1)!=null)?result.getProperty(1).toString():null);
		pacienteResult.setProperty(2,(result.getProperty(2)!=null)?Integer.parseInt(result.getProperty(2).toString()):null);
		pacienteResult.setProperty(3, (result.getProperty(3)!=null)?result.getProperty(3).toString():null);
		pacienteResult.setProperty(4,(result.getProperty(4)!=null)?result.getProperty(4).toString():null);
		pacienteResult.setProperty(5, (result.getProperty(5)!=null)?result.getProperty(5).toString():null);
		pacienteResult.setProperty(6,(result.getProperty(6)!=null)? result.getProperty(6).toString():null);
		pacienteResult.setProperty(7, (result.getProperty(7)!=null)?Integer.parseInt(result.getProperty(7).toString()):null);
		pacienteResult.setProperty(8,(result.getProperty(8)!=null)?result.getProperty(8).toString():null);
		pacienteResult.setProperty(9, (result.getProperty(9)!=null)?result.getProperty(9).toString():null);
		pacienteResult.setProperty(10,(result.getProperty(10)!=null)?result.getProperty(10).toString():null);
		pacienteResult.setProperty(11, (result.getProperty(11)!=null)?result.getProperty(11).toString():null);
		pacienteResult.setProperty(12,(result.getProperty(12)!=null)?Integer.parseInt(result.getProperty(12).toString()):null);
		pacienteResult.setProperty(13, (result.getProperty(13)!=null)?result.getProperty(13).toString():null);
		pacienteResult.setProperty(14,(result.getProperty(14)!=null)?result.getProperty(14).toString():null);
		pacienteResult.setProperty(15, (result.getProperty(15)!=null)?result.getProperty(15).toString():null);
		pacienteResult.setProperty(16,(result.getProperty(16)!=null)?result.getProperty(16).toString():null);
		pacienteResult.setProperty(17, (result.getProperty(17)!=null)?result.getProperty(17).toString():null);
		pacienteResult.setProperty(18, (result.getProperty(18)!=null)?Integer.parseInt(result.getProperty(18).toString()):null);
		pacienteResult.setProperty(19, (result.getProperty(19)!=null)?Integer.parseInt(result.getProperty(19).toString()):null);
		pacienteResult.setProperty(19, (result.getProperty(19)!=null)?Integer.parseInt(result.getProperty(19).toString()):null);
		pacienteResult.setProperty(5,(result.getProperty(20)!=null)?result.getProperty(20).toString():null);
	}


}
