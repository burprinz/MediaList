package de.luiskun.manager.dataholder;

import java.util.List;

public class Category {

    /**
     * Name, z.B Staffelname
     */
    String name;

    /**
     * Laenge
     */
    int length;

    /**
     * Mit sameLength: anzahl Teile
     */
    int amEpisodes;

    public Category() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAmEpisodes() {
        return amEpisodes;
    }

    public void setAmEpisodes(int amEpisodes) {
        this.amEpisodes = amEpisodes;
    }
}
