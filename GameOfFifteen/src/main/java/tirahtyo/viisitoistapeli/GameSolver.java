
package tirahtyo.viisitoistapeli;

/**
 * Solver for 15-puzzle using IDA* and manhattan distance and linear conflict
 * as a heuristic.
 * 
 */
public class GameSolver {
    
    private GameOfFifteen game;
    private Node goalNode;
    private long nodesMade;
    
    public GameSolver(GameOfFifteen game) {
        this.game = game;
        goalNode = null;
        nodesMade = 0;
    }
    public long getNodesMade() {
        return nodesMade;
    }
    /**
     * Solves 15-puzzle.
     * @return returns list of moves to be made to solve the 15-puzzle
     */
    public int[] solver() {
        nodesMade = 0;
        int gScore = 0;
        int[] grid = game.getGrid();
        int treshold = sumManhattan(grid) + linearconflictrow(grid) + linearconflictcol(grid);
        Node starter = new Node(new int[1], grid.clone(), sumManhattan(grid), linearconflictrow(grid), linearconflictcol(grid), gScore, ' ');

        while (true) {
//            if (treshold > 65) {
//                return new int[]{0, 0}; 
//            }
            int temp = search(starter, treshold);
            if (temp == 0) {
                return goalNode.getRoute();
            }
            treshold = temp;
        }
    }
    
