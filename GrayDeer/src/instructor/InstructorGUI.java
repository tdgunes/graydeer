/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. 
 * All rights reserved.
 *
 * Package: instructor
 * @author tdgunes
 */

package instructor;

import homeworks.Homework;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import server.student.Student;
import server.student.StudentDB;


public class InstructorGUI {
    //adding workdir
    private static StudentDB studentDB = new StudentDB("/Users/tdgunes/homeworks/");
    private static JFrame frame = new JFrame();
    
    public static void main(String[] args) {
         InstructorGUI myGUI = new InstructorGUI();
    }
    public InstructorGUI() {
        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("Students: "));
        ArrayList<Student> students = studentDB.getStudents();
        for (Student student : students) {
            JButton button = new JButton(student.getName() + " " + student.getSurname()+" "+student.getSchoolNumber());
            button.addActionListener(new HomeworkListener(student.privateKey));
            frame.add(button);
        }
        frame.setSize(200, 200);
        frame.setVisible(true);
    }


    class HomeworkListener implements ActionListener {
        
        private String privatekey = null;
     
        public HomeworkListener(String privatekey){
            this.privatekey= privatekey;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            ArrayList<Homework> homeworks = null;
            try {
                homeworks = studentDB.getHomeworksOfAStudentByKey(privatekey);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InstructorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            String homeworkCount = privatekey+" "+homeworks.size();
            String homeworkList = homeworkCount + " \n";
            for(Homework homework: homeworks) {
                homeworkList = homeworkList + " "+ homework.getHomeworkName();
            }
            JOptionPane.showMessageDialog(frame, homeworkList);
        }
    }

}

