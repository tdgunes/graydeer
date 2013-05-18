/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. 
 * All rights reserved.
 *
 * Package: instructor
 * @author tdgunes
 */

package instructor;

import homeworks.Homework;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import server.student.Student;
import server.student.StudentDB;


public class InstructorGUI {
    //adding workdir
    private static StudentDB studentDB = new StudentDB("/Users/tdgunes/homeworks/");
    private static JFrame frame = new JFrame();
    
    private static int columns = 5; //this is static
    private int rows = 3; //this can be changed by the data rows-1 total data without tile
    
    public static void main(String[] args) {
         InstructorGUI myGUI = new InstructorGUI();
    }
    public InstructorGUI() {
        frame.setLayout(new BorderLayout());
        
        ArrayList<Student> students = studentDB.getStudents();
     /*   for (Student student : students) {
            JButton button = new JButton(student.getName() + " " + student.getSurname()+" "+student.getSchoolNumber());
            button.addActionListener(new HomeworkListener(student.getPrivateKey()));
            frame.add(button);
        }*/
        
        frame.setBackground(Color.WHITE);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(columns*150,rows*30);
        frame.setLayout(new BorderLayout());
        // This is the margin on the left-hand side of the frame
        JLabel margin = new JLabel("    ");
        frame.add(margin, BorderLayout.WEST);
        
        frame.setTitle("GrayDeer Instructor");
        
        
        rows=rows+1;
        
        JPanel panel = new JPanel(new GridLayout(1,5));
        addTitles(panel);
        
        
        frame.add(panel);
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
    
    
    
    	private void addTitles(JPanel panel) {
		JLabel schoolNum = new JLabel("School Number");

		// Sets the font to bold
		Font newLabelFont=new Font(schoolNum.getFont().getName(),Font.BOLD,schoolNum.getFont().getSize());
		schoolNum.setFont(newLabelFont);

                //Tile Name
		JLabel name = new JLabel("Name");
		name.setFont(newLabelFont);

                //Tile Surname
		JLabel surname = new JLabel("Surname");
		surname.setFont(newLabelFont);

                //Tile Number of Homeworks
		JLabel numberOfHom = new JLabel("Number");
		numberOfHom.setFont(newLabelFont);
                
                JLabel details = new JLabel("Details");
                details.setFont(newLabelFont);

		panel.add(schoolNum);
		panel.add(name);
		panel.add(surname);
		panel.add(numberOfHom);
                panel.add(details);
	}
}

