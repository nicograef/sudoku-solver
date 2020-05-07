package sudokusolver;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class QuickInput extends JOptionPane {
  
  public QuickInput(JTextField[] cells) {
    String input = JOptionPane.showInputDialog("Enter the sudoku field in one line. 0 equals an empty cell.");
    char[] values = input.toCharArray();
    for (int i = 0; i < cells.length; ++i) {
      if (i < values.length) cells[i].setText(values[i] + "");
      else cells[i].setText("0");
      if (!cells[i].getText().equals("0")) cells[i].setBackground(new Color(0,0,255));
    }
  }

}
