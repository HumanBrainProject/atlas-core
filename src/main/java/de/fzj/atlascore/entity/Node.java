package de.fzj.atlascore.entity;

public class Node {

    private String area;
    private Vector averageOrientation;
    private Vector centre;
    private String cortical;
    private String[] tractLength;
    private String volume;
    private String[] weights;

    public Node() {
    }

    public Node(String area, Vector averageOrientation, Vector centre, String cortical, String[] tractLength, String volume, String[] weights) {
        this.area = area;
        this.averageOrientation = averageOrientation;
        this.centre = centre;
        this.cortical = cortical;
        this.tractLength = tractLength;
        this.volume = volume;
        this.weights = weights;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
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

    public String getCortical() {
        return cortical;
    }

    public void setCortical(String cortical) {
        this.cortical = cortical;
    }

    public String[] getTractLength() {
        return tractLength;
    }

    public void setTractLength(String[] tractLength) {
        this.tractLength = tractLength;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String[] getWeights() {
        return weights;
    }

    public void setWeights(String[] weights) {
        this.weights = weights;
    }
}
