package de.fzj.atlascore.region;

import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Vector;
import de.fzj.atlascore.entity.Weights;
import de.fzj.atlascore.tvb.TVBDummyDataService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private final TVBDummyDataService tvbDummyDataService;

    public RegionService(TVBDummyDataService tvbDummyDataService) {
        this.tvbDummyDataService = tvbDummyDataService;
    }

    public List<Region> getAllRegions(String refSpaceName, String parcellationName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getAllNodes().stream().map(n -> new Region(n)).collect(Collectors.toList());
        }
        return Arrays.asList(
                new Region("Region 1")
        );
    }

    public Region getRegionByName(String name) {
        return new Region(name);
    }


    public Double getAreaForNode(String refSpaceName, String parcellationName, String regionName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getAreaForNode(regionName);
        }
        return null;
    }

    public Vector getAverageOrientationForNode(String refSpaceName, String parcellationName, String regionName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getAverageOrientationForNode(regionName);
        }
        return null;
    }

    public Vector getCentresForNode(String refSpaceName, String parcellationName, String regionName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getCentreForNode(regionName);
        }
        return null;
    }

    public Integer isCorticalForNode(String refSpaceName, String parcellationName, String regionName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getCorticalForNode(regionName);
        }
        return null;
    }

    public TractLength[] getTractLengthsForNode(String refSpaceName, String parcellationName, String regionName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getTractLengthForNode(regionName);
        }
        return new TractLength[0];
    }

    public Double getVolumesForNode(String refSpaceName, String parcellationName, String regionName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getVolumeForNode(regionName);
        }
        return null;
    }

    public Weights[] getWeightsForNode(String refSpaceName, String parcellationName, String regionName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getWeightsForNode(regionName);
        }
        return new Weights[0];
    }
}
