package de.fzj.atlascore.allen;

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
public class AllenBrainController {

    @Autowired
    private AllenBrainService allenBrainService;

    @GetMapping(value = "/allen/structure", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStructure() {
        return new ResponseEntity<>(allenBrainService.getOntologyStructure(), HttpStatus.OK);
    }
}
