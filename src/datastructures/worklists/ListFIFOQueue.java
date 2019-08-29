package datastructures.worklists;

import java.util.NoSuchElementException;

import cse332.interfaces.worklists.FIFOWorkList;

/**
 * A subclass of WorkList that stores its elements in FIFO order. All subclasses
 * of this class implicitly agree to the contract to be a FIFO queue. That is,
 * addWork() must add to the "end" and next() must remove from the "beginning".
 *
 *
 *
/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private ListNode<E> head;
    private ListNode<E> end; 
    private int size;
    
    
    public ListFIFOQueue() {
    	head = null;
        end = null;
        size = 0;
    }

    @Override
    public void add(E work) { 
        if (size != 0) {
        	end.next = new ListNode<E>(work, null);
        	end = end.next;
        } else {
        	head = new ListNode<E>(work, null);
        	end = head;
        }
        size++;
    }

    @Override
    public E peek() {
    	if (!hasWork()) {
    		throw new NoSuchElementException(); 	
    	}
    	return head.data; 
    }

    @Override
    public E next() {
    	E work = peek();
    	head = head.next;
    	if (head == null) {
    		end = null;
    	} 
    	size = size - 1;
    	return work; 
    }

    @Override
    public int size() {
        return size; 
    }

    @Override
    public void clear() {
    	 head = null;
         end = null;
         size = 0;
    }
    
    private static class ListNode<E> {
    	public ListNode<E> next;
    	public E data;
    	
    	public ListNode(E data, ListNode<E> next) {
    		this.data = data;
    		this.next = next;
    	}
    }
}
