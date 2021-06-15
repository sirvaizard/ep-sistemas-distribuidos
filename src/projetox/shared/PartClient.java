package projetox.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface PartClient extends Remote{
	// Faz o cliente se conectar a outro servidor e muda e repositório corrente;
	boolean bind(String serverName) throws RemoteException;
	// Lista as pe¸cas do repositório corrente
	List<PartInterface> listParts() throws RemoteException;
	// Busca uma peça por cóodigo. A busca é efetuada no repositóorio corrente. Se encontrada,
	// a peça passa a ser a nova pe¸ca corrente.
	void getPart(String id) throws RemoteException;
	// Mostra atributos da pe¸ca corrente
	void showPart() throws RemoteException;
	// Esvazia a lista de sub-pe¸cas corrente.
	boolean clearList() throws RemoteException;
	// Adiciona à lista de sub-peças corrente n unidades da peça corrente
	boolean addSubPart(int quanitity) throws RemoteException;
	// Adiciona uma pe¸ca ao reposit´orio corrente. A lista de sub-peças corrente é usada como
	// lista de subcomponentes diretos da nova peça
	boolean addPart() throws RemoteException;
	// Encerra a execução do cliente
	boolean quit() throws RemoteException;
	void setCurrentPartName(String name) throws RemoteException;
	String serverInfo() throws RemoteException;
	void listAllServers() throws RemoteException;
	void setSelectedPart() throws RemoteException;
}