package de.luiskun.manager.ui.windows.statsViewing;

import de.luiskun.manager.Quicksort;
import de.luiskun.manager.dataholder.Category;
import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.dataholder.Episode;
import de.luiskun.manager.ui.Konstanten;
import de.luiskun.manager.ui.Screen;
import de.luiskun.manager.ui.windows.entryEditing.EntryCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntryVisualizer extends JPanel {

    public static final int TABLEMODE = 0;
    int mode;
    int w, h;
    List<Entry> entries;
    List<Entry> sortedEntries;

    public EntryVisualizer(List<Entry> entries) {
        this.setLayout(null);
        this.entries = entries;
        switchToTable();
    }

    private void changeTable(List<Integer> pos) {
        sortedEntries.clear();
        for (int i : pos) {
            sortedEntries.add(entries.get(i));
        }
        updateTableButtons();
        updateTableEntries();
    }

    public void switchToTable() {
        this.removeAll();

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
                strs.add(getSeconds(e));
            }
            changeTable(Quicksort.insInt(strs));
        });
        this.add(time);
        x += 200;
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
            JLabel len = new JLabel(createLenString(getSeconds(e)), SwingConstants.CENTER);
            len.setBounds(xp, yp, 200, eh);
            len.setBorder(Konstanten.BLACKBORDER);
            content.add(len);
            xp += 200;
            this.add(content);
        }
    }

    public int getSeconds(Entry e) {
        int len = 0;
        if (e.isWithCategories()) {
            if (e.isSameLength()) {
                // Kategorie selbe laenge
                for (Category c : e.getCategories()) {
                    len += e.getLength() * c.getAmEpisodes();
                }
            } else {
                // Kategorie laenge versch
                for (Category c : e.getCategories()) {
                    len += c.getLength() * c.getAmEpisodes();
                }
            }
        } else {
            if (e.isSameLength()) {
                len = e.getEpisodes().size() * e.getLength();
            } else {
                for (Episode ee : e.getEpisodes()) {
                    len += ee.getLength();
                }
            }
        }
        return len;
    }

    private String createLenString(int len) {
        String s = "";

        int minSec = 60;
        int hourSec = minSec*60;
        int daySec = hourSec*24;
        int yearSec = daySec*365;
        int decSec = yearSec*10;

        int dec = len / decSec;
        if (dec > 0) {
            s += dec + "dec / ";
            len = len%decSec;
        }
        int a = len / yearSec;
        if (a > 0) {
            s += a + "a / ";
            len = len%yearSec;
        }
        int d = len / daySec;
        if (d > 0) {
            s += d + "d / ";
            len = len%daySec;
        }
        int h = len / hourSec;
        if (h > 0) {
            s += h + "h / ";
            len = len%hourSec;
        }
        int min = len / minSec;
        if (min > 0) {
            s += min + "min / ";
            len = len%minSec;
        }
        s += len + "s";


        return s;
    }

}
