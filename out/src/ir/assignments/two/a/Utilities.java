/**
 * Assignment 1 Part A
 *
 * @author Sophia Chan sophiawc 33196560
 */
package ir.assignments.two.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ir.assignments.two.d.PalindromeFrequencyCounter;
/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
    /**
     * Reads the input text file and splits it into alphanumeric tokens.
     * Returns an ArrayList of these tokens, ordered according to their
     * occurrence in the original text file.
     *
     * Non-alphanumeric characters delineate tokens, and are discarded. //can't becomes ["can", "t"]?
     *
     * Words are also normalized to lower case.
     *
     * Example:
     *
     * Given this input string
     * "An input string, this is! (or is it?)"
     *
     * The output list of strings should be
     * ["an", "input", "string", "this", "is", "or", "is", "it"]
     *
     * @param input The file to read in and tokenize.
     * @return The list of tokens (words) from the input file, ordered by occurrence.
     */
    public static ArrayList<String> tokenizeFile(File input) {
        ArrayList<String> tokens = new ArrayList<String>();
        String phrase = null;
        String alphaNumericString = "";
        String[] arrayOfTokens = null;

        try {
            FileReader fr = new FileReader(input);
            BufferedReader r = new BufferedReader(fr);

            phrase = r.readLine();
            while (phrase != null) {
                alphaNumericString = tokenizeString(phrase);
                arrayOfTokens = alphaNumericString.split("\\s+");
                List<String> test = Arrays.asList(arrayOfTokens);
                tokens.addAll(test);

                phrase = r.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("File not Found.");
        }
        return tokens;
    }

    /**
     * Strips the given string of all non-alphanumeric characters except whitespaces.
     *
     * @param line Given string.
     * @return A string stripped of all non-alphanumeric characters except whitespaces.
     */
    public static String tokenizeString(String line) {
        line = line.toLowerCase();        //lowercase everything, gets rid of leading and trailing whitespaces
        String onlyAlphaNumericApostrophe = line.replaceAll("[^a-zA-Z0-9' ]", "");
        String onlyAlphaNumeric = onlyAlphaNumericApostrophe.replaceAll("[^a-zA-Z0-9 ]", " ").trim();

        return onlyAlphaNumeric;
    }

    /**
     * Takes a list of {@link Frequency}s and prints it to standard out. It also
     * prints out the total number of items, and the total number of unique items.
     *
     * Example one:
     *
     * Given the input list of word frequencies
     * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
     *
     * The following should be printed to standard out
     *
     * Total item count: 6
     * Unique item count: 5
     *
     * sentence	2
     * the		1
     * this		1
     * repeats	1
     * word		1
     *
     *
     * Example two:
     *
     * Given the input list of 2-gram frequencies
     * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
     *
     * The following should be printed to standard out
     *
     * Total 2-gram count: 6
     * Unique 2-gram count: 5
     *
     * you think	2
     * how you		1
     * know how		1
     * think you	1
     * you know		1
     *
     * @param frequencies A list of frequencies.
     */
    public static void printFrequencies(List<Frequency> frequencies) {
        int totalNumItems = 0;
        int sizeOfList = frequencies.size();
        String firstElemWord;
        String firstElemWordWOSpaces;

        try {        //used to catch any empty lists
            firstElemWord = frequencies.get(0).getText();
            firstElemWordWOSpaces = firstElemWord.replaceAll("\\s", "");
            int numOfSpaces = firstElemWord.length() - firstElemWordWOSpaces.length();
            for (int i = 0; i < sizeOfList; i++) {
                totalNumItems += frequencies.get(i).getFrequency();
            }
            if (!PalindromeFrequencyCounter.isPalindrome(firstElemWordWOSpaces)) {    //checks if the first element is not a palindrome
                if (numOfSpaces < 1) {        //checks if it is 1 word
                    System.out.println("Total item count: " + totalNumItems);
                    System.out.println("Unique item count: " + sizeOfList + "\n");
                } else {
                    System.out.println("Total " + (numOfSpaces + 1) + "-gram count: " + totalNumItems);
                    System.out.println("Unique " + (numOfSpaces + 1) + "-gram count: " + sizeOfList + "\n");
                }
            } else {
                System.out.println("Total palindrome count: " + totalNumItems);
                System.out.println("Unique palindrome count: " + sizeOfList + "\n");
            }

            //print out text and corresponding frequency
            for (int i = 0; i < sizeOfList; i++) {
                System.out.println(frequencies.get(i).getText() + "\t" + frequencies.get(i).getFrequency());
            }

        } catch (Exception e) {
            System.out.println("Empty File or File only has Non-Alphanumeric Characters or contains no palindromes.");
        }
    }
}