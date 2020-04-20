package de.fzj.atlascore.data;

public class Mask {

    private String parcellationId;
    private String regionId;

    public Mask() {
    }

    public Mask(String parcellationId, String regionId) {
        this.parcellationId = parcellationId;
        this.regionId = regionId;
    }

    public String getParcellationId() {
        return parcellationId;
    }

    public void setParcellationId(String parcellationId) {
        this.parcellationId = parcellationId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "Mask{" +
                "parcellationId='" + parcellationId + '\'' +
                ", regionId='" + regionId + '\'' +
                '}';
    }
}
