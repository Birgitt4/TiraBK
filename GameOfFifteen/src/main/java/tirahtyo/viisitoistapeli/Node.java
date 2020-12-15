
package tirahtyo.viisitoistapeli;

/**
 * Node is a way to save important information of one state of the game.
 * 
 */
public class Node {
    
    private int[] route;
    private int[] grid;
    private int manhattan;
    private int linearrow;
    private int linearcol;
    private int gScore;
    private char direction;
    
    /**
     * constructor.
     * @param route route that tells what numbers have been moved since start to
     * this state
     * @param gamestate order of the tiles when this node is created
     * @param manhattan grids sum of manhattan distances
     * @param linearrow linearconflicts that occurs in rows
     * @param linearcol linearconflicts that occurs in columns
     * @param gScore how many moves have been taken
     * @param direction the direction of the blanks last move
     */
    public Node(int[] route, int[] gamestate, int manhattan, int linearrow, int linearcol, int gScore, char direction) {
        this.route = route;
        grid = gamestate;
        this.manhattan = manhattan;
        this.linearrow = linearrow;
        this.linearcol = linearcol;
        this.gScore = gScore;
        this.direction = direction;
    }
    public int getGScore() {
        return gScore;
    }
    public int getManhattan() {
        return manhattan;
    }
    public void setManhattan(int manhattan) {
        this.manhattan = manhattan;
    }
    public int getLinearrow() {
        return linearrow;
    }
    public void setLinearrow(int linearrow) {
        this.linearrow = linearrow;
    }
    public int getLinearcol() {
        return linearrow;
    }
    public void setLinearcol(int linearcol) {
        this.linearcol = linearcol;
    }
    public int getFScore() {
        return gScore + manhattan + linearrow + linearcol;
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
}
