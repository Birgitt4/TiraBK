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
    private char direction;
    
    public Node(char[] route, int[] gamestate, int hScore, int gScore, char direction) {
        this.route = route;
        grid = gamestate;
        this.hScore = hScore;
        this.gScore = gScore;
        this.direction = direction;
        
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
    public char getDirection() {
        return direction;
    }
    
    public int compareTo(Node b) {
        if ((this.hScore + this.gScore) == (b.hScore+b.gScore)) {
            return b.gScore-this.gScore;
        }
        return (this.hScore + this.gScore) - (b.hScore+b.gScore);
    }
    public String toString() {
        String state = "";
        for (int i=0; i<16; i++) {
            state += grid[i] +",";
        }
        return "grid"+state+"gScore"+gScore;
    }
}
