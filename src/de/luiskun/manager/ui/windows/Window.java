package de.luiskun.manager.ui.windows;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.JsonHandler;
import de.luiskun.manager.ui.Screen;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class Window extends JPanel implements Updatable{

    public List<Entry> currentEntries;
    public List<String> currentTags;
    public List<String> currentCats;

    public Window() {
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setBounds(0, 0, Screen.WIDTH, Screen.HEIGHT);

        currentEntries = JsonHandler.readEntries();
        currentTags = JsonHandler.readAllTags(currentEntries);
        currentCats = JsonHandler.readAllCats(currentEntries);

        initComponents();
    }

    public abstract void initComponents();

    @Override
    public abstract void update();

    @Override
    public abstract void paintComponent(Graphics g);
}
