package de.fzj.atlascore.referencespace;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fzj.atlascore.service.FilenameService;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Static Repository with all referencespaces that are valid at the moment
 *
 * @author Vadim Marcenko
 */
@Repository
public class ReferencespaceRepository {

    public static String BIG_BRAIN = "a1655b99-82f1-420f-a3c2-fe80fd4c8588";
    public static String MNI_COLIN_27 = "7f39f7be-445b-47c0-9791-e971c0b6d992";
    public static String MNI_152 = "dafcffc5-4826-4bf1-8ff6-46b8a31ff8e2";
    public static String WAXHOLM = "495e1936-b37c-461b-aca6-9de690038be1";
    public static String ALLEN_MOUSE = "4891f87d-8f9b-4c5a-b65d-ce8b3e942771";

    private HashMap<String, Referencespace> referencespaceRepo = new HashMap<>();

    public List<Referencespace> findAll() {
        return new ArrayList<>(referencespaceRepo.values());
    }

    public boolean isValid(String id) {
        return Arrays.asList(
                BIG_BRAIN,
                MNI_COLIN_27,
                MNI_152,
                WAXHOLM,
                ALLEN_MOUSE
        ).contains(id);
    }

    public List<Referencespace> getAll() {
        if(referencespaceRepo.isEmpty()) {
            for (Map.Entry<String, String> entry : FilenameService.FILES.entrySet()) {
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new ClassPathResource(
                                            String.format("data/brains/%s.json", entry.getValue()),
                                            this.getClass().getClassLoader()).getInputStream()
                            )
                    );
                    String fileAsString = reader.lines().collect(Collectors.joining(" "));
                    JSONObject jsonObject = new JSONObject(fileAsString);
                    HashMap<String, Object> hashMap = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
                    hashMap.remove("parcellations");
                    Referencespace referencespace = new Referencespace(hashMap);
                    referencespaceRepo.put(entry.getKey(), referencespace);
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return new ArrayList<>(referencespaceRepo.values());
    }

    public Referencespace findOneById(String id) {
        if(referencespaceRepo.isEmpty()) {
            getAll();
        }
        return referencespaceRepo.get(id);
    }

    public boolean isValidReferenceSpace(String name) {
        return referencespaceRepo.containsKey(name);
    }
}
