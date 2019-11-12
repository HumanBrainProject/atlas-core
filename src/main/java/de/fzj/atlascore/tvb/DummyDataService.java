package de.fzj.atlascore.tvb;

import de.fzj.atlascore.entity.Node;
import de.fzj.atlascore.entity.Vector;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DummyDataService {

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
    private BufferedReader getReaderForFile(String fileName) throws IOException {
        return new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("data/dummy/"+fileName, this.getClass().getClassLoader()).getInputStream()
                )
        );
    }

    private List<String> readListFromFile(String fileName) throws IOException {
        BufferedReader reader = getReaderForFile(fileName);

        String currentLine;
        List<String> resultList = new LinkedList();

        while((currentLine = reader.readLine()) != null) {
            resultList.add(currentLine);
        }
        return resultList;
    }
    //endregion

    //region === private methods to get all data from files
     private List<String> getAllAreas() throws IOException {
        if(areas == null) {
            areas = readListFromFile("areas.txt");
        }
        return areas;
    }

    private List<String> getAverageOrientations() throws IOException {
        if(averageOrientations == null) {
            averageOrientations = readListFromFile("average_orientations.txt");
        }
        return averageOrientations;
    }

    private List<String> getCentres() throws IOException {
        if(centres == null) {
            centres = readListFromFile("centres.txt");
        }
        return centres;
    }

    private List<String> getCortical() throws IOException {
        if(cortical == null) {
            cortical = readListFromFile("cortical.txt");
        }
        return cortical;
    }

    private List<String> getTractLength() throws IOException {
        if(tractLength == null) {
            tractLength = readListFromFile("tract_lengths.txt");
        }
        return tractLength;
    }

    private List<String> getVolumes() throws IOException {
        if(volumes == null) {
            volumes = readListFromFile("volumes.txt");
        }
        return volumes;
    }

    private List<String> getWeights() throws IOException {
        if(weights == null) {
            weights = readListFromFile("weights.txt");
        }
        return weights;
    }
    //endregion

    //region === public methods for data used by controller
    public List<String> getAllNodes() throws IOException {
        if(nodes == null) {
            List<String> strings = readListFromFile("centres.txt");
            nodes = strings.stream().map(s -> s.split(" ")[0]).collect(Collectors.toList());
        }
        return nodes;
    }

    public String getAreaForNode(String node) throws IOException {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return getAllAreas().get(indexOfNode);
        }
        return "";
    }

    public Vector getAverageOrientationForNode(String node) throws IOException {
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

    public Vector getCentreForNode(String node) throws IOException {
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

    public String getCorticalForNode(String node) throws IOException {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return getCortical().get(indexOfNode);
        }
        return "";
    }

    public String[] getTractLengthForNode(String node) throws IOException {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return getTractLength().get(indexOfNode).split(" ");
        }
        return new String[]{};
    }

    public String getVolumeForNode(String node) throws IOException {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return getVolumes().get(indexOfNode);
        }
        return "";
    }

    public String[] getWeightsForNode(String node) throws IOException {
        int indexOfNode = getAllNodes().indexOf(node);
        if(indexOfNode > -1) {
            return getWeights().get(indexOfNode).split(" ");
        }
        return new String[]{};
    }

    public Node getAllNodeInformation(String node) throws IOException {
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
    //endregion
}
