package de.luiskun.manager.ui.windows;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.JsonHandler;
import de.luiskun.manager.ui.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class Window extends JPanel {

    List<Entry> currentEntries;
    List<String> currentTags;
    List<String> currentCats;

    public Window() {
        this.setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

        currentEntries = JsonHandler.readEntries();
        currentTags = JsonHandler.readAllTags(currentEntries);
        currentCats = JsonHandler.readAllCats(currentEntries);

        initComponents();
    }

    public abstract void initComponents();

}
