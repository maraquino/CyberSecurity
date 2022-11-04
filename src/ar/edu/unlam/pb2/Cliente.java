package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.pb2.dispositivos.Dispositivo;

public class Cliente implements IDenunciable {
	private Integer cuit;
	private String nombre;
	private Double saldo;
	private Boolean alta;
	private List<Dispositivo>dispositivos;
	private List<IMonitoreable> transacciones;
	
	public Cliente(Integer cuit, String nombre) {
		this.cuit=cuit;
		this.nombre=nombre;
		this.saldo=0.0;
		this.alta=false;
		this.dispositivos=new ArrayList<>();
		this.transacciones=new ArrayList<>();
	}
	
	public void agregarDispositivo(Dispositivo dispositivo) {
		dispositivos.add(dispositivo);
	}

	public Integer cantidadDeDipositivos() {
		return dispositivos.size();
	}

	public void hacerTransaccion(IMonitoreable transaccion) {
		transacciones.add(transaccion);
	}
	
	public IMonitoreable getUltimaTransaccion() {
		Integer cantidadDeTransacciones=transacciones.size()-1;
		if(cantidadDeTransacciones>=0) {
			return this.transacciones.get(cantidadDeTransacciones);
		} return null;
	}

	public boolean ultizoDispositivo(Dispositivo dispositivoBuscadado) {
		for (Dispositivo dispositivo : dispositivos) {
			if(dispositivo.equals(dispositivoBuscadado)) {
				return false;
			}
		}
		dispositivos.add(dispositivoBuscadado);
		return true;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo=saldo;
	}

	public Integer getDispositivo() {
		return dispositivos.size();	
	}
	
	public Integer getCuit() {
		return this.cuit;
	}

	public void darDeAlta() {
		this.alta=true;
	}

}
