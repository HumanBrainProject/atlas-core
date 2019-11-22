package de.fzj.atlascore.tvb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TVBControllerTest {

    private static final String DUMMY_ZIP_FILE = "DUMMY_ZIP_FILE";
    private static final long DUMMY_FILE_SIZE = 1337;
    private static final String NODE_1 = "node1";

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private HttpServletResponse response;

    @Mock
    private File file;

    @Mock
    private Resource resource;

    @Mock
    private ServletOutputStream outputStream;

    @Mock
    private InputStream inputStream;

    @Mock
    private TVBService tvbService;

    @InjectMocks
    private TVBController tvbController;

    @Before
    public void setUp() throws IOException {
        when(file.length()).thenReturn(DUMMY_FILE_SIZE);
        when(resource.getFilename()).thenReturn(DUMMY_ZIP_FILE);
        when(resource.getFile()).thenReturn(file);
        when(resource.getInputStream()).thenReturn(inputStream);

        when(response.getOutputStream()).thenReturn(outputStream);

        when(resourceLoader.getResource(anyString())).thenReturn(resource);
    }

    @Test
    public void shouldGetAllNodes() {
        tvbController.getAllNodes();

        verify(tvbService, times(1)).getAllNodes();
    }

    @Test
    public void shouldGetInformationForNode() {
        tvbController.getInformationForNode(NODE_1);

        verify(tvbService, times(1)).getAllNodeInformation(NODE_1);
    }

    @Test
    public void shouldGetAreaForNode() {
        tvbController.getAreaForNode(NODE_1);

        verify(tvbService, times(1)).getAreaForNode(NODE_1);
    }

    @Test
    public void shouldGetAverageOrientationForNode() {
        tvbController.getAverageOrientationForNode(NODE_1);

        verify(tvbService, times(1)).getAverageOrientationForNode(NODE_1);
    }
    @Test
    public void shouldGetCentresForNode() {
        tvbController.getCentresForNode(NODE_1);

        verify(tvbService, times(1)).getCentreForNode(NODE_1);
    }

    @Test
    public void isCorticalForNode() {
        tvbController.isCorticalForNode(NODE_1);

        verify(tvbService, times(1)).getCorticalForNode(NODE_1);
    }

    @Test
    public void shouldGetTractLengthsForNode() {
        tvbController.getTractLengthsForNode(NODE_1);

        verify(tvbService, times(1)).getTractLengthForNode(NODE_1);
    }

    @Test
    public void shouldGetVolumesForNode() {
        tvbController.getVolumesForNode(NODE_1);

        verify(tvbService, times(1)).getVolumeForNode(NODE_1);
    }

    @Test
    public void shouldGetWeightsForNode() {
        tvbController.getWeightsForNode(NODE_1);

        verify(tvbService, times(1)).getWeightsForNode(NODE_1);
    }

    @Test
    public void shouldReturnAResourceAsStream() throws IOException {
        tvbController.getDummyData(response);

        verify(response).setHeader(HttpHeaders.CONTENT_ENCODING, "binary");
        verify(response).setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
        verify(response).setContentType("application/zip");
        verify(response).setContentLength((int) resource.getFile().length());
        verify(response).flushBuffer();
    }
}