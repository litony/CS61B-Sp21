package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> l = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                l.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int B_size = l.size();
                assertEquals(size, B_size);
                System.out.println("size: " + size);
                System.out.println("B_size: " + B_size);
            } else if (operationNumber == 2) {
                if (L.size() > 0 && l.size() > 0) {
                    int last = L.getLast();
                    int B_last = l.getLast();
                    assertEquals(last, B_last);
                    System.out.println("getLast(" + last +")");
                    System.out.println("getLast(" + B_last +")");
                }
            } else if (operationNumber == 3) {
                if (L.size() > 0 && l.size() > 0) {
                    L.removeLast();
                    l.removeLast();
                    System.out.println("removeLast");

                }
            }
        }
    }
}
