package sudokusolver;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class CellClickListener implements MouseListener {

  @Override
  public void mouseClicked(MouseEvent arg0) {
    ((JTextField) arg0.getSource()).setText("");
    ((JTextField) arg0.getSource()).setBackground(new Color(0,0,255));
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    if (((JTextField) arg0.getSource()).getText().equals("0") && ((JTextField) arg0.getSource()).hasFocus())
      ((JTextField) arg0.getSource()).setBackground(new Color(192,192,192));
    
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub
    
  }

}
