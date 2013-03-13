/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author tdgunes
 */
public class BuildFarm {
    
    private Config config = new Config();
    private String workDir = "";
    private String buildDir = "";
    private ArrayList<Student> students = new ArrayList<Student>();
    
    public BuildFarm(Config config, String workDir, String buildDir) {
        this.config = config;
        this.buildDir = buildDir;
        //  WorkDir is where all of the homeworks are listed 
        //like /home/tdgunes/homeworks/..
        
        this.workDir = workDir;
    }
    
    //building the farm
    public void plantTheFarm() throws UnsupportedOperationException {

        
        //javac is the command
        //javac ./Homework1.java -d ./build/
        ArrayList<String> javaFiles = this.getFilesWithExtension(this.workDir,"java"); //.java
        
  
       
        for (String javaFile : javaFiles) {
            ArrayList<String> args = new ArrayList<String>();
            args.add(javaFile);
            args.add("-d");
            args.add(this.buildDir);//FIXME we can change it
            Executer executer = new Executer("javac", args); //FIXME we can put them into Config.java
            try{
                executer.executeWithoutInputs();
            }
            catch(Exception e){ 
                //build problem
                 throw new UnsupportedOperationException("BUILD FAILED FOR:"+ javaFile + 
                         "\n" + e.getMessage());
            }
        }

    }
    
    //gathering the outputs and adding them to Student Array
    public void harvestTheFarm() {
        
        ArrayList<String> classFiles = this.getFilesWithExtension(this.buildDir,"class");
        
        for (String classFile : classFiles) {
            //java -cp ./ Echo
            ArrayList<String> args = new ArrayList<String>();
            args.add("-cp");
            args.add(this.buildDir);
            args.add(classFile.replaceFirst("[.][^.]+$", ""));//great way 
            // to getting rid of the extension
            Executer executer = new Executer("java",args);
            
            try{
                
                String output = executer.execute(args); //FIXME inputs must be corrected
                Student student = new Student("Chuck", "Bartowski", "S95724", output);
                this.students.add(student);
            }
            catch(Exception e){ 
                //build problem
                 throw new UnsupportedOperationException("BUILD FAILED FOR:"+ classFile + 
                         "\n" + e.getMessage());
            }
            
        }
        
    }
    
    public ArrayList<String> getFilesWithExtension(String path, String extension){
        // Directory path here


        String aFile;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> paths = new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                aFile = listOfFiles[i].getName();
                if (aFile.endsWith("."+extension)) {
                    paths.add(aFile);
                }
            }
        }
        return (paths);
    }


}
