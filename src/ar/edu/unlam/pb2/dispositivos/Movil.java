package ar.edu.unlam.pb2.dispositivos;

public class Movil extends Dispositivo {
	private Integer imei;
	private Boolean registroBiometrico;
	
	public Movil(String so, Integer ip, String localidad, Integer imei, Boolean registroBiometrico) {
		super(so, ip, localidad);
		this.imei=imei;
		this.registroBiometrico=registroBiometrico;
	}

	public Object getImei() {
		return imei;
	}

}
