/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. 
 * All rights reserved.
 *
 * Package: instructor
 * @author tdgunes
 */

package instructor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


import server.student.Student;
import server.student.StudentDB;


public class AddStudentWindow {
    private InstructorGUI rootView = null;
            
    public AddStudentWindow(InstructorGUI rootView) {
        this.rootView = rootView;
    }
    public void showWindow(){
        JFrame window = new JFrame();
        window.setLayout(new GridLayout(5,1));
        window.setTitle("Add a Student");
        
       
        
        JButton submitButton = new JButton("Add Student");
        
        JPanel namePane = new JPanel(new GridLayout(1,2));
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField nameField = new JTextField();
        namePane.add(nameLabel);
        namePane.add(nameField);
        window.add(namePane);
   
        JPanel surnamePane = new JPanel(new GridLayout(1, 2));
        JLabel surnameLabel = new JLabel("Surname: ");
        surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField surnameField = new JTextField();
        surnamePane.add(surnameLabel);
        surnamePane.add(surnameField);
        window.add(surnamePane);

        JPanel numPane = new JPanel(new GridLayout(1, 2));
        JLabel numLabel = new JLabel("School Number: ");
        numLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField numField = new JTextField();
        numPane.add(numLabel);
        numPane.add(numField);
        window.add(numPane);

        JPanel privatePane = new JPanel(new GridLayout(1, 3));
        JLabel privateLabel = new JLabel("Private Key: ");
        privateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField privateField = new JTextField();
        privatePane.add(privateLabel);
        privatePane.add(privateField);
        window.add(privatePane);
        
        submitButton.addActionListener(new ButtonListener(nameField, surnameField, 
                numField, privateField,window));
        window.add(submitButton);
        
        window.setLocationRelativeTo(null);
        window.setSize(200,300);
        window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        window.setVisible(true);
        
    }
    
    class ButtonListener implements ActionListener {

        private JTextField name;
        private JTextField surname;
        private JTextField schoolNum;
        private JTextField privateKey;
        private JFrame frame;
        public ButtonListener(JTextField name, JTextField surname, 
                JTextField schoolNum, JTextField privateKey, JFrame frame) {
                this.name = name;
                this.surname = surname;
                this.schoolNum = schoolNum;
                this.privateKey = privateKey;
                this.frame = frame;
        }



        @Override
		public void actionPerformed(ActionEvent ae) {
           /* System.out.println("Name: " + this.name);
            System.out.println("Surname: " + this.surname);
            System.out.println("SchoolNum: "+ this.schoolNum);
            System.out.println("PrivateKey: " + this.privateKey);*/
            String aName = this.name.getText();
            String aSurName = this.surname.getText();
            String aSchoolNum = this.schoolNum.getText();
            String aPrivateKey = this.privateKey.getText();
            if (aName.trim().equals("") || aSurName.trim().equals("") 
                    || aSchoolNum.trim().equals("") || aPrivateKey.trim().equals("") ) {
                JOptionPane.showMessageDialog(null,"You need to fill all of the fields!");
            }
            else {
                StudentDB studentDB = new StudentDB(Constants.dbPath);
                Student student = new Student(aName,aSurName,aSchoolNum,aPrivateKey);
                try {
                    studentDB.addStudent(student);
                } catch (IOException ex) {
                    Logger.getLogger(AddStudentWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                rootView.refreshFrame();
                this.frame.dispose();
            }
       
            
        }
        
    }

}
