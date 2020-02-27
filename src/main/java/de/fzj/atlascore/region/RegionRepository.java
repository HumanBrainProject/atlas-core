package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import de.fzj.atlascore.region.entity.RegionBuilder;
import de.fzj.atlascore.service.FilenameService;
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

    private final FilenameService filenameService;

    private static String areaLabel = null;

    private HashMap<String, Set<Region>> refSpaceRegions = new HashMap<>();

    public RegionRepository(FilenameService filenameService) {
        this.filenameService = filenameService;
    }

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

    private String getHemisphereFromNameOrNull(String name) {
        if(name == null) {
            return null;
        }
        if(name.toLowerCase().contains("hemisphere") && name.toLowerCase().contains("left")) {
            return "left";
        }
        if(name.toLowerCase().contains("hemisphere") && name.toLowerCase().contains("right")) {
            return "right";
        }
        return null;
    }

    private void getRegionsFromStaticJson(String regionCacheKey, JSONObject jsonObject) {
        JSONArray children = jsonObject.getJSONArray("children");
        String label = getStringOrNullFromObject(jsonObject, "arealabel");
        if(label != null) {
            areaLabel = label;
        }
        if(children.isEmpty()) {
            try {
                refSpaceRegions.get(regionCacheKey).add(
                        RegionBuilder
                                .aRegion()
                                .withName(getStringOrNullFromObject(jsonObject, "name"))
                                .withStatus(getStringOrNullFromObject(jsonObject, "status"))
                                .withRgb(getArrayOrNullFromObject(jsonObject,"rgb"))
                                .withPosition(getArrayOrNullFromObject(jsonObject,"position"))
                                .withLabel(areaLabel)
                                .withHemisphere(getHemisphereFromNameOrNull(getStringOrNullFromObject(jsonObject, "name")))
                                .build()
                );
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            for(Object o : children) {
                getRegionsFromStaticJson(regionCacheKey, (JSONObject) o);
            }
        }
    }

    public List<Region> findAllByReferencespaceAndParcellation(String refSpace, String parcellation) {
        areaLabel = null;
        if(!refSpaceRegions.containsKey(refSpace+parcellation)) {
            refSpaceRegions.put(refSpace+parcellation, new HashSet<>());
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new ClassPathResource(
                                        String.format("data/brains/%s.json", filenameService.getFilenameForReferencespace(refSpace)),
                                        this.getClass().getClassLoader()).getInputStream()
                        )
                );
                String fileAsString = reader.lines().collect(Collectors.joining(" "));
                JSONObject jsonObject = new JSONObject(fileAsString);
                JSONArray jsonArray = jsonObject.getJSONArray("parcellations");
                for (Object o : jsonArray) {
                    if (((JSONObject) o).get("name").toString().equals(parcellation)) {
                        for (int i = 0; i < ((JSONObject) o).getJSONArray("regions").length(); i++) {
                            getRegionsFromStaticJson(refSpace+parcellation, ((JSONObject) o).getJSONArray("regions").getJSONObject(i));
                        }
                    }
                }
            } catch (IOException e) {
                //TODO Log error
            }
        }

        return new ArrayList<>(refSpaceRegions.get(refSpace+parcellation));
    }

    public Region findOneByReferencespaceAndParcellationAndName(String refSpace, String parcellation, String name) {
        if(!refSpaceRegions.containsKey(refSpace+parcellation)) {
            findAllByReferencespaceAndParcellation(refSpace, parcellation);
        }
        return refSpaceRegions.get(refSpace+parcellation)
                .stream()
                .filter(region -> region.getName().equals(name))
                .findFirst().orElse(null);
    }
}
