package de.fzj.atlascore.referencespace;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferencespaceService {

    private final ReferencespaceRepository referencespaceRepository;

    public ReferencespaceService(ReferencespaceRepository referencespaceRepository) {
        this.referencespaceRepository = referencespaceRepository;
    }

    public List<Referencespace> getReferencespaces() {
        return referencespaceRepository.findAll();
    }

    public Referencespace getReferencespaceByName(String name) {
        return referencespaceRepository.findOneByName(name);
    }
}
