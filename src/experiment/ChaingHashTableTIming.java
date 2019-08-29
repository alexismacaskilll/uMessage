package experiment;

import java.io.FileNotFoundException;
import java.util.Random;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.interfaces.misc.DeletelessDictionary;
import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.MoveToFrontList;

//random
//construct time: 1.72
//find time: 86867.56

//construct time: 2.36
//find time: 139.48

//construct time: 2.82
//find time: 93357.82

//sorted

//construct time: 1.44
//find time: 86498.34

//construct time: 1.66
//find time: 246.16


//construct time: 1.76
//find time: 87441.88

//for sorted data BST is best, 
//for random data, MTF is best, 
public class ChaingHashTableTIming {
    private static final int NUM_ELEMENTS = 1000000; 
    private static final int NUM_TESTS = 60; 
    private static final int NUM_WARMUP = 10;

    public static void main(String[] args) throws FileNotFoundException {
        Random rand = new Random();
        Integer[] allKeys = new Integer[NUM_ELEMENTS];
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            allKeys[i] = i;
        }
        
        double totalTime = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            long startTime = System.currentTimeMillis(); 
            //construct(new ChainingHashTable<Integer, Integer>(() -> new MoveToFrontList<>()), allKeys);
            //construct(new ChainingHashTable<Integer, Integer>(() -> new BinarySearchTree<>()), allKeys);
            construct(new ChainingHashTable<Integer, Integer>(() -> new AVLTree<>()), allKeys);
            long endTime = System.currentTimeMillis(); 
            if (NUM_WARMUP <= i ) {
                totalTime += (endTime - startTime); 
            }
        }
        System.out.println("construct time: " + totalTime / (NUM_TESTS - NUM_WARMUP));
        
        //ChainingHashTable<Integer, Integer> dictionary = new ChainingHashTable<Integer, Integer>(() -> new MoveToFrontList<>());
        //ChainingHashTable<Integer, Integer> dictionary = new ChainingHashTable<Integer, Integer>(() -> new BinarySearchTree<>());
        ChainingHashTable<Integer, Integer> dictionary = new ChainingHashTable<Integer, Integer>(() -> new AVLTree<>());
        //construct(dictionary, allKeys);
        //System.out.println("find time: " + timeFind(dictionary, allKeys[rand.nextInt(NUM_ELEMENTS)]));
    }
    
    public static double timeFind(ChainingHashTable<Integer, Integer> dictionary, Integer key) { 
        double totalTime = 0; 
        for (int i = 0; i < NUM_TESTS; i++ ) { 
            long startTime = System.nanoTime(); 
            dictionary.find(key); 
            long endTime = System.nanoTime(); 
            if (NUM_WARMUP <= i ) {
                totalTime += (endTime - startTime); 
            }
        }
        return (totalTime / (NUM_TESTS - NUM_WARMUP));
    }
    
    public static void construct(DeletelessDictionary<Integer, Integer> dictionary, Integer[] allKeys) throws FileNotFoundException {
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            dictionary.insert(allKeys[i], 0);
        }
    }
    
    
}
