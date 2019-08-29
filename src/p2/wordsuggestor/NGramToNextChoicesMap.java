package p2.wordsuggestor;

import java.util.Comparator;
import java.util.function.Supplier;

import cse332.datastructures.containers.Item;
import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.misc.Dictionary;
import cse332.misc.LargeValueFirstItemComparator;
import cse332.sorts.InsertionSort;
import cse332.types.AlphabeticString;
import cse332.types.NGram;
import p2.sorts.QuickSort;
import p2.sorts.TopKSort;

public class NGramToNextChoicesMap {
    private final Dictionary<NGram, Dictionary<AlphabeticString, Integer>> map;
    private final Supplier<Dictionary<AlphabeticString, Integer>> newInner;

    public NGramToNextChoicesMap(
            Supplier<Dictionary<NGram, Dictionary<AlphabeticString, Integer>>> newOuter,
            Supplier<Dictionary<AlphabeticString, Integer>> newInner) {
        this.map = newOuter.get();
        this.newInner = newInner;
    }

    /**
     * Increments the count of word after the particular NGram ngram.
     */
    public void seenWordAfterNGram(NGram ngram, String word) {
        AlphabeticString words = new AlphabeticString(word);
        if (map.find(ngram) == null) {
            map.insert(ngram, newInner.get());

        } 
        if (map.find(ngram).find(words) == null) {
            map.find(ngram).insert(words, 0);
        }
        map.find(ngram).insert(words, map.find(ngram).find(words) + 1);
    }

    /**
     * Returns an array of the DataCounts for this particular ngram. Order is
     * not specified.
     *
     * @param ngram
     *            the ngram we want the counts for
     * 
     * @return An array of all the Items for the requested ngram.
     */
    public Item<String, Integer>[] getCountsAfter(NGram ngram) {
        if (map.find(ngram) == null) {
            return (Item<String, Integer>[])new Item[0];
        }
       Dictionary<AlphabeticString, Integer>  inner = map.find(ngram);
       Item<String, Integer>[] counts = (Item<String, Integer>[])new Item[inner.size()];
       
       int i = 0;
       for (Item<AlphabeticString, Integer> item : inner) {
           Item<String, Integer> pair = new Item<String, Integer>(item.key.toString(), item.value);
           counts[i] = pair;
           i++;
       }
       return counts;
    }

    public String[] getWordsAfter(NGram ngram, int k) {
        Item<String, Integer>[] afterNGrams = getCountsAfter(ngram);

        Comparator<Item<String, Integer>> comp = new LargeValueFirstItemComparator<String, Integer>();
        if (k < 0) {
            QuickSort.sort(afterNGrams, comp);
        }
        else {
            TopKSort.sort(afterNGrams, k, comp.reversed());
            flip(afterNGrams,  Math.min(k, afterNGrams.length));
        }

        String[] nextWords = new String[k < 0 ? afterNGrams.length : k];
        for (int l = 0; l < afterNGrams.length && l < nextWords.length
                && afterNGrams[l] != null; l++) {
            nextWords[l] = afterNGrams[l].key;
        }
        return nextWords;
    }
    
    private void flip(Item<String, Integer>[] array, int size) {
        for  (int i = 0; i < size / 2; i++) {
            Item<String, Integer> temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}
