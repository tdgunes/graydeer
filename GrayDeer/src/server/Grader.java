package server;

import homeworks.examples.HW1;
import server.student.Student;
import java.util.Map;

import homeworks.*;
import java.io.FileNotFoundException;

/*
 * @author erensezener
 * 
 * Grader takes a student object and the answers of the assignment as a string
 * Checks the students answers
 * Sets the grade of the student
 * 
 */


public class Grader {
	private Homework hw = null;

	public Grader(int hwName) throws FileNotFoundException {
		super();

	}

	public void grade(Student std){
		String output = std.getHwNo("HW1").getHwInfo("Output");
		Map<String, Double> gradeMap = hw.getGradeMap();
		String grade = String.valueOf(gradeMap.get(output));
		std.getHwNo("HW1").setHwInfo("Grade", grade);
	}

	/*
	 * All homework classes should be added to switch statement.
	 */
	/*
	private void initializeHw(int hwName) throws FileNotFoundException {
		switch(hwName){
			case 1:  
				hw = new HW1();
				break;
			default:  
				hw = new Homework("Echo"); 
				break;
		}		
		
	}*/
}	

