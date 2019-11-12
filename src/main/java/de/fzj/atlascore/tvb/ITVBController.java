package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.Node;
import de.fzj.atlascore.entity.Vector;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("default")
public interface ITVBController {

    @GetMapping(value = "/node")
    ResponseEntity<List<String>> getAllNodes();
    @GetMapping(value = "/node/{nodeValue}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Node> getInformationForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/area", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getAreaForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/average-orientation", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vector> getAverageOrientationForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/centre", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Vector> getCentresForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/cortical", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> isCorticalForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/tract-length", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String[]> getTractLengthsForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/volume", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> getVolumesForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/weights", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String[]> getWeightsForNode(@PathVariable(value = "nodeValue") String nodeValue);
}
