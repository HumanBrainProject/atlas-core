package de.fzj.atlascore.knowledgegraph;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The KnowledgeGraphService is used for the communication with the external KnowledgeGraph.
 * The communication is REST based and depends on preconfigured graph queries.
 *
 * @author Vadim Marcenko
 */
@Service
public class KnowledgeGraphService {

    @Value("${kg.url}")
    private String kgUrl;

    private final RestTemplate restTemplate;

    public KnowledgeGraphService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Requests all species from the KnowledgeGraph
     *
     * @return A list of all species known to the KnowledgeGraph
     */
    public Object getAllSpecies() {
        return restTemplate.getForObject(
                kgUrl + "/query/minds/core/species/v1.0.0/species-tmp/instances",
                Object.class
        );
    }
}
