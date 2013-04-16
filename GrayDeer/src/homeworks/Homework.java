package homeworks;

import homeworks.configs.Config;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// a homewokr!
public class Homework {


	protected double maxGrade = 5.0;
	protected boolean caseSensitive = false; // If capsSensitive is false, Eren and eren is the same.
	protected boolean whitespaceSensitive = false; // If whitespaceSensitive is false, Eren Sezener and Eren   Sezener is the same.
	//All of the build rules will be here
	public Config homeworkConfig = null;
	public String homeworkName = "";

	protected Map<String, Double> gradeMap = new HashMap<String, Double>();	// Key: Output of a student, Value: Grade of a student

	// Constructors
	public Homework() {

	}

	public Homework(int maxGrade){
		this.maxGrade = maxGrade;
	}

	public Homework(int maxGrade, boolean capsSensitive){
		this.maxGrade = maxGrade;
		this.caseSensitive = capsSensitive;
	}

	public Homework(int maxGrade, boolean capsSensitive, boolean whitespaceSensitive){
		this.maxGrade = maxGrade;
		this.caseSensitive = capsSensitive;
		this.whitespaceSensitive = whitespaceSensitive;
	}

	// Getters and Setters
	public double getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(double max) {
		maxGrade = max;
	}


	public Map<String, Double> getGradeMap() {
		return gradeMap;
	}

	public void transformResults() { //TODO To be tested.

		// If caseSensitive is false, creates lower case copies of all gradeMap elements
		if(!caseSensitive){ 
			Set<String> keys = gradeMap.keySet();
			for(String key : keys){
				String newKey = key.toLowerCase();
				double newValue = gradeMap.get(key);
				gradeMap.put(newKey, newValue);
			}
		}
		if(!whitespaceSensitive){
			//TODO 
		}

	}

}
