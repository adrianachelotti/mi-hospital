package com.android.mihospital.loginandregister;


import android.os.AsyncTask;

import com.android.mihospital.client.ServiceProvider;
import com.android.mihospital.ws.medico.BusquedaMedicoResult;

public class BusquedaMedicosRecientesTask extends AsyncTask<Void, Void, String> {

	String mensajeErrorLogin ;
	String resultadoBusqueda;
	Integer idPaciente;
	String iconoHome ;
	public BusquedaMedicosRecientesTask(Integer idPaciente){
		super();
		this.idPaciente = idPaciente;
		
	}
	
	@Override
	protected String doInBackground(Void... params) {
		
		BusquedaMedicoResult resultado =ServiceProvider.getClient().buscarMedicosMasFrecuentes(this.idPaciente);
		resultadoBusqueda = resultado.getMedicos();
		
		if (resultado.getResultado()==null){
			mensajeErrorLogin = "Error en la conexión.";
			return "error";
		
		}
		return resultadoBusqueda;
		
	}

	@Override
	protected void onPostExecute(final String success) {
		
		
	}

}
