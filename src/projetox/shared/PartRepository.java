package projetox.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface PartRepository extends Remote {
    void insertPart(String name, String description) throws RemoteException;
    PartInterface getPart(UUID id) throws RemoteException;
    List<PartInterface> getAllParts() throws RemoteException;
    int size() throws RemoteException;
    String getName() throws RemoteException;
}
