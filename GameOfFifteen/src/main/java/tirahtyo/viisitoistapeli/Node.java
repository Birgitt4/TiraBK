/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

/**
 *
 * @author birgi
 */
public class Node {
    
    private char[] route;
    private int[] grid;
    private int hScore;
    private int gScore;
    private int fScore;
    private char direction;
    private int tile;
    private int mones;
    
    public Node(char[] route, int[] gamestate, int hScore, int gScore, int tile, int mones) {
        this.route = route;
        grid = gamestate;
        this.hScore = hScore;
        this.gScore = gScore;
        this.tile = tile;
        fScore = hScore+gScore;
        this.mones = mones;
        
    }
    public int getfScore() {
        return fScore;
    }
    
    public int getGScore() {
        return gScore;
    }

    public char[] getRoute() {
        return route;
    }
    
    public int[] getGrid() {
        return grid;
    }
    
    
    public int compareTo(Node b) {
        if (this.fScore == b.fScore) {
            return b.mones-this.mones;
        }
        return this.fScore-b.fScore;
    }
    public String toString() {
        String state = "";
        for (int i=0; i<16; i++) {
            state += grid[i] +",";
        }
        return "grid"+state+"fscore"+fScore+"tile"+tile;
    }
}
