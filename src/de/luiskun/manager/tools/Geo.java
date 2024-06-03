package de.luiskun.manager.tools;

import java.awt.*;

public class Geo {

    public static int getXForCenteredText(String text, int w, Graphics2D g2) {
        return w / 2 - g2.getFontMetrics().stringWidth(text) / 2;
    }

}
