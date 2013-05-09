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
	
	/* FIXME!
	 * If students program output is '6 8 10', he/she gets 5.0 points
	 * If students program output is '6 8', he/she gets 4.0 points
	 */
	
	public void setResults(){
		GradeMap gm = new GradeMap();
		gm.setOutputGradePair("9", 2.0);
		gm.setOutputGradePair("-9", 1.0);
		inputToOutputMap.put("-3", gm);
		
		GradeMap gm2 = new GradeMap();
		gm2.setOutputGradePair("16", 3.0);
		inputToOutputMap.put("4", gm2);
		
		transformResults();
	}
	

	public HW1(String source) throws FileNotFoundException {
		super("Echo",source,new JavaConfig());
                this.status = "Deadline: 14/04/13";
                this.actions = "Upload";
                this.grade = "0.0";
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
