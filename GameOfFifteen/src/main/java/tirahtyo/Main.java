/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo;

import tirahtyo.viisitoistapeli.*;

/**
 *
 * @author birgi
 */
public class Main {
    public static void main(String[] args) {
        GameOfFifteen peli = new GameOfFifteen();
        int grid[] = new int[]{5,2,11,8,6,4,7,12,13,3,10,9,14,1,15,0};
        peli.setGrid(grid);
        //System.out.println("testi");
        //char[] reitti = new char[80];
        //System.out.println(reitti[0]=='\u0000');
        //for (int i=0; i<10; i++) {
        //    System.out.println(reitti[0]);
        //}
        //for (int i=0; i<16; i++) {
        //    System.out.println(peli.manhattanDistance(grid[i], i));
        //}
        //System.out.println(peli.sumManhattanDistance(grid));
        GameSolver ratkaisu = new GameSolver(peli);
        System.out.println(ratkaisu.solver());
    }
    
    
    
}
