package de.luiskun.manager.ui.windows.entryEditing;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.JsonHandler;
import de.luiskun.manager.ui.Screen;
import de.luiskun.manager.ui.windows.Window;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EntryCreator extends Window {

    EntryInformation ei;

    JButton saveButton, cancelButton;

    public EntryCreator(Screen s) {
        super(s);
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

            String name = e.getName();

            if (!Files.exists(Paths.get("res/data/entries/" + name + ".json"))) {
                JsonHandler.saveEntry(e);
                s.goBackToMenu();
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "This name already exists. Overwrite?", "Alert", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    JsonHandler.saveEntry(e);
                    s.goBackToMenu();
                }
            }
        });
        this.add(saveButton);
        x += 110;

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(x, y, 100, 25);
        cancelButton.addActionListener(list -> {
            int reply = JOptionPane.showConfirmDialog(null, "Do you really want to exit? The entry will be lost",
                    "Alert", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                s.goBackToMenu();
            }
        });
        this.add(cancelButton);
    }

    @Override
    public void update() {

    }
}
