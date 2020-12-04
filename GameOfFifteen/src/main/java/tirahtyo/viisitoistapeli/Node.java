/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

/**
 * Node is a way to save important information of one state of the game
 * 
 */
public class Node {
    
    private int[] route;
    private int[] grid;
    private int hScore;
    private int gScore;
    private char direction;
    
    /**
     * constructor.
     * @param route route that tells what numbers have been moved since start to
     * this state
     * @param gamestate order of the tiles when this node is created
     * @param hScore heuristic value
     * @param gScore how many moves have been taken
     * @param direction the direction of the blanks last move
     */
    public Node(int[] route, int[] gamestate, int hScore, int gScore, char direction) {
        this.route = route;
        grid = gamestate;
        this.hScore = hScore;
        this.gScore = gScore;
        this.direction = direction;
        
    }
    
    public int getGScore() {
        return gScore;
    }
    public int getHScore() {
        return hScore;
    }
    public int getFScore() {
        return gScore + hScore;
    }
    public void setHScore(int h) {
        this.hScore = h;
    }
    public void setGScore(int g) {
        this.gScore = g;
    }
    public int[] getRoute() {
        return route;
    }
    
    public int[] getGrid() {
        return grid;
    }
    public char getDirection() {
        return direction;
    }
    
//    /**
//     * this method compares the node which calls the method and a node given.
//     * The node that has smallest fScore is smaller but if both nodes has the
//     * same fScore then smaller node is the one with bigger gScore 
//     * @param b other node "a node given"
//     * @return negative int if this node has better heuristics, positive if node
//     * given has better heuristics.
//     */
//    public int compareTo(Node b) {
//        if ((this.hScore + this.gScore) == (b.hScore+b.gScore)) {
//            return b.gScore-this.gScore;
//        }
//        return (this.hScore + this.gScore) - (b.hScore+b.gScore);
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        Node o = (Node) other;
//        for (int i=0; i<16; i++) {
//            if (this.grid[i] != o.grid[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
}
