package de.fzj.atlascore.tvb;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fzj.atlascore.region.entity.TractLength;
import de.fzj.atlascore.region.entity.Vector;
import de.fzj.atlascore.region.entity.Weights;

/**
 * Representation for one Node with all data for TVB simulation
 */
public class Node {

    private Double area;
    @JsonProperty("average-orientation")
    private Vector averageOrientation;
    private Vector centre;
    private Integer cortical;
    @JsonProperty("tract-length")
    private TractLength[] tractLength;
    private Double volume;
    private Weights[] weights;

    // For JSON parsing
    public Node() {
    }

    public Node(Double area, Vector averageOrientation, Vector centre, Integer cortical, TractLength[] tractLength, Double volume, Weights[] weights) {
        this.area = area;
        this.averageOrientation = averageOrientation;
        this.centre = centre;
        this.cortical = cortical;
        this.tractLength = tractLength;
        this.volume = volume;
        this.weights = weights;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Vector getAverageOrientation() {
        return averageOrientation;
    }

    public void setAverageOrientation(Vector averageOrientation) {
        this.averageOrientation = averageOrientation;
    }

    public Vector getCentre() {
        return centre;
    }

    public void setCentre(Vector centre) {
        this.centre = centre;
    }

    public Integer getCortical() {
        return cortical;
    }

    public void setCortical(Integer cortical) {
        this.cortical = cortical;
    }

    public TractLength[] getTractLength() {
        return tractLength;
    }

    public void setTractLength(TractLength[] tractLength) {
        this.tractLength = tractLength;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Weights[] getWeights() {
        return weights;
    }

    public void setWeights(Weights[] weights) {
        this.weights = weights;
    }
}
