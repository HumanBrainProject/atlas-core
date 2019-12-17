package de.fzj.atlascore.region.entity;

/**
 * Representation of a vector with x, y, z values
 *
 * @author Vadim Marcenko
 */
public class Vector {

    private double x;
    private double y;
    private double z;

    // For JSON parsing
    public Vector() {
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
