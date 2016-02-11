/**
 * Assignment 1 Part D
 *
 * @author Sophia Chan sophiawc 33196560
 */
package ir.assignments.two.d;
import ir.assignments.two.a.Frequency;
import java.util.Comparator;
/**
 * Comparator class which sorts a List of Frequency objects that contain palindromes by descending size first, descending
 * frequencies and then by alphabetical order if the frequency values are equal.
 */
public class SortPalindromeComparator implements Comparator<Frequency> {
    public int compare(Frequency f1, Frequency f2) {
        if (f1.getText().length() > f2.getText().length()) {     //sorts by decreasing length
            return -1;          //puts f1 first (since it has larger length)
        } else if (f1.getText().length() < f2.getText().length()) {
            return 1;
        } else {      //if lengths are the same
            if (f1.getFrequency() - f2.getFrequency() > 0) {  //sorts frequency in descending order
                return -1;
            } else if (f1.getFrequency() - f2.getFrequency() < 0) {
                return 1;
            } else {    //if frequency values are the same
                if (f1.getText().compareTo(f2.getText()) < 0) {      //compares text values
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
