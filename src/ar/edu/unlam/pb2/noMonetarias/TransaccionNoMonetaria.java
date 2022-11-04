package ar.edu.unlam.pb2.noMonetarias;

import ar.edu.unlam.pb2.Cliente;
import ar.edu.unlam.pb2.IMonitoreable;
import ar.edu.unlam.pb2.dispositivos.Dispositivo;

public abstract class TransaccionNoMonetaria implements IMonitoreable {
	protected Cliente cliente;
	protected Dispositivo dispositivo;

	public TransaccionNoMonetaria(Cliente cliente, Dispositivo dispositivo) {
		this.cliente = cliente;
	}
	public abstract Cliente getCliente();

	
}
