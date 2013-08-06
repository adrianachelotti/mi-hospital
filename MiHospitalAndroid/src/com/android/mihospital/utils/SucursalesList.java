package com.android.mihospital.utils;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;

public class SucursalesList {

	public static String[] sucursales = { "Ninguna","Av. Montes de Oca 562. Barracas . CABA", "Juramento 2075. Belgrano. CABA", "Av. San Martín 2868. Caseros. Buenos Aires"};

	public static String[] abrevSucursales = {"Barracas", "Belgrano","Caseros"};
	
	public static Map<String, Integer> mapaDeSucursales = createMap();

	public static String[] getSucursales() {
		return sucursales;
	}

	public static void setSucursales(String[] sucursales) {
		SucursalesList.sucursales = sucursales;
	}

	//INSERT INTO sucursales (id,direccion,localidad,nombre,provincia,telefono) VALUES (1,'Av. Montes de Oca 562','CABA','BARRACAS','CABA','0810-555-6733');
	//INSERT INTO sucursales (id,direccion,localidad,nombre,provincia,telefono) VALUES (2,'Juramento 2075','CABA','BELGRANO','CABA','0810-555-6732');
	//INSERT INTO sucursales (id,direccion,localidad,nombre,provincia,telefono) VALUES (3,'Av. San Martín 2868','CASEROS','CASEROS','BUENOS AIRES','0810-555-6731');

	private static Map<String, Integer> createMap() {
        Map<String, Integer> result = new HashMap<String,Integer>();
        result.put("Barracas",1);
        result.put("Belgrano",2);
        result.put("Caseros",3);
        return result;
    }
	
	public static  String parsear(String sucursales){

		String listaDeSucursales ="";
		if(sucursales!=null && sucursales.contains(",")){
			String[] sucur = sucursales.split(",");

			for (int i =0; i<sucur.length;i++){
				int value = Integer.parseInt(sucur[i]);
				listaDeSucursales= listaDeSucursales.concat(SucursalesList.getSucursales()[value]) + "\n";
			}
			listaDeSucursales = listaDeSucursales.substring(0,listaDeSucursales.length()-1);


		}
		if (!TextUtils.isEmpty(sucursales) && sucursales.length()==1){
			int value = Integer.parseInt(sucursales);
			listaDeSucursales= SucursalesList.getSucursales()[value];

		}



		return listaDeSucursales;
	}

	public static List<String> obtenerAbreviaturas(String sucursales){
		List<String> listaSucursales = new ArrayList<String>();
		for (int i=0; i<abrevSucursales.length; i++){
			if (sucursales.contains(abrevSucursales[i])){
				listaSucursales.add(abrevSucursales[i]);
			}
		}
		return listaSucursales;
	}
	
	public static HashMap<Integer, Integer> obtenerPosiciones(String sucursales){
		HashMap<Integer, Integer> posiciones = new HashMap<Integer, Integer>();
		int posicion=0;
				for (int i=0; i<abrevSucursales.length; i++){
			if (sucursales.contains(abrevSucursales[i])){
				posiciones.put(posicion, i);
				posicion+=1;
			}
		}
		return posiciones;
	}
	
}