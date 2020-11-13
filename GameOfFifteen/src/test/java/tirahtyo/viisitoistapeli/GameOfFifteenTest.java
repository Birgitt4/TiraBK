/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirahtyo.viisitoistapeli;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author birgi
 */
public class GameOfFifteenTest {
    
    GameOfFifteen game;
    
    public GameOfFifteenTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new GameOfFifteen();
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void findBlankWorks() {
        assertEquals("blanks position: 15", "blanks position: "+game.findBlank());
    }
    @Test
    public void blankNotFound() {
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        assertEquals(16, game.findBlank());
    }
    @Test
    public void inversionsCountsCorrectlyOne() {
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,9,10,0,11,12,13,15,14});
        assertEquals("inversions: 1", "inversions: "+ game.inversions());
    }
    @Test
    public void inversionsCountsCorrectlyTwo() {
        game.setGrid(new int[]{12,1,10,2,7,11,4,14,5,0,9,15,8,13,6,3});
        assertEquals("inversions: 49", "inversions: "+ game.inversions());
    }
    @Test
    public void inversionsCountsCorrectlyTree() {
        game.setGrid(new int[]{0,6,13,8,12,9,3,2,1,15,4,7,5,14,10,11});
        assertEquals("inversions: 49", "inversions: "+game.inversions());
    }
    @Test
    public void inversionsCountsCorrectlyFour() {
        game.setGrid(new int[]{6,13,8,12,0,9,3,2,1,15,4,7,5,10,14,11});
        assertEquals("inversions: 48", "inversions: "+game.inversions());
    }
    @Test
    public void isSolvableReturnTrueWhenGridIsSolvable1() {
        game.setGrid(new int[]{6,8,2,3,1,12,4,0,5,10,14,7,9,13,11,15});
        assertEquals("IsSolvable: true", "IsSolvable: "+game.isSolvable());
    }
    @Test
    public void isSolvableReturnTrueWhenGridIsSolvable3() {
        game.setGrid(new int[]{6,13,8,12,0,9,3,2,1,15,4,7,5,10,14,11});
        assertEquals("IsSolvable: true", "IsSolvable: "+game.isSolvable());
    }
    @Test
    public void isSolvableReturnsTrueWhenGridIsSolvable2() {
        game.setGrid(new int[]{0,6,13,8,12,9,3,2,1,15,4,7,5,14,10,11});
        assertEquals("IsSolvable: true", "IsSolvable: "+game.isSolvable());
    }
    @Test
    public void isSolvableReturnsFalseWhenNotSolvable() {
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,15,14,0});
        assertEquals("isSolvable: false", "isSolvable: "+game.isSolvable());
    }
    @Test
    public void countingManhattanDistance1() {
        assertEquals("When solved manhattan distance is 0", "When solved manhattan distance is "+ game.sumManhattanDistance(game.getGrid()));
    }
    @Test
    public void countingManhattanDistance2() {
        game.setGrid(new int[]{1,2,7,3,5,6,4,8,13,10,11,12,14,9,0,15});
        assertEquals("Manhattan distance is 9", "Manhattan distance is "+ game.sumManhattanDistance(game.getGrid()));
    }
}
