package com.android.mihospital.login.test;


import android.test.ActivityInstrumentationTestCase2;

import com.android.mihospital.login.LoginActivity;
import com.jayway.android.robotium.solo.Solo;



/**
 * Clase que valida todas las posibilidades que pueden presentarse en el login
 * @author Adriana
 *
 */
public class TestLogin extends ActivityInstrumentationTestCase2<LoginActivity> {

	private String passwordValido = "adamymili";
	
	private String numeroDocumentoRegistrado ="31026053";
	
	private Solo solo;

	@SuppressWarnings("deprecation")
	public TestLogin() {
		super("com.android.mihospital.login",LoginActivity.class);
	
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testValidarBotonIngresar() {

		// el 0 significa primer EditText que tiene el layout LoginActivity
		solo.enterText(0,numeroDocumentoRegistrado);
		// el 1 significa segundo EditText que tiene el layout LoginActivity
		solo.enterText(1, passwordValido);		
		solo.clickOnButton("Ingresar");
	
		
		
		assertTrue(solo.searchText("Validando"));
	}


	public void testValidarLogeoCorrecto() {
		solo.enterText(0,numeroDocumentoRegistrado);
		solo.enterText(1, passwordValido);
		solo.clickOnButton("Ingresar");
		
		assertTrue(solo.searchText("Usted ha iniciado session"));
	}



	public void testValidarLogeoIncorrecto() {
		solo.enterText(0,"999");		
		solo.enterText(1, "12345");		
		solo.clickOnButton("Ingresar");
		
		assertTrue(solo.searchText("DNI o Clave incorrecta."));
	}


	public void testValidarClaveVacia() {
		solo.enterText(0,numeroDocumentoRegistrado);
		solo.enterText(1, "");
		solo.clickOnButton("Ingresar");
		
		assertTrue(solo.searchText("Campo Obligatorio"));
	}

	public void testValidarClaveCorta() {
		solo.enterText(0,numeroDocumentoRegistrado);
		solo.enterText(1, "223");
		solo.clickOnButton("Ingresar");

		assertTrue(solo.searchText("Clave inválida"));
	}

	public void testValidarDniVacio() {
		solo.enterText(0,"");
		solo.enterText(1, "23232323");
		solo.clickOnButton("Ingresar");
	
		assertTrue(solo.searchText("Campo Obligatorio"));
	}

	public void testValidarDniYClaveVacios() {
		solo.enterText(0,"");
		solo.enterText(1, "");		
		solo.clickOnButton("Ingresar");
		
		assertTrue(solo.searchText("Campo Obligatorio"));
	}

	public void testValidarDniValido() {

		solo.enterText(0,"addsjflksjdfl");		
		solo.enterText(1, "PASSWORD");		
		solo.clickOnButton("Ingresar");
		
		assertTrue(solo.searchText("Dni inválido"));
	}

	



	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

}
