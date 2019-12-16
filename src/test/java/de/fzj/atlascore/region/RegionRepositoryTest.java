package de.fzj.atlascore.region;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegionRepositoryTest {


    private RegionRepository regionRepository;

    @Before
    public void setUp() {
        regionRepository = new RegionRepository();
    }

    //region === findAllByReferencespaceAndParcellation
    @Test
    public void shouldReturnAllRegionsForValidRefSpaceAndParcellation() {
//        regionRepository.findAllByReferencespaceAndParcellation();
    }

    @Test
    public void shouldReturnEmptyListForInvalidRefSpace() {

    }

    @Test
    public void shouldReturnEmptyListForInvalidParcellation() {

    }

    @Test
    public void shouldReturnEmptyListForInvalidRefSpaceAndInvalidParcellation() {

    }

    @Test
    public void shouldUseCachedValuesOnSecondCall() {

    }
    //endregion

    //region === findOneByReferencespaceAndParcellationAndName
    @Test
    public void shouldReturnOneRegionForValidData() {

    }
    //endregion
}
