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

	/* inputToOutput Hashmap
	 * Key: Inputs of a homework
	 * Value: A class which maps outputs to grades
	 * 
	 * i.e Key: "10 4", Value gradeMap for input "10 4"
	 */
	protected Map<String, GradeMap> inputToOutputMap = new HashMap<String, GradeMap>(); 

	/* gradeMap Class
	 * Key: Outputs of a student for a specific homework
	 * Value: Grade of the student
	 * 
	 * i.e. Key: "14", Value: "5.0"
	 */
	protected class GradeMap{
		protected Map<String, Double> outputToGrade = new HashMap<String, Double>();

		public GradeMap() {
			// TODO Auto-generated constructor stub
		}

		public void setOutputGradePair(String key, Double value) {
			outputToGrade.put(key, value);
		}

		public Double getGrade(String key){
			Double value = outputToGrade.get(key);
			return value;	
		}
		
		public Set<String> getKeySet(){
			return outputToGrade.keySet();
		}
	}


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
            
            double totalGrade = 0;
            for (int i = 0; i < this.inputToOutputMap.keySet().size(); i++) {
                ArrayList<String> inputs = new ArrayList<String>();
                //inputs.add(this.gradeMap.); adding the i case
                //getting keys from inputToOutputMap 
                
                //adding it to the inputs:
                //- inputs.add(testCase)
                String output = this.fileStorage.runFile(inputs);
                //grading here
                //totalGrade += 
            }
            //setting the final grade
            
            this.grade = "5.0";
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


	public void transformResults() { //TODO To be tested.

		// If caseSensitive is false, creates lower case copies of all gradeMap elements
		if (!caseSensitive) {
			Homework.GradeMap gm = this.new GradeMap();
			Set<String> keys = gm.getKeySet();
			for (String key : keys) {
				String newKey = key.toLowerCase();
				double newValue = gm.getGrade(key);
				gm.setOutputGradePair(newKey, newValue);
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

	        public String runFile(ArrayList<String> inputs ) {


	            System.out.println("Running... \"java " + config.runArgs.toString() + "\"");

	            Executer executer = new Executer(config.runArgs);
	            try {

	                String output = executer.execute(inputs); //FIXME inputs must be corrected
	                System.out.println("OUTPUT:****\n" + output);
	                
	                return output;
	                


	            } catch (Exception e) {
	                throw new UnsupportedOperationException("BUILD FAILED FOR:" + this.writtenHomeworkFile
	                        + "\n" + e.getMessage());
	                
	            }
	            
	         

	        }

	        public Student getStudent() {
	            return student;
	        }
	    }
	}
