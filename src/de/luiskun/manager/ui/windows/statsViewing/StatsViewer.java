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
    JPanel controlPanel;
    JScrollPane tableScroll;
    JButton goBackB;
    EntryVisualizer drawPanel;

    ButtonGroup modeGroup;
    JRadioButton tableSelect, diagramSelect;

    Map<String, ButtonFunction> commands;

    public StatsViewer(Screen s) {
        super(s);
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
        drawPanel = new EntryVisualizer(currentEntries, this);
        tableScroll = new JScrollPane(drawPanel);
        tableScroll.setBounds(x, y, w, h);
        this.add(tableScroll);
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

        x = 10;
        y = 10;
        w -= 20;
        h = 30;

        tableSelect = new JRadioButton("Table");
        tableSelect.setBounds(x, y, w, h);
        tableSelect.setSelected(true);
        tableSelect.setActionCommand("modeSwitch");
        tableSelect.addActionListener(this);
        controlPanel.add(tableSelect);
        y += (h+5);

        diagramSelect = new JRadioButton("Diagram");
        diagramSelect.setBounds(x, y, w, h);
        diagramSelect.setActionCommand("modeSwitch");
        diagramSelect.addActionListener(this);
        controlPanel.add(diagramSelect);
        y += (h+5);

        modeGroup = new ButtonGroup();
        modeGroup.add(tableSelect);
        modeGroup.add(diagramSelect);

        goBackB = new JButton("Back");
        goBackB.setBounds(x, y, w, h);
        goBackB.addActionListener(list -> {
            s.goBackToMenu();
        });
        controlPanel.add(goBackB);
    }

    public void updateScrollPane() {
        tableScroll.revalidate();
        tableScroll.repaint();
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);

        drawPanel.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        if (com.startsWith("modeSwitch")) {
            if (tableSelect.isSelected()) {
                drawPanel.switchV(EntryVisualizer.TABLEMODE);
            } else if (diagramSelect.isSelected()) {
                drawPanel.switchV(EntryVisualizer.DIAGRAMMODE);
            }
        }
    }
}
