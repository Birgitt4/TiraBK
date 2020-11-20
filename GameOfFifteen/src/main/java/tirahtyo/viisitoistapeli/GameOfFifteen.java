/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

/**
 * This class represents 15-puzzle game board.
 * 
 */
public class GameOfFifteen {
    private int[] grid;
    private int blank;
    
    public GameOfFifteen() {
        grid = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        blank = 15;
        
    }
    public int[] getGrid() {
        return grid;
    }

    public void setGrid(int[] grid) {
        this.grid = grid;
        blank = findBlank();
    }

    public int findBlank() {
        for (int i=0; i<16; i++) {
            if (grid[i] == 0) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Suffle using Fisher-Yates shuffle algorithm.
     */
    public void suffle() {
        for (int i=15;i>0;i--) {
            int rnd = -1;
            while (rnd < 0) {
                rnd = (int)(System.nanoTime()) % i;
            }
            int a = grid[rnd];
            grid[rnd] = grid[i];
            grid[i] = a;
        }
        blank = findBlank();
    }
    
    /**
     * Counts the grids inversions
     * @return returns number of inversions
     */
    public int inversions() {
        int inversions = 0;
        for (int i=0;i<15;i++) {
            int a = grid[i];
            if (a!=0) {
                for (int j=i+1;j<16;j++) {
                    int b = grid[j];
                    if (b!=0 && a>b) {
                        inversions++;
                    }
                }
            }
        }
        return inversions;
    }
    /**
     * checks if the order of the game board is solvable
     * @return true if solvable, false if not
     */
    public boolean isSolvable() {
        int inversions = inversions();
        int blankrow = blank/4;
        if ((inversions % 2 == 1) && (blankrow % 2 == 0)) {
            return true;
        }
        if (inversions % 2 == 0 && blankrow % 2 == 1) {
            return true;
        }        
        return false;
    }
    
    /**
     * Checks if grid is in the right order for 15 puzzle to be solved
     * Numbers 1 to 15 and then zero (representing the blank space)
     * @return true if solved and false if not
     */
    public boolean isSolved() {
        for (int i=0; i<15; i++) {
            if (grid[i] != i+1) {
                return false;
            }
        }
        return true;
    }
    
    
    public boolean left() {
        return blank % 4 != 0;
    }
    public boolean right() {
        return blank % 4 != 3;
    }
    public boolean down() {
        return blank / 4 != 3;
    }
    public boolean up() {
        return blank / 4 != 0;
    }
    
    public void goRight() {
        if (right()) {
            int tile = grid[blank+1];
            grid[blank] = tile;
            grid[blank+1] = 0;
            blank += 1;
        }
    }
    public void goLeft() {
        if (left()) {
            int tile = grid[blank-1];
            grid[blank] = tile;
            grid[blank-1] = 0;
            blank -= 1;
        }
    }
    public void goDown() {
        if (down()) {
            int tile = this.grid[blank+4];
            this.grid[blank] = tile;
            this.grid[blank+4] = 0;
            blank += 4;
        }
    }
    public void goUp() {
        if (up()) {
            int tile = this.grid[blank-4];
            this.grid[blank] = tile;
            this.grid[blank-4] = 0;
            blank -= 4;
        }
    }
    
}
