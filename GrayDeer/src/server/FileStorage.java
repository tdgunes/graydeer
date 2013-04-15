/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import homeworks.Homework;
import homeworks.configs.Config;
import homeworks.configs.JavaConfig;

import server.student.Student;
import java.io.FileNotFoundException;
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
    public String homeworkName;
    public String homeworkFileString; //This is not a path, it is the source
    public Student student;
    public String homeworkStoragePath; //Let's say in /Users/tdgunes/homeworks/
    
    public String studentFolder;
    public String writtenHomeworkFile; 
     //this will be created in constructor
    //the last path of students homework and it is the path
    
    public Config config;
    
    public boolean isBuild = false;


    
    public FileStorage(Homework homework,String homeworkFileString) throws FileNotFoundException{
        
        this.homeworkFileString = homeworkFileString;
        this.student = InformationParser.parse(homeworkFileString);
        this.homeworkName = homework.homeworkName;
        this.config = (JavaConfig) homework.homeworkConfig;
       
        // homeworksStoragePath -> /Users/tdgunes/homeworks/
        
        this.homeworkStoragePath=  Utils.combine(homework.homeworkConfig.getStoragePath(), this.homeworkName); 
        //Users/tdgunes/homeworks/MonteCarlo/
        Utils.createTheDir(this.homeworkStoragePath); //homework dir is created
        
        
        this.studentFolder = Utils.combine(this.homeworkStoragePath, this.student.getSchoolNumber());
        Utils.createTheDir(this.studentFolder);
        //Users/tdgunes/homeworks/MonteCarlo/S002423
        
        this.writtenHomeworkFile = Utils.combine(this.studentFolder, homeworkName+homework.homeworkConfig.conf.get("Extension"));
        //Users/tdgunes/homework/MonteCarlo/s002423/MonteCarlo.java
        Utils.writeAFile(this.writtenHomeworkFile, homeworkFileString); //writing source to the file
        
        
        //initiation is completed :)
        
   
        
    }
    
    
   
    
    public void buildFile(){//FIXME make this work with a config class
          //javac ./Homework1.java -d ./build/

            
        //FIXME we can change it
         //FIXME we can put them into Config.java
        System.out.println("Building... \"javac "+config.buildArgs.toString()+"\"");
        try {
                Executer executer = new Executer(config.buildArgs);
                 executer.executeWithoutInputs();
                 this.isBuild = true;
        } catch (Exception e) {
            //build problem
            throw new UnsupportedOperationException("BUILD FAILED:" + this.writtenHomeworkFile
                    + "\n" + e.getMessage());
        }

    }
    
    
    public void runFile(){
          //javac ./Homework1.java -d ./build/


        //// FIXME *******************
        /// Homework object must give inputs
        ArrayList<String> inputs = new ArrayList<String>();
        inputs.add("hello");
        //// FIXME *******************
        System.out.println("Running... \"java "+config.runArgs.toString()+"\"");
        
        Executer executer = new Executer(config.runArgs);
        try {
            
                 String output = executer.execute(inputs); //FIXME inputs must be corrected
                 System.out.println("OUTPUT: "+ output);
                  //// FIXME *******************
                 ///// This must be not like this 
                 ///// student object should have an array of homeworks
                 /////
                 this.student.setHomeworkOutput(output);
                  //// FIXME *******************

                 
        } catch (Exception e) {
          
            throw new UnsupportedOperationException("BUILD FAILED FOR:" + this.writtenHomeworkFile
                    + "\n" + e.getMessage());
        }

    }

    public Student getStudent() {
        return student;
    }
    

}
