package deque;

import java.lang.Math;
public class ArrayDeque<T> {


    private T[] items;
    private int size;

    private int front;
    private int back;

    public ArrayDeque() {

    items = (T []) new Object[8];
    size = 0;

    front = 0;
    back = 0;

    }

    private void resize(int cap) {

        T[] a = (T []) new Object[cap];
        System.arraycopy(items, 0, a, 0, size);

        items = a;
    }


// add item at front of deque
    public void addFirst(T item) {

        // if the deque is full
        if (size == items.length) {
            this.resize(Math.round( size * 2) );
        }

//        int x = item;

//        items[size] = item;

        if (front == 0) {
            items[size] = item;
            front = size-1;
        }
        else {
            items[size] = items[back];
            items[back] = item;
            front = back;
            back = size-1;
        }


        size += 1;




//        if (size == 0) {
//            items
//        }
//
//
//        int i = size ;
//
//        items[size] = item;
//
//        if (size > 0 ) {
//
//            front =
//        }
//
//
//
//
//        while (i > 0) {
//            items[i] = items[i-1];
//            i = i -1;
//        }
//
//        items[0] = item;
//        size += 1;

    }

    // adds item to the end of the deque
    public void addLast(T item) {

        // if the deque is full
        if (size == items.length) {
            this.resize(Math.round( size * 2) );
        }

        if (back == size-1) {
            items[size] = item;
            back = size -1;
        }

        if (back == 0) {
            items[size] = items[back];
            items[back] = item;
        }

        size += 1;
    }

    // returns item from deque at a given index
    public T get(int index) {

        int temp = offsetMap(index);
        return items[temp];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public T removeFirst() {

        if (size == 0) {
            return null;
        }

        if ( size < (int) Math.round(items.length/4) ) {
            this.resize(Math.round( size * 2) );
        }

        T returnItem = this.get(0);

        items[front] = null;

        if (front == 0) {
            items[front] = items[back];
            items[back] = null;
            front = front + 1;
        }

        size = size - 1;
        return returnItem;
    }

    public T removeLast() {

        if (size == 0) {
            return null;
        }

        if ( size < (int) Math.round(items.length/4) ) {
            this.resize(Math.round( size * 2) );
        }

        T returnItem = this.get(back);

        if (back == size -1) {
            items[back]=null;
            back = back - 1;
        }
        else {
         items[back] = items[size-1];
         items[size-1] = null;
        }

        size = size - 1;
        return returnItem;
    }

    private int offsetMap(int i) {

        int offset = size - front;

        if (i < offset) {
            return front + i;
        }
        else {
            return i - offset;
        }
    }

}


//add
//
//remove
//
//
//        get
//
//
//size
//
//                isEmpty