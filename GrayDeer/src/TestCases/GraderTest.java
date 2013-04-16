package TestCases;

import server.student.Student;
import server.student.Student.HomeworkInfo;


public class GraderTest {

	/**
	 * @author erensezener
	 * 
	 * This is a class to test Grader class.
	 */
	
	public static void main(String[] args) {
		Student myStudent = new Student();
		myStudent.setHwNo("HW1", myStudent.new HomeworkInfo("Output","6 8 10"));
		myStudent.grade(HW1);
		System.out.println(myStudent.getHwNo("HW1").getHwInfo("Output"));
	}
	
	private static final int HW1=1;

}
