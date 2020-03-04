package de.fzj.atlascore.service;

import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

@Service
public class FilenameService {

    public static HashMap<String, String> FILES = new HashMap();
    {
        FILES.put(ReferencespaceRepository.BIG_BRAIN, "bigbrain");
        FILES.put(ReferencespaceRepository.MNI_COLIN_27, "colin");
        FILES.put(ReferencespaceRepository.MNI_152, "MNI152");
        FILES.put(ReferencespaceRepository.WAXHOLM, "waxholmRatV2_0");
        FILES.put(ReferencespaceRepository.ALLEN_MOUSE, "allenMouse");
    }

    public String getFilenameForReferencespace(String referencespace) {
        return FILES.get(referencespace);
    }

    public BufferedReader getBufferedReaderByReferencespaceId(String id) throws IOException {
        return new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource(
                                String.format("data/brains/%s.json", getFilenameForReferencespace(id)),
                                this.getClass().getClassLoader()).getInputStream()
                )
        );
    }
}
