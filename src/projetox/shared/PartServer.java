package projetox.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartServer extends Remote {
    void insertPart(PartInterface p) throws RemoteException;
    PartInterface getPart(int id) throws RemoteException;
    List<PartInterface> getAllParts() throws RemoteException;
    List<PartInterface> test() throws RemoteException;
}
