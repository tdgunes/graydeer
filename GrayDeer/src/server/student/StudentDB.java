/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import homeworks.Homework;
import homeworks.examples.HW1;
import homeworks.examples.HW2;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public final class StudentDB {
    /**
     *
     */
    public String workDir = "";
    
    // constant studentDB name which will be placed inside
    //workdir
    private String dbName = "students.db";
    private String dbFilePath = "";
    //this will be initialized
    
    
    private ObjectInputStream input;
    private  ObjectOutputStream output;
    
    /**
     *
     * @param workdir
     */
    public StudentDB(String workdir){
        this.workDir = workdir;
        this.dbFilePath = Utils.combine(workdir, this.dbName);
        //System.out.println("Students: "+this.getStudents().size());  
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Student getStudentWithKey(String key){
        // NOT TESTED
        Student searchedOne = null;
        for(Student student: this.getStudents()){
            if (student.getPrivateKey().equals(key)){
                System.out.println("Key matches: "+ key);
                System.out.println("Student Name: "+student.getName());
                searchedOne = student;
                break;
            }
        }
        return searchedOne;
    }
    /**
     *
     * @param key
     * @return
     * @throws FileNotFoundException
     */
    public ArrayList<Homework> getHomeworksOfAStudentByKey(String key) throws FileNotFoundException{

        //High level
        Student student = this.getStudentWithKey(key);
        return student.getHomeworks();
    } 
    
    
    /**
     *
     * @return
     */
    public ArrayList<Student> getStudents() {
        this.openFileReadMode();
        ArrayList<Student> students = new ArrayList<Student>();


        try {
            while (true) {
                students.add((Student) input.readObject());
            }
        } catch (EOFException endOfFileException) {
            return students; //reading is finished
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Unable to create object.");
        } catch (IOException ioException) {
            System.err.println("Error during reading from file.");
        } catch (NullPointerException e){
            System.err.println("DB is empty! or not exists");
        }
        this.closeFileReadMode();
    
        return null;


    }
    /**
     *
     * @param student
     */
    public void saveStudent(Student student) throws IOException {
       ArrayList <Student> students = new ArrayList<Student>();
       for(Student stu: students){
           if (stu.getPrivateKey().equals(student.getPrivateKey())){
               stu = student;
           }
       }
       this.setStudents(students);
    }
    /**
     *
     * @param students
     * @throws IOException
     */
    public void setStudents(ArrayList<Student> students) throws IOException{
        this.openFileWriteMode();
        for (Student student : students) {
            output.writeObject(student);
        }
        this.closeFileWriteMode();
        System.out.println("Number of students are set: "+students.size());
    }
    
    //adding to the end of the file
    /**
     *
     * @param student
     * @throws IOException
     */
    public void addStudent(Student student) throws IOException{
       ArrayList <Student> currentStudents = this.getStudents();
       currentStudents.add(student);
       
       this.setStudents(currentStudents);
    }

    
    //WRITEMODE
    /**
     *
     */
    public void openFileWriteMode() {
        try {
            output = new ObjectOutputStream(new FileOutputStream(
                    this.dbFilePath));
        } catch (IOException ioException) {
            System.err.println("Error opening file. WRITEMODE");
        }
    }

    /**
     *
     */
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
    /**
     *
     */
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

    
    /**
     *
     */
    public void openFileReadMode() {
        try {
            input = new ObjectInputStream(new FileInputStream(
                    this.dbFilePath));
        } catch(FileNotFoundException fileNotException){
            System.err.println("Error opening file. READMODE");
            fileNotException.printStackTrace();
        }
        catch (IOException ioException) {
            System.err.println("Error opening file. READMODE");
            ioException.printStackTrace();
        }
    }
}
