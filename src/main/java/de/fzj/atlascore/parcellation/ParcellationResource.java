package de.fzj.atlascore.parcellation;

import de.fzj.atlascore.region.RegionController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Resource to wrap a parcellation and add HATEOAS support with link to all regions and a self link
 *
 * @see Parcellation
 *
 * @author Vadim Marcenko
 */
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
        add(linkTo(methodOn(RegionController.class)
                .getFullRegions(referencespace, parcellation.getName())
        ).withRel("full-structure"));
        add(linkTo(methodOn(ParcellationController.class)
                .getDatasets(referencespace, parcellation.getId())
        ).withRel("data"));
    }

    public Parcellation getParcellation() {
        return parcellation;
    }
}
