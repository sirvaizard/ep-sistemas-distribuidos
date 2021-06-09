package projetox.server;

import projetox.shared.PartInterface;
import projetox.shared.PartRepository;
import projetox.shared.PartServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server implements PartServer {
	private final PartRepository repository = new PartRepositoryMemory();
	
	public Server() throws RemoteException{
		UnicastRemoteObject.exportObject(this, 0);
	}

	@Override
	public void insertPart(PartInterface p) {

	}

	@Override
	public PartInterface getPart(int id) {
		return null;
	}

	@Override
	public List<PartInterface> getAllParts() {
		return null;
	}

	public List<PartInterface> test() {
		PartInterface part = new Part("Peça 1", "Descrição 1");
		PartInterface part2 = new Part("Peça 2", "Descrição 2");

		this.repository.insertPart(part);
		this.repository.insertPart(part2);
		return this.repository.getAllParts();
	}
}
