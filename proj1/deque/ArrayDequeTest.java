package deque;

import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Optional;
//

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
    public void addLastTest2() {
        ArrayDeque<String> A = new ArrayDeque<>();

        A.addLast("a");
        A.addLast("b");
        A.addLast("c");
        A.addFirst("g");
        A.addFirst("f");
        A.printDeque();
    }



    @Test
    public void addTest2(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        assertTrue(ad1.isEmpty());

        ad1.addLast(3);
        assertFalse(ad1.isEmpty());

        ad1.addFirst(4);
        assertFalse(ad1.isEmpty());
        assertEquals(2, ad1.size());

        ad1.printDeque();
    }


    @Test
    public void addRemoveTest2(){
        ArrayDeque<String> ad1 = new ArrayDeque<String>();
        assertTrue(ad1.isEmpty());

        ad1.addFirst("is");
        ad1.addFirst("this");
        ad1.addLast("a");
        ad1.addLast("dog");

        assertEquals(4, ad1.size());
        ad1.printDeque();

        assertEquals("dog", ad1.removeLast());
        assertEquals("this", ad1.removeFirst());

        assertEquals(2, ad1.size());

        ad1.removeLast();
        ad1.removeLast();

        assertNull(ad1.removeLast());
        assertNull(ad1.removeFirst());
    }

    @Test
    public void getTest2(){
        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        ad1.addLast("this");
        ad1.addLast("is");
        ad1.addLast("a");
        ad1.addLast("dog");

        assertEquals("this", ad1.get(0));
        assertEquals("dog", ad1.get(3));

        assertNull(ad1.get(4));
    }

    @Test
    public void resizeTest2(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();

        int targetSize = 1000;
        for (int i = 0; i < targetSize; i++){
            ad1.addLast(i);
        }
        for (int i = 0; i < targetSize; i++){
            ad2.addFirst(i);
        }
        assertEquals(targetSize, ad1.size());
        assertEquals(targetSize, ad2.size());

        int getItem1 = ad1.get(78);
        assertEquals(78, getItem1);

        int getItem2 = ad2.get(0);
        assertEquals(targetSize - 1, getItem2);

        assertNull(ad1.get(targetSize));

    }

    @Test
    public void iteratorTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++){
            ad1.addLast(i);
        }

        Iterator<Integer> iter = ad1.iterator();
        int exp = 0;
        while (iter.hasNext()){
            int act = iter.next();
            assertEquals(exp, act);
            exp += 1;
        }
    }

    @Test
    public void equalTest2(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();

        int targetSize = 1000;
        for (int i = 0; i < targetSize; i++){
            ad1.addLast(i);
            ad2.addLast(i);
        }
        assertTrue(ad1.equals(ad2));

        assertFalse(ad1.equals(30));

        ArrayDeque<String> ad3 = new ArrayDeque<>();
        ad3.addLast("aaa");
        assertFalse(ad1.equals(ad3));
    }

    @Test
    public void randomTest2(){
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();

        int N = 50000;
        for (int i = 0 ; i < N; i++){
            int operationNumber = StdRandom.uniform(0, 6);

            switch (operationNumber){
                case 0:
                    // addLast the same value to both lists
                    int randVal1 = StdRandom.uniform(0, 1000);
                    ad.addLast(randVal1);
                    lld.addLast(randVal1);

                case 1:
                    // addFirst the same value to both lists
                    int randVal2 = StdRandom.uniform(0, 100);
                    ad.addFirst(randVal2);
                    lld.addFirst(randVal2);

                case 2:
                    // assert if size are equal
                    assertEquals(lld.size(), ad.size());

                case 3:
                    // assert if get random index value is equal
                    if (ad.size() > 0 && lld.size() > 0) {
                        int randIndex = StdRandom.uniform(0, ad.size());
                        assertEquals(lld.get(randIndex), ad.get(randIndex));
                    }

                case 4:
                    // assert if removeLast is equal
                    assertEquals(lld.removeLast(), ad.removeLast());

                case 5:
                    // assert if removeFirst is equal
                    assertEquals(lld.removeFirst(), ad.removeFirst());
            }
        }
    }

    @Test
    public void equalsTest3(){

        ArrayDeque<String> ad1 = new ArrayDeque<>();

        ad1.addFirst("z");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        lld1.addFirst("z");

        assertTrue(ad1.equals(lld1));
        assertTrue(lld1.equals(ad1));

    }

    @Test
    public void nextTest3(){

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        Iterator<Integer> iter = ad1.iterator();

        assertFalse(iter.hasNext());

        int targetSize = 10;
        for (int i = 0; i < targetSize; i++){
            ad1.addLast(i);
        }

        assertTrue(iter.hasNext());

        int g;
        for (int i = 0; i < targetSize; i++){
            g = iter.next();
        }

        assertFalse(iter.hasNext());


        for (int i = 0; i < targetSize; i++){
            ad1.addLast(i);
        }

        Iterator<Integer> iter2 = ad1.iterator();

        int k;
        for (int i = 0; i < targetSize; i++){
            k=iter2.next();
            assertEquals(i,k);
        }

        for (int i = 0; i < targetSize; i++){
            k=iter2.next();
            assertEquals(i,k);
        }


//        assertTrue(iter.hasNext()==false);

//        int exp = 0;
//        while (iter.hasNext()){
//            int act = iter.next();
//            assertEquals(exp, act);
//            exp += 1;
//        }



    }



}
