package projetox.server;

import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServerStarter {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {

		if (args.length < 1) {
			System.out.println("You should inform a server name: ");
			System.out.println("Ex: java ServerStart serverName");
			System.exit(1);
		}

		String serverName = args[0];

		Registry reg;
		try {
			reg = LocateRegistry.getRegistry(1099);
			reg.bind(serverName, new Server(serverName));
		} catch (ConnectException connectException) {
			reg = LocateRegistry.createRegistry(1099);
			reg.bind(serverName, new Server(serverName));
		}
		System.out.println("Server " + serverName + " is running.");
		System.out.print("Press enter to exit...");
		new java.util.Scanner(System.in).nextLine();

		try {
			reg.unbind(serverName);
		} catch (NotBoundException notBoundException) {
			System.out.println("Server was not connected to the server.");
		}
		System.exit(0);
	}
}