    /**
     * Recursive function to find the solution with fewest moves possible. 
     * @param node current node
     * @param treshold upper bound, if nodes min amount of moves are over treshold
     * we do not wish to continue to search for goal.
     * @return if goal was found return 0, else returns the smallest fScore above
     * treshold 
     */
    public int search(Node node, int treshold) {
        int f = node.getFScore();
        game.setGrid(node.getGrid());
        if (f > treshold) {
            return f;
        }
        if (node.getManhattan() == 0) {
            if (game.isSolved()) {
                goalNode = node;
                return 0;
            }
        }
        int minOverTres = 160;
        PriorityQueue nextNodes = possibleMoves(node);
        while (!nextNodes.isEmpty()) {
            Node next = nextNodes.poll();
            nodesMade++;
            int temp = search(next, treshold);
            if (temp == 0) {
                return 0;
            }
            if (temp < minOverTres) {
                minOverTres = temp;
            }
        }
        return minOverTres;
    }
    /**
     * Checks what moves are possible and creates nodes corresponding those moves.
     * @param node Current node from graph/tree
     * @return PriorityQueue, where nodes generated are in the most promising order
     */
    public PriorityQueue possibleMoves(Node node) {
        PriorityQueue nextNodes = new PriorityQueue();
        
        int blank = game.getBlank();
        game.setGrid(node.getGrid());
        int[] grid = game.getGrid();
        int gScore = node.getGScore();
        int[] route = new int[gScore + 1];
        for (int i = 0; i < gScore; i++) {
            route[i] = node.getRoute()[i];
        }
        char lastMove = node.getDirection();
        int manhattan = node.getManhattan();
        
        if (lastMove != 'u' && game.down()) {
            int m2 = manhattan;
            game.goDown();
            route[gScore] = grid[blank];
            if (manhattanDistance(grid[blank], blank) < manhattanDistance(grid[blank], blank + 4)) {
                m2 -= 1;
            } else {
                m2 += 1;
            }
            Node down = new Node(route.clone(), grid.clone(), m2, linearconflictrow(game.getGrid()), node.getLinearcol(), gScore + 1, 'd');
            nextNodes.add(down);
            game.goUp();
        }
        if (lastMove != 'd' && game.up()) {
            int m2 = manhattan;
            game.goUp();
            route[gScore] = grid[blank];
            if (manhattanDistance(grid[blank], blank) < manhattanDistance(grid[blank], blank - 4)) {
                m2 -= 1;
            } else {
                m2 += 1;
            }
            Node up = new Node(route.clone(), grid.clone(), m2, linearconflictrow(game.getGrid()), node.getLinearcol(), gScore + 1, 'u');
            nextNodes.add(up);
            game.goDown();
        }
        if (lastMove != 'l' && game.right()) {
            int m2 = manhattan;
            game.goRight();
            route[gScore] = grid[blank];
            if (manhattanDistance(grid[blank], blank) < manhattanDistance(grid[blank], blank + 1)) {
                m2 -= 1;
            } else {
                m2 += 1;
            }
            Node right = new Node(route.clone(), grid.clone(), m2, node.getLinearrow(), linearconflictcol(game.getGrid()), gScore + 1, 'r');
            nextNodes.add(right);
            game.goLeft();
        }
        if (lastMove != 'r' && game.left()) {
            int m2 = manhattan;
            game.goLeft();
            route[gScore] = grid[blank];
            if (manhattanDistance(grid[blank], blank) < manhattanDistance(grid[blank], blank - 1)) {
                m2 -= 1;
            } else {
                m2 += 1;
            }
            Node left = new Node(route.clone(), grid.clone(), m2, node.getLinearrow(), linearconflictcol(game.getGrid()), gScore + 1, 'l');
            nextNodes.add(left);
            game.goRight();
        }
        return nextNodes;
    }
    /**
     * Sums up all the heuristics used.
     * @param grid heuristics counted are from this grid
     * @return total heuristics
     */
    public int heuristic(int[] grid) {
        return sumManhattan(grid) + linearconflict(grid);
    }
    /**
     * adds up Manhattan distances to give us
     * heuristic for the lower bound on moves to be made before 15-puzzle is solved.
     * @param grid array that tells in what order tiles are in the game
     * @return lower bound on how many moves must at least be made
     */
    public int sumManhattan(int[] grid) {
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            if (grid[i] != 0) {
                sum += manhattanDistance(grid[i], i);
            }
        }
        return sum;
    } 
    
    /**
     * Counts where value should be and where it is (index), and then counts
     * the manhattan distance between those two places.
     * @param value some piece on the gameboard 
     * @param index values current place on the gameboard
     * @return manhattan distance between where value/piece is and where its
     * final place is
     */
    public int manhattanDistance(int value, int index) {
        if (value == 0) {
            return 0;
        }
        int y = index / 4;
        int x = index % 4;
        int b = (value - 1) / 4;
        int a = (value - 1) % 4;
        int rows = y - b >= 0 ? y - b : b - y;
        int columns = x - a > 0 ? x - a : a - x;
        
        return rows + columns;
    }
    
    /**
     * Linear conflict is when two tiles in 15-puzzle are in their correct row or colums
     * but in the wrong order. (e.g. if the first row was {3, 7, 1, 4} 3 and 1 are now
     * in linear conflict.)
     * @param grid state of the game
     * @return Amount of linear conflicts
     */
    public int linearconflict(int[] grid) {
        return linearconflictcol(grid) + linearconflictrow(grid);
    }
    private int linearconflictcol(int[] grid) {
        int linear = 0;
        for (int i = 0; i < 4; i++) {
            int n = 0;
            int[] column = new int[4];
            for (int j = 0; j < 16; j += 4) {
                if (grid[i + j] % 4 == i + 1) {
                    column[n] = grid[i + j];
                }
                n++;
            }
            linear += inversions(column);
        }
        return 2 * linear;
    }
    private int linearconflictrow(int[] grid) {
        int linear = 0;
        for (int i = 0; i < 16; i += 4) {
            int[] row = new int[4];
            for (int j = 0; j < 4; j++) {
                if (i < grid[i + j] && grid[i + j] <= i + 4) {
                    row[j] = grid[i + j];
                }
            }
            linear += inversions(row);
        }
        return 2 * linear;
    }
    /**
     * Counts inversions of the given row but ignores zero.
     * @param row array where we want to count inversions
     * @return amount of inversions
     */
    public int inversions(int[] row) {
        int inversions = 0;
        for (int i = 0; i < 3; i++) {
            if (row[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < 4; j++) {
                if (row[j] == 0) {
                    continue;
                }
                if (row[i] > row[j]) {
                    inversions++;
                }
            }
        }
        return inversions;
    }
}
