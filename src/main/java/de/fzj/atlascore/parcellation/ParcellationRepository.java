package de.fzj.atlascore.parcellation;

import de.fzj.atlascore.referencespace.Referencespace;
import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ParcellationRepository {

    private final ReferencespaceRepository referencespaceRepository;

    public ParcellationRepository(ReferencespaceRepository referencespaceRepository) {
        this.referencespaceRepository = referencespaceRepository;
    }

    public List<Parcellation> findAllByReferencespace(String refSpace) {
        Optional<Referencespace> first = referencespaceRepository
                .findAll()
                .stream()
                .filter(referencespace -> referencespace.getName().equals(refSpace))
                .findFirst();

        List<Parcellation> parcellations = new LinkedList<>();
        if(first.isPresent()) {
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new ClassPathResource(
                                        String.format("data/brains/%s.json", first.get().getName()),
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
