package hashmap;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;
import java.util.*;
/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V>, Iterable<K> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    // HashSet contains all the keys
    private Set<K> HashSet;

//    private Collection[] buckets;
//    private Collection<Node>[] backing_table;

    private int initialSize = 16;
    private double maxLoad = 0.75;
    private int tableSize;
    private int size;

    private Set<K> result;

    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;

        createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return null;
    }


    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    public Iterator<K> iterator(){
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {

        this.buckets = null;

//        throw new UnsupportedOperationException("This operation is not supported.");
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */

    // get the index
    // use index to get the node collection
    // return boolean contains(key) from the HashSet
    public boolean containsKey(K key) {
        return this.HashSet.contains(key);
    }

//        int index = getIndex(key);
//        return buckets[index].contains(key);



//        Iterator<Node> iterator = buckets.iterator();





//    public boolean containsKey(K key) {
//        int index = getIndex(key);
//
//        for (Node node : buckets[index]) {
//            if (node.key.equals(key)) {
//                return true;
//            }
//        }
//        return false;
//    }



    private int getIndex(K key) {
//        return getIndex(key, buckets);
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    private int getIndex(K key, Collection<Node>[] table) {

        throw new UnsupportedOperationException("This operation is not supported.");
//        int temp = key.hashCode();
//        return Math.floorMod(temp, tableSize);
    }


    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {

        throw new UnsupportedOperationException("This operation is not supported.");
        //        int index = getIndex(key);
//        Collection<Node> bucket = buckets[index];
//        Collection<Node> i;
//        Iterator<Node> iterator = bucket.iterator();
//
//        while (iterator.hasNext() ){
//            i = iterator.next();
//            if (i.key.equals(key)){
//               return i.value;
//            }
//        }
//
//
//        for (Node node : buckets[index]) {
//            if (node.key.equals(key)) {
//                return node.value;
//            }
//        }
//        return null;
    }


    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        throw new UnsupportedOperationException("This operation is not supported.");
//        return size;

    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {

        throw new UnsupportedOperationException("This operation is not supported.");

//        put(key, value, buckets);
    }

    private void put(K key, V value, Collection<Node>[] table) {

        throw new UnsupportedOperationException("This operation is not supported.");

//        int index = getIndex(key);
//        boolean ck = containsKey(key);
//
//        if (ck) {
//            for (Node node : table[index]) {
//                if (node.key.equals(key)) {
//                    node.value = value;
//                    return;
//                }
//            }
//        } else if (!ck) {
//            table[index].add(Node(key, value));
//            size += 1;
//        }

    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    public Set<K> keySet() {

        throw new UnsupportedOperationException("This operation is not supported.");

//        Set<K> result = new Set<K>();
//
//        for (int i = 0; i < buckets.length; i++){
//            for (Node node: buckets[i]){
//                result.add(node.key);
//            }
//        }
//        return result;

    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("This operation is not supported.");
    }


}
