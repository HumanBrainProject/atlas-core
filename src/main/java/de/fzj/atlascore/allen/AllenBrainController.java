package de.fzj.atlascore.allen;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The AllenBrainController is an abstraction for all endpoints of the Allen Brain Atlas
 *
 * @see AllenBrainService
 */
@RestController
@Api(value = "Provides operations against the allen brain api", tags = {"Allen brain"})
public class AllenBrainController {

    @Autowired
    private AllenBrainService allenBrainService;

    @ApiOperation(value = "Get a structure of the allen brain")
    @GetMapping(value = "/allen/structure", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStructure() {
        return new ResponseEntity<>(allenBrainService.getOntologyStructure(), HttpStatus.OK);
    }
}
