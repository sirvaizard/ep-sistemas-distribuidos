package projetox.client;

import projetox.shared.PartInterface;
import projetox.shared.PartClient;
import projetox.shared.PartServer;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Client implements PartClient {

	private PartServer server;
	private Registry reg;
	private PartInterface currentPart;
	private PartInterface selectedPart; // É preciso referencia pra 2 peças caso queira inserir uma em outra
	
	public Client() throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0); //with 0 it will use the default port
		this.reg = LocateRegistry.getRegistry("localhost", 1099);
	}

	@Override
	public String serverInfo() {
		if (this.server == null) {
			return "404 Server not found.";
		}

		try {
			return "Server \"" + this.server.getName() + "\" has " + this.server.getNumParts() + " parts.";
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
		return "Weird error :/";
	}

	@Override
	public void listAllServers() {
		try {
			List<String> servers = Arrays.asList(this.reg.list());
			for (String server : servers) {
				System.out.println(server);
			}
		} catch (RemoteException remoteException) {
			System.out.println("No server was found");
		}
	}

	@Override
	public boolean bind(String serverName) throws RemoteException {
		try {
			this.server = (PartServer) reg.lookup(serverName);
			System.out.println("Found Server \n server:" + this.server);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<PartInterface> listParts() throws RemoteException {
		List<PartInterface> parts = this.server.getAllParts();
		for (var part : parts) {
			System.out.printf(
					"%s %s %s server: %s\n",
					part.getId(),
					part.getName(),
					part.getDescription(),
					part.getServerName());
		}
		return null;
	}

	@Override
	public void getPart(String id) {
		UUID uuid = UUID.fromString(id);
		try {
			PartInterface p = this.server.getPart(uuid);
			if (p != null) {
				this.currentPart = p;
			} else {
				System.out.println("Part not found.");
			}
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
	}

	@Override
	public void setSelectedPart() {
		this.selectedPart = this.currentPart;
	}

	@Override
	public void showPart() {
		if (this.currentPart == null) {
			System.out.println("No part is selected.");
		} else {
			try {
				System.out.println("Part info: ");
				System.out.printf(" - %s %s %s server: %s\n",
						this.currentPart.getId(),
						this.currentPart.getName(),
						this.currentPart.getDescription(),
						this.currentPart.getServerName());
				System.out.println("Subparts: ");
				for (PartInterface key : this.currentPart.getSubparts().keySet()) {
					System.out.printf(" - %s %s %s quantity: %d",
							key.getId(),
							key.getName(),
							key.getDescription(),
							this.currentPart.getSubparts().get(key));
				}
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
		}
	}

	@Override
	public boolean clearList() {
		try {
			this.currentPart.clearSubparts();
			return true;
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addSubPart(int quantity) {
		try {
			this.selectedPart.addSubpart(this.currentPart, quantity);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addPart() {
		try {
			this.server.insertPart(this.currentPart);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
		return false;
	}

	@Override
	public void setCurrentPartName(String name) {
		try {
			this.currentPart.setName(name);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
	}

	@Override
	public boolean quit() {
		return false;
	}
}
