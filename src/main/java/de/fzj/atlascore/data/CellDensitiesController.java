package de.fzj.atlascore.data;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cell-densities")
public class CellDensitiesController {

    private final ImageServiceCommunicator imageServiceCommunicator;

    public CellDensitiesController(ImageServiceCommunicator imageServiceCommunicator) {
        this.imageServiceCommunicator = imageServiceCommunicator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CellDensities> getCellDensities(@RequestBody(required = false) List<Mask> inputData) {
        return ResponseEntity.ok(imageServiceCommunicator.getCellDensities(inputData, MaskCombination.INTERSECTION));
    }
}
