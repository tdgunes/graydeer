/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. 
 * All rights reserved.
 *
 * Package: instructor
 * @author tdgunes
 */

package instructor;

import homeworks.Homework;
import homeworks.examples.AvailableHomeworks;
import homeworks.examples.HW1;
import homeworks.examples.HW2;
import homeworks.examples.HW3;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import server.Constants;
import server.student.Student;
import server.student.StudentDB;


public class AddHomeworkWindow implements ItemListener {
    private HomeworkWindow rootView = null;
    private String privateKey = null;
    public AddHomeworkWindow(HomeworkWindow rootView, String privateKey) {
        this.rootView = rootView;
        this.privateKey = privateKey;
    }

    public void showWindow(){
        AvailableHomeworks availableHomeworks = new AvailableHomeworks();
        
        JFrame window = new JFrame();
        window.setLayout(new GridLayout(6,1));
        window.setTitle("Add a Homework");
        
       

        
        
        JButton submitButton = new JButton("Add Homework");
        
        JPanel namePane = new JPanel(new GridLayout(1,2));
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField nameField = new JTextField();
        namePane.add(nameLabel);
        namePane.add(nameField);
        window.add(namePane);
   
        JPanel statusPane = new JPanel(new GridLayout(1, 2));
        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField statusField = new JTextField();
        statusPane.add(statusLabel);
        statusPane.add(statusField);
        window.add(statusPane);

        JPanel actionsPane = new JPanel(new GridLayout(1, 2));
        JLabel actionsLabel = new JLabel("Actions: ");
        actionsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField actionsField = new JTextField();
        actionsPane.add(actionsLabel);
        actionsPane.add(actionsField);
        window.add(actionsPane);
        
        
        

        JPanel gradePane = new JPanel(new GridLayout(1, 3));
        JLabel gradeLabel = new JLabel("Grade:(N/A");
        gradeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JTextField gradeField = new JTextField();
        gradePane.add(gradeLabel);
        gradePane.add(gradeField);
        window.add(gradePane);
        
   
        
        submitButton.addActionListener(new ButtonListener(nameField, statusField, 
                actionsField, gradeField, window));
        window.add(submitButton);
        window.setLocationRelativeTo(null);
        window.setSize(200,240);
        window.setVisible(true);
        
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class ButtonListener implements ActionListener {

        private JTextField name;
        private JTextField status;
        private JTextField actions;
        private JTextField grade;
        private JFrame frame;
        
        public ButtonListener(JTextField name, JTextField status, 
                JTextField actions, JTextField grade, JFrame frame) {
                this.name = name;
                this.status = status;
                this.actions = actions;
                this.grade = grade;
                this.frame = frame;
        }



        public void actionPerformed(ActionEvent ae) {
           /* System.out.println("Name: " + this.name);
            System.out.println("Surname: " + this.surname);
            System.out.println("SchoolNum: "+ this.schoolNum);
            System.out.println("PrivateKey: " + this.privateKey);*/
            String aName = (String) this.name.getText();
            String aSurName = this.status.getText();
            String aSchoolNum = this.actions.getText();
            String aPrivateKey = this.grade.getText();
            if (aName.trim().equals("") || aSurName.trim().equals("") 
                    || aSchoolNum.trim().equals("") || aPrivateKey.trim().equals("") ) {
                JOptionPane.showMessageDialog(null,"You need to fill all of the fields!");
            }
            else {
                StudentDB studentDB = new StudentDB(Constants.dbPath);
                Student student = studentDB.getStudentWithKey(privateKey);
                
                try {
                    Homework homework = null;
                    if (AvailableHomeworks.availableHomeworks.get(aName).equals("HW1")){
                        homework = new HW1("");
                    }
                    else if (AvailableHomeworks.availableHomeworks.get(aName).equals("HW2")){
                        homework = new HW2("");
                    }
                    else if (AvailableHomeworks.availableHomeworks.get(aName).equals("HW3")){
                        homework = new HW3("");
                    }
                    
                    ArrayList<Homework> homeworkList = student.getHomeworks();
                    homeworkList.add(homework);
                    student.setHomeworks(homeworkList);
                    studentDB.saveStudent(student);
                } catch (IOException ex) {
                    Logger.getLogger(AddStudentWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                rootView.refreshFrame();
                frame.dispose();      
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddHomeworkWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }
}
