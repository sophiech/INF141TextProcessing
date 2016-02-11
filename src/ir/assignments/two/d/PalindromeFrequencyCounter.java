/**
 * Assignment 1: Part D
 *
 * @author Sophia Chan sophiawc 33196560
 */
package ir.assignments.two.d;
import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;
import java.io.File;
import java.util.*;
public class PalindromeFrequencyCounter {
    /**
     * This class should not be instantiated.
     */
    private PalindromeFrequencyCounter() {
    }

    /**
     * Takes the input list of words and processes it, returning a list
     * of {@link Frequency}s.
     *
     * This method expects a list of lowercase alphanumeric strings.
     * If the input list is null, an empty list is returned.
     *
     * There is one frequency in the output list for every
     * unique palindrome found in the original list. The frequency of each palindrome
     * is equal to the number of times that palindrome occurs in the original list.
     *
     * Palindromes can span sequential words in the input list.
     *
     * The returned list is ordered by decreasing size, with tied palindromes sorted
     * by frequency and further tied palindromes sorted alphabetically.
     *
     * The original list is not modified.
     *
     * Example:
     *
     * Given the input list of strings
     * ["do", "geese", "see", "god", "abba", "bat", "tab"]
     *
     * The output list of palindromes should be
     * ["do geese see god:1", "bat tab:1", "abba:1"]
     *
     * @param words A list of words.
     * @return A list of palindrome frequencies, ordered by decreasing frequency.
     */
    private static List<Frequency> computePalindromeFrequencies(ArrayList<String> words) {
        int numOfWords = words.size();
        int tempVal = 0;
        String element;
        //System.out.println("Total number of words: " + numOfWords);
        HashMap<String, Integer> countP = new HashMap<String, Integer>();
        List<Frequency> palList = new ArrayList<Frequency>();

        if (words.isEmpty()) {    //checks if the input list is empty, if so returns an empty list
            return palList;
        } else {
            //for individual words only
            for (int i = 0; i < numOfWords; i++) {
                element = words.get(i);
                if (PalindromeFrequencyCounter.isPalindrome(element) && element.length() > 0) {    //checks if palindrome
                    if (countP.containsKey(element)) {        //increment value if palindrome already inside
                        tempVal = countP.get(element);
                        countP.put(element, tempVal + 1);
                    } else {
                        countP.put(element, 1);       //add palindrome if not in hashmap
                    }
                }
            }

            //for phrases only
            String tempWithSpaces;
            for (int i = 0; i < numOfWords; i++) {
                element = words.get(i);
                tempWithSpaces = element;            //saves the words from the list with spaces temporarily
                for (int j = i + 1; j < numOfWords; j++) {
                    element += words.get(j);
                    tempWithSpaces = (tempWithSpaces + " " + words.get(j)).trim();
                    if (PalindromeFrequencyCounter.isPalindrome(element) && element.length() > 0) {    //checks if palindrome
                        if (countP.containsKey(tempWithSpaces)) {        //increment value if palindrome already inside
                            tempVal = countP.get(tempWithSpaces);
                            countP.put(tempWithSpaces, tempVal + 1);
                        } else {
                            countP.put(tempWithSpaces, 1);
                        }
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : countP.entrySet()) {
                palList.add(new Frequency(entry.getKey(), entry.getValue()));        //inserts word and frequency into list
            }

            Collections.sort(palList, new SortPalindromeComparator());
            return palList;
        }
    }

    /**
     * Checks if the given string is a palindrome.
     * @param text String of text.
     * @return boolean    If true, it is a palindrome, if false, it is not a palindrome.
     */
    public static boolean isPalindrome(String text) {
        boolean isPal = false;
        String textReversed = new StringBuffer(text).reverse().toString();        //uses StringBuffer to reverse the character sequence of text
        isPal = text.contentEquals(textReversed);        //contentEquals() checks the character sequence of text to textReversed (returns a boolean)
        return isPal;
    }

    /**
     * Runs the 2-gram counter. The input should be the path to a text file.
     *
     * @param args The first element should contain the path to a text file.
     */
    public static void main(String[] args) {
        File file = new File(args[0]);
        ArrayList<String> words = Utilities.tokenizeFile(file);
        long start = System.currentTimeMillis();
        List<Frequency> frequencies = computePalindromeFrequencies(words);
        Utilities.printFrequencies(frequencies);
        System.out.println("Runtime: " + (System.currentTimeMillis() - start) + " ms");
    }
}
