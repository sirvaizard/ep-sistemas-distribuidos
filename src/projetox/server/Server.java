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
	private PartInterface part;
	
	public Server() throws RemoteException{
		UnicastRemoteObject.exportObject(this, 0);
		this.populateRepository();
	}

	private void populateRepository() {
		this.repository.insertPart(Part.getInstance("Sv 1:Joia Ancestral do Poder", "100023"));
		this.repository.insertPart(Part.getInstance("Sv 1:Microfone das Estrelas", "31930"));
		this.repository.insertPart(Part.getInstance("Sv 1:Lentes Intelectuais", "52147"));
		this.repository.insertPart(Part.getInstance("Sv 1:Adorno de Unicórnio", "31937"));
		this.repository.insertPart(Part.getInstance("Sv 1:Peruca das Asinhas Celestes", "31905"));
	}

	@Override
	public void insertPart(PartInterface p) {
		this.part = null;
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
	public void clearPartSubparts(UUID id) {
		try {
			this.part.clearSubparts();
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
	}
}
