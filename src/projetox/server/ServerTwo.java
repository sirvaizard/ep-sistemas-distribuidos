package projetox.server;

import projetox.shared.PartInterface;
import projetox.shared.PartRepository;
import projetox.shared.PartServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerTwo implements PartServer {
	private final PartRepository repository = new PartRepositoryMemory();
	private PartInterface part;

	public ServerTwo() throws RemoteException{
		UnicastRemoteObject.exportObject(this, 0);
		this.populateRepository();
	}

	private void populateRepository() {
		this.repository.insertPart(Part.getInstance("Sv 2:Poção Vermelha", "85426"));
		this.repository.insertPart(Part.getInstance("Sv 2:Erva Vermelha", "641253"));
		this.repository.insertPart(Part.getInstance("Sv 2:Banana", "845214"));
		this.repository.insertPart(Part.getInstance("Sv 2:Bolinhos de Arroz", "320560"));
		this.repository.insertPart(Part.getInstance("Sv 2:Pão de Carne", "874501"));
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
