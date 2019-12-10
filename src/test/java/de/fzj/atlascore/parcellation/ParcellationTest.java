package de.fzj.atlascore.parcellation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParcellationTest {

    private static final String PARCELLATION_NAME = "par1";
    private static final String PARCELLATION_NAME_SECOND = "par2";

    private Parcellation parcellation;
    private Parcellation parcellationSecond;

    @Before
    public void setUp() {
        parcellation = new Parcellation(PARCELLATION_NAME);
        parcellationSecond = new Parcellation(PARCELLATION_NAME);
    }

    @Test
    public void shouldBeEqual() {
        assertEquals(parcellation, parcellationSecond);
    }

    @Test
    public void shouldBeNotEqual() {
        parcellationSecond.setName(PARCELLATION_NAME_SECOND);

        assertNotEquals(parcellation, parcellationSecond);
    }
}
