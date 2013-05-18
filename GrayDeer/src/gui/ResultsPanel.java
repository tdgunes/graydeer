package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultsPanel {

	// There are four columns namely Inputs, Outputs, Answers, Grades
	// Number of rows is equal to the number of homeworks
	private static final int COLUMNS = 4;
	private int rows = 4;
	private JPanel base;

	protected void initializePanel(){
		JFrame newFrame = new JFrame();
		newFrame.setSize(600,rows*50);
		newFrame.setLocation(300,100);

		base = new JPanel();
		base.setLayout(new GridLayout(rows,COLUMNS));
		newFrame.add(base);

		addTable();
		newFrame.setVisible(true);

	}

	// Adds homework results to the pane
	private void addTable() {

		addTitles();


		//		for(int i=0; i< (rows-1)*COLUMNS; i++){
		//				base.add(new JLabel( (String) data[i/COLUMNS][i%COLUMNS]));
		//		}

		for(int i=0; i< (rows-1)*COLUMNS; i++){
			base.add(new JLabel("hi"));
		}
	}

	// Adds first row of the table
	private void addTitles() {
		JLabel hwName = new JLabel("Input");

		// Sets the font to bold
		Font newLabelFont=new Font(hwName.getFont().getName(),Font.BOLD,hwName.getFont().getSize());
		hwName.setFont(newLabelFont);

		JLabel deadline = new JLabel("Output");
		deadline.setFont(newLabelFont);

		JLabel grade = new JLabel("Answer");
		grade.setFont(newLabelFont);

		JLabel action = new JLabel("Grade");
		action.setFont(newLabelFont);

		base.add(hwName);
		base.add(deadline);
		base.add(grade);
		base.add(action);
	}

	// For testing purposes
	public static void main(String args[]){
		ResultsPanel rp = new ResultsPanel();
		rp.initializePanel();
	}

}
