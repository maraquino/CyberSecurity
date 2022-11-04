package ar.edu.unlam.pb2.dispositivos;

import ar.edu.unlam.pb2.IDenunciable;

public class Dispositivo implements IDenunciable {
	private String so;
	private Integer ip;
	private String localidad;
	
	public Dispositivo(String so, Integer ip, String localidad) {
		this.so = so;
		this.ip = ip;
		this.localidad = localidad;
	}
	
	public Integer getIp() {
		return ip;
	}
}
