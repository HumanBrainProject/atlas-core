package de.fzj.atlascore.region.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.Objects;

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
                Arrays.equals(weights, region.weights);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, area, averageOrientation, centres, cortical, volume);
        result = 31 * result + Arrays.hashCode(tractLengths);
        result = 31 * result + Arrays.hashCode(weights);
        return result;
    }
}
