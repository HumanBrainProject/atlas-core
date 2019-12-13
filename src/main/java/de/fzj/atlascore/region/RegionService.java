package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.*;
import de.fzj.atlascore.tvb.TVBDummyDataService;
import de.fzj.atlascore.tvb.TVBService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegionService {

    private final TVBDummyDataService tvbDummyDataService;
    private final TVBService tvbService;

    public RegionService(TVBDummyDataService tvbDummyDataService, TVBService tvbService) {
        this.tvbDummyDataService = tvbDummyDataService;
        this.tvbService = tvbService;
    }

    public List<Region> getAllRegions(String refSpaceName, String parcellationName) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getAllRegions();
        }
        if(refSpaceName.equals("jubrain")) {
            return tvbService.getAllRegions();
        }
        return Arrays.asList(
                RegionBuilder.aRegion().withName("Region 1").build()
        );
    }

    public Region getRegionByName(String refSpaceName, String name) {
        if(refSpaceName.equals("tvb")) {
            return tvbDummyDataService.getRegionByName(name);
        }
        return RegionBuilder.aRegion().withName(name).build();
    }
}
