package de.fzj.atlascore.region;

import de.fzj.atlascore.data.CellDensities;
import de.fzj.atlascore.data.CellDensitiesInput;
import de.fzj.atlascore.data.ImageServiceCommunicator;
import de.fzj.atlascore.data.Mask;
import de.fzj.atlascore.parcellation.ParcellationRepository;
import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import de.fzj.atlascore.region.entity.*;
import de.fzj.atlascore.tvb.TVBDummyDataService;
import de.fzj.atlascore.tvb.TVBService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Service to communicate with all kind of repository sources and other services, to get regions and details
 * The service decides from the given referencespace and parcellation which repository or service should be used
 *
 * @see ParcellationRepository
 * @see ReferencespaceRepository
 * @see RegionRepository
 * @see TVBService
 * @see TVBDummyDataService
 *
 * @author Vadim Marcenko
 */
@Service
public class RegionService {

    private static final String TVB = "tvb";
    private static final String JUBRAIN = "jubrain";

    private final RegionRepository regionRepository;
    private final TVBDummyDataService tvbDummyDataService;
    private final TVBService tvbService;
    private final ImageServiceCommunicator imageServiceCommunicator;

    public RegionService(
            RegionRepository regionRepository, TVBDummyDataService tvbDummyDataService,
            TVBService tvbService, ImageServiceCommunicator imageServiceCommunicator) {
        this.regionRepository = regionRepository;
        this.tvbDummyDataService = tvbDummyDataService;
        this.tvbService = tvbService;
        this.imageServiceCommunicator = imageServiceCommunicator;
    }

    List<Region> getAll(String refspaceId, String parcellationName) {
        if(refspaceId.equals(TVB)) {
            return tvbDummyDataService.getAllRegions();
        }
        if(refspaceId.equals(JUBRAIN)) {
            return tvbService.getAllRegions();
        }
        return regionRepository.findAllByReferencespaceAndParcellation(refspaceId, parcellationName);
    }

    Region getByName(String refspaceId, String parcellationName, String name) {
        if(refspaceId.equals(TVB)) {
            return tvbDummyDataService.getRegionByName(name);
        }
        return regionRepository.findOneByReferencespaceAndParcellationAndName(refspaceId, parcellationName, name);
    }

    HashMap<String, Object> getFullStructure(String refSpaceId, String parcellationName) {
        return regionRepository.getFullRegionObjectFromFile(refSpaceId, parcellationName);
    }

    CellDensities getCellDensities(String parcellationName, String regionId, CellDensitiesInput inputData) {
//        List<Mask> masks = inputData.getMasks();
//        if(masks != null && masks.size() > 0) {
//            masks.add(new Mask(parcellationName, regionId));
//        } else {
//            masks = Arrays.asList(new Mask(parcellationName, regionId));
//            Arrays.asList(new Mask(parcellationName, regionId));
//        }
        return imageServiceCommunicator.getCellDensities(regionId, inputData.getMasks(), inputData.getMaskCombination());
//        return imageServiceCommunicator.getCellDensities(masks, inputData.getMaskCombination());
    }
}
