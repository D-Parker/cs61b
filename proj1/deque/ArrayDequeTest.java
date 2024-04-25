package deque;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
public class ArrayDequeTest {


    @Test
    /**
     Tests construction of ArrayDeque
     */

    public void basicDequeTest() {
        ArrayDeque<String> A = new ArrayDeque<>();
        assertNotNull(A);
        A.addFirst("w");
        assertEquals("w", A.get(0));
        A.addLast("w");
        A.addLast("w");
        A.addLast("w");
        A.addLast("w");
        A.addLast("w");
        A.addLast("w");
        A.addLast("w");
        A.addLast("w");
        assertEquals(A.size(), 9);
        A.addFirst("f");
        assertEquals(A.size(), 10);
        assertEquals(A.get(0), "f");
        assertEquals(A.get(1), "w");

        System.out.print(A.removeFirst() );
        A.addFirst("f");
        System.out.print(A.removeFirst() );

    }

    @Test
    public void removeTest() {

        ArrayDeque<Integer> A = new ArrayDeque<>();

        assertTrue(A.isEmpty());

        assertNotNull(A);
        A.addFirst(1);

        assertFalse(A.isEmpty());
        int c = A.get(0);
        assertEquals(1, c);
        A.addLast(2);
        A.addLast(3);
        A.addLast(7);

        assertEquals(A.size(), 4);

        c = A.removeFirst();
        System.out.println(c);
        assertEquals(c, 1);
        assertEquals(A.size(), 3);



        A.addLast(54);

        System.out.println(A.size());

        c = A.removeLast();
        System.out.println(c);
//        assertEquals(c, 54);
//        assertEquals(A.size(), 3);
    }

    @Test
    public void addFirstTest() {


        ArrayDeque<Integer> A = new ArrayDeque<>();


        A.addLast(5);
        A.addFirst(1);
        A.addFirst(2);
        A.addFirst(3);

        System.out.println(A.get(0));
        System.out.println(A.get(1));
        System.out.println(A.get(2));

    }

}