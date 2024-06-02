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

    Window currentWindow;

    public Screen() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(null);

        currentWindow = new StatsViewer();
        this.add(currentWindow);

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
                if (currentWindow != null) {
                    currentWindow.update();
                    if (!(currentWindow instanceof EntryCreator)) {
                        currentWindow.repaint();
                    }
                }
                delta--;
            }
        }
    }
}
