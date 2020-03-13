package de.fzj.atlascore.datasets;

import de.fzj.atlascore.configuration.ControllerPaths;
import de.fzj.atlascore.knowledgegraph.KnowledgeGraphService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerPaths.DATASETS)
@Api(value = "Datasets for referencespaces/parcellations/regions", tags = {"Datasets"})
public class DatasetsController {

    private final KnowledgeGraphService knowledgeGraphService;

    public DatasetsController(KnowledgeGraphService knowledgeGraphService) {
        this.knowledgeGraphService = knowledgeGraphService;
    }

    @GetMapping(value = "/parcellations")
    public Object getParcellationDatasets(@RequestParam(name = "id", required = false) String id) {
        return knowledgeGraphService.getParcellationDatasets(id);
    }

    @GetMapping(value = "/referencespaces")
    public Object getReferencespaceDatasets(@RequestParam(name = "id", required = false) String id) {
        return knowledgeGraphService.getReferencespaceDatasets(id);
    }

    @GetMapping(value = "/regions")
    public Object getRegionsDatasets(@RequestParam(name = "id", required = false) String id) {
        return knowledgeGraphService.getRegionsDatasets(id);
    }

    @GetMapping
    public Object getAllDatasets(
            @RequestParam(name = "referencespace", required = false) String referencespace,
            @RequestParam(name = "parcellation", required = false) String parcellation,
            @RequestParam(name = "region", required = false) String region) {
        return knowledgeGraphService.getAllDatasets(referencespace, parcellation, region);
    }
}
