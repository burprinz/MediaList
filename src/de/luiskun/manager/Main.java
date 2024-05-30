package de.luiskun.manager;

import de.luiskun.manager.dataholder.Category;
import de.luiskun.manager.dataholder.Entries;
import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.dataholder.Episode;
import de.luiskun.manager.tools.JsonHandler;
import de.luiskun.manager.ui.Screen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

public class Main {

    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        JFrame frame = new JFrame();
        Screen s = new Screen();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(s);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
