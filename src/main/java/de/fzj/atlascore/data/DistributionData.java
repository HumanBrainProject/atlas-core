package de.fzj.atlascore.data;

public class DistributionData {

    private int value;
    private int occurrence;

    public DistributionData() {
    }

    public DistributionData(int value, int occurrence) {
        this.value = value;
        this.occurrence = occurrence;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }
}
