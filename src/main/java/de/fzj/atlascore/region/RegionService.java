package de.fzj.atlascore.region;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegionService {

    public List<Region> getAllRegions() {
        return Arrays.asList(
                new Region("Region 1")
        );
    }

    public Region getRegionByName(String name) {
        return new Region(name);
    }
}
