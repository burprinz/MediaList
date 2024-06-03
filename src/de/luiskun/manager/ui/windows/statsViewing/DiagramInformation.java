package de.luiskun.manager.ui.windows.statsViewing;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.EntryCalculator;
import de.luiskun.manager.tools.Geo;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagramInformation {

    String[] names;
    int[] values;
    String[] valueShow;

    Map<String, Integer> valueIndex;

    int elementW = 160;
    public int totalW;
    int textH = 30;
    int maxH = 655;
    int colW = 50;
    int maxColH = maxH - 3*textH;
    int maxVal;

    public DiagramInformation(List<Entry> entries, List<String> types, List<String> tags, int mode) {
        // 0 = Type am
        // 1 = Rating am
        // 2 = Tag am
        // 3 = Type len
        // 4 = Rating len
        // 5 = Tag len

        int headMode = mode % 3;
        if (headMode == 0) {
            // Type
            names = new String[types.size()];
            values = new int[types.size()];
            valueShow = new String[types.size()];
            for (int i = 0; i < types.size(); i++) names[i] = types.get(i);
            initValueHashmap();

            if (mode == 0) {
                for (Entry e : entries)
                    values[valueIndex.get(e.getType())]++;
            } else {
                for (Entry e : entries)
                    values[valueIndex.get(e.getType())]+=EntryCalculator.getSeconds(e);
            }

        } else if (headMode == 1) {
            // Rating
            names = new String[10];
            values = new int[10];
            valueShow = new String[10];
            for (int i = 1; i <= 10; i++) names[i-1] = "" + i;

            if (mode == 1) {
                for (Entry e : entries)
                    values[e.getRating()-1]++;
            } else {
                for (Entry e : entries)
                    values[e.getRating()-1]+=EntryCalculator.getSeconds(e);
            }
        } else {
            // Tag
            names = new String[tags.size()];
            values = new int[tags.size()];
            valueShow = new String[tags.size()];
            for (int i = 0; i < tags.size(); i++) names[i] = tags.get(i);
            initValueHashmap();

            if (mode == 2) {
                for (Entry e : entries) {
                    for (String s : e.getTags()) values[valueIndex.get(s)]++;
                }
            } else {
                for (Entry e : entries) {
                    for (String s : e.getTags())
                        values[valueIndex.get(s)]+=EntryCalculator.getSeconds(e);
                }
            }
        }

        if (mode / 3 == 0) {
            initValueShowForVal();
        } else {
            initValueShowForLen();
        }

        if (mode == 0) {
            // names setzen
        } else if (mode == 1) {
        } else if (mode == 2) {





            initValueShowForVal();
        }

        maxVal = getMaxVal();
        totalW = names.length * elementW;
    }

    private void initValueHashmap() {
        valueIndex = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            valueIndex.put(names[i], i);
        }
    }

    private void initValueShowForVal() {
        for (int i = 0; i < values.length; i++) {
            valueShow[i] = "" + values[i];
        }
    }

    private void initValueShowForLen() {
        for (int i = 0; i < values.length; i++) {
            valueShow[i] = EntryCalculator.createLenString(values[i]);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);

        int x = 10;
        int y = maxH;

        float stepSize = (float) maxColH / (float) maxVal;

        for (int i = 0; i < names.length; i++) {
            int h = (int) (stepSize * values[i]);
            int yC = y - textH - h;
            g2.setColor(Color.BLACK);
            String s = names[i];
            String ss = valueShow[i];

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
            g2.drawString(s, x+Geo.getXForCenteredText(s, elementW, g2), y-5);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
            g2.drawString(ss, x+Geo.getXForCenteredText(ss, elementW, g2), yC-5);

            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(x+(elementW-colW)/2, yC, colW, h);

            x += elementW;
        }
    }

    private int getMaxVal() {
        int res = 0;
        for (int i : values) if (i > res) res = i;
        return res;
    }

}
