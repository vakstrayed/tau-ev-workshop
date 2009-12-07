package parties;

import java.util.List;

import global.Consts;
import tcp.Server;

public class PartiesManager {

	private Server server;
	private List<Integer> partiesNumbers;

	public PartiesManager() {
		server = new Server(Consts.PARTIES_MANAGER_PORT);
		partiesNumbers = server.getConnectionNumbers();
		server.send(partiesNumbers.get(0), null); // TODO - remove this line
	}

}