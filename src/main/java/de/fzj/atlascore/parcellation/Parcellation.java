package de.fzj.atlascore.parcellation;

import java.util.Objects;

/**
 * Representation of a Parcellation
 *
 * @author Vadim Marcenko
 */
public class Parcellation {

    private String name;

    public Parcellation() {
    }

    public Parcellation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcellation that = (Parcellation) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
