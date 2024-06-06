package de.luiskun.manager.ui.windows.achievements;

import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.tools.EntryCalculator;
import de.luiskun.manager.tools.Geo;
import de.luiskun.manager.tools.PointCalculator;
import de.luiskun.manager.ui.Screen;
import de.luiskun.manager.ui.windows.Updatable;
import de.luiskun.manager.ui.windows.Window;

import javax.swing.*;
import java.awt.*;

public class Achievements extends Window {

    private static final int amountPerPage = 12;

    CategoryAchievement[] achievemts;
    JButton left, right, backB;

    int page = 1;
    int amPages;

    int hours;
    int level;
    int next;
    double percentage;

    public Achievements(Screen s) {
        super(s);
        int amsecs = 0;
        for (Entry e : currentEntries) {
            amsecs += EntryCalculator.getSeconds(e);
        }
        hours = amsecs/3600;
        level = PointCalculator.toLevel(hours);
        next = PointCalculator.fromLevel(level+1);
        percentage = PointCalculator.calcPercentage(hours, next);
    }

    @Override
    public void initComponents() {
        achievemts = new CategoryAchievement[currentCats.size()];
        for (int i = 0; i < achievemts.length; i++)
            achievemts[i] = new CategoryAchievement(currentCats.get(i), EntryCalculator.getCatSeconds(currentCats.get(i), currentEntries));

        amPages = achievemts.length / amountPerPage + 1;

        left = new JButton("<");
        left.setBounds(Screen.WIDTH/2 - 100, Screen.HEIGHT - 25, 50, 20);
        left.addActionListener(list -> {
            if (page > 1) {
                page--;
            }
        });
        this.add(left);

        right = new JButton(">");
        right.setBounds(Screen.WIDTH/2 + 50, Screen.HEIGHT - 25, 50, 20);
        right.addActionListener(list -> {
            if (page < amPages) {
                page++;
            }
        });
        this.add(right);

        backB = new JButton("Back");
        backB.setBounds(10, Screen.HEIGHT-25, 100, 20);
        backB.addActionListener(list -> {
            s.goBackToMenu();
        });
        this.add(backB);
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int x = 0;
        int y = 0;

        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));
        String text = "Level " + level;
        int xpos = Geo.getXForCenteredText(text, Screen.WIDTH, g2);
        int ypos = 25;
        g2.drawString(text, xpos, ypos);

        ypos += 5;
        g2.setColor(Color.GRAY);
        g2.fillRoundRect(50, ypos, Screen.WIDTH-100, 20, 25, 25);
        g2.setColor(new Color(0,120,255));
        g2.fillRoundRect(50, ypos, (int) (percentage * (Screen.WIDTH-100)), 20, 25, 25);
        g2.setColor(Color.BLACK);
        ypos += 40;
        text = hours + "h / " + next + "h";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.drawString(text, Geo.getXForCenteredText(text, Screen.WIDTH, g2), ypos);

        text = page + " / " + amPages;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18));
        g2.drawString(text, Geo.getXForCenteredText(text, Screen.WIDTH, g2), Screen.HEIGHT - 10);

        if (achievemts != null) {
            for (int i = (page-1)*amountPerPage; i < (achievemts.length>page*amountPerPage?page+amountPerPage-1:achievemts.length); i++) {
                achievemts[i].draw(g2, 10 + x*430, y*100 + 80, 420);
                y++;
                if (y >= 6) {
                    y = 0;
                    x++;
                }
            }
        }

    }
}
