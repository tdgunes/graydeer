package TestCases;

import server.student.Student;

public class hashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student st = new Student();
		Student.HomeworkInfo myHwInfo = st.new HomeworkInfo();
		myHwInfo.setHwInfo("Grade", "6");
		st.setHwNo("HW1", myHwInfo);
		System.out.println(st.getHwNo("HW1").getHwInfo("Grade"));
	}

}
