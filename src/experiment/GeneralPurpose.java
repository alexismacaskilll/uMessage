package experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import cse332.datastructures.containers.Item;
import cse332.datastructures.trees.BinarySearchTree;
import cse332.interfaces.misc.DeletelessDictionary;
import cse332.types.AlphabeticString;
import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.HashTrieMap;
import datastructures.dictionaries.MoveToFrontList;


public class GeneralPurpose {
    private static final int NUM_TESTS = 60; 
    private static final int NUM_WARMUP = 10;

    public static void main(String[] args) throws FileNotFoundException {
        double totalTime = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            long startTime = System.currentTimeMillis(); 
            //construct(new BinarySearchTree<String, Integer>());
            //construct(new AVLTree<String, Integer>());
            //construct(new ChainingHashTable<String, Integer>(() -> new MoveToFrontList<>()));
            //construct(new ChainingHashTable<String, Integer>(() -> new BinarySearchTree<>()));
            //construct(new ChainingHashTable<String, Integer>(() -> new AVLTree<>()));
            //construct(new HashTrieMap<>(AlphabeticString.class));
            long endTime = System.currentTimeMillis(); 
            if (NUM_WARMUP <= i ) {
                totalTime += (endTime - startTime); 
            }
        }
        //System.out.println("construct time: " + totalTime / (NUM_TESTS - NUM_WARMUP));
        
        //BinarySearchTree<String, Integer> dictionary = new BinarySearchTree<String, Integer>();
        //AVLTree<String, Integer> dictionary = new AVLTree<String, Integer>();
        //ChainingHashTable<String, Integer> dictionary = new ChainingHashTable<String, Integer>(() -> new MoveToFrontList<>());
        //ChainingHashTable<String, Integer> dictionary = new ChainingHashTable<String, Integer>(() -> new BinarySearchTree<>());
        //ChainingHashTable<String, Integer> dictionary = new ChainingHashTable<String, Integer>(() -> new AVLTree<>());
         HashTrieMap<Character, AlphabeticString, Integer> dictionary = new HashTrieMap<>(AlphabeticString.class);
        construct(dictionary);
        double totalFind = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            double find = find(dictionary);
            if (NUM_WARMUP <= i ) {
                totalFind += find;
            }
        }
        System.out.println("find time: " + totalFind / (NUM_TESTS - NUM_WARMUP) / dictionary.size());
        
    }
    
    private static void construct(
            HashTrieMap<Character, AlphabeticString, Integer> dictionary) throws FileNotFoundException {
        File f = new File("alice.txt");
        Scanner input = new Scanner(f);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                AlphabeticString word = new AlphabeticString(lineScanner.next());
                Integer num = dictionary.find(word);
                if (num == null) {
                    dictionary.insert(word, 0);
                    num = 0;
                }
                dictionary.insert(word, num + 1);
            }
        }
        input.close();
    }

    public static void construct(DeletelessDictionary<String, Integer> dictionary) throws FileNotFoundException {
        File f = new File("alice.txt");
        Scanner input = new Scanner(f);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String word = lineScanner.next();
                Integer num = dictionary.find(word);
                if (num == null) {
                    dictionary.insert(word, 0);
                    num = 0;
                }
                dictionary.insert(word, num + 1);
            }
        }
        input.close();
    }
    
    public static double find(HashTrieMap<Character, AlphabeticString, Integer> dictionary) {
        double totalTime = 0;
        for (Item<AlphabeticString, Integer> word : dictionary) {
            long startTime = System.nanoTime();
            dictionary.find(word.key);
            long endTime = System.nanoTime();
            totalTime = totalTime + endTime - startTime;
        }
        return totalTime;
    }
    
    public static double find(DeletelessDictionary<String, Integer> dictionary) {
        double totalTime = 0;
        for (Item<String, Integer> word : dictionary) {
            long startTime = System.nanoTime();
            dictionary.find(word.key);
            long endTime = System.nanoTime();
            totalTime = totalTime + endTime - startTime;
        }
        return totalTime;
    }

}
