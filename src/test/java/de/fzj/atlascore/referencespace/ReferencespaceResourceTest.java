package de.fzj.atlascore.referencespace;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ReferencespaceResourceTest {

    private String inputName;

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {
                {"bigbrain"},
                 {"colin"}
        });
    }

    public ReferencespaceResourceTest(String inputName) {
        this.inputName = inputName;
    }

    @Test
    public void shouldCreateReferencespaceAndAddLinks() {
        Referencespace referencespace = new Referencespace(inputName);
        ReferencespaceResource referencespaceResource = new ReferencespaceResource(referencespace);

        assertEquals(inputName, referencespaceResource.getReferencespace().getName());
        assertEquals("/referencespaces/" + inputName, referencespaceResource.getLink("self").getHref());
        assertEquals("/referencespaces/" + inputName + "/parcellations", referencespaceResource.getLink("parcellations").getHref());
        assertThat(referencespaceResource.getLinks(), hasSize(2));
    }
}
