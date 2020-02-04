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
    public static String WAXHOLM = "waxholmRatV2_0";
    public static String ALLEN_MOUSE = "allenMouse";

    private HashMap<String, Referencespace> referencespaceRepo = new HashMap<>();
    {
        referencespaceRepo.put(BIG_BRAIN_NAME, new Referencespace(BIG_BRAIN_NAME));
        referencespaceRepo.put(MNI_COLIN_27, new Referencespace(MNI_COLIN_27));
        referencespaceRepo.put(MNI_152, new Referencespace(MNI_152));
        referencespaceRepo.put(WAXHOLM, new Referencespace(WAXHOLM));
        referencespaceRepo.put(ALLEN_MOUSE, new Referencespace(ALLEN_MOUSE));
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
