
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private final double[] result;
    private final int size;
    private final int trials;
    private double mean;
    private double stddev;
    private static final double CONFIDENCE_95=1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n<=0 || trials<=0){
            throw new IllegalArgumentException();
        }
        result = new double[trials];
        size = n;
        this.trials = trials;

        for (int i=0;i<trials;i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int p = StdRandom.uniform(n) + 1;
                int q = StdRandom.uniform(n) + 1;
                percolation.open(p, q);
            }
            result[i] = (double) percolation.numberOfOpenSites()/(size*size);
        }
        mean = StdStats.mean(result);
        stddev = StdStats.stddev(result);
    }

    // sample mean of percolation threshold
    public double mean(){
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean-(CONFIDENCE_95*stddev/Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean+(CONFIDENCE_95*stddev/Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args){
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n,trials);
        StdOut.println("mean\t\t\t\t\t = "+ps.mean());
        StdOut.println("stddev\t\t\t\t\t = "+ps.stddev());
        StdOut.println("95% confidence interval\t = ["+ps.confidenceLo()+","+ps.confidenceHi()+"]");
    }

}
