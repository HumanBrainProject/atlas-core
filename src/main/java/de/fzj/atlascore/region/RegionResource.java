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
        add(linkTo(methodOn(RegionController.class)
                .getArea(referencespace, parcellation, region.getName())
        ).withRel("data"));
        add(linkTo(methodOn(RegionController.class)
                .getAverageOrientation(referencespace, parcellation, region.getName())
        ).withRel("data"));
        add(linkTo(methodOn(RegionController.class)
                .getCentres(referencespace, parcellation, region.getName())
        ).withRel("data"));
        add(linkTo(methodOn(RegionController.class)
                .getTractLengths(referencespace, parcellation, region.getName())
        ).withRel("data"));
        add(linkTo(methodOn(RegionController.class)
                .getVolumes(referencespace, parcellation, region.getName())
        ).withRel("data"));
        add(linkTo(methodOn(RegionController.class)
                .getWeights(referencespace, parcellation, region.getName())
        ).withRel("data"));
        add(linkTo(methodOn(RegionController.class)
                .isCortical(referencespace, parcellation, region.getName())
        ).withRel("data"));
    }

    public Region getRegion() {
        return region;
    }
}
