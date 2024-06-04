package de.luiskun.manager.ui;

import de.luiskun.manager.tools.FileHadler;
import de.luiskun.manager.ui.windows.entryEditing.EntryCreator;
import de.luiskun.manager.ui.windows.statsViewing.StatsViewer;
import de.luiskun.manager.ui.windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JPanel implements Runnable {

    public static final int WIDTH = 900, HEIGHT = 700;
    Thread t;
    int fps = 60;

    // Menu
    JPanel menu;
    boolean menuOpened;

    JButton createButton, editButton, statsButton;
    volatile Window currentWindow;

    // Texture
    JLabel menuBackground;

    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

        menuOpened = true;

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

        menuBackground = new JLabel(new ImageIcon("res/text/background.png"));
        menuBackground.setBounds(0, 0, WIDTH, HEIGHT);
        menu.add(menuBackground);
    }

    private void switchWindow(Window w) {
        menu.setVisible(false);
        menuOpened = false;
        currentWindow = w;
        this.add(currentWindow);
    }

    public void goBackToMenu() {
        this.remove(currentWindow);
        menuOpened = true;
        currentWindow = null;
        menu.setVisible(true);
    }

    public void startThread() {
        t = new Thread(this);
        t.start();
    }

    private void update() {



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
                update();
                if (currentWindow != null) {
                    currentWindow.update();
                    currentWindow.repaint();
                }
                delta--;
            }
        }
    }
}
