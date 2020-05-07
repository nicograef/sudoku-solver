package sudokusolver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class MainWindow {
  
  private final int FIELDSIZE = 9;
  private final int MAX_ITERATIONS = 100;

	private JFrame mainFrame;
	private JButton btnSolve;
	
	private JTextField[] txtCells;
	private JButton btnReset;

	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	
	private CellClickListener cellClickListener;
	private JMenuItem mntmDesolve;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		
		// RESET Button
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent arg0) {
		    for (int i = 0; i < txtCells.length; ++i) {
		      txtCells[i].setText("0");
		      txtCells[i].setBackground(new Color(192,192,192));
		    }
		    btnSolve.setText("SOLVE");
		    btnSolve.setBackground(new Color(192,192,192));
		  }
		});
		btnReset.setFont(new Font("SansSerif", Font.PLAIN, 20));
		btnReset.setForeground(Color.DARK_GRAY);
		btnReset.setBackground(Color.LIGHT_GRAY);
		btnReset.setBounds(544, 823, 245, 40);
		mainFrame.getContentPane().add(btnReset);
		
		separator = new JSeparator();
		separator.setBounds(10, 266, 779, 2);
		mainFrame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(265, 10, 2, 779);
		mainFrame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(532, 10, 2, 779);
		mainFrame.getContentPane().add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(10, 532, 779, 2);
		mainFrame.getContentPane().add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(10, 810, 779, 2);
		mainFrame.getContentPane().add(separator_4);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setSize(new Dimension(950, 950));
		mainFrame.setSize(new Dimension(836, 916));
		mainFrame.setMaximumSize(new Dimension(1920, 1280));
		mainFrame.getContentPane().setForeground(Color.LIGHT_GRAY);
		mainFrame.getContentPane().setBackground(Color.DARK_GRAY);
		mainFrame.getContentPane().setLayout(null);
		
		// create all the sudoku cells as textfields
		cellClickListener = new CellClickListener();
		txtCells = new JTextField[FIELDSIZE * FIELDSIZE];
		int j = -1;
		int hspace = 0, vspace = 0;
		for (int i = 0; i < 81; ++i) {
		  txtCells[i] = new JTextField();
		  
		  // compute the offsets for the separators
		  if (i % 9 == 0) { ++j; hspace = 0; }
		  if (i % 3 == 0 && i % 9 != 0) hspace += 12;
		  if (i % 27 == 0 && i >= 27) vspace += 12;
		  
		  // set the coordinates of every jtextfield
		  txtCells[i].setBounds(10 + (i % 9) * 85 + hspace, 10 + (j % 9) * 85 + vspace, 75, 75);
		  
		  // add some properties and the clickListener
		  txtCells[i].addMouseListener(cellClickListener);
		  txtCells[i].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		  txtCells[i].setSize(new Dimension(75, 75));
		  txtCells[i].setBackground(Color.LIGHT_GRAY);
		  txtCells[i].setHorizontalAlignment(SwingConstants.CENTER);
		  txtCells[i].setForeground(Color.DARK_GRAY);
		  txtCells[i].setFont(new Font("SansSerif", Font.BOLD, 82));
		  txtCells[i].setText("0");
		  txtCells[i].setColumns(1);
		  
		  // add the new textfield to the frame
		  mainFrame.getContentPane().add(txtCells[i]);
		}
    
    btnSolve = new JButton("SOLVE");
    btnSolve.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        solveSudoku();
      }
    });		
		btnSolve.setBackground(Color.LIGHT_GRAY);
		btnSolve.setForeground(Color.DARK_GRAY);
		btnSolve.setFont(new Font("SansSerif", Font.BOLD, 32));
		btnSolve.setBounds(10, 823, 512, 40);
		
		mainFrame.getContentPane().add(btnSolve);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(btnSolve, popupMenu);
		
		JMenuItem mntmQuickinput = new JMenuItem("QuickInput");
		mntmQuickinput.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent arg0) {
		    new QuickInput(txtCells);
		  }
		});
		popupMenu.add(mntmQuickinput);
		
		mntmDesolve = new JMenuItem("Desolve");
		mntmDesolve.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    for (JTextField cell : txtCells) {
		      if (cell.getBackground().getBlue() != 255) {
		        cell.setText("0");
		        cell.setBackground(new Color(192,192,192));
		      }
		    }
		    btnSolve.setText("SOLVE");
		    btnSolve.setBackground(new Color(192,192,192));
		  }
		});
		popupMenu.add(mntmDesolve);
		
		
		mainFrame.setType(Type.UTILITY);
		mainFrame.setTitle("SudokuSolver | solve any sudoku");
		mainFrame.setBounds(100, 100, 806, 902);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Function in which the sudoku algorithm runs.
	 */
	private void solveSudoku() {
	  int[] values = new int[FIELDSIZE * FIELDSIZE];
    for (int i = 0; i < values.length; i++) {
      values[i] = Integer.parseInt( txtCells[i].getText() );
      //System.out.println(txtCells[i].getText() + " : " + i);
      if (values[i] != 0) { txtCells[i].setBackground(new Color(0,0,255)); }
      else txtCells[i].setBackground(new Color(0,255,0));
    }
    
    Field field = new Field(FIELDSIZE, values);
    
    int count = 0;
    
    btnSolve.setText("SOLVED!");
    btnSolve.setBackground(new Color(0,255,0));
    
    while (!field.solved && count < MAX_ITERATIONS) {
      field.computeOptions();
      if (!field.checkForSolvedCells()) {
        btnSolve.setText("NOT SOLVEABLE!");
        btnSolve.setBackground(new Color(255,0,0));
        values = field.getSolution();
        for (int i = 0; i < values.length; ++i) { if (values[i] == 0) { txtCells[i].setBackground(new Color(255,0,0)); } }
        break;
      }
     ++count;
    }
    
    values = field.getSolution();
    for (int i = 0; i < values.length; ++i) { txtCells[i].setText(values[i] + ""); }
	}
	
  private static void addPopup(Component component, final JPopupMenu popup) {
    component.addMouseListener(new MouseAdapter() {
    	public void mousePressed(MouseEvent e) {
    		if (e.isPopupTrigger()) {
    			showMenu(e);
    		}
    	}
    	public void mouseReleased(MouseEvent e) {
    		if (e.isPopupTrigger()) {
    			showMenu(e);
    		}
    	}
    	private void showMenu(MouseEvent e) {
    		popup.show(e.getComponent(), e.getX(), e.getY());
    	}
    });
  }
}
