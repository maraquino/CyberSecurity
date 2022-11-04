package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pb2.dispositivos.Dispositivo;
import ar.edu.unlam.pb2.dispositivos.Movil;
import ar.edu.unlam.pb2.dispositivos.Pc;
import ar.edu.unlam.pb2.monetarias.Extraccion;
import ar.edu.unlam.pb2.monetarias.PagoQr;
import ar.edu.unlam.pb2.monetarias.PagoServicio;
import ar.edu.unlam.pb2.monetarias.TransaccionMonetaria;
import ar.edu.unlam.pb2.monetarias.Transferencia;
import ar.edu.unlam.pb2.noMonetarias.CambioContrasenia;
import ar.edu.unlam.pb2.noMonetarias.Onboarding;
import ar.edu.unlam.pb2.noMonetarias.TransaccionNoMonetaria;

public class TestCyberSecurity {

	@Test
	public void queSePuedaCrearUnCliente() {
		Cliente cliente = new Cliente(111, "Jose");
		assertNotNull(cliente);
	}
	
	@Test
	public void queSePuedaCrearUnDispositivoMovil() {
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		Integer valorEsperado=222;
		assertEquals(valorEsperado, ((Movil)movil).getImei());
	}
	
	@Test
	public void queSePuedaCrearUnDispositivoPc() {
		Dispositivo pc = new Pc("Android", 222, "SanJusto");
		Integer valorEsperado=222;
		assertEquals(valorEsperado,((Pc)pc).getIp());
	}
	
	@Test
	public void queSePuedaMonitorearUnaExtraccion() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionMonetaria extraccion = new Extraccion(cliente, movil);

		sistema.monitorearTransaccion(extraccion);
		
		assertEquals((Integer)1, sistema.cantidadDeTransaccionesMonitoreadas());
	}
	
	@Test
	public void queSePuedaMonitorearUnaTransferencia() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionMonetaria transferencia = new Transferencia(cliente, movil, 20.0);

		sistema.monitorearTransaccion(transferencia);
		
		assertEquals((Integer)1, sistema.cantidadDeTransaccionesMonitoreadas());
	}
	
	@Test
	public void queSePuedaMonitorearUnPagoConQR() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionMonetaria pagoQr = new PagoQr(cliente, movil);

		sistema.monitorearTransaccion(pagoQr);
		
		assertEquals((Integer)1, sistema.cantidadDeTransaccionesMonitoreadas());
	}
	
	@Test
	public void queSePuedaMonitorearUnPagoDeServicio() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionMonetaria pagoServicio = new PagoServicio(cliente, movil);

		sistema.monitorearTransaccion(pagoServicio);
		
		assertEquals((Integer)1, sistema.cantidadDeTransaccionesMonitoreadas());
	}
	
	@Test
	public void queSePuedaMonitorearUnAltaDeUsuario() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionNoMonetaria darDeAltaUsuario = new Onboarding(cliente, movil);

		sistema.monitorearTransaccion(darDeAltaUsuario);
		
		assertEquals((Integer)1, sistema.cantidadDeTransaccionesMonitoreadas());
	}
	
	@Test
	public void queSePuedaMonitorearUnCambioDeContraseña() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionNoMonetaria cambioContrasenia = new CambioContrasenia(cliente, movil);

		sistema.monitorearTransaccion(cambioContrasenia);
		
		assertEquals((Integer)1, sistema.cantidadDeTransaccionesMonitoreadas());
	}
	
	@Test
	public void queElScoreDeUnaTransaccionRechazableSinAntecedentesDeCero() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		cliente.agregarDispositivo(movil);
		TransaccionMonetaria extraccion = new Extraccion(cliente, movil);

		sistema.monitorearTransaccion(extraccion);
		
		assertEquals((Integer)0, sistema.totalScore(extraccion));
	}
	
	@Test
	public void queUnaTransaccionAlertablePuedaSerMarcadaComoFraudulenta() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		cliente.setSaldo(20.0);
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionMonetaria transferencia = new Transferencia(cliente, movil, 20.0);
		
		sistema.monitorearTransaccion(transferencia);
		
		sistema.marcarFraude(transferencia);
		
		assertEquals((Integer)2, sistema.getCantidadListaNegra(transferencia));
	}
	
	@Test
	public void queElScoreDeUnaTransaccionRechazableConNuevoDispositivoDe20Puntos() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionMonetaria extraccion = new Extraccion(cliente, movil);

		sistema.monitorearTransaccion(extraccion);

		assertEquals((Integer)20, sistema.totalScore(extraccion));
	}
	
	@Test
	public void QueUnaTransaccionDeMasDe60PuntosYMenosDeOchentaSeaAlertadaPeroAprobada() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		cliente.setSaldo(20.0);
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);

		TransaccionMonetaria transferencia = new Transferencia(cliente, movil,20.0);

		sistema.monitorearTransaccion(transferencia);

		assertEquals(Estado.CASO_SOSPECHOSO, sistema.getEstadoTransaccion(transferencia));
	}

	@Test (expected=FraudeException.class)
	public void queUnaTransaccionDeMasDe80PuntosLanceLaExcepcionFraudeException() throws FraudeException {
		Sistema sistema = new Sistema();
		Cliente cliente = new Cliente(111, "Jose");
		cliente.setSaldo(20.0);
		Dispositivo movil = new Movil("Android", 222, "SanJusto", 222, true);
		TransaccionMonetaria transferencia = new Transferencia(cliente, movil, 20.0);
		
		sistema.monitorearTransaccion(transferencia);
		
		sistema.marcarFraude(transferencia);
		
		TransaccionMonetaria pagoQr = new PagoQr(cliente, movil);

		sistema.monitorearTransaccion(pagoQr);
	}	
	

}
