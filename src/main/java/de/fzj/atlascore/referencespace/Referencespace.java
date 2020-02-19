package de.fzj.atlascore.referencespace;

/**
 * Representation of a Referencespace
 *
 * @author Vadim Marcenko
 */
public class Referencespace {

    private String name;
    private String id;

    // for JSON mapper
    public Referencespace() {
    }

    public Referencespace(String name, String id) {
        this.name = name;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
