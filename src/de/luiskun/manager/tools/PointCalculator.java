package de.luiskun.manager.tools;

public class PointCalculator {

    public static int toLevel(int hours) {
        int level = 1;
        int gap = 0;
        //am -= level;
        while (hours > 0) {
            gap += level;
            level++;
            hours -= gap;
        }
        level--;
        return level;
    }

    public static int fromLevel(int level) {
        int hours = 1;
        int am = 0;
        for (int i = 1; i <= level; i++) {
            am += i;
            hours += am;
        }
        return hours;
    }

    public static double calcPercentage(int current, int next) {
        return ((100d / next) * current) / 100d;
    }

}
