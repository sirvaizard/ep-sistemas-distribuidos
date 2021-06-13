package projetox.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {
	private static Scanner scanner = new Scanner(System.in);
	private static Client client;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		System.out.println("Client Started");
		client = new Client();
		client.startClient();

		int command = -1;
		while(command != 0) {
			System.out.printf(
					"Change server: 1\n" +
					"List repository parts: 2\n" +
					"Change current part: 3\n" +
					"Show current part: 4\n" +
					"Clear subparts of current part: 5\n" +
					"Add subpart to current part: 6\n" +
					"Add part to current repository: 7\n" +
					"Exit: 0\n"
			);

			command = scanner.nextInt();
			scanner.nextLine(); // Remove a quebra de linha deixada pelo 'Enter' no buffer

			switch (command) {
				case 1:
					changeServer();
					break;
				case 2:
					client.listParts();
					break;
				case 3:
					changeCurrentPart();
					break;
				case 4:
					client.showPart();
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				default:
					System.out.println("Invalid command.");
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

	private static void addSubpartToCurrentPart() {

	}
}
