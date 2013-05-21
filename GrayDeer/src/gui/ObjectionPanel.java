package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ObjectionPanel {
	protected JFrame frame;
	public JButton objectionButton;
	
	
	
	public ObjectionPanel(JButton button) {
		super();
		this.frame = null;
		this.objectionButton = button;
	}

	protected void initializeObjectionPanel(){
		frame = new JFrame("Objection");
		frame.setBackground(Color.WHITE);
		
		frame.setLocation(250,200);
		frame.setSize(300, 200);	
		frame.setLayout(new BorderLayout());

		JLabel label = new JLabel("Please write your objection below");
		frame.add(label, BorderLayout.NORTH);

		JTextField tf = new JTextField();
		frame.add(tf, BorderLayout.CENTER);

		JButton submitButton = new JButton("Submit Your Objection");
		ButtonActionListener buttonListener = new ButtonActionListener(frame, objectionButton);
		submitButton.addActionListener(buttonListener);
		
		frame.add(submitButton, BorderLayout.SOUTH);

		frame.setVisible(true);

	}

	// Test Objection Panel
	public static void main(String[] args) {
		ObjectionPanel ob = new ObjectionPanel(null);
		ob.initializeObjectionPanel();
	}
}


class ButtonActionListener implements ActionListener{
	JFrame frame;
	JButton button;
	
	public ButtonActionListener(JFrame frame, JButton button){
		this.frame = frame;
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Objection submitted. You will be notified when your objection is evaluated.");
		button.setEnabled(false);
		frame.dispose();
	}
}