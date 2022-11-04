package ar.edu.unlam.pb2;

import java.util.Set;

public interface IRechazable {

	public Integer getScore();

	Boolean monitorear(Set<IDenunciable> listaNegra) throws FraudeException;

}
