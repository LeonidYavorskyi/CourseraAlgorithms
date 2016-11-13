package part1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int TOP_ROW = 1;

    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final int[] states;
    private final int gridSideSize;
    private final int topPosition;
    private final int bottomPosition;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid side size must be greater than 0");
        }

        gridSideSize = n;
        final int size = n * n + 2;
        topPosition = size - 1;
        bottomPosition = size - 2;
        weightedQuickUnionUF = new WeightedQuickUnionUF(size);
        states = new int[size];

        for (int state : states) {
            states[state] = BLOCKED;
        }

    }

    // open site (row, col) if it is not open already
    public void open(final int row, final int col) {
        if (!isOpen(row, col)) {
            final int position = countPosition(row, col);
            states[position] = OPEN;

            if (isTop(row)) {
                weightedQuickUnionUF.union(topPosition, position);
            } else if (isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(position, countPosition(row - 1, col));
            }

            if (hasRightElement(col) && isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(position, countPosition(row, col + 1));
            }

            if (hasLeftElement(col) && isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(position, countPosition(row, col - 1));
            }

            if (isBottom(row)) {
                weightedQuickUnionUF.union(bottomPosition, position);
            } else if (isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(position, countPosition(row + 1, col));
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(final int row, final int col) {
        return states[countPosition(row, col)] == OPEN;
    }

    // is site (row, col) full?
    public boolean isFull(final int row, final int col) {
        return weightedQuickUnionUF.connected(countPosition(row, col), topPosition);
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(topPosition, bottomPosition);
    }

    private boolean isTop(final int row) {
        return row == TOP_ROW;
    }

    private boolean hasRightElement(final int col) {
        return col < gridSideSize;
    }

    private boolean hasLeftElement(final int col) {
        return col > 1;
    }

    private boolean isBottom(final int row) {
        return row == gridSideSize;
    }

    private int countPosition(int row, int col) {
        return (--row * gridSideSize + --col);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
