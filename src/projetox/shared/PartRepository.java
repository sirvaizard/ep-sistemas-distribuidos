package projetox.shared;

import java.util.List;
import java.util.UUID;

public interface PartRepository {
    void insertPart(PartInterface p);
    PartInterface getPart(UUID id);
    List<PartInterface> getAllParts();
}
