package de.luiskun.manager.dataholder;

public class Episode {

    /**
     * Name der Episode
     * z.B Chaptername, oder Episodenname
     */
    String name;

    /**
     * Laenge der Episode
     * wenn sameLength=true irrelevant
     */
    int length;

    public Episode() {

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
}
