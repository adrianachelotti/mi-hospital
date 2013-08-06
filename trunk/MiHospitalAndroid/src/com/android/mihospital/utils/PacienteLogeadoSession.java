package com.android.mihospital.utils;

import com.android.mihospital.dominio.Paciente;

public class PacienteLogeadoSession {
	
	private static Paciente paciente;
	
	public static Paciente getPacientLogeado(){
		return paciente;
	}
	public static void setPacienteLogeado(Paciente pacienteLogeado){
		paciente = pacienteLogeado;
	}

}
