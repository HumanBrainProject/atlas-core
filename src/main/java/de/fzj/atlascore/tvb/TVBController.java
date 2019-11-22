package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.Node;
import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Weights;
import de.fzj.atlascore.entity.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Controller to deliver data for brain ...
 */
@RestController
@RequestMapping("/brain/fz")
public class TVBController implements ITVBController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private TVBService tvbService;

    @Override
    public ResponseEntity<List<String>> getAllNodes() {
        return new ResponseEntity<>(tvbService.getAllNodes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Node> getInformationForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getAllNodeInformation(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> getAreaForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getAreaForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Vector> getAverageOrientationForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getAverageOrientationForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Vector> getCentresForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getCentreForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> isCorticalForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getCorticalForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TractLength[]> getTractLengthsForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getTractLengthForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> getVolumesForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getVolumeForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Weights[]> getWeightsForNode(String nodeValue) {
        return new ResponseEntity<>(tvbService.getWeightsForNode(nodeValue), HttpStatus.OK);
    }

    @GetMapping(value = "/{brainId}/node/{nodeValue}/connectivity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getConnectivityForNode(@PathVariable(value = "nodeValue") String nodeValue) {
        return new ResponseEntity<>("{\"value\": \"Connectivity for node: " + nodeValue + "\"}", HttpStatus.OK);
    }

    @GetMapping(value = "/dummy", produces = "application/zip")
    public void getDummyData(HttpServletResponse response) {
        Resource resource = resourceLoader.getResource("classpath:data/connectivity.vep.zip");
        try {
            response.setHeader(HttpHeaders.CONTENT_ENCODING, "binary");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
            response.setContentType("application/zip");
            response.setContentLength((int) resource.getFile().length());

            OutputStream outStream = response.getOutputStream();
            outStream.write(resource.getInputStream().readAllBytes());
            outStream.close();

            response.flushBuffer();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}