/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo;

import tirahtyo.viisitoistapeli.GameOfFifteen;

/**
 *
 * @author birgi
 */
public class Main {
    public static void main(String[] args) {
        GameOfFifteen peli = new GameOfFifteen();
        System.out.println(peli.manhattanDistance(1, 0));
        System.out.println(peli.manhattanDistance(2, 1));
        System.out.println(peli.manhattanDistance(3, 2));
        System.out.println(peli.manhattanDistance(4, 3));
        System.out.println(peli.manhattanDistance(5, 4));
        System.out.println(peli.manhattanDistance(6, 5));
        System.out.println(peli.manhattanDistance(7, 6));
    }
    
    
    
}
