package de.fzj.atlascore.referencespace;

/**
 * Representation of a Referencespace
 *
 * @author Vadim Marcenko
 */
public class Referencespace {

    private String name;

    // for JSON mapper
    public Referencespace() {
    }

    public Referencespace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
