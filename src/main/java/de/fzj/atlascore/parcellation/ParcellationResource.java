package de.fzj.atlascore.parcellation;

import de.fzj.atlascore.region.RegionController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Relation(collectionRelation = "parcellations")
public class ParcellationResource extends ResourceSupport {

    private final Parcellation parcellation;

    public ParcellationResource(final Parcellation parcellation, String referencespace) {
        this.parcellation = parcellation;
        add(linkTo(methodOn(ParcellationController.class)
                .getParcellationForName(referencespace, parcellation.getName())
        ).withSelfRel());
        add(linkTo(methodOn(RegionController.class)
                .getAllRegions(referencespace, parcellation.getName())
        ).withRel("regions"));
    }

    public Parcellation getParcellation() {
        return parcellation;
    }
}
