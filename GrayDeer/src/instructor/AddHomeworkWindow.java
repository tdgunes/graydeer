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
    private AvailableHomeworks availableHomeworks = new AvailableHomeworks();
    public AddHomeworkWindow(HomeworkWindow rootView, String privateKey) {
        this.rootView = rootView;
        this.privateKey = privateKey;
    }

    public void showWindow(){
        
        Object[] currentHomeworks = availableHomeworks.availableHomeworks.keySet().toArray();
        
        JFrame window = new JFrame();
        window.setLayout(new GridLayout(2,1));
        window.setTitle("Add a Homework");
        
       

        
        
        JButton submitButton = new JButton("Add Homework");
        
        JPanel namePane = new JPanel(new GridLayout(1,2));
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JComboBox nameField = new JComboBox(currentHomeworks);
        namePane.add(nameLabel);
        namePane.add(nameField);
        window.add(namePane);
   
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        submitButton.addActionListener(new ButtonListener(nameField, window));
        window.add(submitButton);
        window.setLocationRelativeTo(null);
        window.setSize(240,100);
        window.setVisible(true);
        
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class ButtonListener implements ActionListener {

        private JComboBox name;

        private JFrame frame;
        
        public ButtonListener(JComboBox name, JFrame frame) {
                this.name = name;
                this.frame = frame;
        }



        public void actionPerformed(ActionEvent ae) {
           /* System.out.println("Name: " + this.name);
            System.out.println("Surname: " + this.surname);
            System.out.println("SchoolNum: "+ this.schoolNum);
            System.out.println("PrivateKey: " + this.privateKey);*/
            String aName = (String) this.name.getSelectedItem();

            if (aName.trim().equals("") ) {
                JOptionPane.showMessageDialog(null,"You need to fill all of the fields!");
            }
            else {
                StudentDB studentDB = new StudentDB(Constants.dbPath);
                Student student = studentDB.getStudentWithKey(privateKey);
                
                try {
                    Homework homework = null;
                    if (availableHomeworks.availableHomeworks.get(aName).equals("HW1")){
                        homework = new HW1("");
                    }
                    else if (availableHomeworks.availableHomeworks.get(aName).equals("HW2")){
                        homework = new HW2("");
                    }
                    else if (availableHomeworks.availableHomeworks.get(aName).equals("HW3")){
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
