package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Vector;
import de.fzj.atlascore.entity.Weights;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TVBService implements ITVBService {

    private static List<String> nodes = null;
    private static Weights[] weights = null;

    @Value("${connectivity.url}")
    private String connectivityUrl;

    @Autowired
    private RestTemplate restTemplate;

    private LinkedHashMap getConnectivityForNode(String nodeValue) {
        return restTemplate.postForObject(
                connectivityUrl + "/connectivity" ,
                "{\"area\":\"" + nodeValue + "\"}",
                LinkedHashMap.class
        );
    }

    @Override
    public List<String> getAllNodes() {
        if (nodes == null) {
            LinkedHashMap result = restTemplate.getForObject(connectivityUrl + "/connectivitywholebrain/brain", LinkedHashMap.class);

            JSONObject jsonResult = new JSONObject(result);

            Iterator<String> keys = jsonResult.keys();
            nodes = Stream.generate(() -> null)
                    .takeWhile(x -> keys.hasNext())
                    .map(x -> keys.next())
                    .sorted()
                    .map(Object::toString)
                    .collect(Collectors.toList());

        }
        return nodes;
    }

    @Override
    public Double getAreaForNode(String node) {
        return null;
    }

    @Override
    public Vector getAverageOrientationForNode(String node) {
        return null;
    }

    @Override
    public Vector getCentreForNode(String node) {
        return null;
    }

    @Override
    public Integer getCorticalForNode(String node) {
        return null;
    }

    @Override
    public TractLength[] getTractLengthForNode(String node) {
        return new TractLength[0];
    }

    @Override
    public Double getVolumeForNode(String node) {
        return null;
    }

    @Override
    public Weights[] getWeightsForNode(String node) {
        if (weights == null) {
            LinkedHashMap connectivityForBrain = getConnectivityForNode(node);
            List<Weights> weightsList = new LinkedList<>();
            for (Object key : connectivityForBrain.keySet()) {
                weightsList.add(new Weights(key.toString(), Double.valueOf(connectivityForBrain.get(key).toString())));
            }
            weights = weightsList.stream().toArray(Weights[]::new);
        }
        return weights;
    }

    public LinkedHashMap getConnectivityForBrain(String brain) {
        return restTemplate.getForObject(connectivityUrl + "/connectivitywholebrain/" +  brain, LinkedHashMap.class);
    }
}
