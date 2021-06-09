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

public class Client implements PartClient {

	private PartServer server;
	private Registry reg;
	
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
		System.out.println("listParts");
		System.out.println(this.server.test());
		return null;
	}

	@Override
	public PartInterface getPars(String id) {
		System.out.println("getPars");
		return null;
	}

	@Override
	public String showParts() {
		return null;
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
