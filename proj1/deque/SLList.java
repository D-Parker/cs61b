package deque;

public class SLList {



    private IntNode sentinel;
    private IntNode last;
    private int size;


    public static class IntNode {
        public IntNode prev;
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    // declares the IntNode variable
    // private prevents methods outside this class from changing the IntNode
//    private IntNode first;



    // instantiates the SLList object
    public SLList() {
//        first = null;
        size = 0;
        sentinel = new IntNode(63, null);
    }
    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;

    }
//        first = new IntNode(x, null);
//        size = 1;


// adds an item to the front of the list
    public void addFirst(int x) {

        sentinel.next = new IntNode(x, sentinel.next);
        size = size + 1;
//        first = new IntNode(x, first);
//        size += 1;
    }


    // add an item to the end of the list

    public void addLast(int x) {

        last.next = new IntNode(x,null);
        last = last.next;

        IntNode p = sentinel;
        size += 1;

//        if (p==null) {
//            first = new IntNode(x, first);
////            p.next = new IntNode(x,null);
//            return;
//        }


        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x,null);


    }

    public int size() {
        return size;
    }

    // retrieve the front item from the list
    public int getFirst() {


        return sentinel.next.item;
    }

//    private static int size(IntNode p) {
//        if (p.next == null) {
//            return 1;
//        }
//        return 1 + size(p.next);
//    }
//
//    public int size() {
//        return size(first);
//    }


    public static void main(String[] args) {

//        IntList L1 = new IntList(5, null);
//        SLList L2 = new SLList();
//
//        L2.addFirst(3);
//
//        int z=L2.getFirst();

        SLList L = new SLList();
//        L.addFirst(10);

        L.addLast(29);
        L.addFirst(5);
        L.addLast(30);
        L.addFirst(6);
        int x = L.getFirst();

    }



}
