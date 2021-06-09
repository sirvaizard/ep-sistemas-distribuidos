package projetox.shared;

import java.util.Map;
import java.util.UUID;

public interface PartInterface {
    UUID getId();
    String getName();
    String getDescription();
    Map<PartInterface, Integer> getSubparts();
    boolean clearSubparts();
    boolean addSubparts(PartInterface p, int quantity);
}
