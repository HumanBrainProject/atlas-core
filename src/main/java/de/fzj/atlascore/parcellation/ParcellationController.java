package de.fzj.atlascore.parcellation;

import de.fzj.atlascore.configuration.ControllerPaths;
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

@RestController
@RequestMapping(ControllerPaths.REFERENCESPACES + "/{refSpaceName}" + ControllerPaths.PARCELLATIONS)
public class ParcellationController {

    private final ParcellationService parcellationService;

    public ParcellationController(ParcellationService parcellationService) {
        this.parcellationService = parcellationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<ParcellationResource> getAllParcellationsForReferencespace(@PathVariable("refSpaceName") String refSpaceName) {
        List<Parcellation> parcellations = parcellationService.getParcellations(refSpaceName);

        return new Resources<>(
                parcellations
                        .stream()
                        .map(parcellation -> new ParcellationResource(parcellation, refSpaceName))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/{parcellationName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<ParcellationResource> getParcellationForName(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName) {
        Parcellation parcellationByName = parcellationService.getParcellationByName(parcellationName);
        if (parcellationByName == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Parcellation: %s not found for referencespace: %s", parcellationName, refSpaceName)
            );
        }
        return new Resource<>(new ParcellationResource(parcellationByName, refSpaceName));
    }
}
