package com.android.mihospital.registracion.test;

import android.test.ActivityInstrumentationTestCase2;


import com.android.mihospital.registracion.RegisterActivity;
import com.jayway.android.robotium.solo.Solo;

public class RegistracionActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity>  {

	private Solo solo;
	
	@SuppressWarnings("deprecation")
	public RegistracionActivityTest() {
		super("com.android.mihospital.login",RegisterActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testValidarLogeoIncorrecto() {
		solo.enterText(0,"Dario");		
		solo.enterText(1, "Perez");
		solo.enterText(2, "123456");
		solo.enterText(3, "30761872");
		solo.enterText(4, "Argentina");
		solo.enterText(5, "20/05/1984");
		solo.enterText(6, "Masculino");
		solo.enterText(7,"1");		
		solo.enterText(8, "OSDE");
		solo.enterText(9, "dmpstaltari@gmail.com");
		solo.enterText(10, "42269167");
		solo.enterText(11, "Calle 19");
		solo.enterText(12, "Berazategui");
	//	solo.clickOnRadioButton(1);
		
		solo.clickOnButton("Registrar");
		
		assertTrue(solo.searchText("DNI o Clave incorrecta."));
	}
}
