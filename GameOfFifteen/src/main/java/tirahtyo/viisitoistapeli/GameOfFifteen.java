/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

/**
 *
 * @author birgitta
 */
public class GameOfFifteen {
    private int[] grid;
    private int blank;
    
    public GameOfFifteen() {
        grid = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        blank = 15;
        
    }
    public void setGrid(int[] grid) {
        this.grid = grid;
        setBlank(findBlank());
    }
    public void setBlank(int place) {
        blank = place;
    }
    /**
     * finds the index of zero that is the blank space in the game
     * @return returns the index. If there's no zero returns 16.
     */
    public int findBlank() {
        for (int i=0; i<16;i++) {
            if (grid[i] == 0) {
                return i;
            }
        }
        return 16;
    }
    /**
     * Suffle using Fisher-Yates shuffle algorithm
     */
    public void suffle() {
        for (int i=15;i>0;i--) {
            int rnd = (int)(System.nanoTime()) % i;
            int a = grid[rnd];
            grid[rnd] = grid[i];
            grid[i] = a;
        }
        setBlank(findBlank());
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
        if (inversions%2==1 && blankrow%2==0) {
            return true;
        }
        else if (inversions%2==0 && blankrow%2==1) {
            return true;
        }        
        return false;
    }
    
    /**
     * Counts where value should be and where it is (index) and then counts
     * the manhattan distance between those two places
     * @param value some piece on the gameboard 
     * @param index values current place on the gameboard
     * @return manhattan distance between where value/piece is and where its
     * final place is
     */
    public int manhattanDistance(int value, int index) {
        int y = index/4;
        int x = index%4;
        int b = (value-1)/4;
        int a = (value-1)%4;
        int rows = y-b >=0 ? y-b : b-y;
        int columns = x-a>0 ? x-a : a-x;
        
        return rows+columns;
    }
    
    public int sumManhattanDistance() {
        int sum = 0;
        for (int i=0;i<16;i++) {
            if (grid[i] != 0) {
                sum += manhattanDistance(grid[i], i);
            }
        }
        return sum;
    }
    
}
