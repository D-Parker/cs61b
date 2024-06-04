package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private IntNode sentinel;
    private int size;

    // constructs a deque
    public LinkedListDeque() {
        sentinel = new IntNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        size = 0;
    }

    // inserts an item at the front of the deque
    @Override
    public void addFirst(T item) {
        IntNode temp = new IntNode(item);

        temp.next = sentinel.next;
        temp.prev = sentinel;

        sentinel.next.prev = temp;
        sentinel.next = temp;

        size += 1;
    }

    // inserts an item at the back of the deque
    @Override
    public void addLast(T item) {

        IntNode temp = new IntNode(item);

        temp.prev = sentinel.prev;
        temp.next = sentinel;

        sentinel.prev.next = temp;
        sentinel.prev = temp;

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

        IntNode p = sentinel;

        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            IntNode temp = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size = size - 1;
            return temp.item;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            IntNode temp = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size = size - 1;
            return temp.item;
        }
    }

    @Override
    public T get(int index) {

        if (this.isEmpty()) {
            return null;
        }

        int temp = 0;
        IntNode p = sentinel.next;

        while (temp < index) {
            p = p.next;
            temp += 1;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (this.isEmpty()) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(IntNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int pos;

        public LinkedListDequeIterator() {
            pos = 0;
        }

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


// returns the first item in the deque
//    public T getFirst() {
//        return sentinel.next.item;
//    }

    // constructs an IntNode
    private class IntNode {
        private IntNode prev = null;
        private T item;
        private IntNode next = null;

        public IntNode(T i) {
            item = i;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o){
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
//        if (o == null) {
//            return false;
//        }
//
//        if (this == null) {
//            return false;
//        }
//
//        if (this == o) {
//            return true;
//        }
//
//        if ((o instanceof Deque) == false) {
//            return false;
//        }
//
//        if ((this instanceof Deque) == false) {
//            return false;
//        }
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

//        Iterator a = ((LinkedListDeque) o).iterator();
//        Iterator b = this.iterator();
//
//        while (b.hasNext() == true || a.hasNext() == true) {
//            if (a.next() != b.next()) {
//                return false;
//            }
//        }
//        return true;
    }


//    public static void main(String[] args) {
//
//
//
//
//    }
//


//    public static void main(String[] args) {
//
//        System.out.print("abc");
//
//        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
//        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
//
//        lld1.addFirst("gtg");
//        lld2.addFirst("gtg");
//
//        lld1.addFirst("abb");
//        lld2.addFirst("abb");
//        lld2.addFirst("awpkb");
//
//        boolean  q = lld1.equals(lld2) ;
//
//        System.out.println("test of equal instances: " + q);
//
//
//
//        lld1.addFirst("a");
//        lld1.addFirst("b");
//        lld1.addFirst("c");
//        lld1.addFirst("d");
//        lld1.addFirst("e");
//
//        Iterator<String> b = lld1.iterator();
//
//        System.out.println(b.next());
//        System.out.println(b.next());
//        System.out.println(b.next());
//        System.out.println(b.next());
//
//        System.out.println(b.hasNext());
//        System.out.println(b.hasNext());
//
//
//
//
//
//        lld1.iterator();
//
//        lld1.addFirst("gtg");
//        lld1.addFirst("xyz");
//
//        lld1.printDeque();
//
////        System.out.println(lld1.getFirst());
//
//    }


}


