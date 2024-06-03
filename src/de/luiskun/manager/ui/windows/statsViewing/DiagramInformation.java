package de.luiskun.manager.ui.windows.statsViewing;

import de.luiskun.manager.dataholder.Entry;
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
        if (mode == 0) {
            // names setzen
            names = new String[types.size()];
            for (int i = 0; i < types.size(); i++) names[i] = types.get(i);

            // Werte setzen
            valueIndex = new HashMap<>();
            for (int i = 0; i < names.length; i++) {
                valueIndex.put(names[i], i);
            }
            values = new int[types.size()];
            for (Entry e : entries) {
                values[valueIndex.get(e.getType())]++;
            }
        } else if (mode == 1) {

        } else if (mode == 2) {
            names = new String[tags.size()];
            for (int i = 0; i < tags.size(); i++) names[i] = tags.get(i);

            // Werte setzen
            valueIndex = new HashMap<>();
            for (int i = 0; i < names.length; i++) {
                valueIndex.put(names[i], i);
            }
            values = new int[tags.size()];
            for (Entry e : entries) {
                for (String s : e.getTags()) values[valueIndex.get(s)]++;
            }
        }

        maxVal = getMaxVal();
        totalW = names.length * elementW;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);

        int x = 10;
        int y = maxH;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));

        float stepSize = (float) maxColH / (float) maxVal;

        for (int i = 0; i < names.length; i++) {
            g2.setColor(Color.BLACK);
            String s = names[i];
            g2.drawString(s, x+Geo.getXForCenteredText(s, elementW, g2), y-5);

            g2.setColor(Color.DARK_GRAY);
            int h = (int) (stepSize * values[i]);
            g2.fillRect(x+(elementW-colW)/2, y - textH - h, colW, h);

            x += elementW;
        }
    }

    private int getMaxVal() {
        int res = 0;
        for (int i : values) if (i > res) res = i;
        return res;
    }

}
