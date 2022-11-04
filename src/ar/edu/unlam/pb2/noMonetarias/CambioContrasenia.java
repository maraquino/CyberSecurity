package ar.edu.unlam.pb2.noMonetarias;

import ar.edu.unlam.pb2.Cliente;
import ar.edu.unlam.pb2.dispositivos.Dispositivo;

public class CambioContrasenia extends TransaccionNoMonetaria {
	
	public CambioContrasenia(Cliente cliente, Dispositivo dispositivo) {
		super(cliente, dispositivo);
	}
	@Override
	public void monitorear1() {
		
	}
	public Cliente getCliente() {
		return cliente;
	}
	
}
