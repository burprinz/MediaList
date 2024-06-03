package de.luiskun.manager.tools;

import de.luiskun.manager.dataholder.Category;
import de.luiskun.manager.dataholder.Entry;
import de.luiskun.manager.dataholder.Episode;

public class EntryCalculator {

    public static int getSeconds(Entry e) {
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

    public static String createLenString(int len) {
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
