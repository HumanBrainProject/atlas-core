package de.fzj.atlascore.knowledgegraph;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class KnowledgeGraphIdConverter {

    private static final String REFERENCESPACE_ID_PREFIX = "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/";
    private static final String PARCELLATION_ID_PREFIX = "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationatlas/v1.0.0/";
    private static final String REGION_ID_PREFIX = "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationregion/v1.0.0/";
    private static final String DATASET_ID_PREFIX = "https://nexus.humanbrainproject.org/v0/data/minds/core/dataset/v1.0.0/";

    public String convertReferencespaceId(String id) {
        if(StringUtils.isEmpty(id)) {
            return id;
        }
        return id.contains(REFERENCESPACE_ID_PREFIX) ? id : REFERENCESPACE_ID_PREFIX + id;
    }

    public String convertParcellationId(String id) {
        if(StringUtils.isEmpty(id)) {
            return id;
        }
        return id.contains(PARCELLATION_ID_PREFIX) ? id : PARCELLATION_ID_PREFIX + id;
    }

    public String convertRegionId(String id) {
        if(StringUtils.isEmpty(id)) {
            return id;
        }
        return id.contains(REGION_ID_PREFIX) ? id : REGION_ID_PREFIX + id;
    }

    public String convertDatasetId(String id) {
        if(StringUtils.isEmpty(id)) {
            return id;
        }
        return id.contains(DATASET_ID_PREFIX) ? id : DATASET_ID_PREFIX + id;
    }
}
