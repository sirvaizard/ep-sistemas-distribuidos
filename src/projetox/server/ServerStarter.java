package projetox.server;

import projetox.shared.PartServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServerStarter {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		
		PartServer server1 = new Server();
		//PartServer server2 = new ServerPirata();
		Registry reg = LocateRegistry.createRegistry(1099);
		reg.bind("One", server1);
		//reg.bind("Two", server2);
		System.out.println("Servers Started \n server1:" + server1 + "\n");
		
	}
}
