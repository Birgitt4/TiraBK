/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo;

import java.util.PriorityQueue;
import tirahtyo.GUI.GUI;
import tirahtyo.viisitoistapeli.*;

/**
 *
 * @author birgi
 */
public class Main {
    public static void main(String[] args) {
        
        GUI.main(args);
        
        GameOfFifteen peli = new GameOfFifteen();
        
        //38 siirtoa
        int[] grid = new int[]{5,2,11,8,6,4,7,12,9,3,10,14,13,1,15,0};
        //8 siirtoa
        int[] grid2 = new int[]{1,2,3,4,5,6,7,8,13,10,0,11,14,9,15,12};
        
        //yksi eniten siirtoa vaativa puzzle =80 siirtoa
        //ei mene läpi edes neljässä minuutissa. Jos laittaa treshold arvon valmiiksi
        // 80 löytää kyllä oikean
        int[] grid3 = new int[]{0,12,9,13,15,11,10,14,3,7,2,5,4,8,6,1};
        //54 siirtoa 12 sekuntia
        int[] grid4 = new int[]{12,4,1,3,5,0,10,13,11,15,9,8,6,2,7,14};
        //60 siirtoa. 25 sekuntia
        int[] grid5 = new int[]{0,3,7,14,4,13,10,6,12,2,15,5,11,9,8,1};
        //68 siirtoa, mutta erittäin vaikea. Netissä oleva simulaattorikaan ei 
        //saa ratkaisua tarpeeksi nopeast tällä heustiikala
        //pattern database heuristiikalla saa ratkaisun
        int[] grid7 = new int[]{0,15,14,2,12,4,8,9,10,11,13,5,7,6,3,1};
        //72 siirtoa 290 sekuntia
        int[] grid6 = new int[]{15,12,9,13,3,11,10,0,4,7,2,14,8,6,1,5};

        int[] grid8 = new int[]{12,8,5,6,9,0,1,7,13,11,14,3,2,4,10,15};
        peli.setGrid(grid8);
        
        GameSolver ratkaisu = new GameSolver(peli);
        long alku = System.nanoTime();
        int[] route = ratkaisu.solver();
        long loppu = System.nanoTime();
        for (int i=0; i<route.length; i++) {
            System.out.print(route[i] + ", ");
        }
        System.out.println("");
        System.out.println((loppu-alku)/1000000);
        }
    
    
    
}
