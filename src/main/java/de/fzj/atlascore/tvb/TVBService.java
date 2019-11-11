package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.Area;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TVBService {

    @Value("${connectivity.url}")
    private String connectivityUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<String> getAllNodes() {
        LinkedHashMap result = restTemplate.getForObject(connectivityUrl + "/connectivitywholebrain/brain", LinkedHashMap.class);

        JSONObject jsonResult = new JSONObject(result);

        Iterator<String> keys = jsonResult.keys();

        return Stream.generate(() -> null)
                .takeWhile(x -> keys.hasNext())
                .map(x -> keys.next())
                .sorted()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public void getConnectivityForNode(String nodeValue) {
        restTemplate.postForObject(connectivityUrl + "/connectivitywholebrain/" , "{\"area\":\"" + "\"}", LinkedHashMap.class);
    }




    public LinkedHashMap getConnectivityForBrain(String brain) {
        return restTemplate.getForObject(connectivityUrl + "/connectivitywholebrain/" +  brain, LinkedHashMap.class);
    }

    public List<Area> createTVBExport() {
        LinkedHashMap connectivityAreas = getConnectivityForBrain("123");
        JSONObject jsonResult = new JSONObject(connectivityAreas);

        Iterator<String> keys = jsonResult.keys();

        String[] sortedAreaKeys = Stream.generate(() -> null)
                .takeWhile(x -> keys.hasNext())
                .map(x -> keys.next())
                .sorted()
                .map(Object::toString)
                .toArray(String[]::new);

        LinkedList<Double> weights = new LinkedList<>();

        List<Area> areas = new LinkedList<>();

        for(String areaKey : sortedAreaKeys) {

            for(String areaKeyValue: sortedAreaKeys) {
                weights.add(Double.valueOf(jsonResult.getJSONObject(areaKey).get(areaKeyValue).toString()));
            }
            areas.add(new Area(null, areaKey,null,
                    null,null,null,null, weights.stream().toArray(Double[]::new)));
            weights.clear();
        }
        return areas;
    }
}
