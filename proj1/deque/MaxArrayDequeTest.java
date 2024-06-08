package deque;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/** Performs some basic linked list tests. */
public class MaxArrayDequeTest {

//    @Test
//    /** Adds a few things to the list, checking isEmpty() and size() are correct,
//     * finally printing the results.
//     *
//     * && is the "and" operation. */
//    public void addIsEmptySizeTest() {
//
//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//
//        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
//
//        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
//        lld1.addFirst("front");
//
//        // The && operator is the same as "and" in Python.
//        // It's a binary operator that returns true if both arguments true, and false otherwise.
//        assertEquals(1, lld1.size());
//        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());
//
//        lld1.addLast("middle");
//        assertEquals(2, lld1.size());
//
//        lld1.addLast("back");
//        assertEquals(3, lld1.size());
//
//        System.out.println("Printing out deque: ");
//        lld1.printDeque();
//
//    }

public static class strIntCom implements Comparator<Integer>{
    public int compare(Integer str1, Integer str2){
        return str1 - str2;
    }
}


    @Test
    public void maxTest() {

//equalsTest3        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
//
//
//
//
//        MaxArrayDeque<Integer> cmp = ad1.MaxArrayDeque();

        Comparator<Integer> strInt = new strIntCom();
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(strInt);

        mad1.addLast(1);
        mad1.addLast(5);
        mad1.addFirst(2);

        assertTrue(mad1.max()== 5);
    }

}