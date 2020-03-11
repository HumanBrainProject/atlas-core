package de.fzj.atlascore.datasets;

import de.fzj.atlascore.configuration.ControllerPaths;
import de.fzj.atlascore.knowledgegraph.KnowledgeGraphService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerPaths.DATASETS)
public class DatasetsController {


    private final KnowledgeGraphService knowledgeGraphService;

    public DatasetsController(KnowledgeGraphService knowledgeGraphService) {
        this.knowledgeGraphService = knowledgeGraphService;
    }

    @GetMapping//(params = "parcellation")
    public Object getParcellationDatasets(@RequestParam(name = "parcellation", required = false) String parcellation) {
        return knowledgeGraphService.getParcellationDatasets(parcellation);
    }

    @GetMapping(value = "/all")
    public Object getAllDatasets(
            @RequestParam(name = "referencespace", required = false) String referencespace,
            @RequestParam(name = "parcellation", required = false) String parcellation) {
        return knowledgeGraphService.getAllDatasets(referencespace, parcellation);
    }
}
