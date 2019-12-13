package de.fzj.atlascore.region;

import de.fzj.atlascore.configuration.ControllerPaths;
import de.fzj.atlascore.region.entity.Region;
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
@RequestMapping(
        ControllerPaths.REFERENCESPACES + "/{refSpaceName}" +
        ControllerPaths.PARCELLATIONS + "/{parcellationName}" +
        ControllerPaths.REGIONS
)
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<RegionResource> getAllRegions(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName) {
        List<Region> regions = regionService.getAllRegions(refSpaceName, parcellationName);
        return new Resources<>(
                regions
                        .stream()
                        .map(region -> new RegionResource(region, refSpaceName, parcellationName))
                        .collect(Collectors.toList())
        );

    }

    @GetMapping(value = "/{region}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<RegionResource> getRegion(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        Region region = regionService.getRegionByName(refSpaceName, regionName);
        if (region == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Region: %s not found in parcellation %s", regionName, parcellationName)
            );
        }
        return new Resource<>(new RegionResource(region, refSpaceName, parcellationName));
    }
}
