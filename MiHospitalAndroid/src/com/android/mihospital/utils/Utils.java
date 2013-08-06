package com.android.mihospital.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import android.app.Activity;
import android.util.Patterns;
import android.widget.EditText;

public class Utils {
	public final static String EMISOR_PACIENTE="P";
	public final static String NO_LEIDO="NO_LEIDO";
	public final static String LEIDO="LEIDO";
	
	public static final String RESERVADO = "R";
    public static final String DISPONIBLE = "D";
    public static final String PRESENTE = "P";
    public static final String AUSENTE = "A";
    public static final String CANCELADO_PACIENTE = "CP";
    public static final String CANCELADO_MEDICO = "CM";
    
	public static final CharSequence[] anticipacionItems = { "1 hora antes", "1 dia antes", "Otro" };
	
	public static boolean isValidEmail(String email) {
	    Pattern pattern = Patterns.EMAIL_ADDRESS;
	    return pattern.matcher(email).matches();
	}
	
	public static String getValueFromTextEdit(Activity activity, int id){
		EditText fieldEText = (EditText) activity.findViewById(id);
		return fieldEText.getText().toString();
	}
	
	public static boolean esFormatoFechaDeNacimientoValido(String fechaIngresada) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
            formatoFecha.setLenient(false);
            Date fecha = formatoFecha.parse(fechaIngresada);
            if(fecha.after(new Date()))
            	return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
}
