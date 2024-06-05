package de.luiskun.manager.ui.windows.menu;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.EntryCalculator;
import de.luiskun.manager.tools.Geo;
import de.luiskun.manager.tools.JsonHandler;
import de.luiskun.manager.tools.Vektor;
import de.luiskun.manager.ui.Screen;
import org.locationtech.jts.geom.Geometry;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MenuShow extends JPanel {

    public static final int W = Screen.WIDTH;
    public static final int H = Screen.HEIGHT/2;
    public static final Vektor mid = new Vektor(W/2, H/2);

    // Size stats
    int startR = 10;
    int maxR = 150;
    int sqareR = startR;
    int growSpeed = 3;

    // Text
    String overall, tags, cats, level;

    public MenuShow() {

        List<Entry> entries = JsonHandler.readEntries();
        List<String> tags = JsonHandler.readAllTags(entries);
        List<String> cats = JsonHandler.readAllCats(entries);
        int amsecs = 0;
        for (Entry e : entries) {
            amsecs += EntryCalculator.getSeconds(e);
        }

        level = "Level x";
        overall = "Total time: " + EntryCalculator.createLenString(amsecs);
        this.tags = EntryCalculator.getMostViewedTagInfo(tags, entries);
        this.cats = EntryCalculator.getMostViewedCatInfo(cats, entries);
    }

    public void update() {

        Point mp = this.getMousePosition();
        if (mp != null) {
            int x = mid.xAsInt()-2*sqareR;
            int y = mid.yAsInt()-sqareR;
            int w = 4*sqareR;
            int h = 2*sqareR;
            if (mp.x > x && mp.x < x+w && mp.y > y && mp.y < y+h) {
                sqareR+=growSpeed;
                if (sqareR > maxR) sqareR = maxR;
            } else {
                sqareR -= growSpeed;
                if (sqareR < startR) sqareR = startR;
            }
        } else {
            sqareR -= growSpeed;
            if (sqareR < startR) sqareR = startR;
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int x = (int)(mid.xAsInt()-2.5*sqareR);
        int y = mid.yAsInt()-sqareR;
        int w = 5*sqareR;
        int h = 2*sqareR;

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(x, y, w, h, 25, 25);

        g2.setColor(Color.WHITE);
        if (sqareR == startR) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
            String t = "Overall Stats";
            int sw = Geo.getXForCenteredText(t, W, g2);
            g2.drawString(t, sw, y - sqareR-10);
        } else if (sqareR == maxR) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
            int tx = x + Geo.getXForCenteredText(level, w, g2);
            int ty = y + 50;
            g2.drawString(level, tx, ty);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));

            tx = x + Geo.getXForCenteredText(overall, w, g2);
            ty += 100;
            g2.drawString(overall, tx, ty);

            tx = x + Geo.getXForCenteredText(tags, w, g2);
            ty += 50;
            g2.drawString(tags, tx, ty);

            tx = x + Geo.getXForCenteredText(cats, w, g2);
            ty += 50;
            g2.drawString(cats, tx, ty);
        }

        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, w, h, 25, 25);

    }

}
