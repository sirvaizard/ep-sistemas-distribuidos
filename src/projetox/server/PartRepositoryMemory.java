package projetox.server;

import projetox.shared.PartInterface;
import projetox.shared.PartRepository;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartRepositoryMemory implements PartRepository {
    private final List<PartInterface> parts = new ArrayList<>();

    @Override
    public void insertPart(PartInterface p) {
        this.parts.add(p);
    }

    @Override
    public int size() {
        return this.parts.size();
    }

    @Override
    public PartInterface getPart(UUID id) {
        try {
            for (PartInterface product : this.parts) {
                if (product.getId().compareTo(id) == 0) {
                    return product;
                }
            }
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PartInterface> getAllParts() {
        System.out.println("Getting all parts!");
        return new ArrayList<PartInterface>(this.parts);
    }
}
