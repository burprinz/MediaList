package de.luiskun.manager.ui.windows;

import javax.swing.*;

public class EntryCreator extends Window {

    JTextField entryName;

    public EntryCreator() {
        super();
    }

    @Override
    public void initComponents() {
        int x = 10, y = 10;

        JLabel entryNameInfo = new JLabel("Entry Name");

        entryName = new JTextField();

    }
}
