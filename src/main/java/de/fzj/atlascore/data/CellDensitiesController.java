package de.fzj.atlascore.data;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cell-densities")
public class CellDensitiesController {

    private final ImageServiceCommunicator imageServiceCommunicator;

    public CellDensitiesController(ImageServiceCommunicator imageServiceCommunicator) {
        this.imageServiceCommunicator = imageServiceCommunicator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CellDensities> getCellDensities(@RequestBody(required = false) Mask[] inputData) {
        return ResponseEntity.ok(imageServiceCommunicator.getCellDensities(inputData));
    }

//    @GetMapping
//    public Mask[] getDummyData() {
//        return new Mask[]{new Mask("abc", "123"), new Mask("def", "456")};
//    }
}
