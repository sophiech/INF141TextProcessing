/**
 * Assignment 1 Part B/C
 *
 * @author Sophia Chan sophiawc 33196560
 */
package ir.assignments.two.b;
import ir.assignments.two.a.Frequency;
import java.util.Comparator;
/**
 * Comparator class which sorts a List of Frequency objects by descending frequency first and then
 * by alphabetical order if the frequency values are equal.
 */
public class SortFrequencyComparator implements Comparator<Frequency> {
    public int compare(Frequency f1, Frequency f2) {
        if (f1.getFrequency() == f2.getFrequency()) {        //checks if frequency values are the same
            if (f1.getText().compareTo(f2.getText()) < 0)    //compares text values
                return -1;
            else
                return 1;
        } else {
            if (f1.getFrequency() - f2.getFrequency() > 0)    //sorts frequency in descending order
                return -1;
            else
                return 1;
        }
    }
}
