package de.fzj.atlascore.knowledgegraph;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DummyData {

    private static LinkedHashMap<String, ArrayList> RESULT = null;
    private static ArrayList RESULTARRAY = new ArrayList<LinkedHashMap>();

    private static ArrayList PARCELLATION_WAXHOLM_LIST = new ArrayList();
    private static LinkedHashMap PARCELLATION_WAXHOLM_VALUES = new LinkedHashMap();
    private static LinkedHashMap REGION_MOUSE_VALUES = new LinkedHashMap();
    private static LinkedHashMap REGION_RAT_VALUES = new LinkedHashMap();
    private static ArrayList REGION_MOUSE_LIST = new ArrayList();
    private static ArrayList REGION_RAT_LIST = new ArrayList();

    private static LinkedHashMap REFERENCESPACE_WAXHOLM_VALUES = new LinkedHashMap();
    private static ArrayList REFERENCESPACE_WAXHOLM_LIST = new ArrayList();

    public static String REFERENCESPACE_ID_WAXHOLM = "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/d5717c4a-0fa1-46e6-918c-b8003069ade8";
    public static String PARCELLATION_ID_WAXHOLM = "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationatlas/v1.0.0/2449a7f0-6dd0-4b5a-8f1e-aec0db03679d";
    public static String REGION_ID_RAT_WHOLE = "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationregion/v1.0.0/b2b56201-472c-4f70-842f-cf2133eacaba";
    public static String REGION_ID_MOUSE_WHOLE = "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationregion/v1.0.0/2bdfac7a-b38c-4c55-9843-0b56cb90bb67";

    private static LinkedHashMap DATASET_1 = new LinkedHashMap();
    private static LinkedHashMap DATASET_2 = new LinkedHashMap();


    public static LinkedHashMap<String, ArrayList> getDatasets() {
        if(RESULT == null) {
            RESULT = new LinkedHashMap<>();
            REFERENCESPACE_WAXHOLM_VALUES.put("https://schema.hbp.eu/myQuery/@id", "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/d5717c4a-0fa1-46e6-918c-b8003069ade8");
            REFERENCESPACE_WAXHOLM_VALUES.put("https://schema.hbp.eu/myQuery/name", "Waxholm Space rat brain atlas v.2.0");
            REFERENCESPACE_WAXHOLM_LIST.add(REFERENCESPACE_WAXHOLM_VALUES);
            PARCELLATION_WAXHOLM_VALUES.put("https://schema.hbp.eu/myQuery/@id", "https://nexus.humanbrainproject.org/v0/data/minds/core/referencespace/v1.0.0/d5717c4a-0fa1-46e6-918c-b8003069ade8");
            PARCELLATION_WAXHOLM_VALUES.put("https://schema.hbp.eu/myQuery/name", "Waxholm Space rat brain atlas v.2.0");
            PARCELLATION_WAXHOLM_LIST.add(PARCELLATION_WAXHOLM_VALUES);
            REGION_MOUSE_VALUES.put("https://schema.hbp.eu/myQuery/@id", "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationregion/v1.0.0/2bdfac7a-b38c-4c55-9843-0b56cb90bb67");
            REGION_MOUSE_VALUES.put("https://schema.hbp.eu/myQuery/name", "Mouse Whole brain (v3 2015)");
            REGION_MOUSE_LIST.add(REGION_MOUSE_VALUES);
            REGION_RAT_VALUES.put("https://schema.hbp.eu/myQuery/@id", "https://nexus.humanbrainproject.org/v0/data/minds/core/parcellationregion/v1.0.0/b2b56201-472c-4f70-842f-cf2133eacaba");
            REGION_RAT_VALUES.put("https://schema.hbp.eu/myQuery/name", "Rat Whole brain (v2)");
            REGION_RAT_LIST.add(REGION_RAT_VALUES);
            DATASET_1.put("https://schema.hbp.eu/myQuery/reference_space", REFERENCESPACE_WAXHOLM_LIST);
            DATASET_1.put("https://schema.hbp.eu/myQuery/parcellationAtlas", PARCELLATION_WAXHOLM_LIST);
            DATASET_1.put("https://schema.hbp.eu/myQuery/parcellationRegion", REGION_MOUSE_LIST);
            DATASET_2.put("https://schema.hbp.eu/myQuery/reference_space", REFERENCESPACE_WAXHOLM_LIST);
            DATASET_2.put("https://schema.hbp.eu/myQuery/parcellationAtlas", PARCELLATION_WAXHOLM_LIST);
            DATASET_2.put("https://schema.hbp.eu/myQuery/parcellationRegion", REGION_RAT_LIST);
            RESULTARRAY.add(DATASET_1);
            RESULTARRAY.add(DATASET_2);
            RESULT.put("results", RESULTARRAY);
        }
        return RESULT;
    }
}
