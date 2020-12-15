package tirahtyo.tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import tirahtyo.viisitoistapeli.GameOfFifteen;
import tirahtyo.viisitoistapeli.GameSolver;

/**
 * This class is made to collect data to show performance of GameSolver.
 * 
 */
public class PerformanceTests {
    private long[][] results;
    private GameOfFifteen game;
    private GameSolver solver;
    private String file;
    
    public PerformanceTests(String file) {
        results = new long[81][3];
        game = new GameOfFifteen();
        solver = new GameSolver(game);
        this.file = file;
    }
    
    /**
     * run 50 random boards. Expecting that most of them takes 45-60 steps.
     */
    public void run() {
        for (int i = 0; i < 50; i++) {
            game.suffle();
            while (!game.isSolvable()) {
                game.suffle();
            }
            long start = System.nanoTime();
            int[] route = solver.solver();
            long stop = System.nanoTime();
            if (route[1] == 0) {
                continue;
            }
            stop = stop - start;
            stop = stop / 1000;
            if (stop > 0) {            
                results[route.length][0]++;
                results[route.length][1] = stop;
                results[route.length][2] += solver.getNodesMade();
            }
            game.setGrid(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0});
        }
        average();
    }
    /**
     * Makes random moves to shuffle the game and then measure performance.
     * Makes few enough moves so we get easier puzzles, needing moves from approx. 1-30
     */
    public void runSmallerBoards() {
        for (int i = 0; i < 1000; i++) {
            if (i % 250 == 0) {
                game.setGrid(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0});
            }
            for (int j = 0; j < 40; j++) {
                Random random = new Random();
                int rnd = random.nextInt(4);
                if (rnd == 0) {
                    game.goDown();
                } else if (rnd == 1) {
                    game.goUp();
                } else if (rnd == 2) {
                    game.goRight();
                } else {
                    game.goLeft();
                }
            }
            long start = System.nanoTime();
            int[] route = solver.solver();
            long stop = System.nanoTime();
            stop = stop - start;
            stop = stop / 1000;
            results[route.length][0]++;
            results[route.length][1] += stop;
            results[route.length][2] += solver.getNodesMade();
        }
        average();
    }
    /**
     * Counts the average time and generated nodes for each solution length.
     */
    public void average() {
        for (int i = 0; i < 81; i++) {
            if (results[i][0] != 0) {
                long averageTime = results[i][1] / results[i][0];
                long averageNodes = results[i][2] / results[i][0];
                results[i][0] = 1;
                results[i][1] = averageTime;
                results[i][2] = averageNodes;
            }
        }
    }
    /**
     * Writes test results in a file.
     * solution length:time in microseconds:nodes generated
     * @throws IOException something wrong with opening file
     */
    public void saveTests() throws IOException {
        FileWriter writer = new FileWriter(file, true);
        for (int i = 0; i < 81; i++) {
            if (results[i][0] == 1) {
                String row = i + ":" + results[i][1] + ":" + results[i][2] + "\n";
                writer.append(row);
            }
        }
        writer.close();
    }
}
