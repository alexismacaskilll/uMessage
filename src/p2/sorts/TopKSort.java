package p2.sorts;

import java.util.Comparator;
import datastructures.worklists.MinFourHeap;

public class TopKSort {
    public static <E extends Comparable<E>> void sort(E[] array, int k) {
        sort(array, k, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, int k, Comparator<E> comparator) {
        MinFourHeap heap = new MinFourHeap(comparator);
        for (int i = 0; i < k; i ++) {
            if (i < array.length) {
                heap.add(array[i]);
            }
        }
  
        for (int i = k; i < array.length; i ++) {
            heap.add(array[i]);
            heap.next();
        }

        for (int i = 0; i < k; i++) {
            if (i < array.length) {
                array[i] = (E) heap.next();
            }
        }
        //
        for (int i = k; i < array.length; i ++) {
            array[i] =null;
        }
    }
}
