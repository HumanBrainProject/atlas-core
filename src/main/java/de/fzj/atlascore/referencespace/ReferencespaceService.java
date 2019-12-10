package de.fzj.atlascore.referencespace;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReferencespaceService {

    private HashMap<String, Referencespace> referencespaceRepo = new HashMap<>();
    {
        referencespaceRepo.put("bigbrain", new Referencespace("bigbrain"));
        referencespaceRepo.put("colin", new Referencespace("colin"));
        referencespaceRepo.put("MNI152", new Referencespace("MNI152"));
        referencespaceRepo.put("waxholmRatV2_0", new Referencespace("waxholmRatV2_0"));
        referencespaceRepo.put("allenMouse", new Referencespace("allenMouse"));
    }

    public List<Referencespace> getReferencespaces() {
        return new ArrayList<>(referencespaceRepo.values());
    }

    public Referencespace getReferencespaceByName(String name) {
        return referencespaceRepo.get(name);
    }
}
