package de.luiskun.manager.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.luiskun.manager.dataholder.Entry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    public static final String entryPath = "res/data/entries/";

    public static void saveEntry(Entry entry) {
        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(new File(entryPath + entry.getName() + ".json"), entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Entry> readEntries() {
        File[] files = new File(entryPath).listFiles();

        List<Entry> entries = new ArrayList<>();

        for (File f : files) {
            entries.add(readEntry(f.getName()));
        }

        return entries;
    }

    public static Entry readEntry(String name) {
        String path = entryPath + name;

        try {
            String json = new String(Files.readAllBytes(Paths.get(path)));
            ObjectMapper om = new ObjectMapper();
            Entry e = om.readValue(json, Entry.class);
            return e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readAllTags(List<Entry> entries) {
        List<String> tags = new ArrayList<>();

        // Durch jeden Entry
        for (Entry e : entries) {
            // Durch jeden Tag in Entry
            for (String s : e.getTags()) {
                boolean found = false;
                // Durch jeden aktuellen Tag
                for (String ss : tags) {
                    if (s.equals(ss)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    tags.add(s);
                }
            }
        }

        return tags;
    }

    public static List<String> readAllCats(List<Entry> entries) {
        List<String> cats = new ArrayList<>();

        // Durch jeden Entry
        for (Entry e : entries) {
            boolean found = false;
            for (String ss : cats) {
                if (e.getCategory().equals(ss)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                cats.add(e.getCategory());
            }
        }

        return cats;
    }

}
