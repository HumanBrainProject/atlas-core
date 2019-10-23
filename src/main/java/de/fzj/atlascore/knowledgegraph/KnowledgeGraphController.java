package de.fzj.atlascore.knowledgegraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The AtlasCoreController is an abstraction for all endpoints of the AtlasCore Webservice
 *
 * @see KnowledgeGraphService
 */
@RestController
public class KnowledgeGraphController {

    @Autowired
    private KnowledgeGraphService knowledgeGraphService;

    @GetMapping(value = "kg/species", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllReferences() {
        return new ResponseEntity<>(knowledgeGraphService.getAllSpecies(), HttpStatus.OK);
    }
}
