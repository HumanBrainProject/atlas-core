package de.fzj.atlascore.knowledgegraph;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * The AtlasCoreController is an abstraction for all endpoints of the AtlasCore Webservice
 *
 * @see KnowledgeGraphService
 */
@RestController
@ApiIgnore
public class KnowledgeGraphController {

    @Autowired
    private KnowledgeGraphService knowledgeGraphService;

    @ApiOperation(value = "Get a list of all species")
    @GetMapping(value = "kg/species", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllReferences() {
        return new ResponseEntity<>(knowledgeGraphService.getAllSpecies(), HttpStatus.OK);
    }
}
