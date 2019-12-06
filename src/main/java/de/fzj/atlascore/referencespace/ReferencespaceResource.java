package de.fzj.atlascore.referencespace;

import de.fzj.atlascore.parcellation.ParcellationController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ReferencespaceResource extends ResourceSupport {

    private final Referencespace referencespace;

    public ReferencespaceResource(final Referencespace referencespace) {
        this.referencespace = referencespace;
        add(linkTo(methodOn(ReferencespaceController.class)
                .getReferencespaceByName(referencespace.getName())
        ).withSelfRel());
        add(linkTo(methodOn(ParcellationController.class)
                .getAllParcellationsForReferencespace(referencespace.getName())
        ).withRel("parcellations"));
    }

    public Referencespace getReferencespace() {
        return referencespace;
    }
}
