package TestCases;

import java.util.HashMap;
import java.util.Map;

import server.student.Student;
import server.student.Student.HomeworkInfo;

public class hashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student st = new Student();
//		HomeworkInfo hi = new HomeworkInfo();
		Student.HomeworkInfo myHwInfo = st.new HomeworkInfo();
		myHwInfo.setHwInfo("Grade", "6");
		st.hwMap.put("HW1", myHwInfo);
		HomeworkInfo myMap = st.hwMap.get("HW1");
		System.out.println(myMap.getHwInfo("Grade"));
		
	}

}
