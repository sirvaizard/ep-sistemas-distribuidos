package projetox.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {
	private static final Scanner scanner = new Scanner(System.in);
	private static Client client;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		System.out.println("Client Started");
		client = new Client();

		int command = -1;
		while(command != 0) {
			System.out.print(
					"\n\n" + client.serverInfo() + "\n" +
					"1 - List all servers\n" +
					"2 - Change server\n" +
					"3 - List repository parts\n" +
					"4 - Change current part\n" +
					"5 - Show current part\n" +
					"6 - Clear subparts of current part\n" +
					"7 - Add subpart to selected part\n" +
					"8 - Add part to current repository\n" +
					"9 - Set current part as selected part to add subparts\n" +
					"0 - Exit\n"
			);

			command = scanner.nextInt();
			scanner.nextLine(); // Remove a quebra de linha deixada pelo 'Enter' no buffer

			switch (command) {
				case 1 -> client.listAllServers();
				case 2 -> changeServer();
				case 3 -> client.listParts();
				case 4 -> changeCurrentPart();
				case 5 -> client.showPart();
				case 6 -> client.clearList();
				case 7 -> addSubpart();
				case 8 -> client.addPart();
				case 9 -> client.setSelectedPart();
				default -> System.out.println("Invalid command.");
			}
		};
		
		scanner.close();
		System.out.println("The End");
		System.exit(0);
	}

	private static void changeServer() {
		System.out.print("Server name: ");
		String serverName = scanner.nextLine();
		try {
			RunClient.client.bind(serverName);
			System.out.println("Server changed to: " + serverName);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
		}
	}

	private static void changeCurrentPart() {
		System.out.print("Part id: ");
		String partId = scanner.nextLine();
		client.getPart(partId);
	}

	private static void addSubpart() {
		System.out.print("How many: ");
		int quantity = scanner.nextInt();
		scanner.nextLine(); // Remove enter from buffer

		client.addSubPart(quantity);
	}
}
