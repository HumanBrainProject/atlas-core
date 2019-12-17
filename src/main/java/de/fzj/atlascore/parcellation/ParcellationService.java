package de.fzj.atlascore.parcellation;

import de.fzj.atlascore.referencespace.ReferencespaceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service to communicate with all kind of repository sources, to get parcellations and details
 * The service decides from the given referencespace which repository should be used
 *
 * @see ParcellationRepository
 * @see ReferencespaceRepository
 *
 * @author Vadim Marcenko
 */
@Service
public class ParcellationService {

    private final ParcellationRepository parcellationRepository;
    private final ReferencespaceRepository referencespaceRepository;

    public ParcellationService(ParcellationRepository parcellationRepository, ReferencespaceRepository referencespaceRepository) {
        this.parcellationRepository = parcellationRepository;
        this.referencespaceRepository = referencespaceRepository;
    }

    public List<Parcellation> getParcellations(String refSpaceName) {
        if(refSpaceName.equals("bigbrain") || refSpaceName.equals("colin") || refSpaceName.equals("MNI152")) {
            return parcellationRepository.findAllByReferencespace(refSpaceName);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Referencespace: %s not found", refSpaceName)
        );
    }

    public Parcellation getParcellationByName(String refSpaceName, String name) {
        if (referencespaceRepository.isValidReferenceSpace(refSpaceName)) {
            return parcellationRepository.findOneByReferencespaceAndName(refSpaceName, name);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Referencespace: %s not found", refSpaceName)
            );
        }
    }
}
