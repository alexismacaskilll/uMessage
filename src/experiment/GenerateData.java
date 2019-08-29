package experiment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;


public class GenerateData {
    private static final int NUM_ELEMENTS = 100000; 

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("src/experiment/randomData.txt"));
        Random rand = new Random();
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            for (int j = 0; j < 5; j++) {
                int num =rand.nextInt(100);
                output.print(num + " ");
            }
            output.println();
        }
    }

}
