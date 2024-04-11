package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
//    @Test
//    public void testSquarePrimesSimple() {
//        IntList lst = IntList.of(14, 15, 16, 17, 18);
//        boolean changed = IntListExercises.squarePrimes(lst);
//        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
//        assertTrue(changed);
//    }

    @Test
    public void testSquarePrimesSimpleZ() {
        IntList lst = IntList.of(5,5);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("25 -> 25", lst.toString());
        assertTrue(changed==true);
    }

    @Test
    public void testSquarePrimesSimpleZ2() {
        IntList lst = IntList.of(6,8);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("6 -> 8", lst.toString());
        assertTrue(changed==false);
    }

    @Test
    public void testSquarePrimesSimple2() {
        IntList lst = IntList.of(1, 2, 3, 4,5,6,7,8,9,13,23,31,33, 53, 25);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 9 -> 4 -> 25 -> 6 -> 49 -> 8 -> 9 -> 169 -> 529 -> 961 -> 33 -> 2809 -> 25", lst.toString());
        assertTrue(changed==true);
    }




    @Test
    public void testSquarePrimesSimple4() {
        IntList lst = IntList.of(3, 30, 31, 13, 23, 33);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("9 -> 30 -> 961 -> 169 -> 529 -> 33", lst.toString());
        assertTrue(changed==true);
    }

//    @Test
//    public void testSquarePrimesSimple3() {
//        IntList lst = IntList.of(1,2,3,4,5,6,7,8,9,10,11,12,13);
//        boolean changed = IntListExercises.squarePrimes(lst);
//        assertEquals("1 -> 4 -> 9 -> 4 -> 25 -> 6 -> 49 -> 8 -> 9 -> 10 -> 121 -> 12 <- 169", lst.toString());
//        assertTrue(changed==false);



}




