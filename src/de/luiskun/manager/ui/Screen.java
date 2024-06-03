package de.luiskun.manager.ui;

import de.luiskun.manager.ui.windows.entryEditing.EntryCreator;
import de.luiskun.manager.ui.windows.statsViewing.StatsViewer;
import de.luiskun.manager.ui.windows.Window;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel implements Runnable {

    public static final int WIDTH = 900, HEIGHT = 700;
    Thread t;
    int fps = 60;

    JPanel menu;

    JButton createButton, editButton, statsButton;

    Window currentWindow;

    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

        menu = new JPanel();
        menu.setBounds(0, 0, WIDTH, HEIGHT);
        menu.setLayout(null);
        this.add(menu);

        int x = 10;
        int y = 10;

        createButton = new JButton("Create Element");
        createButton.setBounds(x, y, 150, 30);
        createButton.addActionListener(list -> {
            switchWindow(new EntryCreator(this));
        });
        menu.add(createButton);
        y += 40;

        editButton = new JButton("Edit Element");
        editButton.setBounds(x, y, 150, 30);
        editButton.addActionListener(list -> {

        });
        menu.add(editButton);
        y += 40;

        statsButton = new JButton("Show Stats");
        statsButton.setBounds(x, y, 150, 30);
        statsButton.addActionListener(list -> {
            switchWindow(new StatsViewer(this));
        });
        menu.add(statsButton);

    }

    private void switchWindow(Window w) {
        menu.setVisible(false);
        currentWindow = w;
        this.add(currentWindow);
    }

    public void goBackToMenu() {
        this.remove(currentWindow);
        currentWindow = null;
        menu.setVisible(true);
    }

    public void startThread() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps; // 0.0166s
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        while (t != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                repaint();
                if (currentWindow != null) {
                    currentWindow.update();
                    currentWindow.repaint();
                }
                delta--;
            }
        }
    }
}
