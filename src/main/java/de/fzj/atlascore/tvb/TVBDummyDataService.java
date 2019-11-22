package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.TractLength;
import de.fzj.atlascore.entity.Weights;
import de.fzj.atlascore.entity.Vector;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to read static dummy data from resources
 *
 * @see ITVBService
 */
@Service
public class TVBDummyDataService implements ITVBService {

    /**
     * Static data as cache
     */
    private static List<String> nodes = null;
    private static List<String> areas = null;
    private static List<String> averageOrientations = null;
    private static List<String> centres = null;
    private static List<String> cortical = null;
    private static List<String> tractLength = null;
    private static List<String> volumes = null;
    private static List<String> weights = null;

    //region === Helper methods for reading files
    private BufferedReader getReaderForFile(String fileName) {
        try {
            return new BufferedReader(
                    new InputStreamReader(
                            new ClassPathResource("data/dummy/" + fileName, this.getClass().getClassLoader()).getInputStream()
                    )
            );
        } catch (IOException e) {
            //TODO Log error
            return new BufferedReader(new StringReader(""));
        }
    }

    private List<String> readListFromFile(String fileName) {
        BufferedReader reader = getReaderForFile(fileName);

        String currentLine;
        List<String> resultList = new LinkedList();
        try {
            while ((currentLine = reader.readLine()) != null) {
                resultList.add(currentLine);
            }
        } catch (IOException e) {
            //TODO Log error
            //Otherwise no need to react, since an empty list will be returned
        }
        return resultList;
    }
    //endregion

    //region === private methods to get all data from files
     private List<String> getAllAreas() {
        if(areas == null) {
            areas = readListFromFile("areas.txt");
        }
        return areas;
    }

    private List<String> getAverageOrientations() {
        if(averageOrientations == null) {
            averageOrientations = readListFromFile("average_orientations.txt");
        }
        return averageOrientations;
    }

    private List<String> getCentres() {
        if(centres == null) {
            centres = readListFromFile("centres.txt");
        }
        return centres;
    }

    private List<String> getCortical() {
        if(cortical == null) {
            cortical = readListFromFile("cortical.txt");
        }
        return cortical;
    }

    private List<String> getTractLength() {
        if(tractLength == null) {
            tractLength = readListFromFile("tract_lengths.txt");
        }
        return tractLength;
    }

    private List<String> getVolumes() {
        if(volumes == null) {
            volumes = readListFromFile("volumes.txt");
        }
        return volumes;
    }

    private List<String> getWeights() {
        if(weights == null) {
            weights = readListFromFile("weights.txt");
        }
        return weights;
    }
    //endregion

    //region === public methods for data used by controller
    public List<String> getAllNodes() {
        if(nodes == null) {
            List<String> strings = readListFromFile("centres.txt");
            nodes = strings.stream().map(s -> s.split(" ")[0]).collect(Collectors.toList());
        }
        return nodes;
    }

    public Double getAreaForNode(String node) {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return Double.valueOf(getAllAreas().get(indexOfNode));
        }
        return null;
    }

    public Vector getAverageOrientationForNode(String node) {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return new Vector(
                    Double.valueOf(getAverageOrientations().get(indexOfNode).split(" ")[0]),
                    Double.valueOf(getAverageOrientations().get(indexOfNode).split(" ")[1]),
                    Double.valueOf(getAverageOrientations().get(indexOfNode).split(" ")[2])
            );
        }
        return null;
    }

    public Vector getCentreForNode(String node) {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return new Vector(
                    Double.valueOf(getCentres().get(indexOfNode).split(" ")[1]),
                    Double.valueOf(getCentres().get(indexOfNode).split(" ")[2]),
                    Double.valueOf(getCentres().get(indexOfNode).split(" ")[3])
            );
        }
        return null;
    }

    public Integer getCorticalForNode(String node) {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return Integer.valueOf(getCortical().get(indexOfNode));
        }
        return null;
    }

    public TractLength[] getTractLengthForNode(String node) {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            List<TractLength> tractLengths = new LinkedList<>();
            String[] lengthAsString = getTractLength().get(indexOfNode).split(" ");
            for(int i=0; i<lengthAsString.length; i++) {
                tractLengths.add(new TractLength(getAllNodes().get(i), Double.valueOf(lengthAsString[i])));
            }
            return tractLengths.toArray(new TractLength[0]);
        }
        return new TractLength[]{};
    }

    public Double getVolumeForNode(String node) {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return Double.valueOf(getVolumes().get(indexOfNode));
        }
        return null;
    }

    public Weights[] getWeightsForNode(String node) {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            List<Weights> weights = new LinkedList<>();
            String[] weightsAsString = getWeights().get(indexOfNode).split(" ");
            for(int i=0; i<weightsAsString.length; i++) {
                weights.add(new Weights(getAllNodes().get(i), Double.valueOf(weightsAsString[i])));
            }
            return weights.toArray(new Weights[0]);
        }
        return new Weights[]{};
    }
    //endregion
}
