package de.fzj.atlascore.entity;

public class TractLength {

    private String node;
    private double length;

    public TractLength() {
    }

    public TractLength(String node, double length) {
        this.node = node;
        this.length = length;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
