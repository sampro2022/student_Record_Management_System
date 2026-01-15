package student_Record_Management_System;

import java.util.ArrayList;

public class Sorting {

    /* =====================================================
       MERGE SORT BY NAME (Rule 5)
       ===================================================== */

    public void mergeSortByName(ArrayList<Node> list) {
        if (list.size() < 2) return;

        int mid = list.size() / 2;

        ArrayList<Node> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Node> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSortByName(left);
        mergeSortByName(right);

        mergeByName(list, left, right);
    }

    private void mergeByName(ArrayList<Node> result,
                             ArrayList<Node> left,
                             ArrayList<Node> right) {

        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).name.compareToIgnoreCase(right.get(j).name) <= 0) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }

    /* =====================================================
       QUICK SORT BY CGPA (Rule 5)
       ===================================================== */

    public void quickSortByCGPA(ArrayList<Node> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private void quickSort(ArrayList<Node> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(ArrayList<Node> list, int low, int high) {
        double pivot = list.get(high).cgpa;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).cgpa <= pivot) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(ArrayList<Node> list, int i, int j) {
        Node temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
