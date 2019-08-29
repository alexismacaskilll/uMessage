package experiment;

import datastructures.worklists.CircularArrayFIFOQueue;

public class CircularArrayFIFOQueueToTest<E extends Comparable<E>> extends CircularArrayFIFOQueue<E> {
    int flag;
    
    public CircularArrayFIFOQueueToTest(int capacity, int flag) {
        super(capacity);
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        int result = 0;
        if (flag == 1) {
            for (int i = 0; i < super.size(); i++) {
                result = result + (int) (peek(i).hashCode() * Math.pow(37, i));             
            }
        }
        
        if (flag == 2) {
            result = (int) (peek(0).hashCode());

        }    
        return result;
    }
}
