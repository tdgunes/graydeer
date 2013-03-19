/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

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
public class FileStorage {
    //homeworkName
    private String homeworkName;
    private String homeworkFileString; //This is not a path, it is the source
    private Student student;
    private String homeworkStoragePath; //Let's say in /Users/tdgunes/homeworks
    
    private String studentFolder;

    
    public FileStorage(String homeworkName,String homeworkFileString){
        this.homeworkFileString = homeworkFileString;
        this.student = InformationParser.parse(homeworkFileString);
        this.homeworkName = homeworkName;
        this.studentFolder=this.homeworkStoragePath+this.homeworkName+this.student.getSchoolNumber();
    }
    
    public void createTheDirWithStudentID(){
        
        // Example of the studentFolder /Users/tdgunes/homeworks/MonteCarlo/S002222/
        File dir = new File(this.studentFolder);
        dir.mkdir();
    }
    
    public void writeJavaFileInsideTheDir() throws FileNotFoundException{
        //Users/tdgunes/homeworks/
        String studentFile = this.studentFolder+this.homeworkName+".java";
        // Example of the studentFile /Users/tdgunes/homeworks/MonteCarlo/S002222/MonteCarlo.java
        try{
            PrintWriter out = new PrintWriter(studentFile);
            out.println(this.homeworkFileString);
            out.close(); 
        }
        catch(Exception e){
            
        }
        
        
    }
    
    public void buildFile(){
          //javac ./Homework1.java -d ./build/

        String javaFile = this.studentFolder+this.homeworkName+".java";
        ArrayList<String> args = new ArrayList<String>();
        args.add(javaFile);
        args.add("-d");
        args.add(this.studentFolder+this.homeworkName);//FIXME we can change it
        Executer executer = new Executer("javac", args); //FIXME we can put them into Config.java
        try {
            
                 executer.executeWithoutInputs(); //FIXME inputs must be corrected
               
    //              Student student = InformationParser.parse(args);
                //this.student.setHomeworkOutput(output);
            executer.executeWithoutInputs();
        } catch (Exception e) {
            //build problem
            throw new UnsupportedOperationException("BUILD FAILED FOR:" + javaFile
                    + "\n" + e.getMessage());
        }

    }
    
    /*
         public void buildFile(ArrayList<String> inputs){
          //javac ./Homework1.java -d ./build/

        String javaFile = this.studentFolder+this.homeworkName+".java";
        ArrayList<String> args = new ArrayList<String>();
        args.add(javaFile);
        args.add("-d");
        args.add(this.studentFolder+this.homeworkName);//FIXME we can change it
        Executer executer = new Executer("javac", args); //FIXME we can put them into Config.java
        try {
            
                 String output = executer.execute(inputs); //FIXME inputs must be corrected
               
//                Student student = InformationParser.parse(args);
                this.student.setHomeworkOutput(output);
            executer.executeWithoutInputs();
        } catch (Exception e) {
            //build problem
            throw new UnsupportedOperationException("BUILD FAILED FOR:" + javaFile
                    + "\n" + e.getMessage());
        }

    }
     */
}
