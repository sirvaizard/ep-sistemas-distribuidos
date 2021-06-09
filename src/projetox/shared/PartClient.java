package projetox.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartClient extends Remote{
	// Faz o cliente se conectar a outro servidor e muda e repositório corrente;
	boolean bind(String serverName) throws RemoteException;
	// Lista as pe¸cas do repositório corrente
	List<PartInterface> listParts() throws RemoteException;
	// Busca uma peça por cóodigo. A busca é efetuada no repositóorio corrente. Se encontrada,
	// a peça passa a ser a nova pe¸ca corrente.
	PartInterface getPars(String id) throws RemoteException;
	// Mostra atributos da pe¸ca corrente
	String showParts() throws RemoteException;
	// Esvazia a lista de sub-pe¸cas corrente.
	boolean clearList() throws RemoteException;
	// Adiciona à lista de sub-peças corrente n unidades da peça corrente
	boolean addSubPart(PartInterface p) throws RemoteException;
	// Adiciona uma pe¸ca ao reposit´orio corrente. A lista de sub-peças corrente é usada como
	// lista de subcomponentes diretos da nova peça
	boolean addPart(PartInterface p) throws RemoteException;
	// Encerra a execução do cliente
	boolean quit() throws RemoteException;
}