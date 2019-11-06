package de.fzj.atlascore.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Arrays;

public class Area {

    private Double id;
    private String label;
    @JsonAlias("average-orientations")
    private AverageOrientation averageOrientations;
    private Centre centre;
    private Double cortical;
    @JsonAlias("tract-lengths")
    private Double[] tractLengths;
    private Double volume;
    private Double[] weights;

    public Area() {
    }

    public Area(Double id, String label, AverageOrientation averageOrientations, Centre centre, Double cortical, Double[] tractLengths, Double volume, Double[] weights) {
        this.id = id;
        this.label = label;
        this.averageOrientations = averageOrientations;
        this.centre = centre;
        this.cortical = cortical;
        this.tractLengths = tractLengths;
        this.volume = volume;
        this.weights = weights;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public AverageOrientation getAverageOrientations() {
        return averageOrientations;
    }

    public void setAverageOrientations(AverageOrientation averageOrientations) {
        this.averageOrientations = averageOrientations;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Double getCortical() {
        return cortical;
    }

    public void setCortical(Double cortical) {
        this.cortical = cortical;
    }

    public Double[] getTractLengths() {
        return tractLengths;
    }

    public void setTractLengths(Double[] tractLengths) {
        this.tractLengths = tractLengths;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double[] getWeights() {
        return weights;
    }

    public void setWeights(Double[] weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", averageOrientations=" + averageOrientations +
                ", centre=" + centre +
                ", cortical=" + cortical +
                ", tractLengths=" + Arrays.toString(tractLengths) +
                ", volume=" + volume +
                ", weights=" + Arrays.toString(weights) +
                '}';
    }
}
