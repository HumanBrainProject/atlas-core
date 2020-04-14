package de.fzj.atlascore.parcellation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ParcellationResourceTest {

    private static final String kgId = "ID123";
    private static final HashMap<String, String> kgDataset = new LinkedHashMap<>() {{
        put("kgId", kgId);
    }};
    private static final ArrayList<HashMap<String, String>> originDatasets = new ArrayList<>() {{
        add(kgDataset);
    }};
    private  HashMap<String, Object> PROPERTIES = new HashMap<>();
    private String refSpace;
    private String inputName;

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {
                {"ref1", "par1"},
                {"ref1", "par2"}
        });
    }

    public ParcellationResourceTest(String refSpace, String inputName) {
        this.refSpace = refSpace;
        this.inputName = inputName;
    }

    @Test
    public void shouldCreateParcellationResourceAndAddLinks() {
        PROPERTIES = new HashMap<>();
        PROPERTIES.put("name", inputName);
        PROPERTIES.put("originDatasets", originDatasets);
        Parcellation parcellation = new Parcellation(PROPERTIES);
        ParcellationResource parcellationResource = new ParcellationResource(parcellation, refSpace);

        assertEquals(inputName, parcellationResource.getParcellation().getName());
        assertEquals(
                "/referencespaces/" + refSpace + "/parcellations/" + inputName,
                parcellationResource.getLink("self").getHref()
        );
        assertEquals(
                "/referencespaces/" + refSpace + "/parcellations/" + inputName + "/regions",
                parcellationResource.getLink("regions").getHref()
        );
        assertEquals(
                "/referencespaces/" + refSpace + "/parcellations/" + kgId + "/datasets",
                parcellationResource.getLink("data").getHref()
        );
        assertThat(parcellationResource.getLinks(), hasSize(4));
    }
}
