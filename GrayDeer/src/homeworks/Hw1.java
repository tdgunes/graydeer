package homeworks;



public class Hw1 extends Homeworks {
	
	/*
	 * Results and their grades are added here.
	 * First parameter is the result string.
	 * Second parameter is the grade as a double.
	 */
	
	public void setResults(){
		gradeMap.put("6 8 10", 5.0);
		gradeMap.put("6 8", 4.0);
		gradeMap.put("6 10", 3.0);
		transformResults();
	}
	

	public Hw1() {
		super();
		setResults();
	}
	
	public Hw1(int maxGrade) {
		super(maxGrade);
		setResults();
	}
	
	public Hw1(int maxGrade, boolean capsSensitive) {
		super(maxGrade, capsSensitive);
		setResults();
	}
	
	public Hw1(int maxGrade, boolean capsSensitive, boolean whitespaceSensitive) {
		super(maxGrade, capsSensitive, whitespaceSensitive);
		setResults();
	}
}
