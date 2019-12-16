package de.fzj.atlascore.region;

import de.fzj.atlascore.parcellation.ParcellationRepository;
import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import de.fzj.atlascore.region.entity.*;
import de.fzj.atlascore.tvb.TVBDummyDataService;
import de.fzj.atlascore.tvb.TVBService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private static final String TVB = "tvb";
    private static final String JUBRAIN = "jubrain";

    private final ReferencespaceRepository referencespaceRepository;
    private final ParcellationRepository parcellationRepository;
    private final RegionRepository regionRepository;
    private final TVBDummyDataService tvbDummyDataService;
    private final TVBService tvbService;

    public RegionService(
            ReferencespaceRepository referencespaceRepository,
            ParcellationRepository parcellationRepository,
            RegionRepository regionRepository, TVBDummyDataService tvbDummyDataService,
            TVBService tvbService) {
        this.referencespaceRepository = referencespaceRepository;
        this.parcellationRepository = parcellationRepository;
        this.regionRepository = regionRepository;
        this.tvbDummyDataService = tvbDummyDataService;
        this.tvbService = tvbService;
    }

    public List<Region> getAllRegions(String refSpaceName, String parcellationName) {
        if(refSpaceName.equals(TVB)) {
            return tvbDummyDataService.getAllRegions();
        }
        if(refSpaceName.equals(JUBRAIN)) {
            return tvbService.getAllRegions();
        }
        return regionRepository.findAllByReferencespaceAndParcellation(refSpaceName, parcellationName);
    }

    public Region getRegionByName(String refSpaceName, String parcellationName, String name) {
        if(refSpaceName.equals(TVB)) {
            return tvbDummyDataService.getRegionByName(name);
        }
        return regionRepository.findOneByReferencespaceAndParcellationAndName(refSpaceName, parcellationName, name);
    }
}
