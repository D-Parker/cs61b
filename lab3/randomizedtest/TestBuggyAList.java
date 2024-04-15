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


//        int B = L.removeLast();
//
//        assertTrue(A==B);

    }

//    public void testSquarePrimesSimpleZ() {
//        IntList lst = IntList.of(5,5);
//        boolean changed = IntListExercises.squarePrimes(lst);
//        assertEquals("25 -> 25", lst.toString());
//        assertTrue(changed==true);
//    }
}

