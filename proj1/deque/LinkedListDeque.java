package deque;
import java.util.Iterator;

public class LinkedListDeque<T> {

    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev = null;
        public T item;
        public IntNode next = null;

        public IntNode(T i) {
            item = i;
        }

        public T getRcursive(int index) {
            if (index == 0) {
                return item;
            } else {
                assert next != null;
                return next.getRcursive(index - 1);
            }
        }

    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return sentinel.next.getRcursive(index);
    }




    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int pos = 0;
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T res = get(pos);
            pos += 1;
            return res;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }


        return true;
    }


//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        return false;
//    }


    public LinkedListDeque() {
        sentinel = new IntNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

//    public LinkedListDeque(T i) {
//        size = 0;
//        sentinel = new IntNode(i);
//
//    }
//        sentinel.prev = sentinel
//        sentinel.next = sentinel



//    public LinkedListDeque(int x){
//        size = 0;
//        sentinel = new LinkedListDeque.IntNode(63, null,);
//
//        sentinel.next = new LinkedListDeque.IntNode(x, null);
//        size = 1;
//    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = other.sentinel;
        size = other.size;
    }


    public int size() {
        return size;

    }

    public void addFirst(T item) {
        size = size + 1;

        IntNode b = new IntNode(item);
        b.prev = sentinel;
        b.next = sentinel.next;

        sentinel.next.prev = b;
        sentinel.next = b;
    }
    public void addLast(T item) {
        size = size + 1;

        IntNode b = new IntNode(item);
        b.next = sentinel;
        b.prev = sentinel.prev;

        sentinel.prev.next = b;
        sentinel.prev = b;
    }

//        IntNode<T> b = new IntNode(null, item, null);

        // case of an empty list
//        if (sentinel.next == null) {
//            sentinel.prev = b;
//            sentinel.next = b;
//            b.next = sentinel;
//            b.prev = sentinel;
//
//            return;
//        }
//
//        sentinel.next.prev = b;
//
//        b.next = sentinel.next;
//        b.prev = sentinel;
//
//        sentinel.next = b;
//
//    }

//    public void addLast(int item) {
//        size = size + 1;
//        IntNode b = new IntNode(null, item, null);
//
//        // case of an empty list
//        if (sentinel.prev == null) {
//            sentinel.prev = b;
//            sentinel.next = b;
//            b.next = sentinel;
//            b.prev = sentinel;
//
//            return;
//
//        }
//
//        sentinel.prev.next = b;
//
//        b.next = sentinel;
//        b.prev = sentinel.prev;
//
//        sentinel.prev = b;
//    }

    public boolean isEmpty() {

        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {

        IntNode p = sentinel;

        if (p.next == sentinel) {
            return;
        }

        int counter = 0;

        while (counter < size) {
            System.out.print(p.next.item + " ");
//        System.out.println(" ");
            p = p.next;
            counter += 1;
        }
        System.out.println();
    }
//
//int i;
    public T removeFirst() {

        IntNode p = sentinel.next;
        if (size > 0) {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size = size - 1;
        }
        return p.item;
    }

    public T removeLast() {

        IntNode p = sentinel.prev;
        if (size > 0) {
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size = size - 1;
        }
        return p.item;
    }
//        int i = 0;
//        if (size!=0) {
//            i = sentinel.next.item;
//            sentinel.next = sentinel.next.next;
//            sentinel.next.prev = sentinel;
//            size = size - 1;
//        }

//}
//
//    int j;
//    public int removeLast() {
//
//        if (size!=0) {
//            j = sentinel.prev.item;
//            sentinel.prev = sentinel.prev.prev;
//            sentinel.prev.next = sentinel;
//            size = size - 1;
//
//        }
//        return j;
//    }
//
    public T get(int index){
        int counter = 0;
        IntNode p = sentinel.next;
        while (counter < index){
            p = p.next;
            counter += 1;
        }
        return p.item;
    }
//

//    IntNode p = sentinel.next;
//
//    int item = getRecursive(p, index).item;
//
//    }
//
//    private int getRecursive(IntNode p, int index) {
//
//        if (index == 0){
//            return p;
//        }
//
//        getRecursive(p.next,index-1);
//
//    }

    public static void main(String[] args) {


//        LinkedListDeque<int> L = new LinkedListDeque<>(5);

//        LinkedListDeque<String> L = new LinkedListDeque<>();
//        L.addFirst("ABC");
//        LinkedListDeque<Integer> K = new LinkedListDeque<>();
//        K.addFirst(123);
//        K.addFirst(456);
//        K.addFirst(789);
//
//        System.out.println();
//        System.out.println(K.get(0));
//        System.out.println(K.get(1));
//        System.out.println(K.get(2));
//        System.out.println();
//        System.out.println(K.getRecursive(0));
//        System.out.println(K.getRecursive(1));
//        System.out.println(K.getRecursive(2));
//        K.get(1);
//        K.get(2);


//        L.printDeque();
//        K.printDeque();
//        K.removeLast();
//        K.removeLast();
//        K.removeFirst();
//        K.removeFirst();
//        K.removeFirst();
//        System.out.println(K.get(0));

    }

}





//        LinkedListDeque L = new LinkedListDeque();



//        L.removeFirst();
//        L.removeLast();
//        boolean c=L.isEmpty();
//        int d = L.size();
//        L.addFirst(6);
//        L.removeFirst();
//        L.removeFirst();
//        c = L.isEmpty();
//        L.addLast(5);
//        L.addFirst(4);
//        L.addLast(3);
//        int k=L.get(0);
//        k = L.get(1);
//        k= L.get(2);
//        L.get(0);
//        L.get(1);
//        L.get(2);
//        L.removeLast();
//        d = L.size();


