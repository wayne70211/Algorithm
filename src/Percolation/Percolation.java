package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final boolean[] grid;
    private final WeightedQuickUnionUF unionUF;
    private final WeightedQuickUnionUF backwashUF;
    private final int size;
    private int numberOfOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n<=0){
            throw new IllegalArgumentException();
        }
        size = n;
        grid = new boolean[n*n + 2];
        grid[0] = true;
        grid[n*n+1] = true;
        unionUF = new WeightedQuickUnionUF(n*n+2);
        backwashUF = new WeightedQuickUnionUF(n*n+1);

    }

    // Transform the coordinate
    private int xyTo1D(int row, int col){
        validate(row,col);
        return (row-1)*size+col;
    }

    private void union(int pRow, int pCol, int qRow, int qCol){
        if (qRow > 0 && qCol > 0 && qRow <= size && qCol <= size && grid[xyTo1D(qRow,qCol)]) {
            unionUF.union(xyTo1D(pRow,pCol),xyTo1D(qRow,qCol));
            backwashUF.union(xyTo1D(pRow,pCol),xyTo1D(qRow,qCol));
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validate(row, col);
        if (!isOpen(row,col)){
            int index = xyTo1D(row,col);
            grid[index] = true;
            numberOfOpenSites += 1;

            if (row == 1){
                unionUF.union(index,0);
                backwashUF.union(index,0);
            }
            if (row == size){
                unionUF.union(index,size*size+1);
            }
            union(row,col,row-1,col);
            union(row,col,row+1,col);
            union(row,col,row,col-1);
            union(row,col,row,col+1);

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validate(row, col);
        return grid[xyTo1D(row,col)];
    }

    private void validate(int row, int col) {
        if (row <= 0 || col <= 0 || row > size || col > size) {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row, col);
        return backwashUF.connected(0,xyTo1D(row,col));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return unionUF.connected(0,size*size+1);
    }

    // test client (optional)
    public static void main(String[] args){
//        int time = 100;
//        int temp = 0;
//        int size = 20;
//        for (int i=0;i<time;i++) {
//            Percolation.Percolation percolation = new Percolation.Percolation(size);
//            while (!percolation.percolates()) {
//                int p = StdRandom.uniform(size) + 1;
//                int q = StdRandom.uniform(size) + 1;
//                percolation.open(p, q);
//                //System.out.println(p + "," + q);
//            }
//            //System.out.println(percolation.numberOfOpenSites);
//            temp +=percolation.numberOfOpenSites;
//            //System.out.println((float) percolation.numberOfOpenSites/(size*size));
//        }
//        System.out.println((float) temp/(size*size*time));

    }
}
