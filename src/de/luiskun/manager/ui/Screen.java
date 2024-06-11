package de.luiskun.manager.ui;

import de.luiskun.manager.tools.FileHadler;
import de.luiskun.manager.ui.windows.achievements.Achievements;
import de.luiskun.manager.ui.windows.entryEditing.EntryCreator;
import de.luiskun.manager.ui.windows.entryEditing.EntryEditor;
import de.luiskun.manager.ui.windows.menu.MenuShow;
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

    JButton createButton, editButton, statsButton, achievementsButton;
    volatile Window currentWindow;

    // Texture
    JLabel menuBackground;
    MenuShow menuShow;

    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

        menuOpened = true;

        menu = new JPanel();
        menu.setBounds(0, 0, WIDTH, HEIGHT);
        menu.setLayout(null);

        int x = 100;
        int y = 25;

        createButton = new JButton(new ImageIcon("res/text/createButton.png"));
        createButton.setOpaque(false);
        createButton.setContentAreaFilled(false);
        createButton.setBorderPainted(false);
        createButton.setBounds(x, y, 170, 100);
        createButton.addActionListener(list -> {
            switchWindow(new EntryCreator(this));
        });
        menu.add(createButton);
        x += 180;

        editButton = new JButton(new ImageIcon("res/text/editButton.png"));
        editButton.setOpaque(false);
        editButton.setContentAreaFilled(false);
        editButton.setBorderPainted(false);
        editButton.setBounds(x, y, 170, 100);
        editButton.addActionListener(list -> {
            switchWindow(new EntryEditor(this));
        });
        menu.add(editButton);
        x += 180;

        statsButton = new JButton(new ImageIcon("res/text/statsButton.png"));
        statsButton.setOpaque(false);
        statsButton.setContentAreaFilled(false);
        statsButton.setBorderPainted(false);
        statsButton.setBounds(x, y, 170, 100);
        statsButton.addActionListener(list -> {
            switchWindow(new StatsViewer(this));
        });
        menu.add(statsButton);
        x += 180;

        achievementsButton = new JButton(new ImageIcon("res/text/achievementyButton.png"));
        achievementsButton.setOpaque(false);
        achievementsButton.setContentAreaFilled(false);
        achievementsButton.setBorderPainted(false);
        achievementsButton.setBounds(x, y, 170, 100);
        achievementsButton.addActionListener(list -> {
            switchWindow(new Achievements(this));
        });
        menu.add(achievementsButton);

        menuShow = new MenuShow();
        menuShow.setLayout(null);
        menuShow.setBounds(0, HEIGHT/2, WIDTH, HEIGHT/2);
        menu.add(menuShow);

        menuBackground = new JLabel(new ImageIcon("res/text/background.png"));
        menuBackground.setBounds(0, 0, WIDTH, HEIGHT);
        menu.add(menuBackground);

        this.add(menu);
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
        menuShow.resetInfos();
    }

    public void startThread() {
        t = new Thread(this);
        t.start();
    }

    private void update() {
        menuShow.removeAll();
        menuShow.update();
        menuShow.repaint();

       this.repaint();
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
                if (currentWindow != null) {
                    currentWindow.update();
                    currentWindow.repaint();
                } else {
                    update();
                }
                delta--;
            }
        }
    }
}
