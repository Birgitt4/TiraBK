
package tirahtyo.viisitoistapeli;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;
import tirahtyo.tests.PerformanceTests;

/**
 * Tests for class PerformanceTests. Tests only that we do not write over existing file.
 * 
 */
public class PerformanceTestsTest {
    
    PerformanceTests tester;
    
    @Test
    public void saveTestsDoesNotRemoveOldTests() throws IOException {
        File file = new File("testfile.txt");
        tester = new PerformanceTests("testfile.txt");
        tester.runSmallerBoards();
        tester.saveTests();
        int i = 0;
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                reader.nextLine();
                i++;
            }
        }
        tester.saveTests();
        int j = 0;
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                reader.nextLine();
                j++;
            }
        }
        file.deleteOnExit();
        assertEquals(true,i+10 < j);
    }
}
