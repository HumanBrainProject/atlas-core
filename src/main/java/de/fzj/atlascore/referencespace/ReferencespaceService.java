package de.fzj.atlascore.referencespace;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to communicate with all kind of repository sources, to get referencespaces and details
 *
 * {@link ReferencespaceRepository}
 *
 * @author Vadim Marcenko
 */
@Service
public class ReferencespaceService {

    private final ReferencespaceRepository referencespaceRepository;

    public ReferencespaceService(ReferencespaceRepository referencespaceRepository) {
        this.referencespaceRepository = referencespaceRepository;
    }

    public List<Referencespace> getReferencespaces() {
        return this.referencespaceRepository.getAll();
    }

    public Referencespace getReferencespaceById(String id) {
        return referencespaceRepository.findOneById(id);
    }
}
