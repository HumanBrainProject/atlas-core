package de.fzj.atlascore.referencespace;

import de.fzj.atlascore.parcellation.ParcellationController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Resource to wrap a referencespace and add HATEOAS support with link to all parcellations and a self link
 *
 * @see Referencespace
 *
 * @author Vadim Marcenko
 */
@Relation(collectionRelation = "referencespaces")
public class ReferencespaceResource extends ResourceSupport {

    private final Referencespace referencespace;

    public ReferencespaceResource(final Referencespace referencespace) {
        this.referencespace = referencespace;
        add(linkTo(methodOn(ReferencespaceController.class)
                .getReferencespaceById(referencespace.getId())
        ).withSelfRel());
        add(linkTo(methodOn(ReferencespaceController.class)
                .getDatasets(referencespace.getId())
        ).withRel("data"));
        add(linkTo(methodOn(ParcellationController.class)
                .getAllParcellationsForReferencespace(referencespace.getId())
        ).withRel("parcellations"));
    }

    public Referencespace getReferencespace() {
        return referencespace;
    }
}
