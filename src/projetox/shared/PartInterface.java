package projetox.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public interface PartInterface extends Remote {
    UUID getId() throws RemoteException;
    String getName() throws RemoteException;
    void setName(String name) throws RemoteException;
    String getDescription() throws RemoteException;
    Map<PartInterface, Integer> getSubparts() throws RemoteException;
    boolean clearSubparts() throws RemoteException;
    boolean addSubpart(PartInterface p, int quantity) throws RemoteException;
}
