package de.luiskun.manager.dataholder;

import java.util.List;

public class Entry {

    /**
     * Der Name des Eintrags.
     */
    String name;

    /**
     * Kategorie. z.B Serie, Film, Buch,...
     */
    String type;

    /**
     * Wie oft rewatched.
     */
    int reAmount;

    /**
     * Bewertung 1-10
     */
    int rating;

    /**
     * Mit Kategorien? z.B Staffeln, Buecher,...
     */
    boolean withCategories;

    /**
     * Ohne Kategorie: Unterschiedliche Laengen
     */
    boolean sameLength;

    /**
     * Ohne Kategorie, Mit sameLength: Laenge
     */
    int length;

    /**
     * Ohne Kategorien: Episodenliste
     */
    List<Episode> episodes;

    /**
     * Mit Kategorien: Kategorieliste
     */
    List<Category> categories;

    /**
     * Tags
     */
    List<String> tags;

    public Entry() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReAmount() {
        return reAmount;
    }

    public void setReAmount(int reAmount) {
        this.reAmount = reAmount;
    }

    public boolean isWithCategories() {
        return withCategories;
    }

    public void setWithCategories(boolean withCategories) {
        this.withCategories = withCategories;
    }

    public boolean isSameLength() {
        return sameLength;
    }

    public void setSameLength(boolean sameLength) {
        this.sameLength = sameLength;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
