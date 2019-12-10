package de.fzj.atlascore.tvb;

import de.fzj.atlascore.region.entity.TractLength;
import de.fzj.atlascore.region.entity.Vector;
import de.fzj.atlascore.region.entity.Weights;

import java.util.List;

/**
 * Interface for all services that can provide data for TVB
 */
public interface ITVBService {

    List<String> getAllNodes();
    Double getAreaForNode(String node);
    Vector getAverageOrientationForNode(String node);
    Vector getCentreForNode(String node);
    Integer getCorticalForNode(String node);
    TractLength[] getTractLengthForNode(String node);
    Double getVolumeForNode(String node);
    Weights[] getWeightsForNode(String node);
    default Node getAllNodeInformation(String node) {
        return new Node(
                getAreaForNode(node),
                getAverageOrientationForNode(node),
                getCentreForNode(node),
                getCorticalForNode(node),
                getTractLengthForNode(node),
                getVolumeForNode(node),
                getWeightsForNode(node)
        );
    }
}
