package projetox.server;

import projetox.shared.PartInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class Part extends UnicastRemoteObject implements PartInterface {
    private UUID id;
    private String name;
    private String description;
    private final Map<PartInterface, Integer> subParts = new HashMap<>();

    private Part(String name, String description) throws RemoteException {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }

    public static Part getInstance(String name, String description) {
        try {
            Part p = new Part(name, description);
            return p;
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
        return this.ref.toString();
    }

    @Override
    public Map<PartInterface, Integer> getSubparts() {
        // TODO: Return copy of list
        return this.subParts;
    }

    @Override
    public boolean clearSubparts() {
        this.subParts.clear();
        return true;
    }

    @Override
    public boolean addSubpart(PartInterface p, int quantity) {
        boolean alreadySubpart = this.subParts.containsKey(p);
        if (alreadySubpart) {
            quantity += this.subParts.get(p);
        }
        this.subParts.put(p, quantity);
        return true;
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
