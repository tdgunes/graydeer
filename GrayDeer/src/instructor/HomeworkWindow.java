/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. All rights reserved.
 *
 * Package: instructor
 *
 * @author tdgunes
 */
package instructor;

import homeworks.Homework;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
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

public class HomeworkWindow {
    //adding workdir

    private static StudentDB studentDB = new StudentDB(Constants.dbPath);
    private static JFrame frame = new JFrame();
    private static int columns = 4; //this is static
    private int rows = 1; //this can be changed by the data rows-1 total data without tile
    private String privateKey = "";
    private InstructorGUI rootView = null;

    public HomeworkWindow(InstructorGUI rootView, String privateKey) throws FileNotFoundException {
        this.privateKey = privateKey;
        this.rootView = rootView;
        this.refreshFrame();
    }
    
    public void refreshFrame() throws FileNotFoundException {
        
        frame.removeAll();
        
        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        Student student = studentDB.getStudentWithKey(privateKey);
        ArrayList<Homework> homeworks = student.getHomeworks();
        /*   for (Student student : students) {
         JButton button = new JButton(student.getName() + " " + student.getSurname()+" "+student.getSchoolNumber());
         button.addActionListener(new HomeworkListener(student.getPrivateKey()));
         frame.add(button);
         }*/

        frame.setBackground(Color.WHITE);

        rows = 1 + homeworks.size();
        frame.setSize(columns * 150, rows * 50);
        frame.setLayout(new BorderLayout());
        // This is the margin on the left-hand side of the frame
        JLabel margin = new JLabel("    ");
        frame.add(margin, BorderLayout.WEST);
        frame.add(this.getControlPanel(frame), BorderLayout.NORTH);

        frame.setTitle("GrayDeer Instructor - "+student.getName()+" "+student.getSurname());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
            }
        });


        JPanel panel = new JPanel(new GridLayout(rows, columns));
        addTitles(panel);
        addHomeworksToTable(homeworks, panel);

        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
        
    }
    
    public void onExit() {
        rootView.refreshFrame();
        frame.dispose();
    }

    private JPanel getControlPanel(JFrame frame) {

        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton removeAll = new JButton("Remove All");
        removeAll.addActionListener(new ButtonListener(this,frame));
        JButton addNew = new JButton("Add New Homework");
        addNew.addActionListener(new ButtonListener(this,frame));

        controlPanel.add(removeAll);
        controlPanel.add(addNew);

        return (controlPanel);

    }

    private void addTitles(JPanel panel) {



        JLabel name = new JLabel("Name");
        Font newLabelFont = new Font(name.getFont().getName(), Font.BOLD, name.getFont().getSize());
        name.setFont(newLabelFont);


        JLabel status = new JLabel("Status");
        status.setFont(newLabelFont);


        JLabel actions = new JLabel("Actions");
        actions.setFont(newLabelFont);

        JLabel grade = new JLabel("Grade");
        grade.setFont(newLabelFont);
        
        JLabel details = new JLabel("Details");
        details.setFont(newLabelFont);

        panel.add(name);
        panel.add(status);
        panel.add(actions);
        panel.add(grade);
        panel.add(details);

    }

    private void addHomeworksToTable(ArrayList<Homework> homeworks, JPanel panel) {
        for (Homework homework : homeworks) {
            JLabel name = new JLabel(homework.getHomeworkName());
            JLabel status = new JLabel(homework.getStatus());
            JLabel actions = new JLabel(homework.getActions());
            JLabel grade = new JLabel(homework.getGrade());
            
            JButton detail = new JButton("Click");
            detail.addActionListener(new HomeworkListener(""));
            panel.add(name);
            panel.add(status);
            panel.add(actions);
            panel.add(grade);
            panel.add(detail);

        }
    }

    //Get test cases from here
    class HomeworkListener implements ActionListener {

        private String privatekey = null;

        public HomeworkListener(String privatekey) {
            this.privatekey = privatekey;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            //this will open a new frame with results of the test cases 
            //like a history
            
        }
    }

    class ButtonListener implements ActionListener {

        private HomeworkWindow rootView = null;
        private JFrame frame = null;

        public ButtonListener(HomeworkWindow rootView, JFrame frame) {
            this.rootView = rootView;
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent ae) {
            JButton source = (JButton) ae.getSource();
            if (source.getText().equals("Remove All")) {
                System.out.println("Remove All");
            } else if (source.getText().equals("Add New Homework")) {
                System.out.println("Add New Homework");
                this.frame.dispose();
                AddHomeworkWindow myWindow = new AddHomeworkWindow(rootView,privateKey);
                myWindow.showWindow();
            }
        }
    }
}
