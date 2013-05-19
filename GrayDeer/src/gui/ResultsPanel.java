package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import server.Constants;

public class ResultsPanel {
	String hwName;
	String privateKey;


	public ResultsPanel(String hwName, String privateKey) {
		super();
		this.hwName = hwName;
		this.privateKey = privateKey;
	}

	// There are four columns namely Inputs, Outputs, Answers, Grades
	// Number of rows is equal to the number of homeworks
	private static final int COLUMNS = 4;
	private int rows = 4;
	private JPanel base;
	private JFrame newFrame;
	private Object[][] data;

	protected void initializePanel(){
		data = getData();
		rows = data.length;


		newFrame = new JFrame();
		newFrame.setSize(600,rows*50);
		newFrame.setLocation(200,100);
		newFrame.setLayout(new BorderLayout());
		newFrame.setBackground(Color.WHITE);

		JLabel margin = new JLabel("    ");
		newFrame.add(margin, BorderLayout.WEST);

		base = new JPanel();
		base.setLayout(new GridLayout(rows,COLUMNS));
		newFrame.add(base, BorderLayout.CENTER);

		addTable();
		addObjectionButon();
		newFrame.setVisible(true);

	}

	private Object[][] getData() {
		HTTPLib myLib = new HTTPLib(Constants.hostName, privateKey);
		String response = null;
		try {
			response = myLib.postData("getCases","", hwName);
		} catch (MalformedURLException ex) {
			Logger.getLogger(GridTable.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GridTable.class.getName()).log(Level.SEVERE, null, ex);
		}
		JOptionPane.showMessageDialog(null,response);
		System.out.println(response);


		return null;
	}		


	private void addObjectionButon() {
		JPanel buttonPanel = new JPanel();
		JButton objectionButton = new JButton("Object Your Grades");
		objectionButton.addActionListener(new ButtonHandler(objectionButton));
		buttonPanel.add(objectionButton);
		newFrame.add(buttonPanel, BorderLayout.SOUTH);
	}

	// Adds homework results to the pane
	private void addTable() {

		addTitles();


		for(int i=0; i< (rows)*COLUMNS; i++){
			base.add(new JLabel( (String) data[i/COLUMNS][i%COLUMNS]));
		}

		//		for(int i=0; i< (rows-1)*COLUMNS; i++){
		//			base.add(new JLabel("hi"));
		//		}
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
	//	public static void main(String args[]){
	//		ResultsPanel rp = new ResultsPanel();
	//		rp.initializePanel();
	//	}

}
class ButtonHandler implements ActionListener {
	JButton button;

	public ButtonHandler(JButton button) {
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ObjectionPanel op = new ObjectionPanel(button);
		op.initializeObjectionPanel();
	}
}