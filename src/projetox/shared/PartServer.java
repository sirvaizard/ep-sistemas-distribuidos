package projetox.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface PartServer extends Remote {
    void insertPart(PartInterface p) throws RemoteException;
    PartInterface getPart(UUID id) throws RemoteException;
    List<PartInterface> getAllParts() throws RemoteException;
    void clearPartSubparts(UUID id) throws RemoteException;
}
