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
        int grid[] = new int[]{5,2,11,8,6,4,7,12,9,3,10,14,13,1,15,0};
        int grid2[] = new int[]{1,2,3,4,5,6,7,8,13,10,0,11,14,9,15,12};
        
        
        //eniten siirtoa vaativa puzzle =80 siirtoa
        //tila loppuu eikä saada vastausta
        int grid3[] = new int[]{0,12,9,13,15,11,10,14,3,7,2,5,4,8,6,1};
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
