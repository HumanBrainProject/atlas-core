package de.fzj.atlascore.tvb;

import de.fzj.atlascore.region.entity.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to read static dummy data from resources
 */
@Service
public class TVBDummyDataService {

    /**
     * Static data as cache
     */
    private static List<String> regionNames = null;
    private static List<String> areas = null;
    private static List<String> averageOrientations = null;
    private static List<String> centres = null;
    private static List<String> cortical = null;
    private static List<String> tractLength = null;
    private static List<String> volumes = null;
    private static List<String> weights = null;

    @PostConstruct
    public void init() {
        areas = readListFromFile("areas.txt");
        averageOrientations = readListFromFile("average_orientations.txt");
        centres = readListFromFile("centres.txt");
        cortical = readListFromFile("cortical.txt");
        tractLength = readListFromFile("tract_lengths.txt");
        volumes = readListFromFile("volumes.txt");
        weights = readListFromFile("weights.txt");
        regionNames = centres.stream().map(s -> s.split(" ")[0]).collect(Collectors.toList());
    }

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

    private Double getAreaForRegionName(String name) {
        int indexOfRegion = regionNames.indexOf(name);
        if(indexOfRegion > -1) {
            return Double.valueOf(areas.get(indexOfRegion));
        }
        return null;
    }

    private Vector getAverageOrientationForRegionName(String name) {
        int indexOfRegion = regionNames.indexOf(name);
        if(indexOfRegion > -1) {
            return new Vector(
                    Double.valueOf(averageOrientations.get(indexOfRegion).split(" ")[0]),
                    Double.valueOf(averageOrientations.get(indexOfRegion).split(" ")[1]),
                    Double.valueOf(averageOrientations.get(indexOfRegion).split(" ")[2])
            );
        }
        return null;
    }

    private Vector getCentreForRegionName(String name) {
        int indexOfRegion = regionNames.indexOf(name);
        if(indexOfRegion > -1) {
            return new Vector(
                    Double.valueOf(centres.get(indexOfRegion).split(" ")[1]),
                    Double.valueOf(centres.get(indexOfRegion).split(" ")[2]),
                    Double.valueOf(centres.get(indexOfRegion).split(" ")[3])
            );
        }
        return null;
    }

    private Integer getCorticalForRegionName(String name) {
        int indexOfRegion = regionNames.indexOf(name);
        if(indexOfRegion > -1) {
            return Integer.valueOf(cortical.get(indexOfRegion));
        }
        return null;
    }

    private TractLength[] getTractLengthForRegionName(String name) {
        int indexOfRegion = regionNames.indexOf(name);
        if(indexOfRegion > -1) {
            List<TractLength> tractLengthsList = new LinkedList<>();
            String[] lengthAsString = tractLength.get(indexOfRegion).split(" ");
            for(int i=0; i<lengthAsString.length; i++) {
                tractLengthsList.add(new TractLength(regionNames.get(i), Double.valueOf(lengthAsString[i])));
            }
            return tractLengthsList.toArray(new TractLength[0]);
        }
        return new TractLength[]{};
    }

    private Double getVolumeForRegionName(String name) {
        int indexOfRegion = regionNames.indexOf(name);
        if(indexOfRegion > -1) {
            return Double.valueOf(volumes.get(indexOfRegion));
        }
        return null;
    }

    private Weights[] getWeightsForRegionName(String name) {
        int indexOfRegion = regionNames.indexOf(name);
        if(indexOfRegion > -1) {
            List<Weights> weightsList = new LinkedList<>();
            String[] weightsAsString = weights.get(indexOfRegion).split(" ");
            for(int i=0; i<weightsAsString.length; i++) {
                weightsList.add(new Weights(regionNames.get(i), Double.valueOf(weightsAsString[i])));
            }
            return weightsList.toArray(new Weights[0]);
        }
        return new Weights[]{};
    }
    //endregion

    public List<Region> getAllRegions() {
        List<Region> result = new LinkedList<>();
        for (String region: regionNames) {
            result.add(RegionBuilder
                    .aRegion()
                    .withName(region)
                    .withArea(getAreaForRegionName(region))
                    .withAverageOrientation(getAverageOrientationForRegionName(region))
                    .withCentres(getCentreForRegionName(region))
                    .withCortical(getCorticalForRegionName(region))
                    .withTractLengths(getTractLengthForRegionName(region))
                    .withVolume(getVolumeForRegionName(region))
                    .withWeights(getWeightsForRegionName(region))
                    .build()
            );
        }
        return result;
    }

    public Region getRegionByName(String name) {
        if (regionNames.contains(name)) {
            return RegionBuilder
                    .aRegion()
                    .withName(name)
                    .withArea(getAreaForRegionName(name))
                    .withAverageOrientation(getAverageOrientationForRegionName(name))
                    .withCentres(getCentreForRegionName(name))
                    .withCortical(getCorticalForRegionName(name))
                    .withTractLengths(getTractLengthForRegionName(name))
                    .withVolume(getVolumeForRegionName(name))
                    .withWeights(getWeightsForRegionName(name))
                    .build();
        } else {
            return null;
        }
    }
}
