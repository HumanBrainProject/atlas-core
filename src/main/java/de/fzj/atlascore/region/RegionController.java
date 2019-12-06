package de.fzj.atlascore.region;

import de.fzj.atlascore.configuration.ControllerPaths;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public Resources<RegionResource> getAllRegions(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName) {
        List<Region> regions = regionService.getAllRegions();
        return new Resources<>(
                regions
                        .stream()
                        .map(region -> new RegionResource(region, refSpaceName, parcellationName))
                        .collect(Collectors.toList())
        );

    }

    @GetMapping(value = "/{region}")
    public Resource<RegionResource> getRegion(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        Region region = regionService.getRegionByName(regionName);
        if (region == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Region: %s not found in parcellation %s", regionName, parcellationName)
            );
        }
        return new Resource<>(new RegionResource(region, refSpaceName, parcellationName));
    }
}
