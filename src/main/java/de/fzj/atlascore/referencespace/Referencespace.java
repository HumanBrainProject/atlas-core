package de.fzj.atlascore.referencespace;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Optional;

/**
 * Representation of a Referencespace
 *
 * @author Vadim Marcenko
 */
public class Referencespace {

    private HashMap<String, Object> properties;

    // for JSON mapper
    public Referencespace() {
    }

    public Referencespace(HashMap<String, Object> properties) {
        this.properties = properties;
    }

    public String getName() {
        return Optional.of(properties.get("name")).orElse("").toString();
    }

    public String getId() {
        String id = Optional.ofNullable(properties.get("id")).orElse("").toString();
        if(Strings.isNullOrEmpty(id)) {
            id = Optional.ofNullable(properties.get("fullId")).orElse("").toString();
        }
        int index = id.lastIndexOf("/");
        return id.substring(index+1);
    }

    @JsonAnyGetter
    public HashMap<String, Object> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void setProperties(HashMap<String, Object> properties) {
        this.properties = properties;
    }
}
