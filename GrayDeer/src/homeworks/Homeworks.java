package homeworks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Homeworks {
	protected double maxGrade = 5.0;
	protected boolean caseSensitive = false; // If capsSensitive is false, Eren and eren is the same.
	protected boolean whitespaceSensitive = false; // If whitespaceSensitive is false, Eren Sezener and Eren   Sezener is the same.
	protected Map<String, Double> gradeMap = new HashMap<String, Double>();	

	// Constructors
	public Homeworks() {
	}

	public Homeworks(int maxGrade){
		this.maxGrade = maxGrade;
	}

	public Homeworks(int maxGrade, boolean capsSensitive){
		this.maxGrade = maxGrade;
		this.caseSensitive = capsSensitive;
	}

	public Homeworks(int maxGrade, boolean capsSensitive, boolean whitespaceSensitive){
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
