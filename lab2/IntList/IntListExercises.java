package IntList;

public class IntListExercises {

    /**
     * Part A: (Buggy) mutative method that adds a constant C to each
     * element of an IntList
     *
     * @param lst IntList from Lecture
     */
    public static void addConstant(IntList lst, int c) {
        IntList head = lst;

//        if (head.rest == null) {
//            head.first += c;
//            return;
//        }
//
//        head.first += c;
////        head = head.rest;
//
//        addConstant(head.rest, c);

        while (head != null) {
            head.first += c;
            head = head.rest;
        }
//
//        while (head.rest != null) {
//            head.first += c;
//            head = head.rest;
//        }

//        head.first += c;

    }

    /**
     * Part B: Buggy method that sets node.first to zero if
     * the max value in the list starting at node has the same
     * first and last digit, for every node in L
     *
     * @param L IntList from Lecture
     */
    public static void setToZeroIfMaxFEL(IntList L) {
        IntList p = L;
        while (p != null) {
            if (firstDigitEqualsLastDigit(max(p))) {
                p.first = 0;
            }
            p = p.rest;
        }

//        while (p != null) {
//
//            int currentMax = max(p);
//            boolean firstEqualsLast = firstDigitEqualsLastDigit(currentMax);
//            if (firstEqualsLast) {
//                p.first = 0;
//            }
//        }
    }

    /** Returns the max value in the IntList starting at L. */
    public static int max(IntList L) {
        int max = L.first;
        IntList p = L.rest;
        while (p != null) {
            if (p.first > max) {
                max = p.first;
            }
            p = p.rest;
        }
        return max;
    }

    /** Returns true if the last digit of x is equal to
     *  the first digit of x.
     */
    public static boolean firstDigitEqualsLastDigit(int x) {
        int lastDigit = x % 10;
        while (x >= 10) {
            x = x / 10;
        }
        int firstDigit = x % 10;
        return firstDigit == lastDigit;
    }

    /**
     * Part C: (Buggy) mutative method that squares each prime
     * element of the IntList.
     *
     * @param lst IntList from Lecture
     * @return True if there was an update to the list
     */


//    private static int size(IntList lst) {
//        if (lst.next == null) {
//            return 1;
//        }
//
//        return 1 + size(p.next);
//    }

//    private static boolean changed=false;
//    private static boolean changed=false;
//
//    public static boolean changed() {
//        return changed;
//    }
//    public static boolean squarePrimes(IntList lst) {
//        // Base Case: we have reached the end of the list
//
//        if (lst == null) {
////            return false;
//            return changed();
//        }
//
//        boolean currElemIsPrime = Primes.isPrime(lst.first);
//
//        if (currElemIsPrime) {
//            lst.first *= lst.first;
//            changed = true;
//        }
//
//        boolean changed = false;
//
//        return squarePrimes(lst.rest);
////        return currElemIsPrime || squarePrimes(lst.rest);
//    }

    public static boolean squarePrimes(IntList lst) {
        // Base Case: we have reached the end of the list
        if (lst == null) {
            return false;
        }

        boolean currElemIsPrime = Primes.isPrime(lst.first);

        if (currElemIsPrime) {
            lst.first *= lst.first;
        }

        return squarePrimes(lst.rest) || currElemIsPrime;
    }
}
