package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.LIFOWorkList;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
	private E[] stack;
	private int pointer; 
	
    public ArrayStack() {
    	stack = (E[])new Object[10];
    	pointer = -1;
    }

    @Override
    public void add(E work) {
    	if (pointer == stack.length - 1) {
    		E[] temp = (E[])new Object[stack.length * 2];
    		for (int i = 0; i < stack.length; i++) {
    			temp[i] = stack[i];
    		}
    		stack = temp; 
    	}
        pointer++;
        stack[pointer] = work;
    }

    @Override
    public E peek() {
    	if (!hasWork()) {
    		throw new NoSuchElementException(); 	
    	}
    	return stack[pointer];
    }

    @Override
    public E next() {
        E work = peek();
        pointer--;
        return work;
    }

    @Override
    public int size() {
        return pointer + 1; 
    }

    @Override
    public void clear() {
    	stack = (E[])new Object[10];
    	pointer = -1; 
    }
}
