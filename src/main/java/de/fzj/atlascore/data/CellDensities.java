package de.fzj.atlascore.data;

import java.util.List;

public class CellDensities {

    private List<Mask> masks;
    private double mean;
    private double standardDeviation;
    private double median;
    private double mode;
    private double voxels;
    private DistributionData [] distributionData;

    public CellDensities() {
    }

    public CellDensities(List<Mask> masks, double mean, double standardDeviation, double median, double mode, double voxels, DistributionData[] distributionData) {
        this.masks = masks;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.median = median;
        this.mode = mode;
        this.voxels = voxels;
        this.distributionData = distributionData;
    }

    public List<Mask> getMasks() {
        return masks;
    }

    public void setMasks(List<Mask> masks) {
        this.masks = masks;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getMode() {
        return mode;
    }

    public void setMode(double mode) {
        this.mode = mode;
    }

    public double getVoxels() {
        return voxels;
    }

    public void setVoxels(double voxels) {
        this.voxels = voxels;
    }

    public DistributionData[] getDistributionData() {
        return distributionData;
    }

    public void setDistributionData(DistributionData[] distributionData) {
        this.distributionData = distributionData;
    }
}
