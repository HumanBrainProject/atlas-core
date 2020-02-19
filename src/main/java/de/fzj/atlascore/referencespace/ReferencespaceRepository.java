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

    public static String BIG_BRAIN_NAME = "Big Brain (Histology)";
    public static String MNI_COLIN_27 = "MNI Colin 27";
    public static String MNI_152 = "MNI 152 ICBM 2009c Nonlinear Asymmetric";
    public static String WAXHOLM = "Waxholm Space rat brain MRI/DTI";
    public static String ALLEN_MOUSE = "Allen Mouse Common Coordinate Framework v3";

    private HashMap<String, Referencespace> referencespaceRepo = new HashMap<>();
    {
        referencespaceRepo.put(BIG_BRAIN_NAME, new Referencespace(
                BIG_BRAIN_NAME,
                "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/a1655b99-82f1-420f-a3c2-fe80fd4c8588"
        ));
        referencespaceRepo.put(MNI_COLIN_27, new Referencespace(
                MNI_COLIN_27,
                "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/7f39f7be-445b-47c0-9791-e971c0b6d992"
        ));
        referencespaceRepo.put(MNI_152, new Referencespace(
                MNI_152,
                "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/1518c6d4-0700-4c90-ad8d-8d992b69687e"
        ));
        referencespaceRepo.put(WAXHOLM, new Referencespace(
                WAXHOLM,
                "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/d5717c4a-0fa1-46e6-918c-b8003069ade8"
        ));
        referencespaceRepo.put(ALLEN_MOUSE, new Referencespace(
                ALLEN_MOUSE,
                "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/a6af0661-c717-44b7-a3d0-a5630e42d94b"
        ));
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
