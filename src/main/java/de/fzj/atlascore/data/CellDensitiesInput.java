package de.fzj.atlascore.data;

import java.util.ArrayList;

public class CellDensitiesInput {

    private ArrayList<Mask> masks;
    private MaskCombination maskCombination;

    public CellDensitiesInput() {
    }

    public CellDensitiesInput(ArrayList<Mask> masks, MaskCombination maskCombination) {
        this.masks = masks;
        this.maskCombination = maskCombination;
    }

    public ArrayList<Mask> getMasks() {
        return masks;
    }

    public void setMasks(ArrayList<Mask> masks) {
        this.masks = masks;
    }

    public MaskCombination getMaskCombination() {
        return maskCombination;
    }

    public void setMaskCombination(MaskCombination maskCombination) {
        this.maskCombination = maskCombination;
    }

    @Override
    public String toString() {
        return "CellDensitiesInput{" +
                "masks=" + masks +
                ", maskCombination=" + maskCombination +
                '}';
    }
}
