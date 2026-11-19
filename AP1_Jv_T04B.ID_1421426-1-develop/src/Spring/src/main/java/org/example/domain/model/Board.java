package org.example.domain.model;

public class Board {
    public static final int EMPTY = 0;
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = -1;

    private final int[][] grid;
    private final int size = 3;

    public Board() {
        this.grid = new int[size][size];
    }

    public Board(int[][] grid) {
        if (grid.length != size || grid[0].length != size) {
            throw new IllegalArgumentException("Board must be 3x3");
        }
        this.grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, size);
        }
    }

    public int[][] getGrid() {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    public int getCell(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        return grid[row][col];
    }

    public void setCell(int row, int col, int value) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        this.grid[row][col] = value;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}