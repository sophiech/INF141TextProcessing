/**
 * Assignment 1 Part C
 *
 * @author Sophia Chan sophiawc 33196560
 */
package ir.assignments.two.c;
import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;
import ir.assignments.two.b.SortFrequencyComparator;
import java.io.File;
import java.util.*;
/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCounter {
    /**
     * This class should not be instantiated.
     */
    private TwoGramFrequencyCounter() {
    }

    /**
     * Takes the input list of words and processes it, returning a list
     * of {@link Frequency}s.
     *
     * This method expects a list of lowercase alphanumeric strings.
     * If the input list is null, an empty list is returned.
     *
     * There is one frequency in the output list for every
     * unique 2-gram in the original list. The frequency of each 2-grams
     * is equal to the number of times that two-gram occurs in the original list.
     *
     * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
     * alphabetically.
     *
     *
     *
     * Example:
     *
     * Given the input list of strings
     * ["you", "think", "you", "know", "how", "you", "think"]
     *
     * The output list of 2-gram frequencies should be
     * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
     *
     * @param words A list of words.
     * @return A list of two gram frequencies, ordered by decreasing frequency.
     */
    private static List<Frequency> computeTwoGramFrequencies(ArrayList<String> words) {
        HashMap<String, Integer> countF = new HashMap<String, Integer>();
        int tempVal = 0;
        List<Frequency> fList = new ArrayList<Frequency>();

        if (words.isEmpty()) {    //checks if the input list is empty, if so returns an empty list
            return fList;
        } else {
            for (int i = 0; i < words.size() - 1; i++) {
                if (!countF.containsKey(words.get(i) + " " + words.get(i + 1))) {    //checks to see if the hashmap does not contain a string from the list
                    countF.put(words.get(i) + " " + words.get(i + 1), 1);
                } else {
                    tempVal = countF.get(words.get(i) + " " + words.get(i + 1)) + 1;
                    countF.put(words.get(i) + " " + words.get(i + 1), tempVal);
                }
            }

            for (Map.Entry<String, Integer> entry : countF.entrySet()) {
                fList.add(new Frequency(entry.getKey(), entry.getValue()));        //inserts word and frequency into list
            }

            Collections.sort(fList, new SortFrequencyComparator());
            return fList;
        }
    }

    /**
     * Runs the 2-gram counter. The input should be the path to a text file.
     *
     * @param args The first element should contain the path to a text file.
     */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeTwoGramFrequencies(words);
		Utilities.printFrequencies(frequencies);
	}
}