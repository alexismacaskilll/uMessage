package experiment;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cse332.interfaces.misc.DeletelessDictionary;
import datastructures.dictionaries.AVLTree;
import datastructures.dictionaries.ChainingHashTable;
import datastructures.dictionaries.MoveToFrontList;


public class HashCodeTiming {
    private static final int NUM_ELEMENTS = 10000; 
    private static final int NUM_TESTS = 60; 
    private static final int NUM_WARMUP = 10;
    private static final int Hash_Function = 2;
    private static CircularArrayFIFOQueueToTest<Integer> keyToFind;

    public static void main(String[] args) throws FileNotFoundException {
        double totalTime = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            long startTime = System.currentTimeMillis();
            construct(new ChainingHashTable<CircularArrayFIFOQueueToTest<Integer>, Integer>(() -> new MoveToFrontList()));
            long endTime = System.currentTimeMillis(); 
            if (NUM_WARMUP <= i ) {
                totalTime += (endTime - startTime); 
            }
        }
        System.out.println("construct time: " + totalTime / (NUM_TESTS - NUM_WARMUP));
        
        
        /*ChainingHashTable<CircularArrayFIFOQueueToTest<Integer>, Integer> dictionary = new ChainingHashTable<CircularArrayFIFOQueueToTest<Integer>, Integer>(() -> new MoveToFrontList());
        construct(dictionary);
        double totalFind = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            double find = find(dictionary);
            if (NUM_WARMUP <= i ) {
                totalFind += find;
            }
        }
        System.out.println("find time: " + totalFind / (NUM_TESTS - NUM_WARMUP) / dictionary.size());
        */
        
    }
     
    public static void construct(DeletelessDictionary<CircularArrayFIFOQueueToTest<Integer>, Integer> dictionary) throws FileNotFoundException {
        File f = new File("src/experiment/randomData.txt");
        Scanner input = new Scanner(f);
        while (input.hasNextLine()) {
            CircularArrayFIFOQueueToTest<Integer> key = new CircularArrayFIFOQueueToTest<Integer>(5, Hash_Function);
            String line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNextInt()) {
                int num = lineScanner.nextInt();
                key.add(num);
            }
            dictionary.insert(key, 0);
        }
    }
        
    public static double find(DeletelessDictionary<CircularArrayFIFOQueueToTest<Integer>, Integer> dictionary) throws FileNotFoundException {
            File f = new File("src/experiment/randomData.txt");
            Scanner input = new Scanner(f);
            double totalTime = 0; 
            while (input.hasNextLine()) {
                CircularArrayFIFOQueueToTest<Integer> key = new CircularArrayFIFOQueueToTest<Integer>(5, Hash_Function);
                String line = input.nextLine();
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNextInt()) {
                    int num = lineScanner.nextInt();
                    key.add(num);
                }
                long startTime = System.nanoTime();
                dictionary.find(key);
                long endTime = System.nanoTime();
                totalTime = totalTime + endTime - startTime;
            }
            return totalTime;
    }

}
