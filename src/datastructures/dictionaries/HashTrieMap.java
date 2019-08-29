package datastructures.dictionaries;


import java.util.AbstractMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import cse332.datastructures.containers.Item;
import cse332.interfaces.misc.BString;

import cse332.interfaces.misc.SimpleIterator;

import cse332.interfaces.trie.TrieMap;
import datastructures.dictionaries.HashTrieMap.HashTrieNode;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<ChainingHashTable<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {

            this.pointers = new ChainingHashTable(() -> new MoveToFrontList<>());
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            //return pointers.entrySet().iterator();
            return new EntryIterator();
        }
        
        private class EntryIterator extends SimpleIterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> {
            Iterator<Item<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator;

            public EntryIterator() {
                iterator = HashTrieNode.this.pointers.iterator();
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }
            
          
            @Override
            public Entry<A, HashTrieMap<A, K, V>.HashTrieNode> next() {
                Item<A, HashTrieMap<A, K, V>.HashTrieNode> item = iterator.next();
                return new AbstractMap.SimpleEntry(item.key, item.value);
            }
            
        }
        
   
    
   
        
        
        
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if (value == null) {
        	throw new IllegalArgumentException();
        }	
        HashTrieNode node = findNode(key, true);
        V preVal = node.value;
        if (preVal == null) {
        	this.size++;
        }
        node.value = value;
        return preVal;
 
    }

    @Override
    public V find(K key) {
    	HashTrieNode node = findNode(key, false);
    	if (node != null) {
    		return node.value;
    	}
    	return null;
    }
    
    private HashTrieNode findNode(K key, boolean insert) {
    	if (key == null) {
        	throw new IllegalArgumentException();
        }
    	Iterator<A> iterator = key.iterator();
    	HashTrieNode node = (HashTrieMap<A, K, V>.HashTrieNode) this.root;
    	while (iterator.hasNext()) {
    		A c = iterator.next();
    		if (node.pointers.find(c) == null) {
    			if (insert) {
    				node.pointers.insert(c, new HashTrieNode());
    			} else {
    				return null;
    			}
    		}
    		node = node.pointers.find(c);
    	}
    	return node;
    }

    @Override
    public boolean findPrefix(K key) {
    	return findNode(key, false) != null;
    }


    /*@Override

    public void delete(K key) {
    	if (key == null) {
        	throw new IllegalArgumentException();
        }
    	deleteHelper(key.iterator(), (HashTrieMap<A, K, V>.HashTrieNode) this.root);
    }
    
    private boolean deleteHelper(Iterator<A> key, HashTrieNode node) {
    	if (!key.hasNext()) {
    		if (node.value != null) {
    			this.size--;
    		}
    		node.value = null;
    		return node.pointers.isEmpty();
    	} else {
    		A c = key.next();
    		if (!node.pointers.containsKey(c)) {
    			return false;
    		} else {
    			if (deleteHelper(key, node.pointers.get(c))) {
    				node.pointers.remove(c);
    				return node.pointers.isEmpty() && node.value == null;
    			} else {
    				return false;
    			}
    		}
    	}
    	

    }*/

    @Override
    public void clear() {
    	this.root = new HashTrieNode();
    	this.size = 0;
    }

    @Override
    public void delete(K key) {
        throw new UnsupportedOperationException();
    }
}
