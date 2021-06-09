package projetox.server;

import projetox.shared.PartInterface;

import java.io.Serializable;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class Part implements PartInterface, Serializable {
    private UUID id;
    private String name;
    private String description;
    private final Map<PartInterface, Integer> subParts = new HashMap<>();

    public Part(String name, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
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
    public String getDescription() {
        return this.description;
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
    public boolean addSubparts(PartInterface p, int quantity) {
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
