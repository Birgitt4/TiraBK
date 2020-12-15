package tirahtyo.viisitoistapeli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * junit tests for class GameSolver
 * 
 */
public class GameSolverTest {
    GameOfFifteen game;
    GameSolver solver;
    
    @Before
    public void setUp() {
        game = new GameOfFifteen();
        solver = new GameSolver(game);
    }

    @Test
    public void manhattanDistanceIsZeroWhenRightPlace() {
        for (int i = 0; i < 16; i++) {
            assertEquals(solver.manhattanDistance(game.getGrid()[i], i), 0);
        }
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
        assertEquals(solver.linearconflict(game.getGrid()),2);
    }
    @Test
    public void linearConflictCountsCorrectly2() {
        game.setGrid(new int[]{1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
        assertEquals(solver.linearconflict(game.getGrid()),0);
    }
    @Test
    public void linearConflictCountsCorrectly3() {
        game.setGrid(new int[]{5,4,2,3,1,6,7,8,9,10,11,12,13,14,15,0});
        assertEquals(solver.linearconflict(game.getGrid()),6);
    }
    @Test
    public void heuristics() {
        game.setGrid(new int[]{5,4,2,3,1,6,7,8,9,10,11,12,13,14,15,0});
        assertEquals(solver.heuristic(game.getGrid()),12);
    }
    @Test
    public void searchEndsIfGridSolved() {
        Node node = new Node(null, game.getGrid(), 0, 0, 0, 2, ' ');
        assertEquals(solver.search(node,2), 0);
    }
    @Test
    public void solverReturnsRightAmountOfMoves() {
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,13,10,0,11,14,9,15,12});
        int[] route = solver.solver();
        assertEquals(route.length, 8);
        game.setGrid(new int[]{12,4,1,3,5,0,10,13,11,15,9,8,6,2,7,14});
        route = solver.solver();
        assertEquals(route.length, 54);
        game.setGrid(new int[]{5,2,11,8,6,4,7,12,9,3,10,14,13,1,15,0});
        route = solver.solver();
        assertEquals(route.length, 38);
    }
    
}
