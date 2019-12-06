package de.fzj.atlascore.referencespace;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/referencespaces")
public class ReferencespaceController {

    private final ReferencespaceService referencespaceService;

    public ReferencespaceController(ReferencespaceService referencespaceService) {
        this.referencespaceService = referencespaceService;
    }

    @GetMapping
    @ResponseBody
    public Resources<ReferencespaceResource> getAllReferencespaces() {
        List<Referencespace> referencespaces = referencespaceService.getReferencespaces();
        return new Resources<>(referencespaces.stream().map(ReferencespaceResource::new).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{refSpaceName}")
    public Resource<ReferencespaceResource> getReferencespaceByName(@PathVariable("refSpaceName") String refSpaceName) {
        Referencespace referencespaceByName = referencespaceService.getReferencespaceByName(refSpaceName);
        if (referencespaceByName == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("referencespace: %s not found", refSpaceName)
            );
        }
        return new Resource<>(new ReferencespaceResource(referencespaceByName));
    }
}
