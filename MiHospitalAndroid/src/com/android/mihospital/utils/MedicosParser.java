package com.android.mihospital.utils;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.android.mihospital.dominio.Medico;

public class MedicosParser {
	private static final String SEPARADOR_DATOS_MEDICO = ";";
	private static final String SEPARADOR_MEDICOS = "#";
	private static final String SEPADOR_LUGAR_HORARIOS_MEDICOS = "%";
	private static final String SEPADOR_HORARIOS_MEDICOS = "\\$";
	
	public static List<Medico> parsearMedicos(String resultadosSerializados){
		List<Medico> medicos = new ArrayList<Medico>();
		if (!TextUtils.isEmpty(resultadosSerializados)){
					
			
			String[] medicosArrays = resultadosSerializados
					.split(SEPARADOR_MEDICOS);
			medicos = new ArrayList<Medico>();
			// TODO
			for (int i = 0; i < medicosArrays.length; i++) {
				
				String[] partes = medicosArrays[i].split(SEPADOR_LUGAR_HORARIOS_MEDICOS);
				String[] detalleMedico = partes[0].split(SEPARADOR_DATOS_MEDICO);
				
				Medico user1 = new Medico(Integer.parseInt(detalleMedico[0]),
						detalleMedico[1], detalleMedico[2]);
				user1.setEspecialidad(detalleMedico[3]);
				if (detalleMedico.length>4){
				user1.setSucursales(SucursalesList.parsear(detalleMedico[4]));
				user1.setDuracionTurno(Integer.parseInt(detalleMedico[5]));
				user1.setHorariosLugaresAtencion(partes[1]);
				}
				
				medicos.add(user1);
			}
		}	
		return medicos;
	}
	
	//Viernes;14;16;1$Sabado;10;17;2$Jueves;14;16;3
	public static String  extraerHorarios(String horariosLugaresAtencion, int indiceSucursal) {
		
		//en la base el id de la sucursal empieza en 1
		Integer idSucursal = Integer.valueOf(indiceSucursal+1);
		String[] horariosDias = horariosLugaresAtencion.split(SEPADOR_HORARIOS_MEDICOS);
		String horariosResultantes ="";
		for (int i = 0; i < horariosDias.length; i++) {
			//El ultimo numero es la sucursal ej ://Viernes;14;16;1
			if (horariosDias[i].endsWith(idSucursal.toString())){
				
				String[] componentes = horariosDias[i].split(";");
				String horarioAtencion = String.format("- %s, de %s:00 hs a %s:00 hs ",								
								componentes[0], componentes[1], componentes[2]);
				
				horariosResultantes = horariosResultantes + horarioAtencion+ "\n";
			}
		}

		return horariosResultantes;
	}



}
