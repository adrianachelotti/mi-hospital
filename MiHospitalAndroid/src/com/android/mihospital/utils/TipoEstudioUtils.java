package com.android.mihospital.utils;

public class TipoEstudioUtils {
	private static String[] tipoEstudio = {
		"UROCULTIVO",
		"HEMOGRAMA",
		"ORINA COMPLETA",
		"ECOGRAF�A 3D",
		"MAMOGRAF�A",
		"RESONANCIA MAGN�TICA",
		"TOMOGRAF�A",
		"RADIOLOG�A",
		"RADIOLOG�A INTERVENCIONISTA",
		"ECOCARDIOGRAF�A",
		"ECODOPPLER",
		"DENSITOMETR�A OSEA",
		"ELECTRO ENCEFALOGRAF�A",
		"ELECTROMIOGRAF�A",
		"MEDICINA NUCLEAR"};

	public static String[] getTipoEstudio() {
		return tipoEstudio;
	}

	public static void setEspecilidades(String[] estudios) {
		TipoEstudioUtils.tipoEstudio = estudios;
	}
	
	private static String[] departamento = {
		"Cardiolog�a",
		"Cl�nica M�dica",
		"Dermatolog�a",
		"Neurolog�a",
		"Gastroenterolog�a",
		"Salud Mental",
		"Endocrinolog�a",
		"Neumonolog�a",
		"Reumatolog�a",
		"Alergia",
		"Infectolog�a",
		"Hematolog�a",
		"Nefrolog�a",
		"Oncolog�a",
		"Hepatolog�a Cl�nica"};

	public static String[] getDepartamentos() {
		return departamento;
	}

	public static void setDepartamentos(String[] departamentos) {
		TipoEstudioUtils.departamento = departamentos;
	}
}

