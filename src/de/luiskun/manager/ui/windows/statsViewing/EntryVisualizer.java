package de.luiskun.manager.ui.windows.statsViewing;

import de.luiskun.manager.Quicksort;
import de.luiskun.manager.dataholder.Category;
import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.dataholder.Episode;
import de.luiskun.manager.tools.EntryCalculator;
import de.luiskun.manager.ui.Konstanten;
import de.luiskun.manager.ui.Screen;
import de.luiskun.manager.ui.windows.entryEditing.EntryCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntryVisualizer extends JPanel {

    public static final int TABLEMODE = 0;
    public static final int DIAGRAMMODE = 1;
    StatsViewer sv;
    int mode;
    int w, h;
    List<Entry> entries;
    List<Entry> sortedEntries;
    DiagramInformation diagram;

    public EntryVisualizer(List<Entry> entries, StatsViewer sv) {
        this.setLayout(null);
        this.sv = sv;
        this.entries = entries;
        switchV(DIAGRAMMODE);
    }

    /**
     * Eintragstabelle
     */

    public void switchV(int mode) {
        this.mode = mode;
        switch (mode) {
            case TABLEMODE:
                switchToTable();
                break;
            case DIAGRAMMODE:
                switchToDiagram();
                break;
        }
    }

    public void switchToTable() {
        this.removeAll();

        System.out.println(1);

        sortedEntries = new ArrayList<>();
        for (Entry e : entries) {
            sortedEntries.add(e);
        }

        updateTableButtons();
        updateTableEntries();
    }

    private void updateTableButtons() {
        w = 775;
        int eh = 30;
        h = (entries.size()+1)*eh;

        this.removeAll();
        this.setPreferredSize(new Dimension(w, h));
        int x = 0;
        int y = 0;
        JButton nameB = new JButton("Name");
        nameB.setBounds(x, y, 300, 30);
        nameB.addActionListener(list -> {
            List<String> strs = new ArrayList<>();
            for (Entry e : entries) {
                strs.add(e.getName());
            }
            changeTable(Quicksort.insStr(strs));
        });
        this.add(nameB);
        x += 300;
        JButton catB = new JButton("Type");
        catB.setBounds(x, y, 150, 30);
        catB.addActionListener(list -> {
            List<String> strs = new ArrayList<>();
            for (Entry e : entries) {
                strs.add(e.getType());
            }
            changeTable(Quicksort.insStr(strs));
        });
        this.add(catB);
        x += 150;

        JButton reB = new JButton("Re.");
        reB.setBounds(x, y, 55, 30);
        reB.addActionListener(list -> {
            List<Integer> strs = new ArrayList<>();
            for (Entry e : entries) {
                strs.add(e.getReAmount());
            }
            changeTable(Quicksort.insInt(strs));
        });
        this.add(reB);
        x += 55;

        JButton raB = new JButton("Rating");
        raB.setBounds(x, y, 70, 30);
        raB.addActionListener(list -> {
            List<Integer> strs = new ArrayList<>();
            for (Entry e : entries) {
                strs.add(e.getRating());
            }
            changeTable(Quicksort.insInt(strs));
        });
        this.add(raB);
        x += 70;

        JButton time = new JButton("Time");
        time.setBounds(x, y, 200, 30);
        time.addActionListener(list -> {
            List<Integer> strs = new ArrayList<>();
            for (Entry e : entries) {
                strs.add(EntryCalculator.getSeconds(e));
            }
            changeTable(Quicksort.insInt(strs));
        });
        this.add(time);
        x += 200;
    }

    private void changeTable(List<Integer> pos) {
        sortedEntries.clear();
        for (int i : pos) {
            sortedEntries.add(entries.get(i));
        }
        updateTableButtons();
        updateTableEntries();
    }

    private void updateTableEntries() {
        w = 775;
        int eh = 30;
        h = (entries.size()+1)*eh;
        for (int i = 1; i <= sortedEntries.size(); i++) {
            Entry e = sortedEntries.get(i-1);
            JPanel content = new JPanel();
            content.setLayout(null);
            content.setBounds(0, i*eh, w, eh);
            content.setBorder(Konstanten.BLACKBORDER);
            int xp = 0;
            int yp = 0;
            JLabel name = new JLabel(e.getName());
            name.setBounds(xp, yp, 300, eh);
            name.setBorder(Konstanten.BLACKBORDER);
            content.add(name);
            xp += 300;
            JLabel cat = new JLabel(e.getType(), SwingConstants.CENTER);
            cat.setBounds(xp, yp, 150, eh);
            cat.setBorder(Konstanten.BLACKBORDER);
            content.add(cat);
            xp += 150;
            JLabel re = new JLabel(e.getReAmount() + "", SwingConstants.CENTER);
            re.setBounds(xp, yp, 55, eh);
            re.setBorder(Konstanten.BLACKBORDER);
            content.add(re);
            xp += 55;
            JLabel ra = new JLabel(e.getRating() + "", SwingConstants.CENTER);
            ra.setBounds(xp, yp, 70, eh);
            ra.setBorder(Konstanten.BLACKBORDER);
            content.add(ra);
            xp += 70;
            JLabel len = new JLabel(EntryCalculator.createLenString(EntryCalculator.getSeconds(e)), SwingConstants.CENTER);
            len.setBounds(xp, yp, 200, eh);
            len.setBorder(Konstanten.BLACKBORDER);
            content.add(len);
            xp += 200;
            this.add(content);
        }
    }

    /**
     * Saeulendiagramm
     * 0 = Type
     * 1 = Rating
     * 2 = Tags
     */
    public void switchToDiagram() {
        this.removeAll();
        initDiagramButtons();
        diagram = new DiagramInformation(entries, sv.currentCats, sv.currentTags, 5);
        w = diagram.totalW;
        h = 660;
        this.setPreferredSize(new Dimension(w, h));
    }

    private void initDiagramButtons() {
        int x = 0;
        int y = 0;

        JButton typeB = new JButton("Type");
        typeB.setBounds(x, y, 100, 30);
        typeB.addActionListener(list -> {

        });
        this.add(typeB);
        x += 100;

        JButton rateB = new JButton("Rating");
        rateB.setBounds(x, y, 100, 30);
        rateB.addActionListener(list -> {

        });
        this.add(rateB);
        x += 100;

        JButton tagB = new JButton("Tag");
        tagB.setBounds(x, y, 100, 30);
        tagB.addActionListener(list -> {

        });
        this.add(tagB);
        x += 100;

        w = x;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (mode == DIAGRAMMODE) {
            if (diagram != null) {
                diagram.draw(g2);
            }
        }
    }

}
