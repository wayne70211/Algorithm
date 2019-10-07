package Queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Node<Item> first;
    private Node<Item> last;
    private Node<Item> select;

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // construct an empty randomized queue
    public RandomizedQueue(){
        first = null;
        last = null;
        select = null;
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return first == null;
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
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        select = getRandom();
        Item item = select.next.item;
        select.next = select.next.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException();
        select = getRandom();
        return select.item;
    }

    private Node<Item> getRandom(){
        int randomInt = StdRandom.uniform(n);
        select = first;
        for (int i=0;i<randomInt-1;i++){
            select = select.next;
        }
        return select;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }


    // unit testing (required)
    public static void main(String[] args){
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < n; i++){
            queue.enqueue(i);
        }
//        StdOut.println(queue.dequeue());
//        StdOut.println(queue.dequeue());

        for (int a : queue) {
            for (int b : queue) {
                StdOut.print(a + "-" + b + " ");
            }
            StdOut.println();
        }

    }

}
