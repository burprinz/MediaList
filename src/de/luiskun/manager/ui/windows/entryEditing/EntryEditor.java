package de.luiskun.manager.ui.windows.entryEditing;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.EntryCalculator;
import de.luiskun.manager.tools.JsonHandler;
import de.luiskun.manager.ui.Screen;
import de.luiskun.manager.ui.windows.Window;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EntryEditor extends Window {

    EntryInformation ei;

    JButton saveButton, cancelButton, loadButton, removeButton;
    JComboBox names;
    boolean entrySelected;

    public EntryEditor(Screen s) {
        super(s);
    }

    @Override
    public void initComponents() {
        ei = new EntryInformation(this);
        this.add(ei);

        entrySelected = false;

        int x = Screen.WIDTH - 325;
        int y = Screen.HEIGHT - 30;

        saveButton = new JButton("Update");
        saveButton.setBounds(x, y, 100, 25);
        saveButton.addActionListener(list -> {
            Entry e = ei.getEntry();

            String name = e.getName();

            if (!Files.exists(Paths.get("res/data/entries/" + name + ".json"))) {
                JsonHandler.saveEntry(e);
                s.goBackToMenu();
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Do you really want to update the Entry", "Alert", JOptionPane.YES_NO_OPTION);
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
            int reply = JOptionPane.showConfirmDialog(null, "Do you really want to exit? All changes will be lost",
                    "Alert", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                s.goBackToMenu();
            }
        });
        this.add(cancelButton);

        x += 110;
        removeButton = new JButton("Delete");
        removeButton.setBounds(x, y, 100, 25);
        removeButton.addActionListener(list -> {
            Entry e = ei.getEntry();
            if (Files.exists(Paths.get("res/data/entries/" + e.getName() + ".json"))) {
                int reply = JOptionPane.showConfirmDialog(null, "Do you really want to remove " + e.getName() +"?",
                        "Alert", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        Files.deleteIfExists(Paths.get("res/data/entries/" + e.getName() + ".json"));
                        s.goBackToMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                s.goBackToMenu();
            }
        });
        this.add(removeButton);


        x = 10;

        names = new JComboBox(EntryCalculator.getNames(currentEntries).toArray());
        names.setBounds(x, y, 150, 25);
        this.add(names);

        x += 160;
        loadButton = new JButton("Load");
        loadButton.setBounds(x, y, 100, 25);
        loadButton.addActionListener(list -> {
            if (!entrySelected) {
                loadEntry(names.getSelectedItem().toString());
                entrySelected = true;
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Do you really want to load a new Entry without saving?",
                        "Alert", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    loadEntry(names.getSelectedItem().toString());
                }
            }
        });
        this.add(loadButton);

    }

    private void loadEntry(String name) {
        for (Entry e : currentEntries) {
            if (e.getName().equals(name)) {
                ei.setEntry(e);
                break;
            }
        }
    }

    @Override
    public void update() {

    }


}
