package tirahtyo.viisitoistapeli;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class GameSolverTest {
    GameOfFifteen game;
    GameSolver solver;
    
    public GameSolverTest() {
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
        solver = new GameSolver(game);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void manhattanDistanceIsZeroWhenRightPlace() {
        assertEquals(solver.manhattanDistance(5, 4), 0);
    }
    @Test
    public void manhattanDistanceCountsCorrectly() {
        assertEquals(solver.manhattanDistance(5, 0), 1);
    }
    @Test
    public void manhattanDistanceCountsCorrectly2() {
        assertEquals(solver.manhattanDistance(15, 0), 5);
    }
    @Test
    public void manhattanDistanceDoesNotCountZero() {
        assertEquals(solver.manhattanDistance(0, 4), 0);
    }
    @Test
    public void linearConflictCountsCorrectly() {
        game.setGrid(new int[]{2,0,1,3,4,5,6,7,8,9,10,11,12,13,14,15});
        assertEquals(solver.linearconflict(game.getGrid()),1);
    }
    @Test
    public void linearConflictCountsCorrectly2() {
        game.setGrid(new int[]{1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
        assertEquals(solver.linearconflict(game.getGrid()),0);
    }
    @Test
    public void linearConflictCountsCorrectly3() {
        game.setGrid(new int[]{5,4,2,3,1,6,7,8,9,10,11,12,13,14,15,0});
        assertEquals(solver.linearconflict(game.getGrid()),3);
    }
    @Test
    public void heuristics() {
        game.setGrid(new int[]{5,4,2,3,1,6,7,8,9,10,11,12,13,14,15,0});
        assertEquals(solver.heuristic(game.getGrid()),12);
    }
    @Test
    public void searchEndsIfGridSolved() {
        Node node = new Node(null, game.getGrid(), 0, 2, 'r');
        assertEquals(solver.search(node,2), 0);
    }
    
}
