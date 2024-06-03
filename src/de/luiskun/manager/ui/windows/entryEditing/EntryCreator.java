package de.luiskun.manager.ui.windows.entryEditing;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.JsonHandler;
import de.luiskun.manager.ui.Screen;
import de.luiskun.manager.ui.windows.Window;

import javax.swing.*;
import java.awt.*;

public class EntryCreator extends Window {

    EntryInformation ei;

    JButton saveButton, cancelButton;

    public EntryCreator() {
        super();
    }

    @Override
    public void initComponents() {
        ei = new EntryInformation(this);
        this.add(ei);

        int x = Screen.WIDTH - 215;
        int y = Screen.HEIGHT - 30;

        saveButton = new JButton("Save");
        saveButton.setBounds(x, y, 100, 25);
        saveButton.addActionListener(list -> {
            Entry e = ei.getEntry();
            JsonHandler.saveEntry(e);
        });
        this.add(saveButton);
        x += 110;

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(x, y, 100, 25);
        cancelButton.addActionListener(list -> {

        });
        this.add(cancelButton);
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g) {

    }

}
