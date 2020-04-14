package de.fzj.atlascore.parcellation;

import de.fzj.atlascore.configuration.ControllerPaths;
import de.fzj.atlascore.knowledgegraph.KnowledgeGraphService;
import io.swagger.annotations.Api;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller to deliver a list of all parcellations for a referencespace and details to one parcellation by name
 * and referencespace
 *
 * @see Parcellation
 * @see ParcellationResource
 * @see ParcellationService
 *
 * @author Vadim Marcenko
 */
@RestController
@RequestMapping(ControllerPaths.REFERENCESPACES + "/{refSpaceId}" + ControllerPaths.PARCELLATIONS)
@Api(value = "Parcellations for all valid referencespaces", tags = {"Parcellations"})
public class ParcellationController {

    private final ParcellationService parcellationService;
    private final KnowledgeGraphService knowledgeGraphService;

    public ParcellationController(ParcellationService parcellationService, KnowledgeGraphService knowledgeGraphService) {
        this.parcellationService = parcellationService;
        this.knowledgeGraphService = knowledgeGraphService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<ParcellationResource> getAllParcellationsForReferencespace(@PathVariable("refSpaceId") String refSpaceId) {
        List<Parcellation> parcellations = parcellationService.getParcellations(refSpaceId);

        return new Resources<>(
                parcellations
                        .stream()
                        .map(parcellation -> new ParcellationResource(parcellation, refSpaceId))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/{parcellationName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<ParcellationResource> getParcellationForName(
            @PathVariable("refSpaceId") String refSpaceId,
            @PathVariable("parcellationName") String parcellationName) {
        Parcellation parcellationByName = parcellationService.getParcellationByName(refSpaceId, parcellationName);
        if (parcellationByName == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Parcellation: %s not found for referencespace: %s", parcellationName, refSpaceId)
            );
        }
        return new Resource<>(new ParcellationResource(parcellationByName, refSpaceId));
    }

    @GetMapping(value = "/{parcellationId}/datasets", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getDatasets(
            @PathVariable("refSpaceId") String refSpaceId,
            @PathVariable("parcellationId") String parcellationId) {
        return knowledgeGraphService.getParcellationDatasets(parcellationId);
    }
}
