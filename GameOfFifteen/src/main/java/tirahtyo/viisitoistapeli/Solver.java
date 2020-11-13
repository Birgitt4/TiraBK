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
public class Solver {
    
    private GameOfFifteen game;
    
    public Solver(GameOfFifteen game) {
        this.game = game;
    }
    
    public Node aStar() {
        PriorityQueue<Node> pQueue = new PriorityQueue<Node>((a,b)-> a.compareTo(b));
        char[] route = new char[80];
        int mones = 0;
        Node previous = new Node(route, game.getGrid(), 1000,1000,0,mones);
        mones++;
        int gScore = 1;
        
        char edellinen = ' ';

        while (!game.isSolved()) {
            int blank = game.findBlank();
            //first let's find out what moves are possible and do nodes for each
            if (edellinen!='u' && game.down()) {
                game.goDown();
                for (int i=0; i<80; i++) {
                    if (route[i] == '\u0000') {
                        route[i] 
                    }
                }
                Node down = new Node(route, game.sumManhattanDistance(game.getGrid()),gScore, game.getValue(blank),mones);
                mones++;
                pQueue.add(down);
                System.out.println("DOWN "+down.toString());
                game.goUp();
            }
            if (edellinen!='d' && game.up()) {
                game.goUp();
                Node up = new Node(previous, game.sumManhattanDistance(game.getGrid()),gScore, 'u',game.getValue(blank),mones);
                mones++;
                pQueue.add(up);
                System.out.println("UP "+up.toString());
                game.goDown();
            }
            if (edellinen!='r' && game.left()) {
                game.goLeft();
                Node left = new Node(previous, game.sumManhattanDistance(game.getGrid()),gScore, 'l',game.getValue(blank), mones);
                mones++;
                pQueue.add(left);
                System.out.println("LEFT "+left.toString());
                game.goRight();
            }
            if (edellinen!='l' && game.right()) {
                game.goRight();
                Node right = new Node(previous, game.sumManhattanDistance(game.getGrid()),gScore, 'r',game.getValue(blank), mones);
                mones++;
                pQueue.add(right);
                System.out.println("RIGHT "+ right.toString());
                game.goLeft();
            }
            //first node in the pQueue is the most promising way to go
            Node next = pQueue.poll();
            previous = next;
            System.out.println(next.getDirection());
            edellinen = next.getDirection();
            if (next.getDirection() == 'u') {
                game.goUp();
            }
            if (next.getDirection() == 'd') {
                game.goDown();
            }
            if (next.getDirection() == 'r') {
                game.goRight();
            }
            if (next.getDirection() == 'l') {
                game.goLeft();
            }
            gScore += 1;
            
        
        
        }

        return previous;
    }
    
    
}
