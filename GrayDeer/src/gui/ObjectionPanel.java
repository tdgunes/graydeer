package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import server.Constants;

public class ObjectionPanel {

    protected JFrame frame;
    public JButton objectionButton;
    public String privateKey;
    public String homeworkName;

    public ObjectionPanel(JButton button, String privateKey, String homeworkName) {
        super();
        this.frame = null;
        this.objectionButton = button;
        this.privateKey = privateKey;
        this.homeworkName = homeworkName;
    }

    protected void initializeObjectionPanel() {
        frame = new JFrame("Objection");
        frame.setBackground(Color.WHITE);

        frame.setLocation(250, 200);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Please write your objection below");
        frame.add(label, BorderLayout.NORTH);

        JTextField tf = new JTextField();
        frame.add(tf, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit Your Objection");
        ButtonActionListener buttonListener = new ButtonActionListener(frame, objectionButton, tf);
        submitButton.addActionListener(buttonListener);

        frame.add(submitButton, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    class ButtonActionListener implements ActionListener {

        JFrame frame;
        JButton button;
        JTextField tf;

        public ButtonActionListener(JFrame frame, JButton button, JTextField tf) {
            this.frame = frame;
            this.button = button;
            this.tf = tf;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            HTTPLib myLib = new HTTPLib(Constants.hostName, privateKey);
            try {
                String response = myLib.postData("objection", tf.getText(), homeworkName);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ObjectionPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ObjectionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            JOptionPane.showMessageDialog(null, "Objection submitted. You will be notified when your objection is evaluated.");
            button.setEnabled(false);
            frame.dispose();
        }
    }
}
