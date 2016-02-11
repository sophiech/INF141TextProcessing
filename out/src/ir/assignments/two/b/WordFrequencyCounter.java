/**
 * Assignment 1 Part B
 *
 * @author Sophia Chan sophiawc 33196560
 */
package ir.assignments.two.b;
import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {
    /**
     * This class should not be instantiated.
     */
    private WordFrequencyCounter() {
    }

    /**
     * Takes the input list of words and processes it, returning a list
     * of {@link Frequency}s.
     *
     * This method expects a list of lowercase alphanumeric strings.
     * If the input list is null, an empty list is returned.
     *
     * There is one frequency in the output list for every
     * unique word in the original list. The frequency of each word
     * is equal to the number of times that word occurs in the original list.
     *
     * The returned list is ordered by decreasing frequency, with tied words sorted
     * alphabetically.
     *
     * The original list is not modified.
     *
     * Example:
     *
     * Given the input list of strings
     * ["this", "sentence", "repeats", "the", "word", "sentence"]
     *
     * The output list of frequencies should be
     * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
     *
     * @param words A list of words.
     * @return A list of word frequencies, ordered by decreasing frequency.
     */
    public static List<Frequency> computeWordFrequencies(List<String> words) {
        HashMap<String, Integer> countFrequency = new HashMap<String, Integer>();
        int tempVal = 0;
        List<Frequency> freqList = new ArrayList<Frequency>();

        if (words.isEmpty()) {    //checks if the input list is empty, if so returns an empty list
            return freqList;
        } else {
            for (int i = 0; i < words.size(); i++) {
                if (!countFrequency.containsKey(words.get(i))) {    //checks to see if the hashmap does not contain a string from the list
                    countFrequency.put(words.get(i), 1);
                } else {
                    tempVal = countFrequency.get(words.get(i)) + 1;
                    countFrequency.put(words.get(i), tempVal);
                }
            }

            for (Map.Entry<String, Integer> entry : countFrequency.entrySet()) {
                freqList.add(new Frequency(entry.getKey(), entry.getValue()));        //inserts word and frequency into list
            }

            Collections.sort(freqList, new SortFrequencyComparator());
            return freqList;
        }
    }

    /**
     * Runs the word frequency counter. The input should be the path to a text file.
     *
     * @param args The first element should contain the path to a text file.
     */
    public static void main(String[] args) {
        File file = new File(args[0]);
        List<String> words = Utilities.tokenizeFile(file);
        List<Frequency> frequencies = computeWordFrequencies(words);
        Utilities.printFrequencies(frequencies);
    }
}