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
public class MyHashMap<K, V> implements Map61B<K, V> {


//    Iterable<K>
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
    private Set<K> HashSet = new HashSet<>();

//    private Collection[] buckets;
//    private Collection<Node>[] backing_table;

    private int initialSize = 16;
    private double loadFactor = 0.75;
//    private int tableSize;
//    private int size;

//    private Set<K> result;

    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        this.buckets = createTable(this.initialSize);

    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.buckets = createTable(this.initialSize);
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
        this.loadFactor = maxLoad;

        this.buckets = createTable(this.initialSize);
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

//        return new Collection<>();

        return new ArrayList<>();
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

// ???
    public Iterator<K> iterator() {
        return HashSet.iterator();
//        return new MyIterator();
    }


    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        this.buckets = null;
        HashSet.clear();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */

    // get the index
    // use index to get the node collection
    // return boolean contains(key) from the HashSet
    public boolean containsKey(K key) {
        if (this.HashSet == null) {
            return false;
        }
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
        return getHashCode(key, buckets);
    }

    private int getHashCode(K key, Collection<Node>[] table) {
        int temp = key.hashCode();
        return Math.floorMod(temp, table.length);
    }

    private void addKey(K key) {
        this.HashSet.add(key);
    }


    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        return getNode(key).value;
    }


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


    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        return HashSet.size();
//        throw new UnsupportedOperationException("This operation is not supported.");
//        return size;

    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */

    // Add key to HashSet
    // compute index from key hashcode
    // create node and add to buckets
    // overwrite value if there is already a key
    public void put(K key, V value) {
        put(key, value, this.buckets);
    }

    private Collection<Node> getBucket(int index, Collection<Node>[] table) {
        Collection<Node> bucket;
        if (table[index] == null) {
            bucket = createBucket();
            table[index] = bucket;
        } else {
            bucket = table[index];
        }
        return bucket;
    }

    private void put(K key, V value, Collection<Node>[] table) {
        // resize if table is full

        if (isTableFull(table)) {
            table = resizeMap(table);
        }

        // get hashcode for this key and table
        int index;
        index = getHashCode(key, table);

        // get or create bucket for the key's hash
        Collection<Node> current_bucket;
        current_bucket = getBucket(index, table);

        // set current_node variable
        Node current_node;

        // check if key is in the hashmap using equals to compare
        // if yes, getNode(key) and updateNode(key, value)
        // if no, createNode and addNode -- current_bucket.add(createNode(key,value)

        // if there is no key in the hashmap
        if (!containsKey(key)) {
            // add key to HashSet
            HashSet.add(key);
            // add Node to the bucket

        }
        // check if there is a node in the bucket
        // getBucket already ensures this
        if (getNode(current_bucket, key) == null) {
            addNode(current_bucket, key, value);
        }


        // if there is a key, find its node in the bucket and update the node's value
        // Collection<Node>  getNode(key, bucket)
        // void updateNode(node, value)
        else {
            current_node = getNode(current_bucket, key);
            current_node.value = value;
        }
        return;
    }

    private Node getNode(Collection<Node> bucket, K key) {
        for (Node node : bucket) {
            if (key.equals(node.key)) {
                return node;
            }
        }
        return null;
    }

    private Node getNode(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int index = getHashCode(key, buckets);
        Collection<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }


    private void addNode(Collection<Node> bucket, K key, V value) {
        bucket.add(createNode(key, value));
    }


//    int index = getIndex(key);

//    updateMap(index, key, value)

    // create node and add to buckets
//    int key_hash = key.hashCode();

//    int temp = key.hashCode();
//                return Math.floorMod(key.hashCode(),tableSize);
//}
//                }

    private Collection<Node>[] resizeMap(Collection<Node>[] table) {

//        int new_size = this.buckets.length * 2;
        Collection<Node>[] new_table = createTable(table.length * 2);

        // iterate through buckets and node to populate the new_table
        // then point buckets at the new table

        Collection<Node> current_bucket;
        int new_hash;

        for (int i = 0; i < table.length; i++) {
            if (table[i]!= null) {
                current_bucket = table[i];
                for (Node node : current_bucket) {
                    put(node.key, node.value, new_table);
                }
            }
        }
        this.buckets = new_table;
        return this.buckets;
//        return new_table;
    }
//                new_hash = node.key.hashCode();
//
//                new_hash = getHashCode(key, new_table.length);

    // put(node.key, node.value) in the new table. It will create new buckets as needed


//
//
//        }

    private boolean isTableFull(Collection<Node>[] table) {
        double cl = currentLoad(table);

        if (cl >= this.loadFactor) {
            return true;
        }
        return false;
    }

    private double currentLoad(Collection<Node>[] table) {
        double result = (double) this.HashSet.size() / (double) table.length;
        return result;
    }


//private void addNode(Node node,int index){
//        this.buckets[index].add(node);
//        }


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
//
//    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    public Set<K> keySet() {
        return this.HashSet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {

        int index = getHashCode(key, this.buckets);
        Collection<Node> bucket = getBucket(index, this.buckets);
        Node node = getNode(key);
        V val = node.value;

        bucket.remove(node);
        HashSet.remove(key);
        return val;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {

        Node node = getNode(key);

        if (node.value.equals(value)){
            remove(key);
        }
        return null;
    }


}
