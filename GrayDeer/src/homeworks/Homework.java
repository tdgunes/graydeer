package homeworks;

import homeworks.configs.Config;
import homeworks.configs.JavaConfig;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import server.Executer;
import server.InformationParser;
import server.Utils;
import server.student.Student;

// a homework!
public class Homework {

    protected double maxGrade = 5.0;
    protected boolean caseSensitive = false; // If capsSensitive is false, Eren and eren is the same.
    protected boolean whitespaceSensitive = false; // If whitespaceSensitive is false, Eren Sezener and Eren   Sezener is the same.
    //All of the build rules will be here
    public Config homeworkConfig = null;
    public String homeworkName = "";
    
    public String status =  "";
    public String actions = "";
    
    public String grade = "";

    
    public FileStorage fileStorage;
    public String homeworkSource = "";//string of the file
    
    //may be it can be deleted
    public boolean graded = false;
    
    protected Map<String, Double> gradeMap = new HashMap<String, Double>();	// Key: Output of a student, Value: Grade of a student

    // Constructors
    public Homework(String homeworkName, String homeworkSource,Config homeworkConfig) throws FileNotFoundException {
        this.homeworkName = homeworkName;
        this.homeworkSource = homeworkSource;
        this.homeworkConfig = homeworkConfig;
        this.fileStorage = new FileStorage(this, this.homeworkSource);
     
        this.homeworkConfig.setArgs(this.fileStorage.writtenHomeworkFile,this.fileStorage.studentFolder,this.homeworkName);
        
    }
/*
    public Homework(String homeworkName, int maxGrade) throws FileNotFoundException {
        this.maxGrade = maxGrade;
        this.homeworkName = homeworkName;
        this.fileStorage = new FileStorage(this, homeworkName);
    }

    public Homework(String homeworkName, int maxGrade, boolean capsSensitive) throws FileNotFoundException {
        this.maxGrade = maxGrade;
        this.caseSensitive = capsSensitive;
        this.homeworkName = homeworkName;
        this.fileStorage = new FileStorage(this, homeworkName);
    }

    public Homework(String homeworkName, int maxGrade, boolean capsSensitive, boolean whitespaceSensitive) throws FileNotFoundException {
        this.maxGrade = maxGrade;
        this.caseSensitive = capsSensitive;
        this.whitespaceSensitive = whitespaceSensitive;
        this.homeworkName = homeworkName;
        this.fileStorage = new FileStorage(this, homeworkName);
    }
*/
    public void finalizeHomework() {
        try {
            System.out.println("Finalizing...");
            this.fileStorage.buildFile();
            this.fileStorage.runFile();
            
            //if it is not catched
            
            this.graded = true;
        } catch (Exception e) {
            System.out.println("Unable to finalize homework!:");
            e.printStackTrace();
        }

    }

    // Getters and Setters
    public double getMaxGrade() {
        if(graded) return maxGrade;
        else return -1;
    }

    public void setMaxGrade(double max) {
        maxGrade = max;
    }

    public Map<String, Double> getGradeMap() {
        return gradeMap;
    }

    public void transformResults() { //TODO To be tested.

        // If caseSensitive is false, creates lower case copies of all gradeMap elements
        if (!caseSensitive) {
            Set<String> keys = gradeMap.keySet();
            for (String key : keys) {
                String newKey = key.toLowerCase();
                double newValue = gradeMap.get(key);
                gradeMap.put(newKey, newValue);
            }
        }
        if (!whitespaceSensitive) {
            //TODO 
        }

    }

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

        public FileStorage(Homework homework, String homeworkFileString) throws FileNotFoundException {

            this.homeworkFileString = homeworkFileString;
            this.student = InformationParser.parse(homeworkFileString);
            this.homeworkName = homework.homeworkName;
            this.config = (JavaConfig) homework.homeworkConfig;

            // homeworksStoragePath -> /Users/tdgunes/homeworks/

            this.homeworkStoragePath = Utils.combine(homework.homeworkConfig.getStoragePath(), this.homeworkName);
            //Users/tdgunes/homeworks/MonteCarlo/
            Utils.createTheDir(this.homeworkStoragePath); //homework dir is created


            this.studentFolder = Utils.combine(this.homeworkStoragePath, this.student.getSchoolNumber());
            Utils.createTheDir(this.studentFolder);
            //Users/tdgunes/homeworks/MonteCarlo/S002423

            this.writtenHomeworkFile = Utils.combine(this.studentFolder, homeworkName + homework.homeworkConfig.conf.get("Extension"));
            //Users/tdgunes/homework/MonteCarlo/s002423/MonteCarlo.java
            Utils.writeAFile(this.writtenHomeworkFile, homeworkFileString); //writing source to the file


            //initiation is completed :)



        }

        public void buildFile() {//FIXME make this work with a config class
            //javac ./Homework1.java -d ./build/


            //FIXME we can change it
            //FIXME we can put them into Config.java
            System.out.println("Building... \"javac " + config.buildArgs.toString() + "\"");
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

        public void runFile() {
            //javac ./Homework1.java -d ./build/


            //// FIXME *******************
            /// Homework object must give inputs
            ArrayList<String> inputs = new ArrayList<String>();
            inputs.add("hello");
            //// FIXME *******************
            System.out.println("Running... \"java " + config.runArgs.toString() + "\"");

            Executer executer = new Executer(config.runArgs);
            try {

                String output = executer.execute(inputs); //FIXME inputs must be corrected
                System.out.println("OUTPUT:****\n" + output);
                //// FIXME *******************
                ///// This must be not like this 
                ///// student object should have an array of homeworks
                /////
                
                //NullPointerException
                
                /*java.lang.NullPointerException
	at homeworks.Homework$FileStorage.runFile(Homework.java:188)
	at homeworks.Homework.finalizeHomework(Homework.java:66)
	at server.Server$submitHandler.handle(Server.java:83)
	at com.sun.net.httpserver.Filter$Chain.doFilter(Filter.java:65)
	at sun.net.httpserver.AuthFilter.doFilter(AuthFilter.java:65)
	at com.sun.net.httpserver.Filter$Chain.doFilter(Filter.java:68)
	at sun.net.httpserver.ServerImpl$Exchange$LinkHandler.handle(ServerImpl.java:557)
	at com.sun.net.httpserver.Filter$Chain.doFilter(Filter.java:65)*/
                
                //this.student.getHwNo("HW1").setHwInfo("Output", output);
                //// FIXME *******************


            } catch (Exception e) {

                e.printStackTrace();
               // throw new UnsupportedOperationException("BUILD FAILED FOR:" + this.writtenHomeworkFile
                //        + "\n" + e.getMessage());
                
            }

        }

        public Student getStudent() {
            return student;
        }
    }
}
