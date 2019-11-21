package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.Node;
import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Weights;
import de.fzj.atlascore.entity.Vector;
import org.eclipse.jetty.server.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/brain/dummy")
public class TVBDummyDataController implements ITVBController {

    @Autowired
    private DummyDataService dummyDataService;

    @Override
    public ResponseEntity<List<String>> getAllNodes() {
        return new ResponseEntity<>(dummyDataService.getAllNodes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Node> getInformationForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getAllNodeInformation(nodeValue), HttpStatus.OK);
    }

    @GetMapping(value = "/node/{nodeValue}/restful", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getNodeWithLinks(@PathVariable(value = "nodeValue") String nodeValue, HttpServletRequest request) {
                String result = "{\n" +
                "  \"node\": \"" + nodeValue + "\",\n" +
                "  \"_links\": {\n" +
                "    \"self\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/restful\",\n" +
                "    \"area\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/area\",\n" +
                "    \"average-orientation\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/average-orientation\",\n" +
                "    \"centre\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/centre\",\n" +
                "    \"cortical\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/cortical\",\n" +
                "    \"tract-lengths\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/tract-length\",\n" +
                "    \"volume\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/volume\",\n" +
                "    \"weights\": " + "\"" +((Request) request).getRootURL() + "/brain/dummy/node/" + nodeValue + "/weights\"\n" +
                "  }\n" +
                "}";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> getAreaForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getAreaForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Vector> getAverageOrientationForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getAverageOrientationForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Vector> getCentresForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getCentreForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> isCorticalForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getCorticalForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TractLength[]> getTractLengthsForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getTractLengthForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Double> getVolumesForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getVolumeForNode(nodeValue), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Weights[]> getWeightsForNode(String nodeValue) {
        return new ResponseEntity<>(dummyDataService.getWeightsForNode(nodeValue), HttpStatus.OK);
    }
}
