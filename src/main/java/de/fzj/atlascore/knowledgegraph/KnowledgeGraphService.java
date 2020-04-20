package de.fzj.atlascore.knowledgegraph;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The KnowledgeGraphService is used for the communication with the external KnowledgeGraph.
 * The communication is REST based and depends on preconfigured graph queries.
 *
 * @author Vadim Marcenko
 */
@Service
public class KnowledgeGraphService {

    private static final String REFERENCESPACE_CRITERIA = "https://schema.hbp.eu/myQuery/reference_space";
    private static final String PARCELLATION_CRITERIA = "https://schema.hbp.eu/myQuery/parcellationAtlas";
    private static final String REGION_CRITERIA = "https://schema.hbp.eu/myQuery/parcellationRegion";
    private static final String PARAMETER_ID = "https://schema.hbp.eu/myQuery/@id";
    private static final String DATASET_QUERY = "/query/minds/core/dataset/v1.0.0/atlascore_datasets_all/instances";
    private static final String DATASET_QUERY_REFERENCESPACE = "/query/minds/core/dataset/v1.0.0/ac_ds_referencespace/instances";
    private static final String DATASET_QUERY_PARCELLATION = "/query/minds/core/dataset/v1.0.0/ac_ds_parcellation/instances";
    private static final String DATASET_QUERY_REGION = "/query/minds/core/dataset/v1.0.0/ac_ds_regions/instances";
    private static final Logger LOGGER = LogManager.getLogger(KnowledgeGraphService.class);
    @Value("${kg.url}")
    private String kgUrl;

    private final RestTemplate restTemplate;
    private final KnowledgeGraphIdConverter knowledgeGraphIdConverter;

    public KnowledgeGraphService(RestTemplate restTemplate, KnowledgeGraphIdConverter knowledgeGraphIdConverter) {
        this.restTemplate = restTemplate;
        this.knowledgeGraphIdConverter = knowledgeGraphIdConverter;
    }

    /**
     * Requests all species from the KnowledgeGraph.
     *
     * @return A list of all species known to the KnowledgeGraph
     */
    public Object getAllSpecies() {
        return restTemplate.getForObject(
                kgUrl + "/query/minds/core/species/v1.0.0/species-tmp/instances",
                Object.class
        );
    }

    /**
     * Get all datasets from the KnowledgeGraph. The results can be filtered by different parameters.
     *
     * @param referencespace Id of a referencespace in the KnowledgeGraph
     * @param parcellation Id of a parcellation atlas in the KnowledgeGraph
     * @param region Id of a parcellation region in the KnowledgeGraph
     * @return A JSON formated list of datasets
     */
    @Cacheable("api-data")
    public ArrayList getAllDatasets(String referencespace, String parcellation, String region) {
        LOGGER.info("Data without cache");
        String url = kgUrl + DATASET_QUERY;
        ArrayList results = getDatasetByUrl(url);
        ArrayList filteredArray = new ArrayList();

        for(Object o : results) {
            if(
                    filterResultByParameter(
                            o, REFERENCESPACE_CRITERIA, PARAMETER_ID,
                            Arrays.asList(knowledgeGraphIdConverter.convertReferencespaceId(referencespace))) &&
                    filterResultByParameter(o, PARCELLATION_CRITERIA, PARAMETER_ID,
                            Arrays.asList(knowledgeGraphIdConverter.convertParcellationId(parcellation))) &&
                    filterResultByParameter(o, REGION_CRITERIA, PARAMETER_ID,
                            Arrays.asList(knowledgeGraphIdConverter.convertRegionId(region)))
            ) {
                filteredArray.add(o);
            }
        }
        return filteredArray;
    }

    /**
     * Get a list of all datasets for parcellation atlases.
     * The results can be filtered by the id.
     *
     * @param parcellation Id of a parcellation atlas in the KnowledgeGraph
     * @return A JSON formated list of datasets for parcellation atlases
     */
    public ArrayList getParcellationDatasets(String parcellation) {
        String url = kgUrl + DATASET_QUERY_PARCELLATION;
        if(!StringUtils.isEmpty(parcellation)) {
            url+="?parcellation=" +  knowledgeGraphIdConverter.convertParcellationId(parcellation);
        }
        return getDatasetByUrl(url);
    }

    /**
     * Get a list of all datasets for referencespaces.
     * The results can be filtered by the id.
     *
     * @param referencespace Id of a referencespace in the KnowledgeGraph
     * @return A JSON formated list of datasets for referencespaces
     */
    public ArrayList getReferencespaceDatasets(String referencespace) {
        String url = kgUrl + DATASET_QUERY_REFERENCESPACE;
        if(!StringUtils.isEmpty(referencespace)) {
            url+="?referencespace=" +  knowledgeGraphIdConverter.convertReferencespaceId(referencespace);
        }

        return getDatasetByUrl(url);
    }

    /**
     * Get a list of all datasets for regions.
     * The results can be filtered by the id.
     *
     * @param region Id of a parcellation region in the KnowledgeGraph
     * @return A JSON formated list of datasets for regions
     */
    public ArrayList getRegionsDatasets(String region) {
        String url = kgUrl + DATASET_QUERY_REGION;
        if(!StringUtils.isEmpty(region)) {
            url+="?region=" +  knowledgeGraphIdConverter.convertRegionId(region);
        }

        return getDatasetByUrl(url);
    }

    /**
     * Get a list of all datasets for regions.
     * The results can be filtered by a list of region ids.
     *
     * @param regions List of parcellation region ids in the KnowledgeGraph
     * @return A JSON formated list of datasets for regions
     */
    public ArrayList getRegionsFilteredByList(List<String> regions) {
        ArrayList regionsDatasets = getRegionsDatasets(null);
        ArrayList filteredArray = new ArrayList();
        for(Object o : regionsDatasets) {
            if(filterResultByParameter(o, REGION_CRITERIA, PARAMETER_ID,
                    regions.stream().map(knowledgeGraphIdConverter::convertRegionId).collect(Collectors.toList()))) {
                filteredArray.add(o);
            }
        }
        return filteredArray;
    }

    /*
    Filter results from a JSON List by given parameters.
    Possible parameters are defined as static variables.
     */
    private boolean filterResultByParameter(
            Object o, String filterCriteria, String parameterName, List<String> parameterValue) {
        if(parameterValue == null || parameterValue.isEmpty() || parameterValue.contains(null)) {
            return true;
        }
        try {
            ArrayList criteriaArray = (ArrayList) ((LinkedHashMap) o).get(filterCriteria);
            if(criteriaArray.size() != 0) {
                for(Object criteriaObject : criteriaArray) {

                    String value = ((LinkedHashMap) (criteriaObject))
                            .get(parameterName).toString();
                    if (parameterValue.contains(value)) {
                        return true;
                    }
                }
            }
            return false;
        } catch(Exception e) {
            LOGGER.debug(e);
            LOGGER.info("Error on filtering results for criteria: " + filterCriteria + ", on value: " + parameterValue);
            return false;
        }
    }

    /*
    Performs a HTTP request to the KnowledgeGraph
     */
    private ArrayList getDatasetByUrl(String url) {
        LinkedHashMap results = restTemplate.getForObject(
                url,
                LinkedHashMap.class
        );
        ArrayList resultArray = null;
        try {
            resultArray = (ArrayList) results.get("results");
        } catch (Exception e) {
            LOGGER.info("Could not cast results for url: " + url);
        }
        return resultArray;
    }
}
