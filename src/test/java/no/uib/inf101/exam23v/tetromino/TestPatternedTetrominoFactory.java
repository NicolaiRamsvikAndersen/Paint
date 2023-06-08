package no.uib.inf101.exam23v.tetromino;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestPatternedTetrominoFactory {
  private static final int NUMBER_OF_TRAILS = 10;
  
  @Test
  public void testFactoryProducingOnlyT() {
    PatternedTetrominoFactory factory = new PatternedTetrominoFactory("T");
    
    for (int i = 0; i < NUMBER_OF_TRAILS; i++) {
      Tetromino tetro = factory.getNext();
      boolean[][] shapeTetro = null;  // TODO: get the shape of the tetromino here
      shapeTetro = tetro.getShape();

      // Expected shape of T-piece:
      // 0 0 0
      // 1 1 1
      // 0 1 0
      assertFalse(shapeTetro[0][0]);
      assertFalse(shapeTetro[0][1]);
      assertFalse(shapeTetro[0][2]);
      
      assertTrue(shapeTetro[1][0]);
      assertTrue(shapeTetro[1][1]);
      assertTrue(shapeTetro[1][2]);
      
      assertFalse(shapeTetro[2][0]);
      assertTrue(shapeTetro[2][1]);
      assertFalse(shapeTetro[2][2]);  
    }
  }
}
