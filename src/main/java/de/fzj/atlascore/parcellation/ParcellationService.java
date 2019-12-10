package de.fzj.atlascore.parcellation;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcellationService {

    private List<Parcellation> getParcellationsFromFile(String filename) {
        List<Parcellation> parcellations = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new ClassPathResource(
                                    String.format("data/brains/%s.json", filename),
                                    this.getClass().getClassLoader()).getInputStream()
                    )
            );
            String fileAsString = reader.lines().collect(Collectors.joining(" "));
            JSONObject jsonObject = new JSONObject(fileAsString);
            JSONArray jsonArray = jsonObject.getJSONArray("parcellations");
            for (Object o : jsonArray) {
                parcellations.add(new Parcellation(((JSONObject) o).get("name").toString()));
            }
        } catch (IOException e) {
            //TODO Log error
        }
        return parcellations;
    }

    public List<Parcellation> getParcellations(String refSpaceName) {
        if(refSpaceName.equals("bigbrain") || refSpaceName.equals("colin") || refSpaceName.equals("MNI152")) {
            return getParcellationsFromFile(refSpaceName);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Referencespace: %s not found", refSpaceName)
        );
    }

    public Parcellation getParcellationByName(String name) {
        return new Parcellation(name);
    }
}
