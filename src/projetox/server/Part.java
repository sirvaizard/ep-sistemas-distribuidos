package projetox.server;

import projetox.shared.PartInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class Part extends UnicastRemoteObject implements PartInterface {
    private final UUID id;
    private String name;
    private final String description;
    private final Map<PartInterface, Integer> subParts = new HashMap<>();
    private String serverName;

    private Part(String name, String description, String serverName) throws RemoteException {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.serverName = serverName;
    }

    public static Part getInstance(String name, String description, String serverName) {
        try {
            return new Part(name, description, serverName);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
        return null;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getServerName() {
        return this.serverName;
    }

    @Override
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public Map<PartInterface, Integer> getSubparts() {
        // TODO: Return copy of list
        return this.subParts;
    }

    @Override
    public void clearSubparts() {
        this.subParts.clear();
    }

    @Override
    public void addSubpart(PartInterface p, int quantity) {
        boolean alreadySubpart = this.subParts.containsKey(p);
        if (alreadySubpart) {
            quantity += this.subParts.get(p);
        }
        this.subParts.put(p, quantity);
    }

    @Override
    public int primitiveSubpartsLength() {
        return (int) this.subParts.keySet().stream().filter(partInterface -> {
            try {
                return partInterface.isPrimitive();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            return false;
        }).count();
    }

    @Override
    public boolean isPrimitive() {
        return this.subParts.size() == 0;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", subParts=" + subParts +
                '}';
    }
}
