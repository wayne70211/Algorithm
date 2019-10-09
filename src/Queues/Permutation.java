package Queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int n = 0;
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String context = StdIn.readString();
            queue.enqueue(context);
            n++;
        }

        if (k > n) throw new IllegalArgumentException();

        for (int i=0;i<k;i++) {
            StdOut.println(queue.dequeue());
        }
    }
}

