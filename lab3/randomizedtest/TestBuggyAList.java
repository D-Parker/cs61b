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
    public void testThreeAddThreeRemove() {

        AListNoResizing<Integer> correct = new AListNoResizing<>();
        correct.addLast(4);
        correct.addLast(5);
        correct.addLast(6);

        BuggyAList<Integer> incorrect = new BuggyAList<>();
        incorrect.addLast(4);
        incorrect.addLast(5);
        incorrect.addLast(6);

        assertEquals(correct.size(), incorrect.size());

        assertEquals(correct.removeLast(), incorrect.removeLast());
        assertEquals(correct.removeLast(), incorrect.removeLast());
        assertEquals(correct.removeLast(), incorrect.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        BuggyAList<Integer> K = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                K.addLast(randVal);

//                System.out.println("addLast(" + randVal + ")");
                assertEquals(L.size(), K.size());

            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeK = K.size();
//                System.out.println("size L: " + sizeL + " size K: " + sizeK);
                assertEquals(sizeL, sizeK);
            }
            else if (operationNumber == 2 && L.size() > 0 && K.size() > 0) {

                int a = L.getLast();
                int b = K.getLast();

//                System.out.println("getLast(" + a + " " + b);
                assertEquals(a,b);
//                System.out.println("size check L " + L.size() + " K " + K.size());
                assertEquals(L.size(),K.size());
            }
            else if (operationNumber == 3 && L.size() > 0 && K.size() > 0) {
                int a = L.removeLast();
                int b = K.removeLast();

//                System.out.println("removeLast(" + a + " " + b);
                assertEquals(a,b);
//                System.out.println("size check L " + L.size() + " K " + K.size());
                assertEquals(L.size(),K.size());
            }
        }


    }


}

