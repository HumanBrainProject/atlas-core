package de.fzj.atlascore.tvb;

import de.fzj.atlascore.region.entity.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service to deliver data from connectivity app
 */
@Service
public class TVBService {

    /**
     * static cache
     */
    private static List<String> regionNames = null;
    private static Weights[] weights = null;

    @Value("${connectivity.url}")
    private String connectivityUrl;

    private final RestTemplate restTemplate;

    public TVBService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private LinkedHashMap getConnectivityForRegion(String region) {
        return restTemplate.postForObject(
                connectivityUrl + "/connectivity" ,
                "{\"area\":\"" + region + "\"}",
                LinkedHashMap.class
        );
    }

    private Weights[] getWeightsForRegion(String region) {
        if (weights == null) {
            LinkedHashMap connectivityForBrain = getConnectivityForRegion(region);
            List<Weights> weightsList = new LinkedList<>();
            for (Object key : connectivityForBrain.keySet()) {
                weightsList.add(new Weights(key.toString(), Double.valueOf(connectivityForBrain.get(key).toString())));
            }
            weights = weightsList.stream().toArray(Weights[]::new);
        }
        return weights;
    }

    private List<String> getAllRegionNames() {
        if (regionNames == null) {
            LinkedHashMap result = restTemplate.getForObject(connectivityUrl + "/connectivitywholebrain/brain", LinkedHashMap.class);

            JSONObject jsonResult = new JSONObject(result);

            Iterator<String> keys = jsonResult.keys();
            regionNames = Stream.generate(() -> null)
                    .takeWhile(x -> keys.hasNext())
                    .map(x -> keys.next())
                    .sorted()
                    .map(Object::toString)
                    .collect(Collectors.toList());

        }
        return regionNames;
    }

    public List<Region> getAllRegions() {
        List<Region> regions = new LinkedList<>();
        for(String region : getAllRegionNames()) {
            regions.add(RegionBuilder.aRegion().withName(region).withWeights(getWeightsForRegion(region)).build());
        }
        return regions;
    }
}
