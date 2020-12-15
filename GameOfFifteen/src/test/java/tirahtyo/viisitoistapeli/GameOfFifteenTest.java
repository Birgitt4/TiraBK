
package tirahtyo.viisitoistapeli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * junit tests for class GameOfFifteen.
 * 
 */
public class GameOfFifteenTest {
    
    GameOfFifteen game;
    
    @Before
    public void setUp() {
        game = new GameOfFifteen();
    }

    @Test
    public void findBlankWorks() {
        assertEquals("blanks position: 15", "blanks position: "+game.findBlank());
    }
    @Test
    public void blankNotFound() {
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        assertEquals(-1, game.findBlank());
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
        // nolla toisella rivillä, inversioita parillinen määrä
        game.setGrid(new int[]{6,8,2,3,1,12,4,0,5,10,14,7,9,13,11,15});
        assertEquals("IsSolvable: true", "IsSolvable: "+game.isSolvable());
    }
    @Test
    public void isSolvableReturnTrueWhenGridIsSolvable2() {
        // nolla toisella rivillä, inversioita parillinen määrä
        game.setGrid(new int[]{6,13,8,12,0,9,3,2,1,15,4,7,5,10,14,11});
        assertEquals("IsSolvable: true", "IsSolvable: "+game.isSolvable());
    }
    @Test
    public void isSolvableReturnsTrueWhenGridIsSolvable3() {
        // nolla ensimmäisellä rivillä, inversioita pariton määrä
        game.setGrid(new int[]{0,6,13,8,12,9,3,2,1,15,4,7,5,14,10,11});
        assertEquals("IsSolvable: true", "IsSolvable: "+game.isSolvable());
    }
    @Test
    public void isSolvableReturnsFalseWhenNotSolvable() {
        // nolla neljännellä rivillä, inversioita pariton
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,15,14,0});
        assertEquals("isSolvable: false", "isSolvable: "+game.isSolvable());
    }
    public void isSolvableReturnsFalseWhenNotSolvable2() {
        // nolla kolmannella rivillä, inversioita parillinen määrä
        game.setGrid(new int[]{6,13,8,12,9,3,2,1,15,0,4,7,5,10,14,11});
        assertEquals("isSolvable: false","isSolvable: "+game.isSolvable());
    }
    @Test
    public void isSolvedReturnsTrueWhenSolved() {
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0});
        assertEquals(true, game.isSolved());
    }
    @Test
    public void isSolvedReturnsFalseWhenNotSolved() {
        game.setGrid(new int[]{1,3,2,4,5,6,7,8,9,10,11,12,13,14,15,0});
        assertEquals(false, game.isSolved());
    }
    @Test
    public void leftAllowed() {
        assertEquals(true, game.left());
    }
    @Test
    public void CannotGoLeft() {
        game.goLeft();
        game.goLeft();
        game.goLeft();
        game.goLeft();
        assertEquals(false, game.left());
    }
    @Test
    public void rightAllowed() {
        game.goLeft();
        game.goLeft();
        game.goRight();
        assertEquals(true, game.right());
    }
    @Test
    public void CannotGoRight() {
        game.goRight();
        assertEquals(false, game.right());
    }
    @Test
    public void upAllowed() {
        assertEquals(true, game.up());
    }
    @Test
    public void CannotGoUp() {
        game.goUp();
        game.goUp();
        game.goUp();
        game.goUp();
        assertEquals(false, game.up());
    }
    @Test
    public void downAllowed() {
        game.goUp();
        game.goUp();
        game.goDown();
        assertEquals(true, game.down());
    }
    @Test
    public void CannotGoDown() {
        game.goDown();
        assertEquals(false, game.down());
    }
    @Test
    public void shuffle() {
        game.suffle();
        assertEquals("inversioita yli 10: " + true, "inversioita yli 10: " + (10<game.inversions()));
    }
    @Test
    public void isSolvedWorks() {
        assertTrue(game.isSolved());
        game.setGrid(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,15,14,0});
        assertFalse(game.isSolved());
        game.setGrid(new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
        assertFalse(game.isSolved());
    }
}
