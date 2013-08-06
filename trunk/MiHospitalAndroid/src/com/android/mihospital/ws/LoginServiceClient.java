package com.android.mihospital.ws;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.android.mihospital.client.SystemPC_IP;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.ws.paciente.PacienteResult;

public class LoginServiceClient {

	
	@SuppressWarnings("finally")
	public PacienteResult validar(Paciente paciente){

		PacienteResult pacienteResult = new PacienteResult();
		String NAMESPACE = "http://login.ws";
		String URL = SystemPC_IP.ip_pc +"/services/LoginService?wsdl";  
		String SOAP_ACTION = "http://login.ws/validar"; 
		String METHOD_NAME = "validar";

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
		//envelope.implicitTypes = true;
		envelope.setOutputSoapObject(request);  
		//envelope.addMapping(NAMESPACE, "PacienteResult", PacienteResult.class);

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
		pacienteResult.setProperty(18, (result.getProperty(18)!=null)?Integer.parseInt(result.getProperty(18).toString()):0);
		pacienteResult.setProperty(19, (result.getProperty(19)!=null)?Integer.parseInt(result.getProperty(19).toString()):0);
		pacienteResult.setProperty(20, (result.getProperty(20)!=null)?result.getProperty(20).toString():null);
	}

}
