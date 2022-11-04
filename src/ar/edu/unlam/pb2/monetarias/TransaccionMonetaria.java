package ar.edu.unlam.pb2.monetarias;

import ar.edu.unlam.pb2.Cliente;
import ar.edu.unlam.pb2.Estado;
import ar.edu.unlam.pb2.IAlertable;
import ar.edu.unlam.pb2.IMonitoreable;
import ar.edu.unlam.pb2.dispositivos.Dispositivo;

public abstract class TransaccionMonetaria implements IMonitoreable, IAlertable {
	
	protected Cliente cliente;
	protected Dispositivo dispositivo;
	
	public TransaccionMonetaria(Cliente cliente, Dispositivo dispositivo) {
		this.cliente=cliente;
		this.dispositivo=dispositivo;
	}
	
	public abstract Cliente getCliente();

	public abstract Dispositivo getDispositivo();

	public abstract Estado getEstado();
	
}
