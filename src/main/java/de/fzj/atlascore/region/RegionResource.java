package de.fzj.atlascore.region;

import de.fzj.atlascore.region.entity.Region;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Relation(collectionRelation = "regions")
public class RegionResource extends ResourceSupport {

    private final Region region;

    public RegionResource(Region region, String referencespace, String parcellation) {
        this.region = region;
        add(linkTo(methodOn(RegionController.class)
                .getRegion(referencespace, parcellation, region.getName())
        ).withSelfRel());
    }

    public Region getRegion() {
        return region;
    }
}
