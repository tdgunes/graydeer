package server;

import java.util.Map;

import homeworks.*;

/*
 * @author erensezener
 * 
 * Grader takes a student object and the answers of the assignment as a string
 * Checks the students answers
 * Sets the grade of the student
 * 
 */


public class Grader {
	private Homeworks hw = null;

	public void grade(Student std, int hwName){
		initializeHw(hwName);
		String output = std.getHomeworkOutput();
		Map<String, Double> gradeMap = hw.getGradeMap();
		double grade = (Double) gradeMap.get(output);
		std.setGrade(grade);
	}

	
	private void initializeHw(int hwName) {
		switch(hwName){
			case 1:  
				hw = new Hw1();
				break;
			default:  
				hw = new Homeworks(); 
				break;
		}		
		
	}
}	

