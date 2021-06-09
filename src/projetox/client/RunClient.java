package projetox.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {

	public static void main(String[] args) throws RemoteException, NotBoundException {

		Scanner scan = new Scanner(System.in);
		String command = "";
		
		System.out.println("Client Started");
		Client client = new Client();
		client.startClient();
		
		while(true) {
			command = scan.nextLine();
			if(command.equalsIgnoreCase("exit")) break;
			if(command.equalsIgnoreCase("change")) {
				System.out.println("Change - One or Two");
				client.bind(scan.nextLine());
				System.out.println("Changed server");
				continue;
			};
			
			try {
				client.listParts();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
		};
		
		scan.close();
		System.out.println("The End");
	}
}
