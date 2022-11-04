package ar.edu.unlam.pb2.noMonetarias;

import ar.edu.unlam.pb2.Cliente;
import ar.edu.unlam.pb2.dispositivos.Dispositivo;

public class Onboarding extends TransaccionNoMonetaria {

	public Onboarding(Cliente cliente, Dispositivo dispositivo) {
		super(cliente, dispositivo);
	}
	
	@Override
	public void monitorear1() {
		cliente.darDeAlta();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
}
