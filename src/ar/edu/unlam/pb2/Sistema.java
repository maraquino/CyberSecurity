package ar.edu.unlam.pb2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.pb2.monetarias.TransaccionMonetaria;

public class Sistema {
	private List<IMonitoreable> transaccionesMonitoreadas;
	private Set<IDenunciable> listaNegra;

	public Sistema() {
		transaccionesMonitoreadas = new ArrayList<>();
		listaNegra = new HashSet<>();
	}

	public void monitorearTransaccion(IMonitoreable transaccion) throws FraudeException {
		if (transaccion instanceof IRechazable) {
			if (((IRechazable) transaccion).monitorear(listaNegra)) {
				((TransaccionMonetaria) transaccion).getCliente().hacerTransaccion(transaccion);
				transaccionesMonitoreadas.add(transaccion);
			}
		} else {((IMonitoreable)transaccion).monitorear1();
			((IMonitoreable) transaccion).getCliente().hacerTransaccion(transaccion);
			transaccionesMonitoreadas.add(transaccion);
		}
	}

	public Object totalScore(TransaccionMonetaria extraccion) {
		return ((IRechazable) extraccion).getScore();
	}

	public void marcarFraude(IAlertable transaccion) {
		((IAlertable)transaccion).confirmarSiFueFraude(true);
		listaNegra.add(((TransaccionMonetaria)transaccion).getCliente());
		listaNegra.add(((TransaccionMonetaria)transaccion).getDispositivo());
	}

	public Integer getCantidadListaNegra(TransaccionMonetaria transferencia) {
		return listaNegra.size();
	}

	public Estado getEstadoTransaccion(IAlertable transacion) {
		return ((TransaccionMonetaria)transacion).getEstado();
	}

	public Integer cantidadDeTransaccionesMonitoreadas() {
		return this.transaccionesMonitoreadas.size();
	}

}
