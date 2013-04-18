package homeworks.examples;

import homeworks.Homework;
import homeworks.configs.JavaConfig;
import java.io.FileNotFoundException;



public class HW1 extends Homework {
	
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
	

	public HW1(String source) throws FileNotFoundException {
		super("Echo",source,new JavaConfig());
               
		//setResults();
	}
	/*
	public HW1(int maxGrade,) throws FileNotFoundException {
		super("Echo",maxGrade);
		setResults();
	}
	
	public HW1(int maxGrade, boolean capsSensitive) throws FileNotFoundException {
		super("Echo",maxGrade, capsSensitive);
		setResults();
	}
	
	public HW1(int maxGrade, boolean capsSensitive, boolean whitespaceSensitive) throws FileNotFoundException {
		super("Echo",maxGrade, capsSensitive, whitespaceSensitive);
		setResults();
	}
        */
}
