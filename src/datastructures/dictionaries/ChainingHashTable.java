package datastructures.dictionaries;


import java.util.Iterator;
import java.util.function.Supplier;

import cse332.datastructures.containers.*;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.interfaces.misc.Dictionary;
import cse332.interfaces.misc.SimpleIterator;

/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. You must implement a generic chaining hashtable. You may not
 *    restrict the size of the input domain (i.e., it must accept 
 *    any key) or the number of inputs (i.e., it must grow as necessary).
 * 3. Your HashTable should rehash as appropriate (use load factor as
 *    shown in class).
 * 5. HashTable should be able to grow at least up to 200,000 elements. 
 * 6. We suggest you hard code some prime numbers. You can use this
 *    list: http://primes.utm.edu/lists/small/100000.txt 
 *    NOTE: Do NOT copy the whole list!
 *    
 */
public class ChainingHashTable<K, V> extends DeletelessDictionary<K, V> {
    private Supplier<Dictionary<K, V>> newChain;  
    private Dictionary<K, V>[] hashTable;
    private final int[] PRIME = {1009, 2003, 4001, 8009, 16001, 32003, 64007, 128021, 200003};
    private int tableSize;

    public ChainingHashTable(Supplier<Dictionary<K, V>> newChain) {
        this.hashTable = (Dictionary<K, V>[]) new Dictionary[PRIME[0]];
        tableSize = 0;
        this.newChain = newChain;
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        int index = Math.abs(key.hashCode() % hashTable.length);
        if (hashTable[index] == null) {
            hashTable[index] = newChain.get();
        }
        V result = hashTable[index].insert(key, value);
        if (result == null) {
            super.size++;
        }
        if (super.size > hashTable.length) {
            rehash();
        }
        return result;
    }
    
    private void rehash() {
        Dictionary<K, V>[] temp = hashTable;
        super.size = 0;
        tableSize++;
        if (tableSize > PRIME.length - 1) {
            hashTable = (Dictionary<K, V>[]) new Dictionary[temp.length * 2];
        } else {
            hashTable = (Dictionary<K, V>[]) new Dictionary[PRIME[tableSize++]];
        }
        for(int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                for (Item<K,V> data : temp[i]) {
                    insert(data.key, data.value);
                }
            }
        }
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int index = Math.abs(key.hashCode() % hashTable.length);
        if (hashTable[index] != null) {
            return hashTable[index].find(key);
        } 
        return null;
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return new CHTIterator();
    }
     
    private class CHTIterator extends SimpleIterator<Item<K, V>> {
        private Item<K, V>[] elements;
        private int index;

        public CHTIterator() {
            if (ChainingHashTable.this.hashTable != null) {
                this.index = 0;
                elements = (Item<K, V>[]) new Item[ChainingHashTable.this.size];
                for (int i = 0; i < hashTable.length; i ++) {
                    if (hashTable[i] != null) {
                        for (Item<K, V> data : hashTable[i]) {
                            elements[index] = data;
                            index++;
                        }
                    }
                }
            }
            index = 0;
        }
 
        
        @Override
        public boolean hasNext() {
            return (index < ChainingHashTable.this.size) && ChainingHashTable.this.size > 0;
        }
        
      
        @Override
        public Item<K, V> next() {
            index++;
            return elements[index - 1];
        }
        
    }
}
