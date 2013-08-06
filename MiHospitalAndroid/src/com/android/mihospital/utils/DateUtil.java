package com.android.mihospital.utils;



import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;




public class DateUtil {
	
	private static Map<Integer, String> mapaDias = inicializarMapa();
	
	
	
			
	private static Map<Integer, String> inicializarMapa() {
		Map<Integer,String> mapaDias = new HashMap<Integer, String>();
		mapaDias.put(1, "Domingo");
		mapaDias.put(2, "Lunes");
		mapaDias.put(3, "Martes");
		mapaDias.put(4, "Miercoles");
		mapaDias.put(5, "Jueves");
		mapaDias.put(6, "Viernes");
		mapaDias.put(7, "Sabado");
		return mapaDias;
	}

	public static Date obtenerDiaLimite(){
		int acumulador =0;
		int j = Calendar.getInstance().MONTH +6;
		for ( int i=Calendar.getInstance().MONTH; i < j; i++) {
			
				Calendar  cal = new GregorianCalendar(Calendar.getInstance().getTime().getYear()+1,(i>11)? i-12:i, 1);
				acumulador+=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
		}
		 
		 
		 Calendar diaLimite = Calendar.getInstance();
		 diaLimite.add(Calendar.DATE, acumulador);
		 return diaLimite.getTime();
	
	}
	public static String obtenerNombreDelDia(Date dia){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dia);
		return mapaDias.get(cal.get(Calendar.DAY_OF_WEEK));
	}
	
	public static String obtenerNombreDelDia(String dia){
		Date date = null;
		try {
			date = DateUtils.parseDate(dia, new String[]{"dd/MM/yyyy"} );
		} catch (DateParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obtenerNombreDelDia(date);
	}
	
	public static Date obtenerHoraMinima(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }    

    public static Date obtenerHoraMaxima(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }
    
	public static Date obtenerHoraMinima(String date) {
		return obtenerHoraMinima(obtenerDia(date));
    }    

    public static Date obtenerHoraMaxima(String date) {
    	return obtenerHoraMaxima(obtenerDia(date));
    }
        
    public static Date obtenerDia(String dia){
    	Date date = null;
		try {
			date = DateUtils.parseDate(dia, new String[]{"dd/MM/yyyy"} );
		} catch (DateParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
    }
}
