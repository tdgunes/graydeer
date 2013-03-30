package server;

/* Can Eren Sezener S003777 Department of Computer Science */

public class GraderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student myStudent = new Student();
		Grader myGrader = new Grader();
		myStudent.setHomeworkOutput("6 8");
		myGrader.grade(myStudent, 1);
		System.out.println(myStudent.getGrade());

	}

}
