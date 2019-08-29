package experiment;

import cse332.datastructures.trees.BinarySearchTree;
import cse332.interfaces.misc.DeletelessDictionary;
import datastructures.dictionaries.AVLTree;

public class BstVsAvl {
    private static final int NUM_ELEMENTS = 50000; 
    private static final int NUM_TESTS = 60; 
    private static final int NUM_WARMUP = 10; 
    
    public static double timeConstruct(DeletelessDictionary<Integer, Integer> dictionary, Integer[] allKeys, Integer value) { 
            long startTime = System.currentTimeMillis(); 
            for (int j = 0; j< allKeys.length; j++) {
                dictionary.insert(allKeys[j], value);
            }
            long endTime = System.currentTimeMillis(); 
            return (endTime - startTime); 
    }
    
    public static double timeFind(DeletelessDictionary<Integer, Integer> dictionary, Integer key, Integer value) { 
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
    
    public static void main(String[] args) {
        
        Integer[] allKeys = new Integer[NUM_ELEMENTS];
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            allKeys[i] = i;
        }
        
        //double totalTimeBST = 0;
        double totalTimeAVL = 0;
        //DeletelessDictionary<Integer, Integer> dictionaryBST = new BinarySearchTree<Integer, Integer>();
        DeletelessDictionary<Integer, Integer> dictionaryAVL = new AVLTree<Integer, Integer>();
        for (int i = 0; i < NUM_TESTS; i++) {
            //dictionaryBST = new BinarySearchTree<Integer, Integer>();
            dictionaryAVL = new AVLTree<Integer, Integer>();

            //double timeBST = timeConstruct(dictionaryBST, allKeys, new Integer(0));
            double timeAVL = timeConstruct(dictionaryAVL, allKeys, new Integer(0)); 
            if (NUM_WARMUP <= i ) {
                //totalTimeBST += timeBST; 
                totalTimeAVL += timeAVL; 
            }
        }
 
        //System.out.println("BST Construct time: " + (totalTimeBST / (NUM_TESTS - NUM_WARMUP)));
        System.out.println("AVL Construct time: " + (totalTimeAVL / (NUM_TESTS - NUM_WARMUP)));
        //System.out.println("BST Find time: " + timeFind(dictionaryBST, allKeys[allKeys.length - 1], 0 ));
        //System.out.println("AVL Find time: " + timeFind(dictionaryAVL, allKeys[allKeys.length - 1], 0 ));
       
        

    }
    

}

//

