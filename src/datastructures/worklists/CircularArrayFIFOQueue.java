package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E extends Comparable<E>> extends FixedSizeFIFOWorkList<E> {
	private E[] queue;
	private int front;
	private int size;
	
    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        queue = (E[])new Comparable[super.capacity()];
        front = 0; 
        size = 0;
    }

    @Override
    public void add(E work) {
        if (isFull()) {
        	throw new IllegalStateException();
        }
        queue[(front + size) % super.capacity()] = work;
        size++;
    }

    @Override
    public E peek() {
    	if (!hasWork()) {
    		throw new NoSuchElementException(); 	
    	}
    	return queue[front];
    }
    
    @Override
    public E peek(int i) {
    	if (!hasWork()) {
    		throw new NoSuchElementException(); 	
    	}
    	if (i < 0 || i >= size()) {
    		throw new IndexOutOfBoundsException();
    	}
    	return queue[(i + front) % super.capacity()];
    }
    
    @Override
    public E next() {
        E work = peek(); 
        if (front == super.capacity() - 1) {
        	front = 0;
        } else {
        	front++;
        }
        size--;
        return work;
    }
    
    @Override
    public void update(int i, E value) {
    	peek(i);
    	queue[(i + front) % super.capacity()] = value;
    }
    
    @Override
    public int size() {
    	return size;
    }
    
    @Override
    public void clear() {
        front = 0; 
        size = 0;
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        int i = 0;
        while (i < Math.min(this.size(), other.size()) && this.peek(i).equals(other.peek(i))) {
            i++;
        }
        if (i < Math.min(this.size(), other.size())) {
            return this.peek(i).compareTo(other.peek(i));
        } else {
            return this.size() - other.size();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;
            if (other.size() != this.size()){
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!this.peek(i).equals(other.peek(i))) {
                    return false;
                }
            }
            return true;
            
        }
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i <size; i ++) {
            result = (int) (peek(i).hashCode() * Math.pow(37, i));             
        }
        return result;
    }
}
