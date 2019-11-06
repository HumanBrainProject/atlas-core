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

    @InjectMocks
    private TVBController TVBController;

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
    public void shouldReturnAResourceAsStream() throws IOException {
        TVBController.getDummyData(response);

        verify(response).setHeader(HttpHeaders.CONTENT_ENCODING, "binary");
        verify(response).setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
        verify(response).setContentType("application/zip");
        verify(response).setContentLength((int) resource.getFile().length());
        verify(response).flushBuffer();
    }

    @Test
    public void shouldHandleIoExceptionWithNotFoundError() throws IOException {
        doThrow(new IOException("")).when(resource).getFile();

        TVBController.getDummyData(response);

        verify(response, times(0)).flushBuffer();
    }
}