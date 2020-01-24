package de.fzj.atlascore.region.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representation of a Region
 * When converted to JSON, only not null properties will be included
 *
 * A Region with values can only be build by a {@link RegionBuilder}
 *
 * @author Vadim Marcenko
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Region {

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

    public Region() {
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Double getArea() {
        return area;
    }

    void setArea(Double area) {
        this.area = area;
    }

    public Vector getAverageOrientation() {
        return averageOrientation;
    }

    void setAverageOrientation(Vector averageOrientation) {
        this.averageOrientation = averageOrientation;
    }

    public Vector getCentres() {
        return centres;
    }

    void setCentres(Vector centres) {
        this.centres = centres;
    }

    public Integer getCortical() {
        return cortical;
    }

    void setCortical(Integer cortical) {
        this.cortical = cortical;
    }

    public TractLength[] getTractLengths() {
        return tractLengths;
    }

    void setTractLengths(TractLength[] tractLengths) {
        this.tractLengths = tractLengths;
    }

    public Double getVolume() {
        return volume;
    }

    void setVolume(Double volume) {
        this.volume = volume;
    }

    public Weights[] getWeights() {
        return weights;
    }

    void setWeights(Weights[] weights) {
        this.weights = weights;
    }

    public Integer[] getRgb() {
        return rgb;
    }

    void setRgb(Integer[] rgb) {
        this.rgb = rgb;
    }

    public Integer[] getPosition() {
        return position;
    }

    void setPosition(Integer[] position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHemisphere() {
        return hemisphere;
    }

    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(name, region.name) &&
                Objects.equals(area, region.area) &&
                Objects.equals(averageOrientation, region.averageOrientation) &&
                Objects.equals(centres, region.centres) &&
                Objects.equals(cortical, region.cortical) &&
                Arrays.equals(tractLengths, region.tractLengths) &&
                Objects.equals(volume, region.volume) &&
                Arrays.equals(weights, region.weights) &&
                Arrays.equals(rgb, region.rgb) &&
                Arrays.equals(position, region.position) &&
                Objects.equals(status, region.status) &&
                Objects.equals(label, region.label) &&
                Objects.equals(hemisphere, region.hemisphere);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, area, averageOrientation, centres, cortical, volume, status, label, hemisphere);
        result = 31 * result + Arrays.hashCode(tractLengths);
        result = 31 * result + Arrays.hashCode(weights);
        result = 31 * result + Arrays.hashCode(rgb);
        result = 31 * result + Arrays.hashCode(position);
        return result;
    }
}
