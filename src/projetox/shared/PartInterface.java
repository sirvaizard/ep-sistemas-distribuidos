package projetox.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public interface PartInterface extends Remote {
    UUID getId() throws RemoteException;
    String getName() throws RemoteException;
    void setName(String name) throws RemoteException; // TODO: Remover
    String getDescription() throws RemoteException;
    String getServerName() throws RemoteException;
    void setServerName(String name) throws RemoteException;
    Map<PartInterface, Integer> getSubparts() throws RemoteException;
    void clearSubparts() throws RemoteException;
    void addSubpart(PartInterface p, int quantity) throws RemoteException;
    int primitiveSubpartsLength() throws RemoteException;
    boolean isPrimitive() throws RemoteException;
}
