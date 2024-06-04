package deque;

import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }

    @Test
    public void iteratorTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        LinkedListDeque<String> lld2 = new LinkedListDeque<>();

        ArrayDeque<String> ad1 = new ArrayDeque<>();


        ad1.addFirst("k");

        lld1.addFirst("k");
        lld2.addFirst("k");

        assertTrue(lld1.equals(lld2));

//        assertTrue(lld1.equals(ad1));

        lld1.addFirst("h");

        lld1.addFirst("b");
        lld1.addFirst("a");

        Iterator x = lld1.iterator();

//        assertEquals(x.next(), "b");
//        assertEquals(x.next(), "a");
//        assertEquals(x.next(), "b");
//
//        assertTrue(x.hasNext());
    }
@Test
public void equalsTest() {

            LinkedListDeque<String> lld1 = new LinkedListDeque<>();
            LinkedListDeque<String> lld2 = new LinkedListDeque<>();

            lld1.addFirst("a");
            lld2.addFirst("a");

//            assertEquals(lld1, lld2);

            assertTrue(lld1.equals(lld2));

    LinkedListDeque<String> lld3 = lld1;

    assertTrue(lld1.equals(lld3));


        }


//System.out.println(x.next() );
//        System.out.println(x.next() );
//        System.out.println(x.next() );
//        System.out.println(x.next() );
//        System.out.println(x.next() );
//
//        assertFalse(x.hasNext());

//        Iterator y = lld1.iterator();
//
//
//        Iterator<String> x = lld1.iterator();

    }




//package deque;
//
//import org.junit.Test;
//
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//
///** Performs some basic linked list tests. */
//public class LinkedListDequeTest {
//
//
//    @Test
//    /**
//     Tests construction of deque, and the addFirst and getFirst methods
//     */
//
//    public void basicDequeTest() {
//
//        // Test that String type works
//        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
//        // test case of empty list
//
//        assertEquals(null, lld1.getFirst());
//        // test after adding one item
//        lld1.addFirst("gtg");
//        assertEquals("gtg", lld1.getFirst());
//
//        // test after adding multiple items
//        lld1.addFirst("xyz");
//        assertEquals("xyz", lld1.getFirst());
//
//        // Test that integer type works
////        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
////        lld2.addFirst(6);
////
////        assertEquals(, 6, lld2.getFirst() );
//    }
//
//
//
//
//    @Test
//    /**
//     Test of addLast method
//     */
//
//
//    public void addLastDequeTest() {
//
//        // Test that String type works
//        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
//        // test case of empty list
//
//        assertEquals(0, lld1.size());
//
//        lld1.addLast("abc");
//
//        assertEquals("abc", lld1.getFirst());
//
//        lld1.addLast("def");
//
//        assertEquals("abc", lld1.getFirst());
//
//        assertEquals(2, lld1.size());
//
//        System.out.println("Printing out deque: ");
//        lld1.printDeque();
//
//        lld1.addFirst("www");
//
//        assertEquals("www", lld1.removeFirst());
//
//        assertEquals("abc", lld1.removeFirst());
//
//        assertEquals("def", lld1.removeFirst());
//
//        assertEquals(null, lld1.removeFirst());
//
//
//
//        lld1.addFirst("aaa");
//        assertEquals("aaa", lld1.removeLast());
//        lld1.addFirst("bbb");
//        lld1.addFirst("ccc");
//        assertEquals("bbb", lld1.removeLast());
//        assertEquals("ccc", lld1.removeLast());
//        assertEquals(null, lld1.removeLast());
//
//        // test after adding one item
////        lld1.addFirst("gtg");
////        assertEquals("gtg", lld1.getFirst());
////
////        // test after adding multiple items
////        lld1.addFirst("xyz");
////        assertEquals("xyz", lld1.getFirst());
//
//        // Test that integer type works
////        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
////        lld2.addFirst(6);
////
////        assertEquals(, 6, lld2.getFirst() );
//    }
//
//
//    @Test
//    /**
//     Test of addLast method
//     */
//
//
//    public void getDequeTest() {
//
//        LinkedListDeque<String> lld3 = new LinkedListDeque<>();
//        assertEquals(null, lld3.get(0));
//
//        lld3.addFirst("xyz");
//
//        assertEquals("xyz", lld3.get(0));
//        assertEquals("xyz", lld3.getRecursive(0));
//
//        lld3.addFirst("aaa");
//        lld3.addFirst("bbb");
//
//        assertEquals("xyz", lld3.get(2));
//        assertEquals("xyz", lld3.getRecursive(2));
//
//        assertEquals("aaa", lld3.get(1));
//        assertEquals("xyz", lld3.getRecursive(2));
//    }
//
//
//
//
//
////        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
//////        assertNull(lld1.getFirst());
//////        lld1.addFirst("abc");
////         System.out.println("abc");
//////        assertEquals("abc", lld1.getFirst());
////    }
////
////
//    @Test
//    /** Adds a few things to the list, checking isEmpty() and size() are correct,
//     * finally printing the results.
//     *
//     * && is the "and" operation. */
//    public void addIsEmptySizeTest() {
//
////        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//
//        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
//
//		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
//		lld1.addFirst("front");
//
//		// The && operator is the same as "and" in Python.
//		// It's a binary operator that returns true if both arguments true, and false otherwise.
//        assertEquals(1, lld1.size());
//        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());
//
//		lld1.addLast("middle");
//		assertEquals(2, lld1.size());
//
//		lld1.addLast("back");
//		assertEquals(3, lld1.size());
//
//		System.out.println("Printing out deque: ");
//		lld1.printDeque();
//
//    }
//
//    @Test
//    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
//    public void addRemoveTest() {
//
////        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//
//        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
//		// should be empty
//		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());
//
//		lld1.addFirst(10);
//		// should not be empty
//		assertFalse("lld1 should contain 1 item", lld1.isEmpty());
//
//		lld1.removeFirst();
//		// should be empty
//		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
//
//    }
//
//    @Test
//    /* Tests removing from an empty deque */
//    public void removeEmptyTest() {
//
////        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//
//        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
//        lld1.addFirst(3);
//
//        lld1.removeLast();
//        lld1.removeFirst();
//        lld1.removeLast();
//        lld1.removeFirst();
//
//        int size = lld1.size();
//        String errorMsg = "  Bad size returned when removing from empty deque.\n";
//        errorMsg += "  student size() returned " + size + "\n";
//        errorMsg += "  actual size() returned 0\n";
//
//        assertEquals(errorMsg, 0, size);
//
//    }
//
//    @Test
//    /* Check if you can create LinkedListDeques with different parameterized types*/
//    public void multipleParamTest() {
//
//
//        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
//        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
//        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();
//
//        lld1.addFirst("string");
//        lld2.addFirst(3.14159);
//        lld3.addFirst(true);
//
//        String s = lld1.removeFirst();
//        double d = lld2.removeFirst();
//        boolean b = lld3.removeFirst();
//
//    }
//
//    @Test
//    /* check if null is return when removing from an empty LinkedListDeque. */
//    public void emptyNullReturnTest() {
//
////        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//
//        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
//
//        boolean passed1 = false;
//        boolean passed2 = false;
//        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
//        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
//
//
//    }
//
//    @Test
//    /* Add large number of elements to deque; check if order is correct. */
//    public void bigLLDequeTest() {
//
////        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//
//        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
//        for (int i = 0; i < 1000000; i++) {
//            lld1.addLast(i);
//        }
//
//        for (double i = 0; i < 500000; i++) {
//            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
//        }
//
//        for (double i = 999999; i > 500000; i--) {
//            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
//        }
//
//
//    }
//}
