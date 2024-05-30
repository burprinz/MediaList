package de.luiskun.manager;

import java.util.List;

public class Quicksort {

    static List<String> names;
    static int length;

    public static List<String> sort(List<String> array) {
        if (array == null || array.size() == 0) {
            return array;
        }
        names = array;
        length = array.size();
        quickSort(0, length - 1);
        return names;
    }

    private static void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = names.get(lowerIndex + (higherIndex - lowerIndex) / 2);

        while (i <= j) {
            while (names.get(i).compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (names.get(j).compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                exchangeNames(i, j);
                i++;
                j--;
            }
        }
        //call quickSort recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }

    private static void exchangeNames(int i, int j) {
        String temp = names.get(i);
        names.set(i, names.get(j));
        names.set(j, temp);
    }

}
