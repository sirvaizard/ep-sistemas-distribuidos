package projetox.server;

import java.rmi.AlreadyBoundException;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServerStarter {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		System.out.println(" ______ _____  __    _____ _         _____  _     _        _ _           _     _");
		System.out.println("|  ____|  __ \\/_ |  / ____(_)       |  __ \\(_)   | |      (_) |         (_)   | |");
		System.out.println("| |__  | |__) || | | (___  _ ___    | |  | |_ ___| |_ _ __ _| |__  _   _ _  __| | ___  ___");
		System.out.println("|  __| |  ___/ | |  \\___ \\| / __|   | |  | | / __| __| '__| | '_ \\| | | | |/ _` |/ _ \\/ __|");
		System.out.println("| |____| |     | |  ____) | \\__ \\_  | |__| | \\__ \\ |_| |  | | |_) | |_| | | (_| | (_) \\__ \\");
		System.out.println("|______|_|     |_| |_____/|_|___(_) |_____/|_|___/\\__|_|  |_|_.__/ \\__,_|_|\\__,_|\\___/|___/\n");

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
