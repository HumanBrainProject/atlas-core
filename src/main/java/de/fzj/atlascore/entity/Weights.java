package de.fzj.atlascore.entity;

public class Weights {

    private String node;
    private double weight;

    // For JSON parsing
    public Weights() {
    }

    public Weights(String node, double weight) {
        this.node = node;
        this.weight = weight;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
