package de.fzj.atlascore.allen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The AllenBrainService calls the allen brain atlas rest api, to get information needed for the atlas core service
 *
 * @author Vadim Marcenko
 */
@Service
public class AllenBrainService {

    private static final Logger LOGGER = LogManager.getLogger(AllenBrainService.class);

    @Value("${allen.url}")
    private String allenBrainUrl;

    private final RestTemplate restTemplate;

    public AllenBrainService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("api-data")
    public Object getOntologyStructure() {
        LOGGER.info("Getting Allen brain structure by requesting api");
        return restTemplate.getForObject(
                allenBrainUrl + "/structure_graph_download/1.json",
                Object.class
        );
    }
}
