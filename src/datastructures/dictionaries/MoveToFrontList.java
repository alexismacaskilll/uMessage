package datastructures.dictionaries;

import java.util.Iterator;

import cse332.datastructures.containers.*;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.SimpleIterator;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. The list is typically not sorted.
 * 2. Add new items to the front oft he list.
 * 3. Whenever find is called on an item, move it to the front of the 
 *    list. This means you remove the node from its current position 
 *    and make it the first node in the list.
 * 4. You need to implement an iterator. The iterator SHOULD NOT move
 *    elements to the front.  The iterator should return elements in
 *    the order they are stored in the list, starting with the first
 *    element in the list.
 */
public class MoveToFrontList<K, V> extends DeletelessDictionary<K, V> {
    private ListNode<K, V> front;
    
    public MoveToFrontList() {
        front = null; 
    }
    
    
    @Override
    public V insert(K key, V value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        V v = find(key);
        if (v != null) {
            front.data.value = value;
        } else {
            front = new ListNode<K, V>(new Item<K, V>(key, value), front);
            super.size++;
        }
        return v;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        ListNode curr = front;
        if (front != null && front.data.key.equals(key)) {
            return front.data.value;
        }
        while (curr != null && curr.next != null) {
            if (curr.next.data.key.equals(key)) {
                ListNode temp = curr.next.next; 
                curr.next.next = front;
                front = curr.next;
                curr.next = temp;
                return front.data.value;
            }
            curr= curr.next;
        }
        return null;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new MTFIterator();
    }
    
    private class MTFIterator extends SimpleIterator<Item<K, V>> {
        private ListNode current;

        public MTFIterator() {
            if (MoveToFrontList.this.front != null) {
                this.current = MoveToFrontList.this.front;
            }
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public Item<K, V> next() {
            Item<K, V> item = current.data;
            current = current.next;
            return item;
        }
    }
    
    private class ListNode<K, V> {
        public ListNode<K, V> next;
        public Item<K, V> data;
        
        public ListNode(Item<K, V> data, ListNode<K, V> next) {
            this.data = data;
            this.next = next;
        }
    }
}
