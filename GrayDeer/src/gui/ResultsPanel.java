package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JPanel;



public class ResultsPanel {

    String hwName;
    String privateKey;
    // There are four columns namely Inputs, Outputs, Answers, Grades
    // Number of rows is equal to the number of homeworks
    private static final int COLUMNS = 3;
    private int rows = 5;
    private JPanel base;
    private JFrame newFrame;
    private String totalGrade;


    public ResultsPanel() {
        super();
    }

    public ResultsPanel(String hwName, String privateKey, String totalGrade) {
        super();
        this.hwName = hwName;
        this.privateKey = privateKey;
        this.totalGrade = totalGrade;
    }

    public void initializePanel() {
        Object[][] data = getData();
        rows = data.length;


        newFrame = new JFrame();
        newFrame.setTitle("GrayDeer - Results: " + this.hwName);
        newFrame.setSize(600, rows * 50);
        newFrame.setLocation(200, 100);
        newFrame.setLayout(new BorderLayout());
        newFrame.setBackground(Color.WHITE);

        JLabel margin = new JLabel("    ");
        newFrame.add(margin, BorderLayout.WEST);

        base = new JPanel();
        base.setLayout(new GridLayout(rows + 2, COLUMNS));
        newFrame.add(base, BorderLayout.CENTER);

        addTable(data);
        addObjectionButon();
        this.addTotalGrade();
        newFrame.setVisible(true);

    }

    private Object[][] getData() {
        Object[][] data = null;
        
        int listNum = 0;
        HTTPLib myLib = new HTTPLib(Constants.hostName, privateKey);
        String response = null;
        try {
            response = myLib.postData("getcases", "", hwName);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GridTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GridTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(response);

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
    }

    private void addObjectionButon() {
        JPanel buttonPanel = new JPanel();
        JButton objectionButton = new JButton("Object Your Grade");
        objectionButton.addActionListener(new ButtonHandler(objectionButton));
        buttonPanel.add(objectionButton);
        newFrame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addTotalGrade(){
        JLabel hwName = new JLabel("");

        // Sets the font to bold
        Font newLabelFont = new Font(hwName.getFont().getName(), Font.BOLD, hwName.getFont().getSize());
        hwName.setFont(newLabelFont);

        JLabel deadline = new JLabel("");
        deadline.setFont(newLabelFont);

        JLabel grade = new JLabel("Total Grade: ");
        grade.setFont(newLabelFont);

        JLabel action = new JLabel(this.totalGrade);
        action.setFont(newLabelFont);

        base.add(hwName);
        base.add(deadline);
        base.add(grade);
        base.add(action);
    }
    // Adds homework results to the pane
    private void addTable(Object[][] data) {

        addTitles();

        System.out.println("Rows: " + rows + "Columns: " + COLUMNS);
        for (int i = 0; i < rows; i++) {

            JLabel testInput = new JLabel((String) data[i][0]);



            JLabel output = new JLabel((String) data[i][1]);


            JLabel answer = new JLabel((String) data[i][2]);


            JLabel grade = new JLabel((String) data[i][3]);

            if (output.getText().equals(answer.getText())){ // make grade green
                grade.setForeground(Color.GREEN);
            }
            else { // make grade red
                grade.setForeground(Color.RED);
            }
            
            base.add(testInput);
            base.add(output);
            base.add(answer);
            base.add(grade);


           
        }


    }

    // Adds first row of the table
    private void addTitles() {
        JLabel hwName = new JLabel("Test Input");

        // Sets the font to bold
        Font newLabelFont = new Font(hwName.getFont().getName(), Font.BOLD, hwName.getFont().getSize());
        hwName.setFont(newLabelFont);

        JLabel deadline = new JLabel("Your Output");
        deadline.setFont(newLabelFont);

        JLabel grade = new JLabel("Correct Answer");
        grade.setFont(newLabelFont);

        JLabel action = new JLabel("Your Grade");
        action.setFont(newLabelFont);

        base.add(hwName);
        base.add(deadline);
        base.add(grade);
        base.add(action);
    }

    class ButtonHandler implements ActionListener {

    JButton button;

    public ButtonHandler(JButton button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObjectionPanel op = new ObjectionPanel(button,privateKey,hwName);
        op.initializeObjectionPanel();
    }
}
}

