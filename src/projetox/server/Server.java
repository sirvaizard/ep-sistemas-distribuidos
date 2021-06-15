package projetox.server;

import projetox.shared.PartInterface;
import projetox.shared.PartRepository;
import projetox.shared.PartServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server implements PartServer {
	private final PartRepository repository = new PartRepositoryMemory();
	private final String serverName;
	
	public Server(String name) throws RemoteException{
		this.serverName = name;
		UnicastRemoteObject.exportObject(this, 0);
		this.populateRepository();
	}

	private void populateRepository() {
		this.repository.insertPart(Part.getInstance(this.serverName + ": Joia Ancestral do Poder", "100023"));
		this.repository.insertPart(Part.getInstance(this.serverName + ": Microfone das Estrelas", "31930"));
		this.repository.insertPart(Part.getInstance(this.serverName + ": Lentes Intelectuais", "52147"));
		this.repository.insertPart(Part.getInstance(this.serverName + ": Adorno de Unicórnio", "31937"));
		this.repository.insertPart(Part.getInstance(this.serverName + ": Peruca das Asinhas Celestes", "31905"));
	}

	@Override
	public String getName() {
		return this.serverName;
	}

	@Override
	public int getNumParts() {
		return this.repository.size();
	}

	@Override
	public void insertPart(PartInterface p) {
		this.repository.insertPart(p);
	}

	@Override
	public PartInterface getPart(UUID id) throws RemoteException {
		return this.repository.getPart(id);
	}

	@Override
	public List<PartInterface> getAllParts() {
		return new ArrayList<>(this.repository.getAllParts());
	}

	@Override
	public String toString() {
		return this.serverName; // TODO: Remover método
	}
}
