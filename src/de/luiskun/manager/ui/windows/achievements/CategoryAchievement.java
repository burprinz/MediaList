package de.luiskun.manager.ui.windows.achievements;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.EntryCalculator;
import de.luiskun.manager.tools.Geo;
import de.luiskun.manager.tools.PointCalculator;

import java.awt.*;

public class CategoryAchievement {

    String name;
    int hours;
    int level;
    int untilNext;
    double percentage;

    public CategoryAchievement(String name, int seconds) {

        this.name = name;
        this.hours = seconds/3600;
        this.level = PointCalculator.toLevel(this.hours);
        this.untilNext = PointCalculator.fromLevel(this.level+1);
        this.percentage = PointCalculator.calcPercentage(this.hours, untilNext);

    }

    public void draw(Graphics2D g2, int x, int y, int w) {
        final int h = 90;

        int xpos = x + 5;
        int ypos = y + 25;

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x, y, w, h, 10, 10);

        String text = name + ": Level " + level;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.drawString(text, xpos, ypos);
        ypos += 15;

        g2.setColor(Color.GRAY);
        g2.fillRoundRect(xpos, ypos, w-10, 20, 10, 10);

        g2.setColor(new Color(0,120,255));
        g2.fillRoundRect(xpos, ypos, (int)(percentage*(w-10)), 20, 10, 10);

        ypos += 40;
        text = hours + "h / " + untilNext + "h";
        xpos = Geo.getXForCenteredText(text, w, g2);
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + xpos, ypos);
    }

}
