package bstmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K,V> {

    private final Comparator<K> comparator;
    private class BSTNode {
        public K key;
        public V value;
//        public BSTNode parent;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(K k, V v){
            key = k;
            value = v;
//            parent = null;
            left = null;
            right = null;
        }
    }

    private BSTNode root;
    private int size;

    // BSTMap constructor
public BSTMap(Comparator<K> c) {

    super();
    comparator = c;

    root = new BSTNode(null, null);
    size = 0;
}

    public void clear(){
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return !(get(key)==null);
        }


    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return get(key, root);
    }

    private V get(K k, BSTNode T){
        if (T==null){
            return null;
        }
        if (k == T.key){
            return T.value;
        }
        else if (k < T.key){
            get(k, T.left);
        }
        else if (k > T.key){
            get(k, T.right);
        }
    }


    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K k, V v) {

        if (this.containsKey(k)==true){
            return;
        }

        BSTNode parent = put(k, root, null);
        BSTNode child = new BSTNode(k, v);

        if (parent==null){
            root = child;
            size +=1;
            return;
        }
        if (child.key < parent.key) {
            parent.left = child;
            size +=1;
        }
        else if (child.key > parent.key) {
            parent.right = child;
            size +=1;
        }
    }

    private BSTNode put(K k, BSTNode curr, BSTNode prev){
        if (curr == null){
            return prev;
        }
        if (k < curr.key){
            put(k, curr.left, curr);
        }
        else if (k > curr.key){
            put(k, curr.right, curr);
        }
        return curr;
    }



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
