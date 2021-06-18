package projetox.server;

import projetox.shared.PartInterface;
import projetox.shared.PartRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server implements PartRepository {
	private final String serverName;
	private final List<PartInterface> repository = new ArrayList<>();

	public Server(String name) throws RemoteException{
		this.serverName = name;
		UnicastRemoteObject.exportObject(this, 0);
		this.populateRepository();
	}

	private void populateRepository() {
		this.repository.add(Part.getInstance(this.serverName + ": Joia Ancestral do Poder", "100023", this.serverName));
		this.repository.add(Part.getInstance(this.serverName + ": Microfone das Estrelas", "31930", this.serverName));
		this.repository.add(Part.getInstance(this.serverName + ": Lentes Intelectuais", "52147", this.serverName));
		this.repository.add(Part.getInstance(this.serverName + ": Adorno de Unicórnio", "31937", this.serverName));
		this.repository.add(Part.getInstance(this.serverName + ": Peruca das Asinhas Celestes", "31905", this.serverName));
	}

	@Override
	public String getName() {
		return this.serverName;
	}

	@Override
	public int size() {
		return this.repository.size();
	}

	@Override
	public void insertPart(String name, String description) {
		this.repository.add(Part.getInstance(name, description, this.serverName));
	}

	@Override
	public PartInterface getPart(UUID id) {
		try {
			for (PartInterface product : this.repository) {
				if (product.getId().compareTo(id) == 0) {
					return product;
				}
			}
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PartInterface> getAllParts() {
		return new ArrayList<>(this.repository);
	}

	@Override
	public String toString() {
		return this.serverName; // TODO: Remover método
	}
}
