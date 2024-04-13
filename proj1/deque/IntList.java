package deque;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

//    recursive version
    public int size() {
        if (rest == null) {
           return 1;
        }
        return 1 + this.rest.size();
    }

    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    public int get(int x) {

        IntList p = this;
        int i = 0;

        while (i < x) {
            p = p.rest;
            i += 1;
        }

        return p.first;


    }


    public static void main(String[] args) {

//        IntList L = new IntList(5, null);
//        L.rest = new IntList(10, null);
//        L.rest.rest = new IntList(15, null);
//        L.rest.rest.rest = new IntList(173, null);

        IntList L = new IntList(15,null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        int b = L.size();

        int c = L.iterativeSize();

        int d = L.get(0);
        int e = L.get(1);
        int f = L.get(2);

    }

}