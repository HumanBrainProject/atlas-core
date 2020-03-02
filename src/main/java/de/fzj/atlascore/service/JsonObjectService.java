package de.fzj.atlascore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.HashMap;

public final class JsonObjectService {

    public static Integer[] getArrayOrNullFromObject(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONArray(key).toList().stream().toArray(Integer[]::new);
        } catch(Exception e) {
            return null;
        }
    }

    public static HashMap getHashMapOrNullFromJsonObject(JSONObject jsonObject, String key) {
        try {
            return new ObjectMapper().readValue(jsonObject.get(key).toString(), HashMap.class);
        } catch(Exception e) {
            return null;
        }
    }
}
