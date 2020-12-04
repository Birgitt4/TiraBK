/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

//import java.util.PriorityQueue;
/**
 * Solver for 15-puzzle using IDA* and manhattan distance and linear conflict
 * as a heuristic.
 * 
 */
public class GameSolver {
    
    private GameOfFifteen game;
    private Node goalNode;
    
    public GameSolver(GameOfFifteen game) {
        this.game = game;
        goalNode = null;
    }
    
    /**
     * Solves 15-puzzle.
     * @return returns list of moves to be made to solve the 15-puzzle
     */
    public int[] solver() {

        int gScore = 0;
        int[] grid = game.getGrid();
        int treshold = heuristic(grid);
        Node starter = new Node(new int[1], grid.clone(), heuristic(game.getGrid()), gScore, ' ');

        while (true) {
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
        if (game.isSolved()) {
            goalNode = node;
            return 0;
        }
        int minOverTres = 160;
        PriorityQueue nextNodes = possibleMoves(node);
        while (!nextNodes.isEmpty()) {
            Node next = nextNodes.poll();
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
        
        if (lastMove != 'u' && game.down()) {
            game.goDown();
            route[gScore] = grid[blank];
            Node down = new Node(route.clone(), grid.clone(), heuristic(game.getGrid()), gScore + 1, 'd');
            nextNodes.add(down);
            game.goUp();
        }
        if (lastMove != 'd' && game.up()) {
            game.goUp();
            route[gScore] = grid[blank];
            Node up = new Node(route.clone(), grid.clone(), heuristic(game.getGrid()), gScore + 1, 'u');
            nextNodes.add(up);
            game.goDown();
        }
        if (lastMove != 'l' && game.right()) {
            game.goRight();
            route[gScore] = grid[blank];
            Node right = new Node(route.clone(), grid.clone(), heuristic(game.getGrid()), gScore + 1, 'r');
            nextNodes.add(right);
            game.goLeft();
        }
        if (lastMove != 'r' && game.left()) {
            game.goLeft();
            route[gScore] = grid[blank];
            Node left = new Node(route.clone(), grid.clone(), heuristic(game.getGrid()), gScore + 1, 'l');
            nextNodes.add(left);
            game.goRight();
        }
        return nextNodes;
    }
    
    /**
     * adds up Manhattan distances and the amount of linear conflicts to give us
     * heuristic for the lower bound on moves to be made before 15-puzzle is solved.
     * @param grid array that tells in what order tiles are in the game
     * @return lower bound on how many moves must at least be made
     */
    public int heuristic(int[] grid) {
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            if (grid[i] != 0) {
                sum += manhattanDistance(grid[i], i);
            }
        }
        sum += 2 * linearconflict(grid);
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
        return linear;
    }
    /**
     * Counts inversions of the given row but ignores zero.
     * @param row
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
