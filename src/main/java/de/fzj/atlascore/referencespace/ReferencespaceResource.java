package de.fzj.atlascore.referencespace;

import de.fzj.atlascore.parcellation.ParcellationController;
import io.micrometer.core.instrument.util.StringEscapeUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import org.unbescape.html.HtmlEscape;

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
                .getReferencespaceByName(referencespace.getId())
        ).withSelfRel());
        add(linkTo(methodOn(ParcellationController.class)
                .getAllParcellationsForReferencespace(referencespace.getId())
        ).withRel("parcellations"));
    }

    public Referencespace getReferencespace() {
        return referencespace;
    }
}
