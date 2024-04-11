public class IntList {

    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {

        first = f;
        rest = r;


    }

    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }


    public int iterativeSize(){

        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;

        }

        return totalSize;
        }

        public int new_size() {

        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.new_size();

        }

        public int get(int i) {

            IntList p = this;

            if (i == 0) {

                return p.first;

            }

            while (i > 0) {

                p = p.rest;
                i = i-1;

            }
            return p.first;



        }


    public static void main(String[] args) {

        IntList L = new IntList(5, null);
        L.rest = new IntList(10,null);
        L.rest.rest = new IntList(15,null);

        int kv = L.size();

        L.rest.rest.rest = new IntList(20,null);


        int nw = L.iterativeSize();

        int ns = L.new_size();

        int gt = L.get(3);

        System.out.println(kv);
        System.out.println(nw);
        System.out.println(gt);

    }



}
