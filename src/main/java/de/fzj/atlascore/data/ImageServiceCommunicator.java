package de.fzj.atlascore.data;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImageServiceCommunicator {

    private static final HashMap<String, String> CORTICAL_LAYER_MAPPING = new LinkedHashMap() {{
        put("cortical layer 1", "layer1");
        put("cortical layer 2", "layer2");
        put("cortical layer 3", "layer3");
        put("cortical layer 4", "layer4");
        put("cortical layer 5", "layer5");
        put("cortical layer 6", "layer6");
    }};

    private static final HashMap<String, String> CYTOARCHITECTONIC_MAPPING = new LinkedHashMap<>() {{
       put("Area hOc1 (V1, 17, CalcS)", "v1");
       put("Area hOc2 (V2, 18)", "v2");
    }};

    private HashMap<String, CellDensities> cellDensities;

    public CellDensities getCellDensities(String region, List<Mask> masks, MaskCombination maskCombination) {
        try {
            readResultsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String mappedLayerRegion = !StringUtils.isEmpty(CORTICAL_LAYER_MAPPING.get(region)) ? CORTICAL_LAYER_MAPPING.get(region) : region;

        if(StringUtils.isEmpty(mappedLayerRegion)) {
            //throw not found Exception
        } else {
            if(masks.isEmpty()) {
                //throw Input Exception
            } else {
                String resultName = mappedLayerRegion + "_" + masks.get(0).getRegionId();
                return cellDensities.get(resultName);
            }
        }
        return new CellDensities(
                masks,
                maskCombination,
                237.56355746025292,
                24.582238428256325,
                255,
                255,
                13655596,
                createDistributionData()
        );
    }

    public CellDensities getCellDensities(List<Mask> masks, MaskCombination maskCombination) {
        /**
         * Call ImageService with a list of masks:
         * Mask defined by:
         * {
         *   "parcellationId" : "string",
         *   "regionId" : "string",
         * ? "url" : "string"
         * }
         */
        for(Mask m : masks) {
            m.getRegionId();
        }

        return new CellDensities(
                masks,
                maskCombination,
                237.56355746025292,
                24.582238428256325,
                255,
                255,
                13655596,
                createDistributionData()
        );
    }

    public HashMap<String, CellDensities> getCellDensities() {
        try {
            readResultsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(cellDensities.keySet());
        return cellDensities;
    }

    private void readResultsFromFile() throws IOException {
        if(cellDensities == null) {
            cellDensities = new HashMap<>();
        } else {
            return;
        }
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource(
                                "data/cell-densities/ALL_RESULT.csv",
                                this.getClass().getClassLoader()).getInputStream()
                )
        );

        // Read first line with headers
        // NAME, MEAN, STDEV, MEDIAN, MODE, VOXELS
        reader.readLine();
        // Read next lines with data
        String dataLine = reader.readLine();
        while(dataLine != null) {
            String[] cellDensitieData = dataLine.split(",");
            cellDensities.put(
                    cellDensitieData[0],
                    new CellDensities(
                            null,
                            null,
                            Double.parseDouble(cellDensitieData[1].trim()),
                            Double.parseDouble(cellDensitieData[2].trim()),
                            Double.parseDouble(cellDensitieData[3].trim()),
                            Double.parseDouble(cellDensitieData[4].trim()),
                            Double.parseDouble(cellDensitieData[5].trim()),
                            readDistributionDataFromFile(cellDensitieData[0])
                    )
            );
            dataLine = reader.readLine();
        }
    }

    private DistributionData[] readDistributionDataFromFile(String name) {
        List<DistributionData> distributionData = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new ClassPathResource(
                                    "data/cell-densities/histogram_" + name + ".csv",
                                    this.getClass().getClassLoader()).getInputStream()
                    )
            );
            String data = reader.readLine();
            while (data != null) {
                String[] splitData = data.split(",");
                distributionData.add(new DistributionData(
                        Integer.parseInt(splitData[0].trim()),
                        Integer.parseInt(splitData[1].trim())
                ));
                data = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return distributionData.stream().toArray(DistributionData[]::new);
    }

    private DistributionData[] createDistributionData() {
        return new DistributionData[]{
                new DistributionData(0,0),
                new DistributionData(1,0),
                new DistributionData(2,1),
                new DistributionData(3,5),
                new DistributionData(4,11),
                new DistributionData(5,17),
                new DistributionData(6,20),
                new DistributionData(7,34),
                new DistributionData(8,23),
                new DistributionData(128,200),
                new DistributionData(200,324),
                new DistributionData(255,99)
        };
    }

    private String regionNameMapping(String region1, String region2) {
        return "";
    }
}
