/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import homeworks.Config;
import server.student.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author tdgunes
 * 
 *  This tool gets string of a student's homework file
 * and creates a folder with students ID and stores .java file
 * inside that folder.
 */
public final class FileStorage {
    //homeworkName
    private String homeworkName;
    private String homeworkFileString; //This is not a path, it is the source
    private Student student;
    private String homeworkStoragePath; //Let's say in /Users/tdgunes/homeworks/
    
    private String studentFolder;
    private String writtenHomeworkFile; //this will be created in constructor
    //the last path of students homework and it is the path
    
    public boolean isBuild = false;


    
    public FileStorage(String homeworkName,String homeworkFileString,String homeworksStoragePath,
            String extension) throws FileNotFoundException{
        
        this.homeworkFileString = homeworkFileString;
        this.student = InformationParser.parse(homeworkFileString);
        this.homeworkName = homeworkName;
       
        // homeworksStoragePath -> /Users/tdgunes/homeworks/
        
        this.homeworkStoragePath=  Utils.combine(homeworksStoragePath, this.homeworkName); 
        //Users/tdgunes/homeworks/MonteCarlo/
        Utils.createTheDir(this.homeworkStoragePath); //homework dir is created
        
        
        this.studentFolder = Utils.combine(this.homeworkStoragePath, this.student.getSchoolNumber());
        Utils.createTheDir(this.studentFolder);
        //Users/tdgunes/homeworks/MonteCarlo/S002423
        
        this.writtenHomeworkFile = Utils.combine(this.studentFolder, homeworkName+extension);
        //Users/tdgunes/homework/MonteCarlo/s002423/MonteCarlo.java
        Utils.writeAFile(this.writtenHomeworkFile, homeworkFileString); //writing source to the file
        
        
        //initiation is completed :)3
        
        
    }
    
    
   
    
    public void buildFile(Config config){//FIXME make this work with a config class
          //javac ./Homework1.java -d ./build/

            
        ArrayList<String> args = new ArrayList<String>();
        args.add(this.writtenHomeworkFile);
        args.add("-d");
        args.add(this.studentFolder);//FIXME we can change it
         //FIXME we can put them into Config.java
        System.out.println("Building... \"javac "+args.toString()+"\"");
        try {
                Executer executer = new Executer("javac", args);
                 executer.executeWithoutInputs();
                 this.isBuild = true;
        } catch (Exception e) {
            //build problem
            throw new UnsupportedOperationException("BUILD FAILED:" + this.writtenHomeworkFile
                    + "\n" + e.getMessage());
        }

    }
    
    
    public void runFile(Config config){
          //javac ./Homework1.java -d ./build/

        ArrayList<String> args = new ArrayList<String>();
        args.add("-cp");
        args.add(this.studentFolder);
        args.add(this.homeworkName);//great way to just getting the name
        // to getting rid of the extension
        
        ArrayList<String> inputs = new ArrayList<String>();
        inputs.add("hello");
        System.out.println("Running... \"java "+args.toString()+"\"");
        Executer executer = new Executer("java", args);
        try {
            
                 String output = executer.execute(inputs); //FIXME inputs must be corrected
                 System.out.println("OUTPUT: "+ output);
                 this.student.setHomeworkOutput(output);
//                 this.student.grade(hw1);
                 
        } catch (Exception e) {
          
            throw new UnsupportedOperationException("BUILD FAILED FOR:" + this.writtenHomeworkFile
                    + "\n" + e.getMessage());
        }

    }

    public Student getStudent() {
        return student;
    }
    

   
    //There must be an id for all homeworks.
    private final static int HW1 = 1;
    private final static int HW2 = 2;

}
