package projetox.client;

import projetox.shared.PartInterface;
import projetox.shared.PartClient;
import projetox.shared.PartServer;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.UUID;

public class Client implements PartClient {

	private PartServer server;
	private Registry reg;
	private PartInterface currentPart;
	
	public Client() throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0); //with 0 it will use the default port
	}
	
	public void startClient() throws RemoteException, NotBoundException {
		this.reg = LocateRegistry.getRegistry("localhost", 1099);
		this.server = (PartServer) reg.lookup("One");
		System.out.println("Found Server \n server:" + this.server);
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
					"%s %s %s\n",
					part.getId(),
					part.getName(),
					part.getDescription());
		}
		return null;
	}

	@Override
	public void getPart(String id) {
		UUID uuid = UUID.fromString(id);
		try {
			PartInterface p = (PartInterface) this.server.getPart(uuid);
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
	public void showPart() {
		if (this.currentPart == null) {
			System.out.println("No part is selected.");
		} else {
			try {
				System.out.printf("%s %s %s\n",
						this.currentPart.getId(),
						this.currentPart.getName(),
						this.currentPart.getDescription());
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
			}
		}
	}

	@Override
	public boolean clearList() {
		return false;
	}

	@Override
	public boolean addSubPart(PartInterface p) {
		return false;
	}

	@Override
	public boolean addPart(PartInterface p) {
		return false;
	}

	@Override
	public boolean quit() {
		return false;
	}
}
