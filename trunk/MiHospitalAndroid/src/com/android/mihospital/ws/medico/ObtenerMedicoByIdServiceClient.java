package com.android.mihospital.ws.medico;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.android.mihospital.client.SystemPC_IP;



public class ObtenerMedicoByIdServiceClient {

	String NAMESPACE = "http://medico.ws";
	String URL = SystemPC_IP.ip_pc +"/services/MedicoService?wsdl";  
  
	String SOAP_ACTION = "http://medico.ws/obtenerDetalleMedico"; 
	String METHOD_NAME = "obtenerDetalleMedico";
	
	
	@SuppressWarnings("finally")
	public MedicoResult buscarMedicoById(int id){

		MedicoResult medicoResult = new MedicoResult();
		

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 
				
		PropertyInfo idMedicoProoerty = new PropertyInfo();
		idMedicoProoerty.setName("medico");
		idMedicoProoerty.setValue(id);
		idMedicoProoerty.setType(Integer.class);
		request.addProperty(idMedicoProoerty);
	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);  
		HttpTransportSE httpTransport = new HttpTransportSE(URL);  
		httpTransport.debug = true;
		
		try {
			httpTransport.call(SOAP_ACTION, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();
			
			cargarResultadoMedicos(result, medicoResult);

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return medicoResult;
		}
		
		
	}
	private void cargarResultadoMedicos(SoapObject result,MedicoResult busquedaMedicoResult) {
		busquedaMedicoResult.setProperty(0, (result.getProperty(0)!=null)?result.getProperty(0).toString():null);
		busquedaMedicoResult.setProperty(1,(result.getProperty(1)!=null)?result.getProperty(1).toString():null);
		busquedaMedicoResult.setProperty(2, (result.getProperty(2)!=null)?result.getProperty(2).toString():null);		
		busquedaMedicoResult.setProperty(3,(result.getProperty(3)!=null)?Integer.parseInt(result.getProperty(3).toString()):null);
		busquedaMedicoResult.setProperty(4, (result.getProperty(4)!=null)?result.getProperty(4).toString():null);
		
		busquedaMedicoResult.setProperty(5,(result.getProperty(5)!=null)?result.getProperty(5).toString():null);
		busquedaMedicoResult.setProperty(6, (result.getProperty(6)!=null)?result.getProperty(6).toString():null);
		busquedaMedicoResult.setProperty(7, (result.getProperty(7)!=null)?Integer.parseInt(result.getProperty(7).toString()):null);
				
		busquedaMedicoResult.setProperty(8,(result.getProperty(8)!=null)?result.getProperty(8).toString():null);
		busquedaMedicoResult.setProperty(9, (result.getProperty(9)!=null)?result.getProperty(9).toString():null);
		busquedaMedicoResult.setProperty(10,(result.getProperty(10)!=null)?result.getProperty(10).toString():null);
		
	}
	

	
}
