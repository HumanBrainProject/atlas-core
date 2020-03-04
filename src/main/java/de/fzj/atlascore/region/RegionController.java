package de.fzj.atlascore.region;

import de.fzj.atlascore.configuration.ControllerPaths;
import de.fzj.atlascore.region.entity.Region;
import io.swagger.annotations.Api;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller to deliver a list of valid regions for a parcellation and more details to one region by name
 *
 * @see Region
 * @see RegionResource
 * @see RegionService
 *
 * @author Vadim Marcenko
 */
@RestController
@RequestMapping(
        ControllerPaths.REFERENCESPACES + "/{refSpaceId}" +
        ControllerPaths.PARCELLATIONS + "/{parcellationName}" +
        ControllerPaths.REGIONS
)
@Api(value = "Regions for a valid parcellation and referencespace", tags = {"Regions"})
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<RegionResource> getAllRegions(
            @PathVariable("refSpaceId") String refSpaceId,
            @PathVariable("parcellationName") String parcellationName) {
        List<Region> regions = regionService.getAll(refSpaceId, parcellationName);
        return new Resources<>(
                regions
                        .stream()
                        .map(region -> new RegionResource(region, refSpaceId, parcellationName))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "full")
    public HashMap<String, Object> getFullRegions(
            @PathVariable("refSpaceId") String refSpaceId,
            @PathVariable("parcellationName") String parcellationName) {
        HashMap<String, Object> fullRegionStructure = regionService.getFullStructure(refSpaceId, parcellationName);
        if(fullRegionStructure == null || fullRegionStructure.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Full region data not found for parcellation: %s", parcellationName)
            );
        }
        return fullRegionStructure;
    }

    @GetMapping(value = "/{regionName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<RegionResource> getRegion(
            @PathVariable("refSpaceId") String refSpaceId,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("regionName") String regionName) {
        Region region = regionService.getByName(refSpaceId, parcellationName, regionName);
        if (region == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Region: %s not found in parcellation %s", regionName, parcellationName)
            );
        }
        return new Resource<>(new RegionResource(region, refSpaceId, parcellationName));
    }
}
