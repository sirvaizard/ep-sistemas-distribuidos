package projetox.shared;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartClient extends Remote{
	// Faz o cliente se conectar a outro servidor e muda e repositório corrente;
	void bind(String serverName) throws RemoteException, NotBoundException;
	// Lista as pe¸cas do repositório corrente
	void listParts() throws RemoteException;
	// Busca uma peça por cóodigo. A busca é efetuada no repositóorio corrente. Se encontrada,
	// a peça passa a ser a nova pe¸ca corrente.
	void getPart(String id) throws RemoteException;
	// Mostra atributos da pe¸ca corrente
	void showPart() throws RemoteException;
	// Esvazia a lista de sub-pe¸cas corrente.
	void clearList() throws RemoteException;
	// Adiciona à lista de sub-peças corrente n unidades da peça corrente
	void addSubPart(int quantity) throws RemoteException;
	// Adiciona uma pe¸ca ao reposit´orio corrente. A lista de sub-peças corrente é usada como
	// lista de subcomponentes diretos da nova peça
	void createPart(String name, String description) throws RemoteException;
	// Encerra a execução do cliente
	boolean quit() throws RemoteException;
	void setCurrentPartName(String name) throws RemoteException;
	String serverInfo() throws RemoteException;
	void listAllServers() throws RemoteException;
	void setSelectedPart() throws RemoteException;
}