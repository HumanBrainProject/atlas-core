package de.fzj.atlascore.referencespace;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Static Repository with all referencespaces that are valid at the moment
 *
 * @author Vadim Marcenko
 */
@Repository
public class ReferencespaceRepository {

    private HashMap<String, Referencespace> referencespaceRepo = new HashMap<>();
    {
        referencespaceRepo.put("bigbrain", new Referencespace("bigbrain"));
        referencespaceRepo.put("colin", new Referencespace("colin"));
        referencespaceRepo.put("MNI152", new Referencespace("MNI152"));
        referencespaceRepo.put("waxholmRatV2_0", new Referencespace("waxholmRatV2_0"));
        referencespaceRepo.put("allenMouse", new Referencespace("allenMouse"));
    }

    public List<Referencespace> findAll() {
        return new ArrayList<>(referencespaceRepo.values());
    }

    public Referencespace findOneByName(String name) {
        return referencespaceRepo.get(name);
    }

    public boolean isValidReferenceSpace(String name) {
        return referencespaceRepo.containsKey(name);
    }
}
