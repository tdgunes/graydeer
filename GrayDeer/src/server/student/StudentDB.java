/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import server.Utils;

/**
 *
 * @author tdgunes
 */
public class StudentDB {
    public String workDir = "";
    
    // constant studentDB name which will be placed inside
    //workdir
    private String dbName = "students.db";
    private String dbFilePath = "";
    //this will be initialized
    
    
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    
    public StudentDB(String workdir){
        this.workDir = workdir;
        this.dbFilePath = Utils.combine(workdir, this.dbName);
        
    }
    
    public ArrayList<Student> getStudents() {
        this.openFileReadMode();
        ArrayList<Student> students = new ArrayList<Student>();


        try {
            while (true) {
                students.add((Student) input.readObject());
            }
        } catch (EOFException endOfFileException) {
            System.err.println(""); //reading is finished
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Unable to create object.");
        } catch (IOException ioException) {
            System.err.println("Error during reading from file.");
        }
        this.closeFileReadMode();
        
        return students;

    }

    //directly overwritting the file
    public void setStudents(ArrayList<Student> students) throws IOException{
        this.openFileWriteMode();
        for (Student student : students) {
            output.writeObject(student);
        }
        this.closeFileWriteMode();
    }
    
    //adding to the end of the file
    public void addStudent(Student student) throws IOException{
       ArrayList <Student> currentStudents = this.getStudents();
       currentStudents.add(student);
       
       this.setStudents(currentStudents);
    }

    
    //WRITEMODE
    public void openFileWriteMode() {
        try {
            output = new ObjectOutputStream(new FileOutputStream(
                    this.dbFilePath));
        } catch (IOException ioException) {
            System.err.println("Error opening file. WRITEMODE");
        }
    }

    public void closeFileWriteMode() {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioException) {
            System.err.println("Error closing file. WRITEMODE");
            System.exit(1);
        }
    }

    //READMODE
    public void closeFileReadMode() {
        try {
            if (input != null) {
                input.close();
            }
            System.exit(0);
        } catch (IOException ioException) {
            System.err.println("Error closing file. READMODE");
            System.exit(1);
        }
    }

    
    public void openFileReadMode() {
        try {
            input = new ObjectInputStream(new FileInputStream(
                    this.dbFilePath));
        } catch (IOException ioException) {
            System.err.println("Error opening file. READMODE");
        }
    }
}