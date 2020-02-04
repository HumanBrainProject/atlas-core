package de.fzj.atlascore.service;

import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FilenameService {

    public static HashMap<String, String> FILES = new HashMap();
    {
        FILES.put(ReferencespaceRepository.BIG_BRAIN_NAME, "bigbrain");
        FILES.put(ReferencespaceRepository.MNI_COLIN_27, "colin");
        FILES.put(ReferencespaceRepository.MNI_152, "MNI152");
    }

    public String getFilenameForReferencespace(String referencespace) {
        return FILES.get(referencespace);
    }
}
