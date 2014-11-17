/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import homeworks.Homework;
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
    private String dbName = Constants.dbName;
    private String dbFilePath = "";
    //this will be initialized
    
    
    private ObjectInputStream input;
    private  ObjectOutputStream output;
    
    /**
     *
     * @param workdir
     */
    public StudentDB(String workdir){
        //Constructor for StudentDB
        //It defines workdir and combines workdir and database name(aka dbName)
        
        this.workDir = workdir;
        this.dbFilePath = Utils.combine(workdir, this.dbName);
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Student getStudentWithKey(String key){
        //Searches database for finding a student with its own private key
        //If student is not found, function returns null
        //It is required to check whether it is null or not in the usage
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
        //This is just for simply getting homeworks of a student with giving
        //with using other functions.
        Student student = this.getStudentWithKey(key);
        return student.getHomeworks();
    } 
    
    
    /**
     *
     * @return
     */
    public ArrayList<Student> getStudents() 
    {
        //Gets all of the students that are inside the database
        
        //Opens DB in read mode
        this.openFileReadMode();
        
        //Creates an empty arraylist
        ArrayList<Student> students = new ArrayList<Student>();


        
        try {
            //tries to add all of them until EOF is thrown
            while (true) {
                
                students.add((Student) input.readObject());
            }
        } catch (EOFException endOfFileException) {
            return students; //reading is finished
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Unable to create object.");
        } catch (IOException ioException) {
            System.err.println("Error during reading from file.");
            ioException.printStackTrace();
        } catch (NullPointerException e){
            System.err.println("DB is empty! or not exists");
        }
        //closes the database file
        this.closeFileReadMode();
    
        return null;


    }
    /**
     *
     * @param student
     */
    public void saveStudent(Student student) throws IOException {
      
       //final student objects that will be saved in the DB
       ArrayList <Student> students = new ArrayList<Student>();
       
       //current students that are in the database will be gathered
       ArrayList <Student> currentStudents = this.getStudents();
       
       //searching the student in currentStudents
       for(Student stu: currentStudents){
           
           //if that student is found looking by all students private key
           if (stu.getPrivateKey().equals(student.getPrivateKey())){
               //if that is found, stu will be student so it will be saved in the database
               stu = student;
               
           }
           //stu is added for all of students that are inside currentStudents
           //however stu is changed if given student object is found inside the database
           students.add(stu);
       }
       //and this final list will be saved in to the database
       this.setStudents(students);
    }
    
    /**
     *
     * @param students
     * @throws IOException
     */
    
    //this method writes given students list to the database
    //clears all of the old records
    public void setStudents(ArrayList<Student> students) throws IOException{
        //opens the database for write mode
        this.openFileWriteMode();
        //for every student object
        for (Student student : students) {
            //student will be written to the database
            output.writeObject(student);
        }
        //closes the database for write mode
        this.closeFileWriteMode();
        //prints for test purposes
        System.out.println("Number of students are set: "+students.size());
    }
    
  
    /**
     *
     * @param student
     * @throws IOException
     */
    
    //this methods adds a new single student to the database
    //does not clear old records
    public void addStudent(Student student) throws IOException{
       
        //current students that are written in the database 
       ArrayList <Student> currentStudents = this.getStudents();
       //adds given student to the current student list
       currentStudents.add(student);
       
       //overwrites all of the databases with old records + a new record
       this.setStudents(currentStudents);
    }

    
    //WRITEMODE
    /**
     *
     */
    //opens database for writing
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
    //closes database for writing
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
    //closes database for reading
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
    //opens database for reading
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
