package com.android.mihospital.utils;

public class TipoEstudioUtils {
	private static String[] tipoEstudio = {
		"UROCULTIVO",
		"HEMOGRAMA",
		"ORINA COMPLETA",
		"ECOGRAFÍA 3D",
		"MAMOGRAFÍA",
		"RESONANCIA MAGNÉTICA",
		"TOMOGRAFÍA",
		"RADIOLOGÍA",
		"RADIOLOGÍA INTERVENCIONISTA",
		"ECOCARDIOGRAFÍA",
		"ECODOPPLER",
		"DENSITOMETRÍA OSEA",
		"ELECTRO ENCEFALOGRAFÍA",
		"ELECTROMIOGRAFÍA",
		"MEDICINA NUCLEAR"};

	public static String[] getTipoEstudio() {
		return tipoEstudio;
	}

	public static void setEspecilidades(String[] estudios) {
		TipoEstudioUtils.tipoEstudio = estudios;
	}
	
	private static String[] departamento = {
		"Cardiología",
		"Clínica Médica",
		"Dermatología",
		"Neurología",
		"Gastroenterología",
		"Salud Mental",
		"Endocrinología",
		"Neumonología",
		"Reumatología",
		"Alergia",
		"Infectología",
		"Hematología",
		"Nefrología",
		"Oncología",
		"Hepatología Clínica"};

	public static String[] getDepartamentos() {
		return departamento;
	}

	public static void setDepartamentos(String[] departamentos) {
		TipoEstudioUtils.departamento = departamentos;
	}
}

