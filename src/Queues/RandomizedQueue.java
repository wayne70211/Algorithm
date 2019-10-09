package Queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private int randomIdx;
    private Item[] a;

    // construct an empty randomized queue
    public RandomizedQueue(){
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return n;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (n == a.length) {
            resize(2*a.length);
        }
        a[n++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        randomIdx = getRandomIdx();
        Item item = a[randomIdx];
        Item[] temp = (Item[]) new Object[a.length];
        for (int i = 0,k = 0; i < n; i++) {
            if (i == randomIdx) continue;
            temp[k++] = a[i];
        }
        a = temp;
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }

    private int getRandomIdx(){
        randomIdx = StdRandom.uniform(0,n);
        return randomIdx;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException();
        randomIdx = getRandomIdx();
        return a[randomIdx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedArrayIterator implements Iterator<Item> {
        private int i;
        private final int[] index;

        public RandomizedArrayIterator() {
            i = n-1;
            index = new int[n];
            for (int j=0;j<n;j++) {
                index [j] = j;
            }
            StdRandom.shuffle(index);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[index[i--]];
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < n; i++){
            queue.enqueue(i);
        }

        for (int a : queue) {
            for (int b : queue) {
                StdOut.print(a + "-" + b + " ");
            }
            StdOut.println();
        }
        StdOut.println(queue.dequeue());

    }

}

