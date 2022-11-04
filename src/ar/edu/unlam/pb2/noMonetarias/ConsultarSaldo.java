package ar.edu.unlam.pb2.noMonetarias;

import ar.edu.unlam.pb2.Cliente;
import ar.edu.unlam.pb2.dispositivos.Dispositivo;

public class ConsultarSaldo extends TransaccionNoMonetaria {

	public ConsultarSaldo(Cliente cliente, Dispositivo dispositivo) {
		super(cliente, dispositivo);
	}

	@Override
	public void monitorear1() {
		cliente.getSaldo();
	}

	@Override
	public Cliente getCliente() {
		return cliente;
	}

}
