package de.fzj.atlascore.region;

import de.fzj.atlascore.configuration.ControllerPaths;
import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Vector;
import de.fzj.atlascore.entity.Weights;
import de.fzj.atlascore.tvb.TVBDummyDataService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final TVBDummyDataService tvbDummyDataService;


    public RegionController(RegionService regionService, TVBDummyDataService tvbDummyDataService) {
        this.regionService = regionService;
        this.tvbDummyDataService = tvbDummyDataService;
    }

    @GetMapping
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

    @GetMapping(value = "/{region}/full", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFullRegion(@PathVariable("region") String region) {

        //language=JSON
        String jsonResult = "{\n" +
                "  \"name\": \"Left-Frontal-pol\",\n" +
                "  \"area\": 2001.57,\n" +
                "  \"average-orientation\": {\n" +
                "    \"x\": -0.29,\n" +
                "    \"y\": 0.89,\n" +
                "    \"z\": 0.34\n" +
                "  },\n" +
                "  \"centre\": {\n" +
                "    \"x\": -20.115,\n" +
                "    \"y\": 56.8811,\n" +
                "    \"z\": 22.2188\n" +
                "  },\n" +
                "  \"tract-length\": [\n" +
                "    {\n" +
                "      \"region\": \"Left-Frontal-pole\",\n" +
                "      \"length\": 20.481\n" +
                "    },\n" +
                "    {\n" +
                "      \"region\": \"Left-Orbito-frontal-cortex\",\n" +
                "      \"length\": 38.193\n" +
                "    }\n" +
                "  ],\n" +
                "  \"volume\": 3998,\n" +
                "  \"weights\": [\n" +
                "    {\n" +
                "      \"region\": \"Left-Frontal-pole\",\n" +
                "      \"weight\": 14901\n" +
                "    },\n" +
                "    {\n" +
                "      \"region\": \"Left-Orbito-frontal-cortex\",\n" +
                "      \"weight\": 7150\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cortical\": 1\n" +
                "}";
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(value = "/{region}/area", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getArea(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        return ResponseEntity.ok(regionService.getAreaForNode(refSpaceName, parcellationName, regionName));
    }

    @GetMapping(value = "/{region}/average-orientation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vector> getAverageOrientation(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        return ResponseEntity.ok(regionService.getAverageOrientationForNode(refSpaceName, parcellationName, regionName));
    }

    @GetMapping(value = "/{region}/centre", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vector> getCentres(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        return ResponseEntity.ok(regionService.getCentresForNode(refSpaceName, parcellationName, regionName));
    }

    @GetMapping(value = "/{region}/cortical", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> isCortical(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        return ResponseEntity.ok(regionService.isCorticalForNode(refSpaceName, parcellationName, regionName));
    }

    @GetMapping(value = "/{region}/tract-length", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TractLength[]> getTractLengths(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        return ResponseEntity.ok(regionService.getTractLengthsForNode(refSpaceName, parcellationName, regionName));
    }

    @GetMapping(value = "/{region}/volume", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getVolumes(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        return ResponseEntity.ok(regionService.getVolumesForNode(refSpaceName, parcellationName, regionName));
    }

    @GetMapping(value = "/{region}/weights", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Weights[]> getWeights(
            @PathVariable("refSpaceName") String refSpaceName,
            @PathVariable("parcellationName") String parcellationName,
            @PathVariable("region") String regionName) {
        return ResponseEntity.ok(regionService.getWeightsForNode(refSpaceName, parcellationName, regionName));
    }
}
