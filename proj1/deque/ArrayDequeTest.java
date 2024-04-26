package deque;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
public class ArrayDequeTest {


    @Test

    public void addFirstTest() {
        ArrayDeque<String> A = new ArrayDeque<>();
        assertNotNull(A);
        A.addLast("z");
        A.addLast("z");

        ArrayDeque<String> B = new ArrayDeque<>();
        assertNotNull(A);
        B.addLast("k");

        A.equals(B);

        assertEquals(A.get(0),"z");

        A.addFirst("a");
        A.addLast("z");

        A.addFirst("b");
        A.addFirst("c");
        A.printDeque();
        assertEquals(A.get(4),"z");
        assertEquals(A.get(2),"a");

    }
    @Test

    public void removeFirstTest() {
        ArrayDeque<String> A = new ArrayDeque<>();
        assertEquals(A.removeFirst(),null);

        A.addFirst("a");
        assertEquals(A.removeFirst(),"a");
        assertEquals(A.size(), 0);
        A.addFirst("b");

        A.printDeque();


        ArrayDeque<String> B = new ArrayDeque<>();
        B.addLast("a");
        assertEquals(B.removeFirst(),"a");
        assertEquals(B.size(), 0);
        B.addFirst("c");

        B.printDeque();
    }
@Test
    public void removeLastTest() {
        ArrayDeque<String> A = new ArrayDeque<>();
        assertEquals(A.removeLast(),null);

        A.addFirst("a");
        assertEquals(A.removeLast(),"a");
        assertEquals(A.size(), 0);

        A.addLast("b");
        assertEquals(A.removeLast(), "b");
        A.printDeque();
    }

    @Test
    public void resizeTest(){

        ArrayDeque<String> A = new ArrayDeque<>();
        A.addFirst("a");
        A.addFirst("b");
        A.addFirst("c");

        A.addFirst("d");
        A.addFirst("e");
        A.addFirst("f");

        A.addFirst("g");
        A.addFirst("h");
        A.addFirst("i");
        A.addFirst("j");

        assertEquals(A.removeLast(), "a");
        assertEquals(A.removeLast(), "b");
        assertEquals(A.removeLast(), "c");

        A.removeLast();
        A.removeLast();
        A.removeLast();
        A.removeLast();

        System.out.println(A.size());

        A.printDeque();
    }

    @Test
    public void addLastTest() {
        ArrayDeque<String> A = new ArrayDeque<>();

        A.addLast("a");
        A.addLast("b");
        A.addLast("c");
        A.addFirst("g");
        A.addFirst("f");
        A.printDeque();
    }
}
