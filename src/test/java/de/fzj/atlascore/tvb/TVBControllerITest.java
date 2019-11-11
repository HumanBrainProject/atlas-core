package de.fzj.atlascore.tvb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TVBControllerITest {

    private static LinkedList<String> expectedFilenames = new LinkedList<>();

    @Before
    public void setUp() {
        expectedFilenames.addAll(Arrays.asList(
                "areas.txt",
                "average_orientations.txt",
                "centres.txt",
                "cortical.txt",
                "tract_lengths.txt",
                "volumes.txt",
                "weights.txt"
        ));
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldStartDownload() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/brain/fz/dummy")).andExpect(status().isOk()).andReturn();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mvcResult.getResponse().getContentAsByteArray());
        ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);

        ZipEntry ze;

        LinkedList<String> filenames = new LinkedList<>();

        while((ze = zipInputStream.getNextEntry()) != null) {
            filenames.add(ze.getName());
        }

        assertTrue(mvcResult.getResponse().getContentLength() > 0);
        assertEquals("application/zip", mvcResult.getResponse().getContentType());
        assertThat(filenames, containsInAnyOrder(expectedFilenames.toArray()));
    }
}