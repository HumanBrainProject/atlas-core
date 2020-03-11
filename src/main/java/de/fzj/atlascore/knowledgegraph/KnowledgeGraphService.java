package de.fzj.atlascore.knowledgegraph;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    private final String DATASET_QUERY = "/query/minds/core/dataset/v1.0.0/atlascore_datasets_all/instances";
    private final String DATASET_QUERY_PARCELLATION = "/query/minds/core/dataset/v1.0.0/ac_ds_parcellation/instances";

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

    public Object getAllDatasets(String referencespace, String parcellation) {
        HttpEntity request = new HttpEntity(new HttpHeaders());

        String url = kgUrl + DATASET_QUERY;
        if(!StringUtils.isEmpty(referencespace)) {
            url+="?referencespace=" +  referencespace;
        }
        if(!StringUtils.isEmpty(parcellation)) {
            if(url.contains("?")) {
                url+="&parcellation=" + parcellation;
            } else {
                url+="?parcellation=" + parcellation;
            }
        }
//        ResponseEntity<Object> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                request,
//                Object.class,
//                1
//        );
        return restTemplate.getForObject(
                url,
                Object.class
        );
    }

    public Object getParcellationDatasets(String parcellation) {
        HttpEntity request = new HttpEntity(new HttpHeaders());

        String url = kgUrl + DATASET_QUERY_PARCELLATION;
        if(!StringUtils.isEmpty(parcellation)) {
            url+="?parcellation=" +  parcellation;
        }
//        ResponseEntity<Object> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                request,
//                Object.class,
//                1
//        );
        return restTemplate.getForObject(
                url,
                Object.class
        );
    }
}
