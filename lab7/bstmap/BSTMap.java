package bstmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V> {

    private BSTNode root;
    private int size;

    public class BSTNode {
         K key;
         V value;

         BSTNode left;
         BSTNode right;

        public BSTNode(K k, V v){
            key = k;
            value = v;
//            left = null;
//            right = null;
        }
    }

public BSTMap() {
//        this.root = root;

}

    public void clear(){
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    /* DP: try returning a boolean for get()   */
    public boolean containsKey(K key) {

        BSTNode temp = get(root, key);

        if (temp == null) {
            return false;
        }

        return true;

//        if (key == null){
//            return false;
//        }
//        V temp = get(key);
//        if (temp == null){
//            return false;
//        }
//        return true;
//        return containsKey(root, key);
    }

//    private boolean containsKey(BSTNode T, K key){
//
//        if (T==null){
//            return false;
//        }
//
//        BSTNode p = T;
//        int cmp;
//
//        while (p != null) {
//            cmp = key.compareTo(T.key);
//            if (cmp == 0 ){
//                return true;
//            }
//            else if (cmp < 0){
//                containsKey(T.left, key);
//            }
//            else if (cmp > 0){
//                containsKey(T.right, key);
//            }
//        }
//        return false;
//        }


    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        BSTNode temp = get(root, key);

        if (temp == null){
            return null;
        }

        return temp.value;
    }

    private BSTNode get(BSTNode T, K k) {
        if (T == null) {
            return null;
        }
        if (k.compareTo(T.key) == 0) {
            return T;
        } else if (k.compareTo(T.key) < 0) {
            return get(T.left, k);
        } else if (k.compareTo(T.key) > 0) {
            return get(T.right, k);
        }
        return null;
    }


//        BSTNode p = T;
//        int cmp;
//
//        while (p != null) {
//            cmp = k.compareTo(T.key);
//
//            if (cmp == 0){
//                return T.value;
//            }
//            else if (cmp < 0){
//                get(T.left, k);
//            }
//            else if (cmp > 0){
//                get(T.right, k);
//            }
//        }
//        return null;
//    }


    /* Returns the number of key-value mappings in this map. */
    public int size(){

        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K k, V v) {
//        if (this.containsKey(k)) {
//            return;
//        }
        root = put(root, k, v);

    }

    private BSTNode put(BSTNode T, K key, V value) {

        if (T == null) {
            size += 1;
            return new BSTNode(key, value);
        }
        if (key.compareTo(T.key) < 0) {
            T.left = put(T.left, key, value);
        } else if (key.compareTo(T.key) > 0) {
            T.right = put(T.right, key, value);
        }

        return T;
    }









//    private BSTNode put(BSTNode T, K key, V value, BSTNode p){
//        if (T==null){
//            size += 1;
//
//T = p;
//            return p;
//        }
//        int cmp = key.compareTo(T.key);
//
//        if (cmp < 0){
//            size += 1;
//            put(T.left, key, value, T);
//        }
//        else if (cmp > 0){
//            size += 1;
//            put(T.right, key, value, T);
//        }
//        return T;
//    }



//
//    private BSTNode put(K k, BSTNode curr, BSTNode prev){
//        if (curr == null){
//            return prev;
//        }
//
//        int cmp = k.compareTo(curr.key);
//
//        if (cmp < 0){
//            put(k, curr.left, curr);
//        }
//        else if (cmp > 0){
//            put(k, curr.right, curr);
//        }
//        return curr;
//    }



    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
   public Set<K> keySet(){

        throw new UnsupportedOperationException("This operation is not supported.");

    }
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){

        throw new UnsupportedOperationException("This operation is not supported.");

    }
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {

        throw new UnsupportedOperationException("This operation is not supported.");

    }

    public Iterator<K> iterator(){
        throw new UnsupportedOperationException("This operation is not supported.");
    }


}
