package de.fzj.atlascore.datasets;

import de.fzj.atlascore.configuration.ControllerPaths;
import de.fzj.atlascore.knowledgegraph.KnowledgeGraphService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ControllerPaths.DATASETS)
@Api(value = "Datasets for referencespaces/parcellations/regions", tags = {"Datasets"})
public class DatasetsController {

    private final KnowledgeGraphService knowledgeGraphService;


    public DatasetsController(KnowledgeGraphService knowledgeGraphService) {
        this.knowledgeGraphService = knowledgeGraphService;
    }

    @GetMapping(value = "/parcellations")
    public Object getParcellationDatasets(
            @ApiParam(name="id", value="KnowledgeGraph id https://schema.hbp.eu/myQuery/@id \n " +
                    "Format: https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationatlas/v1.0.0/..."
            )
            @RequestParam(name = "id", required = false) String id) {
        return knowledgeGraphService.getParcellationDatasets(id);
    }

    @GetMapping(value = "/referencespaces")
    public Object getReferencespaceDatasets(
            @ApiParam(name="id", value="KnowledgeGraph id https://schema.hbp.eu/myQuery/@id \n " +
                    "Format: https://nexus.humanbrainproject.org/v0/data/minds/core/reference_space/v1.0.0/..."
            )
            @RequestParam(name = "id", required = false) String id) {
        return knowledgeGraphService.getReferencespaceDatasets(id);
    }

    @GetMapping(value = "/regions")
    public Object getRegionsDatasets(
            @ApiParam(name="id", value="KnowledgeGraph id https://schema.hbp.eu/myQuery/@id \n " +
                    "Format: https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationregion/v1.0.0/..."
            )
            @RequestParam(name = "id", required = false) String id) {
        return knowledgeGraphService.getRegionsDatasets(id);
    }

    @GetMapping
    public Object getAllDatasets(
            @ApiParam(name="referencespace id")
            @RequestParam(name = "referencespace", required = false) String referencespace,
            @ApiParam(name="parcellationatlas id")
            @RequestParam(name = "parcellation", required = false) String parcellation,
            @ApiParam(name="parcellationsregion id")
            @RequestParam(name = "region", required = false) String region) {
        return knowledgeGraphService.getAllDatasets(
                referencespace,
                parcellation,
                region
        );
    }

    @GetMapping(value = "/{id}")
    public Object getDatasetById(@RequestParam(name = "id") String id) {
        return knowledgeGraphService.getDatasetById(id);
    }

    @PostMapping(value = "/regions")
    public Object getRegionsDatasets(@RequestBody List<String> regions) {
        if(regions == null || regions.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("no regions provided")
            );
        }
        return knowledgeGraphService.getRegionsFilteredByList(regions);
    }
}
