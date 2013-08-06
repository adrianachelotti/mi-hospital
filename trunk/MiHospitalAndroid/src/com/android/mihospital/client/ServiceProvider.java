package com.android.mihospital.client;

public class ServiceProvider {

	private static HospistalClient cliente=null;
	
	private static boolean debugMode=false;
	
	public static HospistalClient getClient(){
		if(cliente==null){

			if(debugMode)
				cliente = new ClienteMock();
			else
				cliente = new ClienteSOAP();

		}
		return cliente;
	}
}
