package no.uib.inf101.exam23v.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory {

  private int nextIndex = 0;
  private final String pattern;

  public PatternedTetrominoFactory(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public Tetromino getNext() {
    char symbol = pattern.charAt(nextIndex);
    nextIndex = (nextIndex + 1) % pattern.length();
    return Tetromino.newTetromino(symbol);
  }   
}
