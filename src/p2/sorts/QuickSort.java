package p2.sorts;

import java.util.Comparator;

import cse332.exceptions.NotYetImplementedException;

public class QuickSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> comparator) {
        quickSort(array, comparator, 0, array.length - 1);
    }
    
    private static <E> void quickSort(E[] array, Comparator<E> comparator, int front, int end) {
        if (front < end) {
            //pick a pivot
            swap(end - 1, medium(front, end, array, comparator), array);
            int pivot = end - 1;

            for (int i = front; i <= pivot; i++) {
                if (comparator.compare(array[i], array[pivot]) > 0) {
                    swap(i, pivot, array);
                    swap(pivot - 1, i, array);
                    pivot--;
                    i--;
                }
            }
            //recursion
            quickSort(array, comparator, front, pivot - 1);
            quickSort(array, comparator, pivot + 1, end);
        }
    }
    
    private static <E> int medium(int a, int b, E[] array, Comparator comparator) {
       // int mid = (a + b) / 2;
        int mid = (a + b) >>> 1;
        if (comparator.compare(array[mid], array[a]) < 0) {
           swap(a, mid, array);
        } 
        if (comparator.compare(array[b], array[a]) < 0) {
            swap(b, a, array);
        }
        if (comparator.compare(array[b], array[mid]) < 0) {
            swap(b, mid, array);
        }
        return mid;
    }
    
    private static <E> void swap(int a, int b, E[] array) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
