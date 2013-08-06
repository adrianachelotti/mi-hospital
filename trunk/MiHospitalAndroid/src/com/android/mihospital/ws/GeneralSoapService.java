package com.android.mihospital.ws;

import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class GeneralSoapService {

	public ServiceResult makeSoapRequest(final String url,final String namespace,final String methodName, final String soapAction,List<PropertyInfo> propertyList) {

		ServiceResult requestResult = new ServiceResult();
		SoapObject request = new SoapObject(namespace,
				methodName);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		envelope.implicitTypes = true;
		HttpTransportSE httpTransport = new HttpTransportSE(url);
		httpTransport.debug = true;

		for(PropertyInfo prop: propertyList)
			request.addProperty(prop);
		
		try {
			httpTransport.call(soapAction, envelope);
			SoapObject result = (SoapObject) envelope.getResponse();

			cargarResultado(result, requestResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestResult;
	}
	
	private void cargarResultado(SoapObject result,
			ServiceResult requestResult) {
		requestResult.setProperty(0, (result.getProperty(0) != null) ? result
				.getProperty(0).toString() : null);
		requestResult.setProperty(1, (result.getProperty(1) != null) ? result
				.getProperty(1).toString() : null);
	}
}
