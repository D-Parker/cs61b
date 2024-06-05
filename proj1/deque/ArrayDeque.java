package deque;

import java.util.Iterator;

import java.lang.Math;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {


    private T[] items;
    private int size;

    private int first;
    private int last;

    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {

        items = (T[]) new Object[8];
        size = 0;

//    first = ;
//    last = ;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int cap) {

        T[] a = (T[]) new Object[cap];
//        System.arraycopy(items, 0, a, 0, size);

        int i;

        int j = 0;

        for (i = first; i < items.length; i++) {
            if (items[i] != null) {
                a[j] = items[i];
                j++;
            }
        }
        for (i = 0; i < first; i++) {
            if (items[i] != null) {
                a[j] = items[i];
                j++;
            }
        }

        items = a;

        first = 0;
        last = size - 1;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    // add item at front of deque
    @Override
    public void addFirst(T item) {

        // if the deque is full
        if (size == items.length) {
            this.resize(Math.round(size * 2));
        }

        // if the deque is empty

        if (this.isEmpty()) {

            items[nextFirst] = item;
            first = nextFirst;
            last = nextFirst;
        } else {
            items[nextFirst] = item;
            first = nextFirst;
        }

        nextFirst = nextFirst - 1;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {

        // if the deque is full
        if (size == items.length) {
            this.resize(Math.round(size * 2));
        }

        if (this.isEmpty()) {

            items[nextLast] = item;
            first = nextLast;
            last = nextLast;
        } else {
            items[nextLast] = item;
            last = nextLast;
        }

        nextLast = nextLast + 1;
        if (nextLast > items.length - 1) {
            nextLast = 0;
        }
        size += 1;
    }

    //    public boolean isEmpty() {
//        if (size == 0) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
    @Override
    public int size() {

        return size;
    }

    @Override
    public void printDeque() {
        int i;
        for (i = first; i < items.length; i++) {
            if (items[i] != null) {
                System.out.print(items[i] + " ");
            }
        }

        for (i = 0; i < first; i++) {
            if (items[i] != null) {
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {

        if (this.isEmpty()) {
            return null;
        }

        T returnItem = items[first];

        items[first] = null;

        nextFirst = first;
        first = first + 1;
        if (first > items.length - 1) {
            first = 0;
        }
        size = size - 1;


        if (this.size() <= items.length / 4 && items.length >= 16) {
            this.resize(size * 2);
        }


        return returnItem;
    }

    @Override
    public T removeLast() {

        if (this.isEmpty()) {
            return null;
        }

        T returnItem = items[last];

        items[last] = null;

        nextLast = last;
        last = last - 1;

        if (last < 0) {
            last = items.length - 1;
        }
        size = size - 1;

        if (this.size() <= items.length / 4 && items.length >= 16) {
            this.resize(size * 2);
        }
        return returnItem;
    }


    //     returns item from deque at a given index. temp is the offset from the first item in an increasing direction, which recycles at 0.
    @Override
    public T get(int index) {

        int temp = index + first;
        if (temp > items.length - 1) {
            temp = temp - (items.length);
        }
        return items[temp];

    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int pos;

        public ArrayDequeIterator() {
            pos = 0;
        }

//        @Override
//        public boolean hasNext() {
//            return pos < last;
//        }
//
//        @Override
//        public T next() {
//
//            T returnItem = get(pos);
//
//            pos += 1;
////            if (pos > items.length - 1) {
////                pos = 0;
////            }
//            return returnItem;
//        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos = pos + 1;
            return returnItem;
        }





    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque<T> target = (Deque<T>) o;
            if (target.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!target.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

//    public boolean equals(Object o) {
//
//        if ((o instanceof Deque) == false) {
//            return false;
//        }
////        Iterator a = ((ArrayDeque) o).iterator();
//
//        Iterator a = ((Deque) o).iterator();
//        Iterator b = this.iterator();
//
//
//        Deque<T> other = (Deque<T>) o;
//        if (this.size() != other.size()) {
//            return false;
//        }
//
//        for (int i = 0; i < size(); i++) {
//            if (!this.get(i).equals(other.get(i))) {
//                return false;
//            }
//        }
//
//        return true;




//        while (b.hasNext() == true || a.hasNext() == true) {
//
////            System.out.print(a.next());
////            System.out.print(b.next());
////            System.out.println();
//
//            if (a.next() != b.next()) {
//                return false;
//            }
//        }
//        return true;
    }

//    public static void main(String[] args) {
//        ArrayDeque<String> lld1 = new ArrayDeque<>();
//
//
//        lld1.addLast("a");
//        lld1.addLast("b");
//        lld1.addLast("c");
//
////        Iterator<String> b = lld1.iterator();
//
////        System.out.println(b.next() );
////        System.out.println(b.next() );
////        System.out.println(b.next() );
//
//        ArrayDeque<String> lld2 = new ArrayDeque<>();
//
//        lld2.addLast("a");
//        lld2.addLast("b");
//        lld2.addLast("c");
////        lld2.addLast("d");
////        lld2.addLast("e");
//
////        Iterator<String> a = lld1.iterator();
//
//        System.out.println(lld1.equals(lld2)) ;
//
//
//
//    }



