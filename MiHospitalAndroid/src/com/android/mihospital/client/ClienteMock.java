package com.android.mihospital.client;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.android.mihospital.dominio.Medicamento;
import com.android.mihospital.dominio.Estudio;
import com.android.mihospital.dominio.Formato;
import com.android.mihospital.dominio.Receta;
import com.android.mihospital.dominio.Medico;
import com.android.mihospital.dominio.Mensaje;
import com.android.mihospital.dominio.Paciente;
import com.android.mihospital.ws.ServiceResult;
import com.android.mihospital.ws.medico.BusquedaMedicoResult;
import com.android.mihospital.ws.medico.MedicoResult;
import com.android.mihospital.ws.mensaje.ListaDeMensajeResult;
import com.android.mihospital.ws.paciente.PacienteResult;
import com.android.mihospital.ws.turnos.ListadoDeTurnosResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ClienteMock implements HospistalClient {

	public ClienteMock() {
	}

	public ServiceResult actualizarPaciente(Paciente paciente) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		return servicio;
	}

	public PacienteResult validar(Paciente paciente) {
		PacienteResult resultado = new PacienteResult();
		resultado.setAfiliado(1234);
		resultado.setApellido("Topo");
		resultado.setNombre("Juan");
		resultado.setDireccion("Av Cabildo 200");
		resultado.setFechaNacimiento("01/01/1985");
		resultado.setId(1234);
		resultado.setLocalidad("Capital");
		resultado.setMensaje("lalala");
		resultado.setMail("a@a.com");
		resultado.setNacionalidad("Uruguayo");
		resultado.setNumeroDocumento(12345678);
		resultado.setObraSocial("osde");
		resultado.setProvincia("Buenos Aires");
		resultado.setResultado("OK");
		resultado.setSexo("M");
		resultado.setTipoDocumento("DNI");
		resultado.setTelefono("12345678");
		resultado.setClave("1234");
		resultado.setTotalMensajesNuevos(1);
		resultado.setTotalTurnosCancelados(1);
		return resultado;
	}

	public ServiceResult registrarPaciente(Paciente paciente) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		return servicio;
	}

	public PacienteResult obtenerPaciente(Paciente paciente) {
		PacienteResult resultado = new PacienteResult();
		resultado.setAfiliado(1234);
		resultado.setApellido("Topo");
		resultado.setNombre("Juan");
		resultado.setDireccion("Av Cabildo 200");
		resultado.setFechaNacimiento("01/01/1985");
		resultado.setId(1234);
		resultado.setLocalidad("Capital");
		resultado.setMensaje("lalala");
		resultado.setMail("a@a.com");
		resultado.setNacionalidad("Uruguayo");
		resultado.setNumeroDocumento(12345678);
		resultado.setObraSocial("osde");
		resultado.setProvincia("Buenos Aires");
		resultado.setResultado("OK");
		resultado.setSexo("M");
		resultado.setTipoDocumento("DNI");
		resultado.setTelefono("12345678");
		resultado.setClave("1234");
		return resultado;
	}

	public BusquedaMedicoResult buscarMedicos(String apellido, String nombre,
			String especialidad, Integer sucursal, String dias) {
		BusquedaMedicoResult busquedaResult = new BusquedaMedicoResult();
		busquedaResult.setResultado("OK");
		busquedaResult
				.setMedicos("9;Adriana;Chelotti;Adolescencia y Pubertad;1,2,3;30%Viernes;14;16;1$Sabado;10;17;2$Jueves;14;16;3$Lunes;14;16;3#8;Daniel;Montenegro;Adolescencia y Pubertad;1;15%Domingo;9;17;1");

		return busquedaResult;
	}

	public BusquedaMedicoResult buscarMedicosMasFrecuentes(Integer idPAciente) {
		BusquedaMedicoResult busquedaResult = new BusquedaMedicoResult();
		busquedaResult.setResultado("OK");
		busquedaResult
				.setMedicos("9;Adriana;Chelotti;Adolescencia y Pubertad;1,2,3;30%Viernes;14;16;1$Sabado;10;17;2$Jueves;14;16;3$Lunes;14;16;3#8;Daniel;Montenegro;Adolescencia y Pubertad;1;15%Domingo;9;17;1");

		return busquedaResult;
	}

	public MedicoResult obtenerMedicoById(int id) {
		MedicoResult medico = new MedicoResult();
		medico.setApellido("Rimolo");
		medico.setId(Integer.valueOf(32));
		medico.setNombre("Adriana");
		medico.setApellido("Rimolo");
		medico.setEspecialidad("Nutrición");

		medico.setMail("dfs");
		medico.setResultado("OK");

		medico.setMail("foo@foo.com");
		// dia, horario inicio, horario final, numero sucursal SusucrsalesList
		// (1,2,3)
		medico.setHorariosLugaresAtencion("Lunes;9;18;3#Miercoles;9;18;2");
		medico.setSucursales("2;3");
		medico.setDuracionTurno(Integer.valueOf(30));

		// Imagen que esta encodeada en Base64 y esta es su representacion como
		// string
		medico.setFoto("/9j/4AAQSkZJRgABAQEBLAEsAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcG"
				+ "BwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwM"
				+ "DAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAGxASgDASIA"
				+ "AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA"
				+ "AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3"
				+ "ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm"
				+ "p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA"
				+ "AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx"
				+ "BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK"
				+ "U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3"
				+ "uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9+KKK"
				+ "KACgcUUUALk470ZPqaSigByvzzTqjpdxxQA+ikU5paACg0jHAqnrWu2mg2b3F5PFBDGMs7nAAqJS"
				+ "tuNJvYtliB0oDKK8h8V/taaLpzlNMjbU9px50XzxH2znrXnvib9uTWbWZltdM0yOJR8zzylSv865"
				+ "Z4+jHqdVPL601dRPqEkEdaaCFPWviIf8FHdatL26N8+mRRQE48rLFh+dee69/wAFltTtppY4LGAq"
				+ "hKrvBDP+tc7zegna5p/ZdbsfpDuGOuKTcP71fltef8FqPEcV1tGhaTYNjImu7mTaffGQP1qa2/4L"
				+ "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"
				+ "X3H1NFFQtxPY1NO/4+bn/dP86KKKKm5yH//Z");

		medico.setId(id);
		medico.setDuracionTurno(15);
		medico.setResultado("OK");
		return medico;

	}

	public ListadoDeTurnosResult obtenerTurnosDisponibles(Integer idPaciente,
			Integer idMedico, Integer idSucursal, String date) {
		ListadoDeTurnosResult resultado = new ListadoDeTurnosResult();
		resultado.setResultado("OK");
		resultado
				.setTurnos("2;12/06/2013 22:30;12/06/2013 23:00#2;12/06/2013 23:30;13/06/2013 00:00");
		return resultado;
	}

	public ServiceResult reservarTurno(Integer idTurno, Integer idPaciente) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		return servicio;
	}

	private static int historialRequestNuber = 0;

	public ListadoDeTurnosResult buscarHistorialDeTurnos(Integer idPaciente) {
		ListadoDeTurnosResult resultado = new ListadoDeTurnosResult();
		resultado.setResultado("OK");
		if(historialRequestNuber > 6){
		if (historialRequestNuber < 5)
			resultado
					.setTurnos("1;Giselle;Rimolo;Nutrición y Diabetología;2;132;27/06/2013 09:40;27/06/2013 10:00;CM#1;Cancelame;Ester;Nutrición y Diabetología;2;131;27/06/2013 09:20;27/06/2013 09:40;CP#1;Giselle;Rimolo;Nutrición y Diabetología;2;130;27/06/2013 09:00;27/06/2013 09:20;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;70;31/06/2013 14:00;31/06/2013 14:20;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;66;31/06/2013 12:40;31/06/2013 13:00;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;65;31/06/2013 12:20;31/06/2013 12:40;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;111;29/06/2013 11:40;29/06/2013 12:00;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;34;24/05/2013 11:00;24/05/2013 11:20;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;30;24/05/2013 09:40;24/05/2013 10:00;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;29;24/05/2013 09:20;24/05/2013 09:40;R");
		else
			resultado
					.setTurnos("1;Gisellessssss;Rimolo;Nutrición y Diabetología;2;130;27/06/2013 09:00;27/06/2013 09:20;CM#1;Giselle;Rimolo;Nutrición y Diabetología;2;70;31/06/2013 14:00;31/06/2013 14:20;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;66;31/06/2013 12:40;31/06/2013 13:00;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;65;31/06/2013 12:20;31/06/2013 12:40;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;111;29/06/2013 11:40;29/06/2013 12:00;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;34;24/05/2013 11:00;24/05/2013 11:20;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;30;24/05/2013 09:40;24/05/2013 10:00;R#1;Giselle;Rimolo;Nutrición y Diabetología;2;29;24/05/2013 09:20;24/05/2013 09:40;R");
		}
		historialRequestNuber++;
		return resultado;
	}

	public ServiceResult marcarMensajeComoLeido(Integer id) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		return servicio;
	}

	public ServiceResult enviarMensaje(Mensaje mensaje) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		return servicio;
	}

	public ListaDeMensajeResult traerMensajesDelPaciente(Integer idPaciente,
			Integer idMedico, String estado) {

		ListaDeMensajeResult resultado = new ListaDeMensajeResult();
		resultado.setResultado("OK");

		Mensaje msg1 = new Mensaje();
		msg1.setAsunto("Asunto0");
		msg1.setMensaje("Contenido0");
		msg1.setEmisor("p");
		msg1.setEstado("LEIDO");
		msg1.setFecha(new Date());
		msg1.setIdMensaje(2);
		msg1.setIdMedico(2);
		msg1.setIdPaciente(3);

		Mensaje msg = new Mensaje();
		msg.setAsunto("Asunto1");
		msg.setMensaje("Contenido1");
		msg.setEmisor("m");
		msg.setEstado("LEIDO");
		msg.setFecha(new Date());
		msg.setIdMensaje(2);
		msg.setIdMedico(2);
		msg.setIdPaciente(3);

		Mensaje msg2 = new Mensaje();
		msg2.setAsunto("Asunto2");
		msg2.setMensaje("Contenido2");
		msg2.setEmisor("m");
		msg2.setEstado("NO_LEIDO");
		msg2.setFecha(new Date());
		msg2.setIdMensaje(2);
		msg2.setIdMedico(2);
		msg2.setIdPaciente(3);

		changes++;
		List<Mensaje> list = new ArrayList<Mensaje>();
		list.add(msg1);
		list.add(msg);
		list.add(msg2);
		if (changes == 2) {
			Mensaje msg3 = new Mensaje();
			msg3.setAsunto("Asunto2");
			msg3.setMensaje("Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo." + "Contenido2 muy largo."
					+ "Contenido2 muy largo.");
			msg3.setEmisor("m");
			msg3.setEstado("NO_LEIDO");
			msg3.setFecha(new Date());
			msg3.setIdMensaje(3);
			msg3.setIdMedico(2);
			msg3.setIdPaciente(3);
			list.add(msg3);
		}
		Type listType = new TypeToken<List<Mensaje>>() {
		}.getType();
		Gson gson = new Gson();

		resultado.setMensajeEnviados(gson.toJson(list, listType));
		return resultado;
	}

	private static int changes = 0;

	public ServiceResult cancelarTurno(Integer id) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		servicio.setMensaje("No se pudo cancelar el turno");
		return servicio;
	}

	public ServiceResult obtenerDetalleEstudio(Integer id) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		String html="";
		html += "<body><div class=\"container\"><div class=\"page-header\"><h1>Estudio: RMN </h1></div>";
		html += "<h4>Departamento: Diagnostico por imagen </h4>";
		html += "# 2341";
		html += "<h3>Paciente: Max Pain</h3>";
		html += "<h2>Medico: Rimolo Maria </h2>";
		html += "<h3>Fecha: 22/07/2013 22:50</h3></div>";
		
			html += "<h2>Detalle</h2>";
		servicio.setMensaje(html+"<div class=\"well well-large\" align=\"justify\">Esto es una prueba, la verdad que no salio del todo bien. <p/><strong>Empeza a cuidarte un poco</strong>"
				+ "<h2>Imagen 1</h2><img alt=\"\" src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/4QBgRXhpZgAASUkqAAgAAAACADEBAgAHAAAAJgAAAGmHBAABAAAALgAAAAAAAABQaWNhc2EAAAMAAJAHAAQAAAAwMjIwAqAEAAEAAABAAQAAA6AEAAEAAADwAAAAAAAAAP/bAIQAAwICCAgICAgICAgICAgICAgICAgICAgICAgICAoICAgICAgICAgICAgICAgICggICAgJCgoICAsNCggNCAgKCAEDBAQGBQYKBgYKDQ0MDQ0NDQ0ODA0NDQ0NDAwNDQ0MDQ0NDA0MDAwNDQwNDQwMDAwNDAwNDAwMDAwMDAwMDAwM/8AAEQgA8AFAAwERAAIRAQMRAf/EAB0AAQABBQEBAQAAAAAAAAAAAAABAgUGBwgEAwn/xABWEAABAwICBgcEBAkGDAQHAAABAAIDBBEFIQYHEjFB8AgTIlFhgZEUcaHBIzKx0QkzQlJUcnSUtBZic7LT4RUkQ1NkdZKToqPC0iVVgvE0NWWDhKSz/8QAGwEBAAIDAQEAAAAAAAAAAAAAAAQFAQMGAgf/xAA8EQACAgEBBQYCBgoDAQEBAAAAAQIDEQQFEiExQRNRYXGRsSKBMjNSocHRBhQjQkNicoKS8BWy4fFTFv/aAAwDAQACEQMRAD8A/KpAEAQBAEAQBAdqfg3HbP8Ah197Wgog09zi6osfQHzseC6XYfG2Ue9L3ON/SbhVXLuk/vRnuN6WyNkd9NIMz+W7710spYbOKpqyiz1WmzwPx0n+8f8AetfakyOmbLFXay3tv9NIP/uP7/etbt8TatHx5Foq9bMmdp5f94/71Elfjq/UsatCpc19xi1frhn4TzX/AKWT71Blq8f/AEta9mw7vuMWxHXBVEm1TPbwmk7v1lDnqm+TfqT69nwXRehj1ZrWqv0qo/38v/eoz1Uu9+pLjoq+5Fqqta9Yd1VU/vE3/eorvk+r9STHSVroi2T6xq5x/wDjKsf/AJM3f+utTtk+r9TbGitfur0PN/L2u/Tav95m/wC9Y35d79TbuR7l6Efy8rv0yr/eZu79dY333v1M7se5ehP8vK79Nq/3mb/vTffe/UxuR7kBp7XfptX+8zd/9Is70u9+o3IfZRP8va39Mq/3mb+0WN6Xe/UbkPsr0DdPq79Mq/3mb+0Tel3v1MuEH+6vQHT6u/Taz95m7r/5xN+Xe/Ux2cO5eg/l7W/plX+8zf2ib7736js4fZXoVfy8rf0yr/eZv7RY35d79R2cPsr0Khp5W/plX+8zf2izvy736js4fZXoR/Lyt/TKv95m/tFjfl3v1HZx+yvQfy9rv02s/eZv7RZ333v1MdnD7K9CtmsTEBa1dWg7sqqcZd34zcm/LvfqY7Gv7K9F+R6Y9Z+Jf+Y1/wC+VH9ovXaz+0/V/mef1er7EfRfkXSg1t4mCCMTxEHLMVtTwN/84nb2L96Xq/zD09L/AIcP8Y/kXHpBaXVFbJhk1TK6aU4XDtPdbacfaagXdYAFxAALrXNhe5Wy+yc2nNt8Fz7jRo6YVQca4pLefBGqVGJwQBAEAQBAEAQBAEAQBAEAQHZP4PGcNjx8ncIKM+hqV0+wuFsn4HF/pPxprX8xatLNLAJHkHibZqyncuOCBptE8ZMExPTU96gT1HQua9KYtiOll755+J8VXy1LLGOkwWKs0r4X+Kiy1GSXCnBZ6jHSeJUWVmSUoJHhkryePNlqbPeDzl3isGQ5vigPndANpAC5ARtICdpZyBtJkDaWARdALoCrrCgI2ygKmOQFfPwQAICoBAfemfYj3/NeWEZTrPfdmFn/AOmRevtVTf43W+zp5GmpYTz3swdajcEAQBAEAQBAEAQBAEAQBAEB110Dpdmm0jd3UlKf4ldJsV4lY/5Tkf0iSlGpP7f4GmdI9JO07tXzKhW34TLmihGFV+OE8VXyteSwVaRbJq0nnxWreZ7UUfAu5815PRAagKXNQFNkBVbnNAUlAEAQBAEAQBAEAQBAEABQEh3igKw7xQEmVAVtl8VgGV6xWER4Xf8A8sjPkaupt8Fsl08jVB5z5mGLwbQgCAIAgCAIAgCAIAgCAIAgOqOhlWCPDtKHnhR0nxdUBdBsh4dn9Jye31lU/wBf4HNeLVm04+vqLqhlJts6iEcItx+/5LybCoR8+aGSQ1ZACwYJvz5ICAgIcEAEaAZICLDn3ICksQE9WgHVoMDq0BGwgJDQgK78+aAjZQyRsoAW8+SAFqAnZ8EBIaMlgGY6zfxeFf6qh/iahbJdPI1Q6+ZhC8GwIAgCAIAgCAIAgCAIAgCAIDo3oyzFuDaWOBsRRUefhtz3HmLhXezeVj/l/E5zayzZQv5/wOepTf0+SpEdGfI/f8kBXfnzQAFDICGBz8EBJQEc/FAQSgJiiJIABJNgAMyTwAHEnuWUsjzN2asuitiFe5u2x8Qds2jawvncHZNJZYCIG4zkIPgrejZs7Fmb3Uc/q9sV0vdgnJ+HI6t0Z/Bd1MbGyVETGgi4dWTEWHjHEGNB8C4lTatNpt7G9kq7tpazd3tzdXf/APcl+j6DmGxZyz4W33wF2fcNp5vnleytloKl+79xQPa18v4j9X+BNX0JMLeLtnwm39E6L4te2/vSWhp+x9xiG1r1x7X73+KLFin4NJs7S+mbE8cHUs7rX8GuMjfsUG3QabvwWVG2dT3by8TUunn4OmvpGlwdM23GSPrG7uLohce/ZUJ7LU/q5+pbx27u/W1teRz7plqMxCiBdJF1kYveSG72j9YWD2n3tUC7Q2Vc+PkXGn2lRe8Rfqa/cw8+9VxaAPQwSAgFufJALIZF+fNDBDTnz3oDMNZTvo8L/wBVw/xNQtk+nka4dfMwpazYEAQBAEAQBAEAQBAEAQBAEBv/AFASkYDpZY2JpsNHkaiUEeYJCtNG2oW4+yvcotoRTu06f23/ANWaGl59FWd5eIpCwekSefVAShgW559yAmyGSHfegKOfihg+1FRvke1jGl73kNa1ouXEnIAd5WUm3hBvCyzuDon9FUSvbNI0dixnq3C8VObZxQk5GXeHSbwbtHEjqtFoowSnLizhNp7TlZmuDws8/wAzq7SHWlTYI3q8PEcbhfamOyZnut9YuNyPde+eZdvVw4Qw+1XyOThbbKea/Xr5eRzprR6Z1dKSHVksgHDby9OH96r3qqqV8EUvIva9m36n4rpyfzNEYp0hqh7i4yOPvce9Qp7Sk+/1LiGw60uRRTdIOo/zht3XyWtbRl/rN/8AwdW7/wCGZaIdKCoiybK5o47LrfDcplW0Ivmyst2Lh/Cbo0N6WM9xefbF82ybMjT4G+f2FWEdRGfcVduzZ1rKbN34XjmB40zq6mKOkqXCzaqnNgb/AOdiJsRfi08d2SzOufOEk19l/gyLVdGHw2Rx/MuXzXU5a6QvQpdD1kjIgBmYq2Bv+LSg5jrg24bfi8WIN7jequ7RRtTcFiR0Gm2pOh4m8xOINItG5qWV0M8bo3ttk4bxwc0jJzTvDgSCuYsrlW92SwdvVbGyKlB5RbWFazaVrIFlgEObz5oChp580Bl2sV30eGf6si/iahe5dPI1Q6+Zhq8G0IAgCAIAgCAIAgCAIAgCAIDfOon/AOQ6V/0GGfxMv2b1a6P6u3+le5Sa/wCv0/8AW/8AqzRch58lVF2gAgJCGQEMABASTz5ICl6AgDnzQHUPQ61CS4jUMe2zXSbWzK7JlNTxkioqnHZIFh2GeNznkDd6KhJdq/ku853aWqUX2WfF/kdd63tclLh1OygoB1VNTt2BkA6VwAaZX23lxF/dZdJ2nZwy+fscJGl6mbjHlk4Y1m65JZ3EbTrZ8T965zVa1zZ3Oi2XGuKb5mo6zGXOOZPqqaUmzoYxUVhHmNZlv5uOeSvOT2UNqyOKwCqLECOKzk87qZdsM0rew3Dj6lb67nHkaZ0qSwza+gmu10bm7TiDfeD/AHq1p12HxKLUbMViwjuDUf0k2yxCCe00LgGua6zsiLHf9q6Sq6N8fE4bUaezTTw1lGD9LroxwSwMq6Zw9mld9DLa5pZXXvFIQPxD8ha/ZdskeEDV6VW8HzRcbN2h2DyuMXzR+dWN4M+nlfDK0tfG4sc08CPtvvB4g3XIzg4vDPoVc1OKkup4mLWbCtZYKH8+qwChu8c8UBk+nEt48Otww9o8/aqgn4le5dPI1w6+Ziq8GwIAgCAIAgCAIAgCAIAgCAIDeuo0n/AelA74cMHl7RKT8ArfQ8YW/wBK9yg2k8X6d/zv/qaPcefJVBfklyABAOfigIQC/PkgIcgLtongTqqphgZ9aWRrPcC4bTvIXPktlcO0komq2xVwcn0P0/1fRx4RgjTEAyStYRfcRR073xwtHcJXtknd37bSdy7mmmMIceh8w1uoldbwOMtcunbpHyDavnl6qj1V74rJ1WztIopNo0NU1RcTcrnuZ1KWDzkoZIKAjZQyUkFARfnyQwfanqSCsp4GDZurjWPLTyNIOV8x4XVlptS4NcSo1ukjbF5XE/R7o1604q+B9DWBstLUsMb2n8nbs24O8EX3jMb8l1u/20FKPNHzSyt6W7clyZx1049RDsMrJHt2niN0bTIc+sp5G3p5T3uBBic7iWg8VzWvq/iL5nebI1Of2Un5HKQPPkqQ6YrCxkEOWegKOfigL7pW+8dD4UYH/wCzOvUjzHr5mPLyeggCAIAgCAIAgCAIAgCAIAgN46kZbYJpN4xYaP8AnTH5b1b6F/Bb5L3Of2ms3af+t+xpJ3Poqg6AAoCrn4oCQEBQgItz5IA5Abp6K+hz6vEfo2lzg1sUdhulqJWxtOdvqR9bIeIDCVa7Or3rM4KLbFu5Q13nX/Sg0sjp2iCK2zHG2GNrcg2Ng2GNAGWTQL+NzxXU6ye7HdRw+za+2t3mcD6XYmZHm/f4ri75NyPptEcRMWeopIKggLxololUV1VBR0kL56mpkbFDCy21JI45C7i1rQMy573NY0AucQASMpOTwjzKSit6XJHY+iH4NF+yRiWMQQTmOQshoojUsa/ZBi62oldC221tskayK12tLZHh11eVbKnKOW8fI5e/b9VbxCOTw6c/g2ahsJmwrEY6x3WFopqmNsDnNEJf9HUxSzQvlc8CMRvZA0E5yDZJWbtkziswlkab9IKbHuzi19/qcdaQYDNSzSU9RE+CeFxZLFK0skY4DMOacx39xBBFwReiaaeGdSmmsotqwZPVRVNiOGazF4Z5kkzpHo76xnU8zRtGxIyz7x6LotBqdyWTktr6JWRzg7d1/wCj0WMaPQVbmhzoHGjmdv8AoKnKMk8ermDS08M7WvnYaiCcnH7Sz8zmdHe4RU+sH+J+RldSGN72O+sxzmO97SWn4hcdJbraPqcZbyUu/ifO68noW388VgFHPxWWC9aTj6Oi/ZB/Ezr1I1w6+ZYV5NgQBAEAQBAEAQBAEAQBAEAQG6dTklsE0k8WYaP+bOrbRPFdj8F7lFtFZuo/qfsaafz6KpL0EfP5ICQ7nzQEX580BA5+KAkHnyQEH70B3v8Ag99A3Q9bWSC2zBNVgkfVB2aSn3/n9bUSt7xEfeuq2XTiO8+bZwm3dSpS3F0Rbte7evqHuF7C4Hra6m6x70iHslKKycl6Y0ZbIuSuj8R31Ek4mLyt58lFJJDUBtHo060ocFxzD8UqInzQ0r5usZHYyATU8kHWMDiGudEZRIGuNnbNri4I3UzUJqT6EbVVOyuUI82jvXQ7XvhGO1seHwYjWkTOPVRU0NRSSuswvk2iG9kMZH+PMmy0F4LdpzSesjr6bIvDeT5+9k6mqWZJY6vgbx0JphRzGmMVVI1tQ0009U+ORxZ1TXdV14Imk2XlzB7Qzbv2duSwJkVtuG9kiXwjC3dSPz9/Ch6JMptKDKwvLq7DaKqmLrbPXBslJaOzR2Gx0sYzLztB93cBx2p+sZ9C2dLeoRyK4KMWQBz8/mgM51c1+zK3Owy+1StO8S4kXUQ3oM/TrUjWGr0ZxWnvd3swlj4/SU7utH9VdZNb0IS7mfMFB1Tsh3n5c628NEWJVrRkPaJXf7Z6z/qXJ6qKjbJf7zPpeinv0Qfh7GINUUnorK85B8ufivTBfNKvqUX7GP4idema49fMsC8nsIAgCAIAgCAIAgCAIAgCAIDceqWW2B6R+Iwwf82oNvgrTSfV2eS9yl1yzfR/VL2NQO59FVl0VALKZkp2efNYMEDn1QAIABz5IDcvR81Az4tPG4xuNP1zYwNzqmU2tBGbiw3dZJcNYL577T9Lpu0e/LgkVWu1yoW7HjJ8l3eJ+r2keryLA8OhooGh01U6M1Uw+p2WFscMeZ2YYWkta0b7lxzcV1egxZJyXJckfO9qNw49XxZy7rB0DkeXBsZIG4gb8ypV1DcsmnSapQXM5t1j6snm7urII4WKor9LwbOv0e0I8Ms03imi0rDmwqinTKL4o6iF0Z9Syy0xG8KO0SD5c/FeQZ3qM0snosYw2op3ujlbW07Npts45ZRDKwgggtkie9hFtzrixAI9QeGarYKcHFnZ+s/8IxLQ1uJ0FJh0czIZ6qljqZap9+vikcwVAjbFnG1wLhEHtuQDtjK1rPXPG6kc7DY0W99y8cY/E4V0806rMSqH1VfUy1dTJ9eaZ2062ZDW7msYC4kMYGsBJsASb1Tk2+J0tcI1x3YLCMakYvJ7KG/NDBlehx+lbbvH2qRR9Mj6j6DP0w6J9f1WGYpI76kdDUOPdcxloHvJIsu2f1MV4o+Z6hftpY7mfm3rsqQ7FK0jcJi3zYxrT8QVx2s43Sf+8z6Js+ONPDy9zBg1QixKwVgwfMc+qyzKL3pT9Si/Y2/xEy9SPESwLyeggCAIAgCAIAgCAIAgCAIAgNs6s3WwTH/1sMHq6q5/91ZaV4rs8l7lTq1m+jzl7Gqnc+irmWxUVgE7XPmgIuEBLQhkhhz57llA/Vv8HKIWxUtRLA32Wkw4SNqpDsRRVZcesBJs25DnOub2LW7siujjBy00Yx68zir5xhqpTn0fA6H1jdIPA6mR0IxqhjdfNvs752A7r7YBYPffz79uml2HDGfmir1yeobm84fL4WWrCKabqTPRz0GKwDMuiaNsDxaCXC3uV1XcpvEsx9vyKKdLgt6OH4Pge/D9FMKx2BzRFHHO3sv2MnNcB+U11itN0pVPi8okURjaklwl9xyXr76NTqF7mloLHAlpy7Qt8Cm7C2O/H5kivWW6ae7b6nEmsDRcwvdYWF93ouZ1lG68pHf6LU9rFcTAKin581U4LXJlGqCK+L4UO/EqAetXGEXMPkWzS2rL6ure4kudVVDne90zyT6lYYXIsr3oZPk9yAMjRgzjVzhhfOweI+1TNNHMyJqZqMGfpRo1RCg0XqNvsur56enB72MLqibvyEcOfvHeupt4bq7uJ82jmcpN9XhfM/KvSTFOunmlP+Vllk/23lw+1cda96bfiz6fTDcgo9ySLeCtZtK2rBk+R3896yEXvSlv0dD40Y/iZx8l7l08jxEsC8HoIAgCAIAgCAIAgCAIAgCAIDaert9sEx3xkwv+tVKfp/q7P7fcq9Uv29PnL2NYubz5KAWgc5AQefVABz6oCQckBk2rLQSXE6+loYsn1MzY9rgxpze8+DGBzs+5b6qnZJRRovuVMHN9Dd+u7Xo50cWD4e58OF4XtUsDWuLTVPhPVvq5tmwe6Z4Lmg3AbsqZqNT/AA4clwKvS6FN9vasuXHHdk0S3HnhxO0b333N737z6qArJd5buqOMYNtaldfWIYdVRTQVBZsuaXNtdrwCbtcBYkHxVjp9VOL4vh3FPrNn1W8UuJ+gtFrFbFUYdpFRANpazZp8SgF9hkrxsl4vaxa43BsLgjxXT47SCycJvdnNpLijorSarpcapersBK1rix1wbkZFt7ZG3fe6011y082+cWLbq9bXjGJI/PTXjqDkMkwAALXOG7nepmo03bRWDzs/Xuie4+hyRpPoS+Em4OR8e9cfdpHXzPpWn1UbVlHl1dDqsTw55y2cQonX7rVTDfysoCXEmZ4MtWsKk6uvro93V1tUy36tRI35Ly+eD3HijHnHnyWAQAgPXTQ3I9/zXpIxk3pqW0ZLpmG17kDjxIV9oKN55Oa2pqd2OEdU9LnTgUOBUlI0kPZTyutfPrq09TGffHBFI8eEg71P181BSl4bqOb2XU7Jwj4tvyPzReefJcgfSiooCppQFLBnz3oDJ9Pm2Zh1v0Bv8XUrZPp5GuHIxJazYEAQBAEAQBAEAQBAEAQBAEBsfQiS2D4x41GFj4Vh+Sm0v9nP+33K7UfW1ecvZGvn8+ihFiUH7/kgHPxQAc+qAlqGUdG9CHR+WXEax1PG6Wriwqr9kY0Xd7RM0QxkX3EF+/IWJvkrXZ8cyfkyk2rPFcV0ckYZrG1V1WFTy0ddF7PUw5Pic7bkJLQRISzaYI332mkuBIztndRrKmviZYUXxsilHpw9DWlUy17jnnncoZKPbgta5rxskjPh717g3nga54P0N6O1S+q0Zx2F4LjBHDNECDcShgebeg4ZrstPl1RyfPNdGMbnn7jYuoHTOrpurjcx0h2Gvddt9lzgCRcA+611a9mpQxPgc3O3s5OcO86Qr9FKPEmbckfVzPByIIu73FQ1ZKmW6nlImRhC74sYkcR6/NR4a6RrYyCCRkDkb5Lbfp43RU0btJrZaaW63wOPsc0WfTVMd2EOjljeLjix4cPK4C5W7TbkuKO/p1SthmL6Fp164ds4ximyDZ1fUyAeEsrpf+tVtkcSLKqeYria9MPPktODfk+sEPgUwYbMs0T0dMkjbg2J7j3qbTVvMh3XKKO1ejLqu66qhY1l7uYBkd22ATl3bz5rq9NHsk2+iyfP9pWystjFPman/CFaxmVOKOpoSDFC42I3FkbW00BFvzo4XSf+u657X3OWIvzZ02xtOo5t+S+XM5Ivz5KnOoKigJAWDJTx5716YMo09/F4b/q9v8XUr1N8vI1Q6+ZiS8GwIAgCAIAgCAIAgCAIAgCAIDP9ET/4TinjV4WP+CuPyUyn6uf9vuV+o+uq85exgsnHngoZYshADz6oYJa2/PigNzaK9FrE52xl3UU7pW7TIZnSuqXMO5/s1PDNKGn+c1p77K0q2bbYsvC8+ZS27XorbSzJ/wAv5nWnRE6K2OYRiD6xrSdujqGNk9mqWMZJs7cReZ4oxs7bW+Jzy4Kzo0f6tJ78ovKKTXbQ/W4KNcGsccs5X6QGsKvr8SrJsQLxVGZ7ZYyT1cbmdgsjYc2taBs7NyN542FTqXiWDotDCMalJPP5mpJYST5/NV2MlkXTCqB7HA2zOQuAeOdgb5+IUmMHE0zkj9JOjPTupdGZWyl3tWLVEcFOHC4MWy1u8jJo7VjbcPdfrdNGW7FPlzPne0Zwla4x6nfuqTVbSU1MxgZG94a0SP2QSXAd9s1Ta/WWSnjLSL3Zuz6XDik2ZhiOgUEhadnZLCHN2bC1uHiPf3Kvhq7IlvPZlUuKRpbXjqnDmPeGizs7jvHz4rptn65TW4zh9r7LlU1NH55a59WW3KDaz2uG/K/at3Kw1NCksshaDXSrW6zQ+vPQxzsUr3AWvVS/B1hb0XMX6fLyfQNJet3izVc2h0nAc2Ve6Giy7VHvwjQV5OYsttenbZrnekjc+rbVw9z2NAyyJPp96vqNK1jByuu18YJo730E0YGCYJXYm7ZbK+I0lHfeJ5zsdYNx+iaXPNjuaVv1U1vRpj5y8l0KnSVucZaiXLGI+b6n5A6zdJfa66ee92ufss4/RsGwz/haD5rkdRPesbXefQtHV2VMYvuMVaOfJRyYVoCWjnzWAUWz571kGTadPvHh37A0elXUr3Lp5HiPXzMUXg9hAEAQBAEAQBAEAQBAEAQBAZvoy8/4LxEWyNXhtz4iOtsPPP0Uqr6uf9vuQrl+2qfi/Yw1x3+/5KKTiSgwUnn1QwbO6OGBRz4vSNlAc1hknDD+VJBG6WNp7xttabcbW4qy2fFSvjvd5VbVslDSzcHh8vVo3/q115VtDPNUk9ayZ7jUMuGyOufyJb3BjH1Qbt7xuXSwvcJSb7/uOVv0EbakocHhfNs7a1M69oK2GRtPWVAbK2xBe11RA62TzC8kuAPFhLSO7O06Ua70pRSyc3+30Vjrsbw+ppnpI9Gmnxd/theyhxFoEck9j/g/E8hsVAdmYJn7nNeQQRYg5OdX36CNnHOC70e1nQt36SNAVfQzxOEZQie+50J6wHxBYTl5qK9mtci0lt2tvBn+h3RONOWT4pUU0ELQC6BwDqhzczssjJ7JOQ2nbrk2UuGkUHmXHBBu2w5pwryvE2VhGs6V+I0hhpRLTUjmhrGjsMiY0hoZn2iBYlx3m9huVglvLdiUDar/AGk5cWd1aF63YY6V9S6OdrXu2hE9ga9rjYGxJA2DvBNuK57UaCVk1FM6DRbWhp4Sk0Z/ojp+yribIGlhe3aDSQcrlu8eI7lV36N0y3eZ0Gj2tHUw3sYPXVNbWQuaMnZjPgR9oK1wctPNM3zUdbW49Uc7ad6h2TTxtc0tJe25DbtI2s+B9666rXpwbbRwGp2bKE+T5nKuvPUc51XVSCO+1PI7s3BzcSN4I496mRo7WKkupF/Xp027jfI05HqQmcSOrcLd/wD7KM9H4FxHa3DmZFo3qFO2C8HI+P2LZDRdSLfteT4HU+pDo5l7mFzdlu0DnkbeOXwXrUaiGlg8cyDVprNdZjozWP4RvXayGKXCqTZbBh0bY3PGZkr6lro3Nve1oIHOJ47Zd+aFyqlPs5XSfFnaxqj29enh9GPH5o/J6beqbOeZ1xDefRYBDkMlTChgh7c+e9GDItNm2jw/9hB8jV1JXqR4iYuvJ7CAIAgCAIAgCAIAgCAIAgCAzPRt/wD4ZiH7Th/psVil1fVz/t9yDd9bX5v2MRPPoohPKuCA+Z59UMGTat9MnYfW0tYwbRp5WvLfz2ZtkZ3dtjnNzyzW+i11TU10ZF1VC1FUqn1OsotWtJiLjPgtdRSxVB632OWojhqKd7xd0bo5CDZpOR92/JddF13ZlW1x6M4vtLNLFwvi+HJ44NefIznV10OsdbUwzQujgLJA4Sx1ER2AD/MebtO4jcQV7Vbr+Lex6nizXV2rd3c5XXB2ZpNqSnkB9mxGna98TfaaWaF0lNNJsAS3aARsPIOQ78rLP65J84v7vzRVx0Va5S493L8GYQ/o11RdsAvohbOSgq3CA7svZqjq9lp/mSZcLZLa7o46keVU4yxw8+BbsH6EkAqBLNiFRUPdfJw607Wd3FrZZMx7z8VoVsYve3W/N4JLlJrczFeST/E2/Dqqwdsfs1HLEys2Wxhr29W8uGbiGvtm7ja6V6i6L3pw+HwPN1GnsSSnmXc+HE9ulGjFR1MLHkt2G7LxwJYQCPNov5+9SKdTCUm0QNTprI1ceXI+OrzWTHFWR0TO0WRPvb8kZuF/UeqxrdKrE5N8TZs/VOqW4lwNp6vq6odI60cgYTtF5HZHhnvPuuqPWxqUEs8TqNmyvdmUnjy4G0H0rTmWgkd/Bc8pNcjtJVxl9JGIY9qrpKja2owCTe9s7991ZU7Rtq5FHqdjUXccYMSl6O9NvDgP/T4W71aLbU8cYlO/0cguUj74dqQooO2/ZkcN23YNHcLXK1z2rdb8MUFsWmr4pSRpPpLdLSnwaF9Bhzo5cRkaWl7LbNIDlc5EGQcG8DmbLVGl2yzbnyNzsjCLjQl4y/I/LDpTaSO2aSmdI58ry+tqHOcXPdJLdrHPJzLnAyOz71jaclFRrXz/AAJGxK3Leufkc5k/JUR1ZPPwQFLkMkx7+e/koCSc1hgv+mH4vD/2EfxdQvbNcevmY0vJ7CAIAgCAIAgCAIAgCAIAgCAzLR1t8NxD9ow/+rVKVV9Cfy9yFc/2tfnL2MRPPoopNK2lAUnn1QyTbnzQwZrqe1az4tXwUcGTn3c+Q/VhhZnJK47wGg5d5IHFS9NU7rFFeZB1uphp6ZTnjHj3n6e6L6YwYTTRUGHNL2RBodNI4l8r7WLnHiXbwNzRcDeSe+hXCuKifJrrbL7HPl3YPsNfta2do2wxttqwvutcbrdy8yUO4RjPvZjo6QmJuldeclnadmXWDR4bu5a1OC/dQnTJ/vs+NHr/AKgjaluHg5SMJBHjcWIzv3+aldrFr6KI/YTjxUm2XlmtZ1W1pqHdfskFkzezURe54sTbx+9eY7uOCPM9/hvczbFBr96uk6mtd1kZBbT1rLkf0dRxZI0H61rG29RXpYxsVke8mLVSnW6pceiNOaitZbJNKKkB4MdrMN7g9loyz/mr3bJTlNRfTgbKqnVXGclzfE/SvR7EI3MAa4EgC43H32XCaiucZfEfTNn2VupKLRd3FRUWjeDDdN8QlLNmnl2Hgm5A2uG42uRx4K20dcd7NiyjmNp6qbTjTLDNRaR6R4pTsdMauzAdloLdsuNr/VIaAAAb59y6SqjS2yUVA4u7Ua6tb0rDj7Xd0k8V+kj9pc03ILowIyRwsQA4ZeK92Rrp4RSPek7TUNOcmznLRRrquqBkcSS7ac51yTc3JJN/FRq+Mt7oXep+CG5HuOddc2lvtmJVUwPY2xFGO6OLsNHnYnzXMau3tLW/l6HV7No7HTxj1xl/MwNv3KGWZWTz5ICl3PwQEX580BVfd5fahlF/0wbaOgzv/iQI9xqqg28ty9SPEepja8noIAgCAIAgCAIAgCAIAgCAIDZeqzATVUtZTguvLU4e0bDOtdk2rdlHtxh27O72gC5vkVP00N+E14L3K3VT3LK5eMvYwrSbDRDUTRASARSPjtK0Nk7J2e21pIa643AkBQWsPBYxe8sotzQsHoOHPmhgi/PmgOyuhdgLYMMxHESLSzzx0LH8RBGwTThv67yxp79kdy6jZFPwyt+SOG/SK1TcaHyxl+v5G/MNwdrnM33A6yQ8BfMgd2y2zR9y6WMN44yd27hFudAHzSPP1GQuP/CQ0X7yo7XEmVzSS72YfCP8WdMb7T5OqaB3F1/RaFB7rZ7lNb+6+h95MBc2K78jexHrn6ral8JqcouxJFp0RqJWSOkjvstuD+aTbcRuOVysRz0N9m41x5m0sA0vZsPIY0g2bUwO7TJGHLaA4EHiPBSq5R6lXZCS4R+TMA0o0JZhFZS4xhxd7FJO1s8RO0ad7hkC6+cbjmDw3cFFsr7KxTiuDLOm56miVE38UeTP0P1R6wxUPZLc7IYWu4hz9gHh4W+xRNoaVbix5m3ZeqcJLPTgbpOkF4y5t3W3gXuPEDefJcr2HxYZ2z10nXlGoNIa+TtyROfE4uPaJc9pNtxa9xte1+zY/PqKK4JKMsHD6mybm5RyvU1trBxDFJaNwcYurYb9cBZ5uLm17kZeHmrKqqmE81ZyyrutvnFOzkcEa02StlPWXO0Sdo5381D1EcyeTotmSi45R4dGqEwUtZV8YqWokB7i2I7N/PgtcY7tUpdyN2ot39TCC6tHF877k8c9/HeuI8z6KljgfNoQySUMFP8AegHPxQHppYb+vzXlmTINPfqYfb9BA9KupC2y6eRrhyMTXg9hAEAQBAEAQBAEAQBAEAQBAbn6ODztyhu81NJb62X0NZ2uw17xs77tbcAX2o7bbbXQc5Ly9yo17XwZ8fY11p4+9dWGwF6qoybbZH0rvq2awbPdZrcrZBV0/pPzLKrhBY7kWILWb0QefVDye/AcIfUTQwRgGSeWOFl923K8Mb8XBeox3njvPMpKMXJ9Pw4n6OYVofBRQU2E0l3xU34+U2vNUOs+eQ7u6w8LDgvo2noVUFWunM+O6zVu+6Vz5ZwjL6OPYp55B9aRxYCN9vDuurBrdjlc2VCe9PjyMX0il6tjGD/KN7XiI2DL/aKg2cCzg8vBbqmnax9NBwYWOd/SfWPoMlrXB4Fj4SlnofHH6/6F773AcfVzj8d1vcszZ4ri95HzhIijZELCzGvf37Ugvbybb+5eOhIS4vI0JksXSEdl7jFnuIN7n5+i9VfC2+806hrCXcZroBLE989BUgPpp2GKRp4McOzJH/PjJDgRY5HvU3Dkmiu31GSlE2HqdmqcMmfSVEhLqed7G3tsyN6oOY++60gIIz4rzCrtYYZmy11WKSOkdH9ZETmFjngTOsQwAuIFyBfZDrAuvYutkqS7RyUspcC/o2gnFxzxMRn0sqo5th4tHJJxaLHayG648d91Zx01coZXNIrJam6E8Pkzza2NOKSCjtM522H3Y1pbsuu2xaQTc237lFojKue/0JF9isiodThbWZpHSzvfs5dw4d9/DuXq+2DeepI0NFsEY7phL1WjuJOBFnQMj/3sjWn7VFvklpZssNNFy2jVk4jec+e9cRjB9NDQhkEc+SDIO5AUnn1Qweqidz5ryzKZd9MT2KH9kd/G1S2SNceRjS8nsIAgCAIAgCAIAgCAIAgCAIDPNXukclJT1U8Wz1jJ6Mt22B7M21LTdrsjk427jYixAUyibhGTXh7kDUVqycIvx9jD8RrHSSPkdbakc97rDZG08lzrNGQFybAZDcojeXknJYWD5ALBkhw580BdtDsfNLVU1S1oc6mqIZw05BxhkEmyTw2tnZvbitlc3CSkujNVtfaQlB9U16n6cassXpcVDq+iftxyte6WMkdbTTPaNuORoNxY3LXWs4fH6VpdRXet6DPjOv0lmmm65r5mUP0ZfaKHItJuXA5DMnn3qc4sqo2JMnSTQYGVh2W9W1oz48DkOIJWns1LibI3bqLT/IoOD5dnth7iPcSBe3C1vtXqVJ4/WOaZ4IdXO1TuY9pIL25+5xI9fsK89hlGY6lqSwejEtVD3uc+1gQN+VwBYem7yWt6fJulqpLoeGTVrKIYWsFgxzy4nvNre9ev1fPBGj9ZzlsmlwF4qoxHs3a1hcXEDIizxn62z+1SOza5EXtE0zMukbi7aWlo6trr7VW2mc4bz1cb2NceNrFo47goXaup4LOvT/rMV/vIzzo+V3Wydc293xC7tobW1ch1yb5AtytmRbuW3VtSq8zxovhtcX0Mo0vx8ROvLC59nhwIJAHe4jdtDLO3qtOmq31wlg2am/dniUcloxB8OI009MRG2Jw2miawLZG7jtdk3JJ7QISyhxfHiYq1ClwjwOCdbmrx9NM8NdtNDrdl7XgHeO0Cbg+qq7K0nzOo2fqW4OMkeDT5pOjla3u9lcbdzZm39eKj6pZ0kzfpcf8AI1/P2OOH7/MfauNPpDJQwRz8EAQFJ59UB6qMeCw2C86ajsUP7Gf4ypXuRrhyMZAXk2EIAgCAIAgCAFALIAgCAIAgCA2FquwyKaOeKYkRPnpdstNiAI6kix4doC5U/SRUsqXLh7kHUtqUXHnx9jC8VgaySRrTdrXva0g7V2gkAhwydcAZjf4KFJYbXiTYvKWT4XXk9FVufNDB825IDJ9BdZFbhs4qKGplpZgLF0ZFnN/NexwcyRvg9rh4LbXbOp5g8eRGv01d8d22Ka8f9ydA6P8A4QLFGN2Kuloa24zfsvpJXcLuNO4Rk+6Jquq9s3RXxYZzl36N6ef1bcfv9zKKP8IDTODBLgjrMzAjxOcAceyDH8CbKUttv7P3kD/+Xxys9V/6de6v9OKSqZS1BourfVUragN9qnkYGvcLMHbaxzmi20QAL3XR0WO6CmnjJw+rpVFkoOOcPHUyOt1gRMbZrYmAOIIDNs3BORMhfuyz38VJjBc22Q3dLlGKMdxnHmzPDZJW2kaCwtY2N1gMxdgbY+XctmIrkzW7JPmjHKvC4De1RUGNztnZ65ws7eM948M7LZGEcZbZqd8o8MfcY9jujkL4yaVs3tAlYztSPfthx2cw4kDPutuWJ0p8Y59We6tRibhNc/As3TVxptPheH0W1d7ZuscQfrO+qXcctoO772XP6+1xWTrth1qVm6uWGZv0TtJpJ4IHNs8uAaCTssZY2J7Ns75WKs6mrNMpN9Sn1UOw1jhHvOltNcLa9mxLIS57d9mtAuM9nZAvccXXPFRKJuMvhRu1UO/uNcYDolSxsfFOx1REXv2ttzhYODW5bLtrK18ja/BT7d5rKKmpRi8tHIHSA1cSUVTI6IuNO47TBndrTwufrBp4g9yqr6nzOt0OphL4ZLj+Bg+I4j1mDYlCcyaVxaO8x2kHjfsnnfDu40Tj3ol1JR1tUvE5BnGfPeuNPo5A3IBz8EA4ICgnnzQHtoBz5rywXjTV3Yof2Mjd/plStkjzF8DF7ryeggCAIAgCAIAgF0AQBAEAQBAbD1WyRiOo614jj6yDaefybw1QbwdvcWi+yTnlnZTtM0t5vw9yBqU3KGPH2MJxL8Y/O/adn4G9uA4cbDyUOXNk9LgfJzrLAKXP581gEhyGSi6GAefRAShk6h6MnSlFH7Ph2IW9kY93s9V2jJSukcOy8D69OXHPcWXvnbLodm7S7H4LOXLPccbtnYi1G9dT9Pm13/l952bPFHKZJA4WdYvaM7O4uadzmubYhw3iy7NYnxR80kpReMcuZhuL1BERbn11IQ9v8+Mfey/mON1onLgSK61lN9Txe1vlE0DLlzupqILb3WcLt94vu35L1vOSweHGMW5NGf09YynY4FzetDRNUzXHV0zGNO1d27b7gLqfv4jnw4lbGp2S3UuL5HCPSL17jFKwuaT1MfYiBuDsN7Lbi3dmct5O5fP9oa1Wy3VyPr2xtmPS1Zn9LBvboTa0QIn0gILhK2SMHcdp5ufe08PHirzY93aVyqkcx+kmklXJXwXmd5Y/pcx8AcbPla36rRY3DdwBGzv47Qy4KyhRuzaRQWXqyC8jmfGsWr3VDS0uYHSg2aRtNJIvcZ8O9SJqeeHIiVOvdafM8fSRw3EWUrZHyieGzgTZpc2xtZ1hu3eGfBQ9RvJf+Fps9V9olI5S0b0qYHuhmIDJA6N3ucNg/aqNXJ/DI7KzSvd7SHNcTQulujzqaolgdvjcRf8AOac2uH6zSCuYsrcJbp19Fva1qX+5LFdajeSRz5ICHoCk8+qA9dDJYry0ZyXbTF12UP7I7+NqVskaoLgY2vJsCAIAgCAIAgCAIAgCAIAgCA2Jqlp9vrWjeZqcDdvMdTbeC25OQ2su+wuRO03KXy9yv1X0ofP2MGrzd7veef71CfMsEfJw+awZKefihgc/FAQPu+aAAc+SAEoCoHnzQydJajOlOKSKKhxBsroIzsxVUXanhYSbMew5Sxt3AbwMlf6LabqW5ZxXscltTYnbt208H3Pl/vmdbaMSiuayWikpMQZs2HVyBshZs2s5hcHtNt9xlmuorthNKUWmcDfprKpbs8xx4Htxmjkw+Ez1Zp8LiaC3bLzLUvaczHCwFzrmxN7D3hbp3xgsy4f74GmGllc92Dcvkcja9+kmJoHUFAx0VIXEve8/T1J37UpGQbfPYHmuY1203NbkOHf4nb7K2Eqpq27n0Xccylxce+/3rmmdydD9HWjnpKqnmDSWuIva+Q2tzjuBXVbPrnVJSON2zbCymcW+Xufo9T42xtK53Fw2gc7gFvf9y7KcX9Jdx8srwljxNGxa1Y4pnNJLiHE5d1we/hf4qF+sqLLN6BzjnkZs7WdDVUsjJYH9Xu6wZ7GWdxnvG+4sb94CzKasWTxCuUGllczgHXRUQx1jxT3a25J9+14LjtfKMZ8D6dspTlX8fE19pZpP7V1bnt+lYwRueP8AKNbk0n+c0XF+6yp7re04l9TV2WUuRiTgo5JKm8+iApeUBJ59UMkwnMe/5o+QL1pUfo6H9lf8K6qHyXqR4iY+vJ6CAIAgCAIAgCAIAgCAIAgCA2Jqmw7retZtll5ac7bQwluzHUG427tGdhc7r99lP0qypLwXuV+qeHB+LMFqpLuJN7kkm++5uTfIZ+SgvmWBSsApfz6hAUA8+aAD7kBJHPkgFkA5+KADn1QG2ui1jTosaomhxa2V0kb7Ei4dC+187HtAb/grTZs92+K6PgUm2alPSTbXFLKNr9IvS/MxFxJ7RzJNvX7Fba+3dzHxOd2HVn4jlWuqNpxPvXLyeWd6lgopz2h7/sK85wHxOxNS9cx8MTgbizQQOHDyOS7vSyi4J+B8w2qppyWOpvXSTT0Mpi24a1reJzybZXV18VD5HI0aSyVy7snEeOa0XMqS9j9zid+Vr7vRcPqNWt7gz63p9np14kb81UdIKndG6J8rGF4s5khAB7rXyPvVvpdfBxwzltobInF78V6GlukNhMbZhNE5pEl77JB43BGZVTtKMc76Oi2LObi4zXI0jK8359yoTqT4XQFYHPkgJcEBKGSmIZjnisMwXrSkfR0X7K/+NqVskeI8jH14PYQBAEAQBAEAQBAEAQBAEAQGd6tcfFMyokcXAB8P1AHOzZOAA1zmg3Jzu4WFzZxACmUT3VL5e5C1EHOUcePsYbPJck95uoZNKboCgnnzQAc+qAgfd80BI59EAP3/ACQA8+qAlvPqgL9q/wAY9nraWcf5KeJ3jYOG18LrdTLdnGXiaNRDtKpR70Z90gsT2q6oF7hrrD3EbXzVhr5708lXsqrcqwaid9/yVSXZF+fNAZdohrFno/xbsr32TmP7vJTadXOrkQNTo4Xr4i86Ra7auoYWOIa0ixDeIt71ss11k1hkenZlNTyka8lmJJPO5VzLcRzkG+eSyngw1k+0+Ivd9Yk23XJNvd3LLk3zMKKXJHmuvJ6B+5AV258kMoXQMpcefNDBXTtz571hgvumIGxQ2Fv8TPmfbKm58znyF7keY8jGl5PQQBAEAQBAEAQBAEAQBAEAQF1oJLQTDvkg/qyrZHkzXJfEvn7FuPPotZtZUUMFF+fNABz6oAPu+aAc/BACfn8kBP8Af9qGSsc+qGCmN9iPJZTBlOm2OGof1p+s5jNr9ZrA05eNltss3yNRV2aa8TFCtJJHPxQDn4oAPuQE358kAJQEHn1QFTefVAQfuQFSApcUBB59UB7aCHnzWBkvOnMdm0I/0P7aupK2T6eR4hyMWXg9hAEAQBAEAQBAEAQBAEAQBAXKiP0Mv9JD/VlWyPJnh/SXz9jw8/BazYVFAUgIYHPxQAD5fNAQefRASR8/kgHPxQFYPPmgKEM4PsZiRbnchg+Lh80BLefVAVOQFAQAZ8+CAktQE23oZItz5oCAhgi/PkgJP3/JAfWKAk896AyTCcO3Zc3W2CNc5YPvrNg2TRD/AEMfGqqEsWHg81PMTC1qNwQBAEAQBAEAQBAEAQBAEAQF0w6O8Mv68X9WVbI8Is84+JfP2PI2ErWbd1k9WgcX3Hz6k8+9DG6yNjnzWcDdY2EwYwyNnnyTAwxZBgbPPmsGCsDnzQFCAA8+SAEoCQOfNAVPKA+bUMlW1z5IYIIQEk8+aAhAAEBU2I8+5ZwD3UeEOd4e9eoxZ53kjIsMwMnhzdbo1cTXKxJGZ4Vo2RbLf96mwpz0K+y1d5YNe1D1c9Gw8KCL4zzn5qHqVieCZpnmBrVRSULIAgCAIAgCAAoAgCAIAgCAIDrHoFarsPxR2LMxCmZUsiZRujDy9uy5zpwSCxzTmABv4BdFsamu2U1YsrC5+Zyu39XZpo1yqk03Jrh5HT9D0VdH3NiL8FYwvdsyNE1SeqGwXbRcJfzrCxF8z3Lo1s7TNca0cc9saxPha36fkY1X9FbAQ51sAld2JCCysr2M2mu2WtIMzSA5t3bVt9shtAqPLZ+n5dn97N62xq0uNy/xj+R9Z+idgDXEf4DmdZ7G9ivxE3a622Wu60M+jub3dY5bJPaLcf8AGab/APP73+Zte2NU/wCKv8Y/kZBT9CfRp7GuOHSMJa1xb7bXXFxcg3qN43bh9q2/8RpX+597/Mhvb+sTxvL/ABj+R55+gzo0d1HKL8RWVX/VKfsXl7H03RP1Z7X6Qa37S/xX5FuHQK0f2r9VUbN/q+0zfbtXXj/h9OnnD9Tctv6x9V/ijVHSu6L+D4XhXtVDBKyf2mCPadPNKNh99vsvcW8L3tkqraWhqpr34Zznq8l9sjauo1N3Z2NYw3wWDjWShP5p9D9y5hHaHxNKb7j6FZwDzugd+a70P3LAINO7813oe73ICOpd+afQoCoQu7j6HvQEmF35rvQoCBTu/Nd6H7kBU2iefyXeh7vcs4MZPq3Cnn8l3oVndYyj1Q4C8/ku9D9yzuM8OZ7ItHHk/Vd5tPfzzmvfZM89oXCk0OcfyT6FbI0nh2ovlLoV/Md6Hu9ykRoIs78F9w3Q0n8l1vcVJVOCNK/PUyWg0Ntnsn0KkV1p8iPO/gZ1o9oyHPY2xtlwPepe5ulJde8pGo+l/RCPE4WAWAoIP/6zLn9b9azrNnSzSvNmjlBLMIAgCAIAgCA//9k=\" />"
				+ "<h2>Imagen 2</h2><img alt=\"\" src=\"data:image/jpeg;base64,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\" />"
				+ "</div></body>");
		return servicio;
	}

	public ServiceResult obtenerDetalleReceta(Integer id) {
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		Gson gson = new Gson();
		Receta m=new Receta();
		m.setFecha("27/06/2013 09:40");
		m.setId(id);
		Medicamento droga=new Medicamento();
		droga.setMedicamento("Ibuprofeno");
		m.setMedicamento(droga);
		m.setIndicaciones("Cada 8hs");
		Formato formato=new Formato();
		m.setDetalle("400gr");
		formato.setFormato("Caja 10 comprimidos");
		m.setMedico(new Medico(1, "Rimolo", "Maria"));
		servicio.setMensaje(gson.toJson(m, Receta.class));
		
		return servicio;
	}

	public ServiceResult obtenerRecetas(Integer idPaciente) {
		List<Receta> list= new ArrayList<Receta>();
		Receta m = new Receta();
		m.setId(1);
		m.setFecha("01/01/2013 20:55");
		Medicamento droga=new Medicamento();
		droga.setMedicamento("Ibuprofeno");
		m.setMedicamento(droga);
		list.add(m);
		m = new Receta();
		m.setId(2);
		m.setFecha("01/06/2013 8:55");
		droga=new Medicamento();
		droga.setMedicamento("Ciruelax");
		m.setMedicamento(droga);
		list.add(m);
		
		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Receta>>() {
		}.getType();
		servicio.setMensaje(gson.toJson(list, listType));
		return servicio;
	}

	public ServiceResult obtenerEstudios(Integer idPaciente) {
		List<Estudio> list= new ArrayList<Estudio>();
		
		Estudio estudio = new Estudio();
		estudio.setDescripcion("RMN");
		estudio.setTipoDeEstudio(3);
		estudio.setFecha("01/06/2013 20:50");
		estudio.setId(1);
		estudio.setIdDepartamento(2);
		list.add(estudio);
		
		estudio = new Estudio();
		estudio.setDescripcion("Radiografía de Torax");
		estudio.setTipoDeEstudio(2);
		estudio.setFecha("01/06/2013 20:50");
		estudio.setId(2);
		estudio.setIdDepartamento(2);
		list.add(estudio);

		ServiceResult servicio = new ServiceResult();
		servicio.setResultado("OK");
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Estudio>>() {
		}.getType();
		servicio.setMensaje(gson.toJson(list, listType));
		return servicio;
	}
}
