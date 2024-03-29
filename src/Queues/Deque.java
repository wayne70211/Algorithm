package Queues;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    // construct an empty deque
    public Deque(){
        first = null;
        last  = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null || last == null;
    }

    // return the number of items on the deque
    public int size(){
        return n;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (oldFirst == null){
            last = first;
        } else {
            oldFirst.previous = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (oldLast == null)  {
            first = last;
        } else {
            last.previous = oldLast;
            oldLast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (first == null) {
            last = null;   // to avoid loitering
        } else {
            first.previous = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        n--;
        if (last == null) {
            first = null;   // to avoid loitering
        } else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
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

//        Deque<Integer> deque = new Deque<Integer>();
//        deque.addLast(1);
//        for (int s : deque) {
//            StdOut.print(s+ " ");
//        }
//        StdOut.println();
//        deque.addFirst(2);
//        for (int s : deque) {
//            StdOut.print(s+ " ");
//        }
//        StdOut.println();
//        deque.addFirst(3);
//        for (int s : deque) {
//            StdOut.print(s+ " ");
//        }
//        StdOut.println();
//        deque.addLast(4);
//        for (int s : deque) {
//            StdOut.print(s+ " ");
//        }
//        StdOut.println();
//        StdOut.println(deque.isEmpty());
//        StdOut.println(deque.removeLast());
//        for (int s : deque) {
//            StdOut.print(s+ " ");
//        }

    }

}
