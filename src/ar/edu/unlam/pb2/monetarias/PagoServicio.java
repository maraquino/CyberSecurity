package ar.edu.unlam.pb2.monetarias;

import ar.edu.unlam.pb2.Cliente;
import ar.edu.unlam.pb2.Estado;
import ar.edu.unlam.pb2.dispositivos.Dispositivo;

public class PagoServicio extends TransaccionMonetaria {
	
	private Estado estado;

	public PagoServicio(Cliente cliente, Dispositivo dispositivo) {
		super(cliente, dispositivo);
	}

	@Override
	public void monitorear1() {
		this.estado=Estado.MONITOREADO;
	}

	@Override
	public void marcarComoCasoSospechoso() {
		this.estado=Estado.CASO_SOSPECHOSO;
	}

	@Override
	public void confirmarSiFueFraude(Boolean fueFraude) {
		this.estado=Estado.FRAUDE;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	@Override
	public Estado getEstado() {
		return this.estado;
	}


}
