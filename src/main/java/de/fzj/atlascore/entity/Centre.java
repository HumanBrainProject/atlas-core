package de.fzj.atlascore.entity;


public class Centre {
    private String label;
    private double x;
    private double y;
    private double z;

    // For JSON parsing
    public Centre() {
    }

    public Centre(String label, double x, double y, double z) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
