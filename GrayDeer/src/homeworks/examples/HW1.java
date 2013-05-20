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
	
	@Override
	public void setResults(){
		GradeMap gm = new GradeMap();
		gm.setOutputGradePair("Hello", 2.0);
		inputToOutputMap.put("hello", gm);
		
		GradeMap gm2 = new GradeMap();
		gm2.setOutputGradePair("This is graydeer", 3.0);
		inputToOutputMap.put("this is graydeer", gm2);
		
		transformResults();
	}
	

	public HW1(String source) throws FileNotFoundException {
		super("Echo",source,new JavaConfig());
                this.setStatus("April 05, 2013");
                this.setActions("Upload");
                this.setGrade("0.0");
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
