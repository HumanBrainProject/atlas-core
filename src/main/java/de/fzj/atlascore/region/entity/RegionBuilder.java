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
    private Integer[] rgb;
    private Integer[] position;
    private String status;
    private String label;
    private String hemisphere;

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

    public RegionBuilder withRgb(Integer[] rgb) {
        this.rgb = rgb;
        return this;
    }

    public RegionBuilder withPosition(Integer[] position) {
        this.position = position;
        return this;
    }

    public RegionBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public RegionBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public RegionBuilder withHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
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
        region.setRgb(rgb);
        region.setPosition(position);
        region.setStatus(status);
        region.setLabel(label);
        region.setHemisphere(hemisphere);
        return region;
    }
}
