package de.fzj.atlascore.region.entity;

public final class RegionBuilder {
    private String name;
    private Double area;
    private Vector averageOrientation;
    private Vector centres;
    private Integer cortical;
    private TractLength[] tractLengths;
    private Double volume;
    private Weights[] weights;

    private RegionBuilder() {
    }

    public static RegionBuilder aRegion() {
        return new RegionBuilder();
    }

    public RegionBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RegionBuilder withArea(Double area) {
        this.area = area;
        return this;
    }

    public RegionBuilder withAverageOrientation(Vector averageOrientation) {
        this.averageOrientation = averageOrientation;
        return this;
    }

    public RegionBuilder withCentres(Vector centres) {
        this.centres = centres;
        return this;
    }

    public RegionBuilder withCortical(Integer cortical) {
        this.cortical = cortical;
        return this;
    }

    public RegionBuilder withTractLengths(TractLength[] tractLengths) {
        this.tractLengths = tractLengths;
        return this;
    }

    public RegionBuilder withVolume(Double volume) {
        this.volume = volume;
        return this;
    }

    public RegionBuilder withWeights(Weights[] weights) {
        this.weights = weights;
        return this;
    }

    public Region build() {
        Region region = new Region();
        region.setName(name);
        region.setArea(area);
        region.setAverageOrientation(averageOrientation);
        region.setCentres(centres);
        region.setCortical(cortical);
        region.setTractLengths(tractLengths);
        region.setVolume(volume);
        region.setWeights(weights);
        return region;
    }
}
