package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF union;
    boolean[] open;
    int size;
    int top;
    int bottom;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        size = N;
        union = new WeightedQuickUnionUF(size * size + 2);
        top = size * size + 1;
        bottom = top + 1;
        open = new boolean[size * size];
        for(int i = 0; i < size; i += 1){
            union.union(top, i);
            union.union(bottom, xyTo1D(i, size - 1));
        }
    }

    public int xyTo1D(int x, int y){
        return x + y * size;
    }

    public void open(int row, int col){
        int pos = xyTo1D(row, col);
        open[pos] = true;
        if(isOpen(row - 1, col)){
            union.union(xyTo1D(row - 1, col), pos);
        }
        if(isOpen(row + 1, col)){
            union.union(xyTo1D(row + 1, col), pos);
        }
        if(isOpen(row, col + 1)){
            union.union(xyTo1D(row, col + 1), pos);
        }
        if(isOpen(row, col - 1)){
            union.union(xyTo1D(row, col - 1), pos);
        }
    }

    public boolean isOpen(int row, int col){
        if(row >= size || col >= size){
            return false;
        }else{
            return open[xyTo1D(row, col)];
        }
    }

    public boolean isFull(int row, int col){
        return union.connected(top, xyTo1D(row, col));
    }

    public int numberOfOpenSites(){
        int returnInt = 0;
        for(int x = 0; x < size; x += 1){
            for(int y = 0; y < size; y += 1){
                if(isOpen(x, y)){
                    returnInt += 1;
                }
            }
        }
        return returnInt;
    }
}
