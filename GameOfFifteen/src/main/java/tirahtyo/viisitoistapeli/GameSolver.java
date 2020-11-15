/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

import java.util.PriorityQueue;
/**
 *
 * @author birgi
 */
public class GameSolver {
    
    private GameOfFifteen game;
    
    public GameSolver(GameOfFifteen board) {
        game = board;
    }
    
    public char[] solver() {
        
        int testi = 0;
        
        
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>((a,b)-> a.compareTo(b));
        int gScore = 0;
        int[] grid = game.getGrid();
        Node starter = new Node(null, grid.clone(), sumManhattanDistance(game.getGrid()), gScore, ' ');
        pQueue.add(starter);
        char lastMove = ' ';
        
        Node bestNode = null;
        
        while (!game.isSolved()) {
            
            bestNode = pQueue.poll();
            
            //System.out.println(bestNode.toString());
            testi++;
            
            
            
            game.setGrid(bestNode.getGrid());
            grid = game.getGrid();
            gScore = bestNode.getGScore();
            char[] route = new char[gScore+1];
            for (int i=0; i<gScore; i++) {
                route[i] = bestNode.getRoute()[i];
            }
            lastMove = bestNode.getDirection();
            
            int blank = game.findBlank();
            
            if (lastMove!='u' && game.down()) {
                
                game.goDown();
                
                route[gScore] = 'd';
                Node down = new Node(route.clone(), grid.clone(), sumManhattanDistance(game.getGrid()), gScore+1, 'd');
                pQueue.add(down);
                game.goUp();
                
            }
            if (lastMove!='d' && game.up()) {
                
                game.goUp();

                route[gScore] = 'u';
                Node up = new Node(route.clone(), grid.clone(), sumManhattanDistance(game.getGrid()), gScore+1, 'u');
                
                
                pQueue.add(up);
                
                
                game.goDown();
            }
            if (lastMove!='l' && game.right()) {
                
                game.goRight();
                route[gScore] = 'r';
                Node right = new Node(route.clone(), grid.clone(), sumManhattanDistance(game.getGrid()), gScore+1, 'r');
                pQueue.add(right);
                game.goLeft();
                
            }
            if (lastMove!='r' && game.left()) {
                
                game.goLeft();
                route[gScore] = 'l';
                Node left = new Node(route.clone(), grid.clone(), sumManhattanDistance(game.getGrid()), gScore+1, 'l');
                
                
                pQueue.add(left);
                game.goRight();
                
                
            }
            
        }
        
        System.out.println(testi);
        return bestNode.getRoute();
        
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
        if (value==0) {
            return 0;
        }
        int y = index/4;
        int x = index%4;
        int b = (value-1)/4;
        int a = (value-1)%4;
        int rows = y-b >=0 ? y-b : b-y;
        int columns = x-a>0 ? x-a : a-x;
        
        return rows+columns;
    }
    
    public int sumManhattanDistance(int[] grid) {
        int sum = 0;
        for (int i=0;i<16;i++) {
            if (grid[i] != 0) {
                sum += manhattanDistance(grid[i], i);
            }
        }
        return sum;
    }

}
