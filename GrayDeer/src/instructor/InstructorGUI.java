/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. All rights reserved.
 *
 * Package: instructor
 *
 * @author tdgunes
 */
package instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import server.Constants;
import server.student.Student;
import server.student.StudentDB;

public class InstructorGUI {
    //adding workdir

    private static StudentDB studentDB = new StudentDB(Constants.dbPath);
    private static JFrame frame = new JFrame();
    private static int columns = 4; //this is static
    private int rows = 1; //this can be changed by the data rows-1 total data without tile

    public static void main(String[] args) {
        InstructorGUI myGUI = new InstructorGUI();
    }

    public InstructorGUI() {
        this.refreshFrame();

    }
    public void refreshFrame() {
        frame.removeAll();
        frame = new JFrame();
  

        ArrayList<Student> students = studentDB.getStudents();
        /*   for (Student student : students) {
         JButton button = new JButton(student.getName() + " " + student.getSurname()+" "+student.getSchoolNumber());
         button.addActionListener(new HomeworkListener(student.getPrivateKey()));
         frame.add(button);
         }*/

        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        rows = 1 + students.size();
        frame.setSize(columns * 150, (rows+1) * 40);
        frame.setLayout(new BorderLayout());
        // This is the margin on the left-hand side of the frame
        JLabel margin = new JLabel("    ");
        frame.add(margin, BorderLayout.WEST);
        frame.add(this.getControlPanel(frame),BorderLayout.NORTH);

        frame.setTitle("GrayDeer Instructor - " + students.size() +" students in "+Constants.dbName);


        

        JPanel panel = new JPanel(new GridLayout(rows, columns));
        addTitles(panel);
        addStudentsToTable(students,panel);
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    private JPanel getControlPanel(JFrame frame){
        
        JPanel controlPanel = new JPanel(new FlowLayout());
        
        JButton removeAll = new JButton("Remove All");
        removeAll.addActionListener(new ButtonListener(this,frame));
        JButton addNew = new JButton("Add New Student");
        addNew.addActionListener(new ButtonListener(this,frame));
        
        //this button required for the cases, if the db file is edited by server
        JButton refresh = new JButton("Refresh"); 
        refresh.addActionListener(new ButtonListener(this,frame));
        
        JButton about = new JButton("About"); 
        about.addActionListener(new ButtonListener(this,frame));
        
        controlPanel.add(addNew);
        controlPanel.add(refresh);
        controlPanel.add(removeAll);        
        controlPanel.add(about);
        
        return(controlPanel);
        
    }
    
    private void addStudentsToTable(ArrayList<Student> students, JPanel panel){
        for (Student student : students) {
            JLabel schoolNum = new JLabel(student.getSchoolNumber());
            JLabel name = new JLabel(student.getName());
            JLabel surname = new JLabel(student.getSurname());
            JLabel number = new JLabel(""+student.getHomeworks().size());
            JButton detail = new JButton("Click");
            detail.addActionListener(new HomeworkListener(student.getPrivateKey(),this));
            
            panel.add(schoolNum);
            panel.add(name);
            panel.add(surname);
            panel.add(number);
            panel.add(detail);
            
        }
    }

    private void addTitles(JPanel panel) {
        JLabel schoolNum = new JLabel("School Number");

        // Sets the font to bold
        Font newLabelFont = new Font(schoolNum.getFont().getName(), Font.BOLD, schoolNum.getFont().getSize());
        schoolNum.setFont(newLabelFont);

        //Tile Name
        JLabel name = new JLabel("Name");
        name.setFont(newLabelFont);

        //Tile Surname
        JLabel surname = new JLabel("Surname");
        surname.setFont(newLabelFont);

        //Tile Number of Homeworks
        JLabel numberOfHom = new JLabel("H's Number");
        numberOfHom.setFont(newLabelFont);

        JLabel details = new JLabel("Details");
        details.setFont(newLabelFont);

        panel.add(schoolNum);
        panel.add(name);
        panel.add(surname);
        panel.add(numberOfHom);
        panel.add(details);
    }

    class HomeworkListener implements ActionListener {

        private String privatekey = null;
        private InstructorGUI rootView = null;
     
        
        public HomeworkListener(String privatekey, InstructorGUI rootView) {
            this.privatekey = privatekey;
            this.rootView = rootView;
     
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                //this will open a new frame
                frame.dispose();
                HomeworkWindow myWindow = new HomeworkWindow(rootView,privatekey);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InstructorGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
   class ButtonListener implements ActionListener {

        private InstructorGUI rootView = null;
        private JFrame frame = null;
        
        public ButtonListener(InstructorGUI rootView, JFrame frame) {
            this.rootView = rootView;
            this.frame = frame;
        }
        
        @Override
		public void actionPerformed(ActionEvent ae) {
            JButton source = (JButton) ae.getSource();
            if (source.getText().equals("Remove All")) {
                System.out.println("Remove All");
                ArrayList<Student> students = new ArrayList<Student>();
                try {
                    frame.dispose();
                    studentDB.setStudents(students);
                    rootView.refreshFrame();
                } catch (IOException ex) {
                    Logger.getLogger(InstructorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if (source.getText().equals("Add New Student")) {
                frame.dispose();
                System.out.println("Add New Student");
                AddStudentWindow myWindow = new AddStudentWindow(rootView);
                myWindow.showWindow();
            }
            
            else if (source.getText().equals("Refresh")) {
                frame.dispose();
                rootView.refreshFrame();
           } else if (source.getText().equals("About")) {
               frame.dispose();
               JOptionPane.showMessageDialog(null,"Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener.\n"
                       + " All rights reserved.", "GrayDeer - Instructor GUI", JOptionPane.INFORMATION_MESSAGE);
               rootView.refreshFrame();
           }
       }
       
       
   }
}
