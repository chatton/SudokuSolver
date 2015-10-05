import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class InputGrid extends JFrame {

	static int[][] customBoard = new int[9][9];

	public InputGrid() {
		super("Input Custom Sudoku");
		JButton submit = new JButton();
		JButton add = new JButton();
		TextArea[][] in = new TextArea[9][9];

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				in[row][col] = new TextArea();
			}
		}

		setVisible(true); // makes it visible
		setLocationRelativeTo(null); // centered
		setSize(550, 550); // creates a 900x800 window
		setResizable(false);// user can't change the window size
		setLayout(new GridLayout(10, 9));

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {

				add(in[row][col]);

				int num = 0;
				try {
					num = Integer.parseInt(GameBoard.grid[row][col].getText().trim());
				} catch (NumberFormatException e) {
					// okay to be left 0
				}

				customBoard[row][col] = num;
				in[row][col].setText("" + customBoard[row][col]);
			}
		}

		add(submit);
		add(new JLabel());
		add(add);
		submit.setText("OK");
		submit.setToolTipText("Submit your custom puzzle");

		add.setText("ADD");
		add.setToolTipText("Save custom puzzle");

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////
				for (int row = 0; row < 9; row++) {
					for (int col = 0; col < 9; col++) {

						try {
							customBoard[row][col] = Integer.parseInt(in[row][col].getText());
						} catch (NumberFormatException n) { // if user puts in
															// an invalid
															// character
							System.out.println("Invalid Value Assigned to grid.");
						}

					}
				}

				GameBoard.makeGray();
				GameBoard.showBoard(customBoard);
				////////////////////////////////////
			}
		});

		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				////////////////////////////////////
				try {
					PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("premadePuzzles.dat",true)));
					out.write("\n");
					for(int row = 0; row < 9; row++){
						
						for(int col = 0; col < 9; col++){
							
							out.write(customBoard[row][col] + " ");
						}	
					}
				//	out.write("\n");
					
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				////////////////////////////////////
			}

		}); // Add Button

	} // InputGrid

} // Class
