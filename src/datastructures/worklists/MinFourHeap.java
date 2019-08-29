package datastructures.worklists;

import cse332.interfaces.worklists.PriorityWorkList;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/PriorityWorkList.java
 * for method specifications.
 */
public class MinFourHeap<E> extends PriorityWorkList<E> {
    /* Do not change the name of this field; the tests rely on it to work correctly. */
    private E[] data;
    int index; 
    Comparator<E> comparator;
    
    public MinFourHeap(Comparator<E> comparator) {
        data = (E[])new Object[10];
        index = -1;
        this.comparator = comparator;
    }

    @Override
    public boolean hasWork() {
        return index + 1 > 0; 
    }

    @Override
    public void add(E work) {
       if (index == data.length - 1) {
    	   E[] temp = (E[]) new Comparable[data.length * 2];
    	   for (int i = 0; i < data.length; i++) {
    		   temp[i] = data[i];
    	   }
    	   data = temp; 
       }
       index++;
       int i = percolateUp(index, work);
       data[i] = work;  
    }
    
    private int percolateUp(int hole, E val) {
    	while(hole > 0 && comparator.compare(val, data[(hole - 1) / 4]) < 0) {
    		data[hole] = data[(hole - 1) / 4];
    		hole = (hole - 1) / 4;
    	}
    	return hole;
    }

    @Override
    public E peek() {
    	if (!hasWork()) {
    		throw new NoSuchElementException(); 	
    	}
    	return data[0];
    }

    @Override
    public E next() {
        E work = peek();
        int hole = percolateDown(0, data[index]);
        data[hole] = data[index];
        index--;
        return work;
    }
    
    private int percolateDown(int hole, E val) {
    	while((4 * hole + 1) <= index) { 
    		int min = hole;
    		E minVal = val;
    		for (int i = 1; i <= 4; i++) {
    			if ( (4 * hole + i) <= index && comparator.compare(data[4 * hole + i], minVal) < 0) {
    				min = 4 * hole + i;
    				minVal = data[4 * hole + i];
    			}
    		}
    		if (min != hole) {
    			data[hole] = data[min];
    			hole = min;
    		} else {
    			break;
    		}
    	}
    	return hole; 
    }

    @Override
    public int size() {
        return index + 1; 
    }

    @Override
    public void clear() {
        index = -1;
    }
}
