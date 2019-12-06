package de.fzj.atlascore.region;

import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RegionResource extends ResourceSupport {

    private final Region region;

    public RegionResource(Region region, String referencespace, String parcellation) {
        this.region = region;
        add(linkTo(methodOn(RegionController.class)
                .getRegion(referencespace, parcellation, region.getName())
        ).withSelfRel());
    }
}
