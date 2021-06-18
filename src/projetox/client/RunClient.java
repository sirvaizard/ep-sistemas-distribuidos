package projetox.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {
	private static final Scanner scanner = new Scanner(System.in);
	private static Client client;

	public static void main(String[] args) throws RemoteException {
		System.out.println(" ______ _____  __    _____ _         _____  _     _        _ _           _     _");
		System.out.println("|  ____|  __ \\/_ |  / ____(_)       |  __ \\(_)   | |      (_) |         (_)   | |");
		System.out.println("| |__  | |__) || | | (___  _ ___    | |  | |_ ___| |_ _ __ _| |__  _   _ _  __| | ___  ___");
		System.out.println("|  __| |  ___/ | |  \\___ \\| / __|   | |  | | / __| __| '__| | '_ \\| | | | |/ _` |/ _ \\/ __|");
		System.out.println("| |____| |     | |  ____) | \\__ \\_  | |__| | \\__ \\ |_| |  | | |_) | |_| | | (_| | (_) \\__ \\");
		System.out.println("|______|_|     |_| |_____/|_|___(_) |_____/|_|___/\\__|_|  |_|_.__/ \\__,_|_|\\__,_|\\___/|___/");
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
					"8 - Create part into current repository\n" +
					"9 - Set current part as selected part to add subparts\n" +
					"0 - Exit\n"
			);

			command = scanner.nextInt();
			scanner.nextLine(); // Remove a quebra de linha deixada pelo 'Enter' no buffer

			try {
				switch (command) {
					case 1 -> client.listAllServers();
					case 2 -> changeServer();
					case 3 -> client.listParts();
					case 4 -> changeCurrentPart();
					case 5 -> client.showPart();
					case 6 -> client.clearList();
					case 7 -> addSubpart();
					case 8 -> createPart();
					case 9 -> client.setSelectedPart();
					case 10 -> changeName();
					default -> System.out.println("Invalid command.");
				}
			} catch (NullPointerException nullPointerException) {
				System.out.println("You should select a server first.");
				System.out.print("Press enter to continue...");
				scanner.nextLine();
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
		} catch (NotBoundException notBoundException) {
			System.out.println("Server not found, are you sure this server is in the servers list?");
			System.out.print("Press enter to continue...");
			scanner.nextLine();
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

	private static void createPart() {
		System.out.print("Part name: ");
		String partName = scanner.nextLine();

		System.out.print("Part description: ");
		String partDescription = scanner.nextLine();

		client.createPart(partName, partDescription);
		System.out.println();
	}

	private static void changeName() {
		System.out.print("Enter new name: ");
		String name = scanner.nextLine();
		client.setCurrentPartName(name);
		System.out.println();
	}
}
