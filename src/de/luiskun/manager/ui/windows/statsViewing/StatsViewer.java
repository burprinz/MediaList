package de.luiskun.manager.ui.windows.statsViewing;

import de.luiskun.manager.ui.ButtonFunction;
import de.luiskun.manager.ui.Konstanten;
import de.luiskun.manager.ui.Screen;
import de.luiskun.manager.ui.windows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StatsViewer extends Window implements ActionListener {


    /**
     * Swing components
     */
    JPanel controlPanel, drawPanel;
    JScrollPane tableScroll;
    JButton b;

    boolean tagsWindowOpened;

    Map<String, ButtonFunction> commands;

    public StatsViewer() {
        super();
    }

    @Override
    public void initComponents() {
        initVisualizer();
        initControl();
    }

    private void initVisualizer() {
        int x = 10;
        int y = 10;
        int w = Screen.HEIGHT - 20;
        int h = Screen.HEIGHT - 20;
        drawPanel = new EntryVisualizer(currentEntries);
        tableScroll = new JScrollPane(drawPanel);
        tableScroll.setBounds(x, y, w, h);
        this.add(tableScroll);
        switchToTable();
    }

    private void switchToTable() {

    }

    private void initControl() {
        int x = Screen.HEIGHT + 10;
        int y = 10;
        int w = Screen.WIDTH-Screen.HEIGHT - 20;
        int h = Screen.HEIGHT - 20;

        controlPanel = new JPanel();
        controlPanel.setBounds(x, y, w, h);
        controlPanel.setBorder(Konstanten.BLACKBORDER);
        controlPanel.setLayout(null);
        this.add(controlPanel);
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
