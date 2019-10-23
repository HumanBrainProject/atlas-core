package de.fzj.atlascore.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateConfigurationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void asd() {
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        long interceptorCount = interceptors
                .stream()
                .filter(clientHttpRequestInterceptor -> clientHttpRequestInterceptor instanceof RestTemplateRequestInterceptor)
                .count();

        assertEquals(1, interceptorCount);
    }
}