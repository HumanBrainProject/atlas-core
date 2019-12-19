package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import de.fzj.atlascore.region.entity.RegionBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository for all regions for in a given parcellation and referencespace
 * The data is stored in static files
 *
 * @author Vadim Marcenko
 */
@Repository
public class RegionRepository {

    private HashMap<String, Set<Region>> refSpaceRegions = new HashMap<>();

    private String getStringOrNullFromObject(JSONObject jsonObject, String key) {
        try {
            return jsonObject.get(key).toString().trim();
        } catch(Exception e) {
            return null;
        }
    }

    private Integer[] getArrayOrNullFromObject(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONArray(key).toList().stream().toArray(Integer[]::new);
        } catch(Exception e) {
            return null;
        }
    }

    private void getRegionsFromStaticJson(String refSpace, JSONObject jsonObject) {
        JSONArray children = jsonObject.getJSONArray("children");
        if(children.isEmpty()) {
            try {
                refSpaceRegions.get(refSpace).add(
                        RegionBuilder
                                .aRegion()
                                .withName(getStringOrNullFromObject(jsonObject, "name"))
                                .withStatus(getStringOrNullFromObject(jsonObject, "status"))
                                .withRgb(getArrayOrNullFromObject(jsonObject,"rgb"))
                                .withPosition(getArrayOrNullFromObject(jsonObject,"position"))
                                .build()
                );
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            for(Object o : children) {
                getRegionsFromStaticJson(refSpace, (JSONObject) o);
            }
        }
    }

    public List<Region> findAllByReferencespaceAndParcellation(String refSpace, String parcellation) {
        if(!refSpaceRegions.containsKey(refSpace)) {
            refSpaceRegions.put(refSpace, new HashSet<>());
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new ClassPathResource(
                                        String.format("data/brains/%s.json", refSpace),
                                        this.getClass().getClassLoader()).getInputStream()
                        )
                );
                String fileAsString = reader.lines().collect(Collectors.joining(" "));
                JSONObject jsonObject = new JSONObject(fileAsString);
                JSONArray jsonArray = jsonObject.getJSONArray("parcellations");
                for (Object o : jsonArray) {
                    if (((JSONObject) o).get("name").toString().equals(parcellation)) {
                        for (int i = 0; i < ((JSONObject) o).getJSONArray("regions").length(); i++) {
                            getRegionsFromStaticJson(refSpace, ((JSONObject) o).getJSONArray("regions").getJSONObject(i));
                        }
                    }
                }
            } catch (IOException e) {
                //TODO Log error
            }
        }

        return new ArrayList<>(refSpaceRegions.get(refSpace));
    }

    public Region findOneByReferencespaceAndParcellationAndName(String refSpace, String parcellation, String name) {
        if(!refSpaceRegions.containsKey(refSpace)) {
            findAllByReferencespaceAndParcellation(refSpace, parcellation);
        }
        return refSpaceRegions.get(refSpace)
                .stream()
                .filter(region -> region.getName().equals(name))
                .findFirst().orElse(null);
    }
}
