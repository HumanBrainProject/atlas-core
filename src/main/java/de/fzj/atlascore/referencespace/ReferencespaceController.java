package de.fzj.atlascore.referencespace;

import de.fzj.atlascore.configuration.ControllerPaths;
import io.swagger.annotations.Api;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import
org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller to deliver a list of valid referencespaces and more details to one referencespace by id
 *
 * @see Referencespace
 * @see ReferencespaceResource
 * @see ReferencespaceService
 *
 * @author Vadim Marcenko
 */
@RestController
@RequestMapping(ControllerPaths.REFERENCESPACES)
@Api(value = "All valid referencespaces", tags = {"Referencespace"})
public class ReferencespaceController {

    private final ReferencespaceService referencespaceService;

    public ReferencespaceController(ReferencespaceService referencespaceService) {
        this.referencespaceService = referencespaceService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Resources<ReferencespaceResource> getAllReferencespaces() {
        List<Referencespace> referencespaces = referencespaceService.getReferencespaces();
        return new Resources<>(referencespaces.stream().map(ReferencespaceResource::new).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{refSpaceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Resource<ReferencespaceResource> getReferencespaceById(@PathVariable("refSpaceId") String refSpaceId) {
        Referencespace referencespaceByName = referencespaceService.getReferencespaceById(refSpaceId);
        if (referencespaceByName == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("referencespace: %s not found", refSpaceId)
            );
        }
        return new Resource<>(new ReferencespaceResource(referencespaceByName));
    }
}
