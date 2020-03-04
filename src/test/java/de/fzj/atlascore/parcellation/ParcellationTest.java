package de.fzj.atlascore.parcellation;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ParcellationTest {

    private static final HashMap<String, Object> PROPERTIES = new HashMap<>() {{
        put("key1", "value1");
        put("key2", "value2");
    }};
    private static final String PARCELLATION_NAME = "par1";
    private static final String PARCELLATION_NAME_SECOND = "par2";

    private Parcellation parcellation;
    private Parcellation parcellationSecond;

    @Before
    public void setUp() {
        parcellation = new Parcellation(PROPERTIES);
        parcellationSecond = new Parcellation(PROPERTIES);
    }

    @Test
    public void shouldBeEqual() {
        assertEquals(parcellation, parcellationSecond);
    }

//    @Test
//    public void shouldBeNotEqual() {
//        parcellationSecond.setName(PARCELLATION_NAME_SECOND);
//
//        assertNotEquals(parcellation, parcellationSecond);
//    }
}
