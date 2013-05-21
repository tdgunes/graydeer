/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. All rights reserved.
 *
 * Package: client
 *
 * @author tdgunes
 */
package client;

import gui.FileChooser;
import gui.GridTable;
import gui.HTTPLib;
import gui.ResultsPanel;
import instructor.AddStudentWindow;
import instructor.HomeworkWindow;
import instructor.InstructorGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import server.Constants;
import server.Utils;
import server.student.Student;
import server.student.StudentDB;

public class StudentGUI {

    private static StudentDB studentDB = new StudentDB(Constants.dbPath);
    private static JFrame frame = new JFrame();
    private static int columns = 4;
    private int rows = 1;
    private String privateKey = "";

    public static void main(String[] args) {
        StudentGUI myGUI = new StudentGUI();
    }

    public StudentGUI() {
        this.refreshFrame();
    }

    public void refreshFrame() {
        frame.removeAll();
        frame = new JFrame();


        frame.setTitle("GrayDeer - Student GUI");


        frame.setBackground(Color.WHITE);

        frame.setLayout(new BorderLayout());
        // This is the margin on the left-hand side of the frame
        JLabel margin = new JLabel("    ");
        frame.add(margin, BorderLayout.WEST);
        frame.add(this.getControlPanel(frame), BorderLayout.NORTH);





        this.privateKey = this.getPrivateKey();
        Object[][] readData = this.fetchData(Constants.hostName);
        rows = 1 + readData.length;


        JPanel panel = new JPanel(new GridLayout(rows, columns));
        frame.setSize(columns * 150, (rows + 1) * 40);

        addTitles(panel);
        this.addHomeworksToTable(readData, panel);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);




    }

    private void addHomeworksToTable(Object[][] readData, JPanel panel) {
        for (int i = 0; i < readData.length; i++) {
            JLabel hwName = new JLabel((String) readData[i][0]);
            JLabel deadline = new JLabel((String) readData[i][1]);
            JLabel grade = new JLabel((String) readData[i][2]);
            JButton action = new JButton((String) readData[i][3]);
            action.addActionListener(new HomeworkListener(privateKey, this, 
                    hwName.getText(),grade.getText()));
            

            panel.add(hwName);
            panel.add(deadline);
            panel.add(grade);
            panel.add(action);
        }
    }

    private String getPrivateKey() {
        String readFile = Utils.readFromFile(Constants.privateKeyPreDef);
        String reply = "";
        if (readFile.equals("FNF")
                || readFile.equals("") || readFile.equals("null")) {

            reply = JOptionPane.showInputDialog(null, "Welcome to GrayDeer,"
                    + "as for first start, you need to enter your private-key,"
                    + " which is given by your instructor to you",
                    "your private key!");
            try {
                //writing the given reply to the predefined path     
                Utils.writeAFile(Constants.privateKeyPreDef, reply);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GridTable.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        } else {
            reply = readFile;
        }

        return reply;
    }



    private JPanel getControlPanel(JFrame frame) {
        JPanel controlPanel = new JPanel(new FlowLayout());



        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ButtonListener(this, frame));

        JButton about = new JButton("About");
        about.addActionListener(new ButtonListener(this, frame));


        controlPanel.add(refresh);

        controlPanel.add(about);

        return (controlPanel);
    }

    private void addTitles(JPanel panel) {

        JLabel hwName = new JLabel("HW Name");

        // Sets the font to bold
        Font newLabelFont = new Font(hwName.getFont().getName(), Font.BOLD,
                hwName.getFont().getSize());
        hwName.setFont(newLabelFont);

        JLabel deadline = new JLabel("Deadline");
        deadline.setFont(newLabelFont);

        JLabel grade = new JLabel("Grade");
        grade.setFont(newLabelFont);

        JLabel action = new JLabel("Action");
        action.setFont(newLabelFont);


        panel.add(hwName);
        panel.add(deadline);
        panel.add(grade);
        panel.add(action);


    }

    private Object[][] fetchData(String hostName) {
        Object[][] data = null;
        try {

            // get privatekey from a window
            HTTPLib httpLib = new HTTPLib(hostName, privateKey);
            int listNum = 0;
            String response = httpLib.postData("fetch", "", "");
            System.out.println("||| GrayDeer POST response: " + response);
            String[] lines = HTTPLib.splitItWithString(response, "+=+");

            //fetched data
            listNum = lines.length;
            System.out.println("ListNum: " + listNum);
            data = new Object[listNum][4];

            for (int i = 0; i < lines.length; i++) {

                String string = lines[i];
                System.out.println(string);
                String[] parsedItems = HTTPLib.splitItWithString(string, "**");
                System.arraycopy(parsedItems, 0, data[i], 0, 4);

            }
            return data;
        } catch (ConnectException er) {
            //unable to get the list, server is unavailable

            int reply = JOptionPane.showConfirmDialog(null, "Unable to access to"
                    + " the host(\"" + hostName + ")\"\n"
                    + "Do you want to try to connect again ?", "Warning!", 
                    JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                //looping :)
                return this.fetchData(hostName);
            } else {
                System.exit(0);
            }
        } catch (MalformedURLException ex) {

            Logger.getLogger(GridTable.class.getName()).log(Level.SEVERE, null,
                    ex);
        } catch (IOException ex) {
            //if the key is not found in the server 

            JOptionPane.showMessageDialog(null, "Your data is not found in the "
                    + "server, if you have written\n"
                    + "wrongly you need to change it from " 
                    + Constants.privateKeyPreDef + "\nif not, "
                    + "you need to contact to your instructor.", 
                    "GrayDeer - Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        return null;

    }

    class ButtonListener implements ActionListener {

        private StudentGUI rootView = null;
        private JFrame frame = null;

        public ButtonListener(StudentGUI rootView, JFrame frame) {
            this.rootView = rootView;
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton source = (JButton) ae.getSource();


            if (source.getText().equals("Refresh")) {
                frame.dispose();
                rootView.refreshFrame();
            } else if (source.getText().equals("About")) {
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Copyright (c) 2013 by "
                        + "Taha Doğan Güneş and Eren Sezener.\n"
                        + " All rights reserved.", "GrayDeer - Student GUI",
                        JOptionPane.INFORMATION_MESSAGE);
                rootView.refreshFrame();
            }
        }
    }
    class HomeworkListener implements ActionListener {

        private String privatekey = null;
        private StudentGUI rootView = null;
        private String homeworkName = null;
        private String totalGrade = null;
        
        public HomeworkListener(String privatekey, StudentGUI rootView,
                String homeworkName, String totalGrade) {
            this.privatekey = privatekey;
            this.rootView = rootView;
            this.homeworkName = homeworkName;
            this.totalGrade = totalGrade;

        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton source = (JButton) ae.getSource();
            
            if (source.getText().equals("See Notes")) {//see notes window here
                ResultsPanel rp = new ResultsPanel(homeworkName, privateKey,this.totalGrade);
		rp.initializePanel();
            }
            else if (source.getText().equals("Upload")) { //upload view here
                frame.dispose();
                FileChooser fc = new FileChooser();
                String homeworkPath = fc.showFileChooser();
                if (homeworkPath.equals("null")){
                    //user canceled
                }
                else {
                    String homeworkSource = Utils.readFromFile(homeworkPath);
                    System.out.println("Source\n" + homeworkSource);
                    HTTPLib myLib = new HTTPLib(Constants.hostName, privateKey);
                    String response = null;
                    try {
                        response = myLib.postData("submit", homeworkSource,
                                homeworkName);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(GridTable.class.getName()).log(
                                Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(GridTable.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, response);

                 
                }
                rootView.refreshFrame();

            }


        }
    }
}
