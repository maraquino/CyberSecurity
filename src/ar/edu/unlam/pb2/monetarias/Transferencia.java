package ar.edu.unlam.pb2.monetarias;

import java.util.Set;

import ar.edu.unlam.pb2.Cliente;
import ar.edu.unlam.pb2.Estado;
import ar.edu.unlam.pb2.FraudeException;
import ar.edu.unlam.pb2.IDenunciable;
import ar.edu.unlam.pb2.IRechazable;
import ar.edu.unlam.pb2.dispositivos.Dispositivo;
import ar.edu.unlam.pb2.noMonetarias.CambioContrasenia;

public class Transferencia extends TransaccionMonetaria implements IRechazable{
	private Integer score;
	private Double monto;
	private Estado estado;
	
	public Transferencia(Cliente cliente, Dispositivo dispositivo, Double monto) {
		super(cliente, dispositivo);
		this.score = 0;
		this.monto=monto;
	}
	
	@Override
	public Boolean monitorear(Set<IDenunciable> listaNegra) throws FraudeException {
		calcularScore(listaNegra);
		if(this.score>80) {
			throw new FraudeException();
		} else if(this.score>=60 && this.score<80) {
			marcarComoCasoSospechoso();
			return true;
		} else return true;
	}

	private void calcularScore(Set<IDenunciable> listaNegra) {
		if(listaNegra.contains(cliente)) {
			this.score+=80;
		} if(listaNegra.contains(dispositivo)) {
			this.score+=80;
		} if(cliente.ultizoDispositivo(dispositivo)) {
			this.score+=20;
		} if(cliente.getUltimaTransaccion()!=null) {
			if(cliente.getUltimaTransaccion()instanceof CambioContrasenia) {
				this.score+=20;
			}
		}if(monto.equals(cliente.getSaldo())) {
			cliente.setSaldo(0.0);
			this.score+=40;
		}
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
		if(fueFraude) {
			this.estado=Estado.FRAUDE;
		}
	}

	public Double getMontoTransaccion() {
		return monto;
	}

	@Override
	public Integer getScore() {
		return this.score;
	}

	@Override
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
