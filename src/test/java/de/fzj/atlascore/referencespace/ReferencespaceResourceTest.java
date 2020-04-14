package de.fzj.atlascore.referencespace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ReferencespaceResourceTest {

    private static final String REFERENCESPACE_BIGBRAIN = "bigbrain";
    private String inputId;
    private HashMap<String, Object> properties = new HashMap<>();

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {
                {"bigbrain"},
                 {"colin"}
        });
    }

    public ReferencespaceResourceTest(String inputId) {
        this.inputId = inputId;
    }

    @Test
    public void shouldCreateReferencespaceAndAddLinks() {
        properties.put("id", inputId);
        properties.put("name", REFERENCESPACE_BIGBRAIN);
        Referencespace referencespace = new Referencespace(properties);
        ReferencespaceResource referencespaceResource = new ReferencespaceResource(referencespace);

        assertEquals(inputId, referencespaceResource.getReferencespace().getId());
        assertEquals("/referencespaces/" + inputId, referencespaceResource.getLink("self").getHref());
        assertEquals("/referencespaces/" + inputId + "/parcellations", referencespaceResource.getLink("parcellations").getHref());
        assertEquals("/referencespaces/" + inputId + "/datasets", referencespaceResource.getLink("data").getHref());
        assertThat(referencespaceResource.getLinks(), hasSize(3));
    }
}
