package de.luiskun.manager;

import java.util.ArrayList;
import java.util.List;

public class Quicksort {

    static List<String> names;
    static int length;

    public static List<String> sortStr(List<String> array) {
        if (array == null || array.size() == 0) {
            return array;
        }
        names = array;

        length = array.size();
        quickSortStr(0, length - 1);
        return names;
    }

    private static void quickSortStr(int lowerIndex, int higherIndex) {
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
            quickSortStr(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSortStr(i, higherIndex);
        }
    }

    private static void exchangeNames(int i, int j) {
        String temp = names.get(i);
        names.set(i, names.get(j));
        names.set(j, temp);
    }

    public static List<Integer> insStr(List<String> arr) {
        int n = arr.size();

        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pos.add(i);
        }

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr.get(j).compareToIgnoreCase(arr.get(min_idx)) < 0)
                    min_idx = j;

            String temp = arr.get(min_idx);
            arr.set(min_idx, arr.get(i));;
            arr.set(i, temp);

            int tmp = pos.get(min_idx);
            pos.set(min_idx, pos.get(i));;
            pos.set(i, tmp);

        }

        return pos;
    }

    public static List<Integer> insInt(List<Integer> arr) {
        int n = arr.size();

        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pos.add(i);
        }

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr.get(j) > arr.get(min_idx))
                    min_idx = j;

            int temp = arr.get(min_idx);
            arr.set(min_idx, arr.get(i));;
            arr.set(i, temp);

            int tmp = pos.get(min_idx);
            pos.set(min_idx, pos.get(i));;
            pos.set(i, tmp);

        }

        return pos;
    }

}
