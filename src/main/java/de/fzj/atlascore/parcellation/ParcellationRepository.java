package de.fzj.atlascore.parcellation;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import de.fzj.atlascore.service.FilenameService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for all parcellations in a given referencespace
 * At the moment the data is read from files
 *
 * @see ReferencespaceRepository
 *
 * @author Vadim Marcenko
 */
@Repository
public class ParcellationRepository {

    private final FilenameService filenameService;

    public ParcellationRepository(FilenameService filenameService) {
        this.filenameService = filenameService;
    }

    public List<Parcellation> findAllByReferencespace(String refSpace) {
        List<Parcellation> parcellations = new LinkedList<>();
        BufferedReader reader;
        if(FilenameService.FILES.containsKey(refSpace)) {
            try {
                reader = filenameService.getBufferedReaderByReferencespaceId(refSpace);
                String fileAsString = reader.lines().collect(Collectors.joining(" "));
                JSONObject jsonObject = new JSONObject(fileAsString);
                JSONArray jsonArray = jsonObject.getJSONArray("parcellations");
                for (Object o : jsonArray) {
                    JSONObject parcellation = (JSONObject) o;
                    HashMap hashMap = new ObjectMapper().readValue(parcellation.toString(), HashMap.class);
                    hashMap.remove("regions");
                    parcellations.add(new Parcellation(hashMap));
                }
                reader.close();
            } catch (IOException e) {
                //TODO Log error
            }
        }
        return parcellations;
    }

    public Parcellation findOneByReferencespaceAndName(String refSpace, String name) {
        return findAllByReferencespace(refSpace)
                .stream()
                .filter(parcellation -> parcellation.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
