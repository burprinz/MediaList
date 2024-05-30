package de.luiskun.manager.ui;

import de.luiskun.manager.ui.windows.EntryCreator;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    public static final int WIDTH = 900, HEIGHT = 700;

    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

        EntryCreator ec = new EntryCreator();
        this.add(ec);

    }

}
