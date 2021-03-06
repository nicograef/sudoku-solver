package sudokusolver;

import java.util.LinkedList;

public class Column {

  public LinkedList<Cell> cells;
  
  public Column(int fieldSize, Cell[] field, int startIndex) {
    cells = new LinkedList<Cell>();
    
    for (int i=0; i < fieldSize; ++i) {
      cells.add(field[startIndex + i * fieldSize]);
    }
  }

}