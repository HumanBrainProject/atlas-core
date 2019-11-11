package de.fzj.atlascore.tvb;

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
    @GetMapping(value = "/node/{nodeValue}")
    ResponseEntity<String> getInformationForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/area")
    ResponseEntity<String> getAreaForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/average-orientation")
    ResponseEntity<String> getAverageOrientationForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/centre")
    ResponseEntity<String> getCentresForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/cortical")
    ResponseEntity<String> isCorticalForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/tract-length")
    ResponseEntity<String> getTractLengthsForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/volume")
    ResponseEntity<String> getVolumesForNode(@PathVariable(value = "nodeValue") String nodeValue);
    @GetMapping(value = "/node/{nodeValue}/weights")
    ResponseEntity<String> getWeightsForNode(@PathVariable(value = "nodeValue") String nodeValue);
}
