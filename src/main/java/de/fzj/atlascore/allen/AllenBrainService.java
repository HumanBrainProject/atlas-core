package de.fzj.atlascore.allen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * The AllenBrainService calls the allen brain atlas rest api, to get information needed for the atlas core service
 */
@Service
public class AllenBrainService {

    @Value("${allen.url}")
    private String allenBrainUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Object getOntologyStructure() {
        return restTemplate.getForObject(
                allenBrainUrl + "/structure_graph_download/1.json",
                Object.class
        );
    }
}
