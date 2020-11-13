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
        
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>((a,b)-> a.compareTo(b));
        int nodesCreated = 0;
        int gScore = 0;
        char[] route = new char[80];
        Node starter = new Node(route, game.getGrid(), sumManhattanDistance(game.getGrid()), gScore, 0, nodesCreated);
        nodesCreated++;
        pQueue.add(starter);
        char lastMove = ' ';
        
        Node bestNode = null;
        
        while (!game.isSolved()) {
            
            int[] grid = game.getGrid();
            for (int i=0; i<16; i++) {
                System.out.print(grid[i]);
            }
            System.out.println("game grid");
            bestNode = pQueue.poll();
            System.out.println(bestNode.toString());
            game.setGrid(bestNode.getGrid());
            grid = game.getGrid();
            for (int i=0; i<16; i++) {
                System.out.print(grid[i]);
            }
            System.out.println("pullauksen jlk");
            route = bestNode.getRoute();
            gScore = bestNode.getGScore();
            
            
            System.out.println(bestNode.toString());
            
            
            
            int blank = game.findBlank();
            
            if (lastMove!='u' && game.down()) {
                
                game.goDown();
                
                route[gScore] = 'd';
                Node down = new Node(route, game.getGrid(), sumManhattanDistance(game.getGrid()), gScore+1,game.getValue(blank), nodesCreated);
                pQueue.add(down);
                nodesCreated++;
                game.goUp();
                
            }
            if (lastMove!='d' && game.up()) {
                
                game.goUp();
                
                int[] ggrid = new int[16];
                for (int i=0; i<16;i++) {
                    ggrid[i] = grid[i];
                }
                for (int i=0; i<16; i++) {
                    System.out.print(ggrid[i]+",");
                }
                System.out.println("liikkeen jälkeen");
                route[gScore] = 'u';
                Node up = new Node(route, ggrid, sumManhattanDistance(ggrid), gScore+1,ggrid[blank], nodesCreated);
                ggrid = up.getGrid();
                for (int i=0; i<16; i++) {
                    System.out.print(ggrid[i]+",");
                }
                System.out.println("noden grid ylös");
                
                
                pQueue.add(up);
                ggrid = up.getGrid();
                for (int i=0; i<16; i++) {
                    System.out.print(ggrid[i]+",");
                }
                System.out.println("noden grid ylös");
                
                System.out.println(up.toString());
                
                nodesCreated++;
                
                //MIKSI IHMEESSÄ game.goDown vaikuttaa juuri luotuun Nodeen
                //Nodessa siis muuttuu tuo grid kanssa
                
                
                game.goDown();
                
                System.out.println(up.toString());
                
                ggrid = up.getGrid();
                for (int i=0; i<16; i++) {
                    System.out.print(ggrid[i]+",");
                }
                System.out.println("noden grid ylös");
            }
            if (lastMove!='l' && game.right()) {
                
                game.goRight();
                route[gScore] = 'r';
                Node right = new Node(route, game.getGrid(), sumManhattanDistance(game.getGrid()), gScore+1,game.getValue(blank), nodesCreated);
                pQueue.add(right);
                nodesCreated++;
                game.goLeft();
                
            }
            if (lastMove!='r' && game.left()) {
                
                game.goLeft();
                route[gScore] = 'l';
                Node left = new Node(route, game.getGrid(), sumManhattanDistance(game.getGrid()), gScore+1,game.getValue(blank), nodesCreated);
                
                grid = left.getGrid();
                for (int i=0; i<16; i++) {
                    System.out.print(grid[i]+",");
                }
                System.out.println("noden left grid ylös");
                
                pQueue.add(left);
                nodesCreated++;
                game.goRight();
                
                
                grid = left.getGrid();
                for (int i=0; i<16; i++) {
                    System.out.print(grid[i]+",");
                }
                System.out.println("noden left grid ylös");
                
            }
            
            
            
            
        }
        
        
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
