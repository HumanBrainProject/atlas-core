package de.fzj.atlascore.region.entity;

import java.util.Objects;

/**
 * Representation of weights
 *
 * @author Vadim Marcenko
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weights weights = (Weights) o;
        return Double.compare(weights.weight, weight) == 0 &&
                Objects.equals(node, weights.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, weight);
    }
}
