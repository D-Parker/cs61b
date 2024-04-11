public class SLList {

    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    private IntNode first;

    private int size;

    public SLList(int x) {
//        first = new IntNode(x,null);
        first = null;
        size = 0;


    }

    public void addFirst(int x) {

        first = new IntNode(x, first);
        size +=1;
    }

    public void addLast(int x) {

        if (first == null) {
            first=new IntNode(x, null);
            return;
        }
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);

    }

    private static int size(IntNode p) {
        if (p.next == null) {
            return 0;
        }
        return 1 + size(p.next);
    }

    /* note the public method doesn't have any arguments */
//    public int size() {
//        return size();
//    }




    public int getFirst() {
        return first.item;
    }

    public static void main(String[] args) {

        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);

        L.addLast(45);
        L.addLast(99);
//        L.size();
        int x = L.getFirst();

//        System.out.println(L.size());

    }


}
