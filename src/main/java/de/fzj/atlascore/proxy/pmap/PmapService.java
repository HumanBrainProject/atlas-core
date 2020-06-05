package de.fzj.atlascore.proxy.pmap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Service for getting probability maps. The Service is mainly a reverse proxy for
 * https://pmap-pmap-service-new.apps.hbp.eu
 */
@Service
public class PmapService {

    private final RestTemplate restTemplate;

    public PmapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get all available areas
     * @return List of areas
     */
    @Cacheable("api-data")
    public String[] getAllRegions() {
        return this.restTemplate.getForEntity("https://pmap-pmap-service-new.apps.hbp.eu/images", String[].class).getBody();
    }

    /**
     * Get a probability map for given area and specifications
     * @param data
     * @return Downloadable zip file
     */
    public byte[] getPmap(Object data) {
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

//        File file = restTemplate.execute(
//                "https://pmap-pmap-service-new.apps.hbp.eu/multimerge",
//                HttpMethod.POST,
//                requestCallback(data),
//                clientHttpResponse -> {
//                    File ret = File.createTempFile("download", "tmp");
//                    StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
//                    return ret;
//                });
//        return file;
//        File file = null;
//        try {
//            final File ret = File.createTempFile("download", "tmp");
//            restTemplate.execute(
//                    "https://pmap-pmap-service-new.apps.hbp.eu/multimerge",
//                    HttpMethod.POST,
//                    clientHttpRequest -> clientHttpRequest.getBody().write(data.toString().getBytes()),
//                    clientHttpResponse -> {
//                        StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret, true));
//                        return ret;
//                    });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return file;
//        return this.restTemplate.postForEntity("https://pmap-pmap-service-new.apps.hbp.eu/multimerge", data, null, "").getBody();
        byte[] bytes = this.restTemplate.postForObject("https://pmap-pmap-service-new.apps.hbp.eu/multimerge", data, byte[].class);
        return bytes;
    }


    RequestCallback requestCallback(final Object data) {
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), data);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"merged_volume.nii.gz\""
            );
//            clientHttpRequest.getHeaders().add(
//                    HttpHeaders.AUTHORIZATION, "Basic " + getBase64EncodedLogPass());
        };
    }


    private class BodySettingRequestCallback implements RequestCallback {

        private String body;
        private ObjectMapper objectMapper;

        public BodySettingRequestCallback(String body, ObjectMapper objectMapper){
            this.body = body;
            this.objectMapper = objectMapper;
        }

        @Override
        public void doWithRequest(ClientHttpRequest request) throws IOException {
            byte[] json = getEventBytes();
            request.getBody().write(json);
        }

        byte[] getEventBytes() throws JsonProcessingException {
            return objectMapper.writeValueAsBytes(body);
        }
    }
}
