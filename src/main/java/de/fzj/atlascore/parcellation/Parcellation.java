package de.fzj.atlascore.parcellation;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Representation of a Parcellation
 *
 * @author Vadim Marcenko
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parcellation {

    private HashMap<String, Object> properties;

    // for JSON mapper
    public Parcellation() {
    }

    public Parcellation(HashMap properties) {
        this.properties = properties;
    }

    public String getName() {
        return Optional.of(properties.get("name")).orElse("").toString();
    }

    @JsonAnyGetter
    public HashMap<String, Object> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void setProperties(HashMap<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcellation that = (Parcellation) o;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }
}
