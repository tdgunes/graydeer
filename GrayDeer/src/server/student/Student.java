/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import homeworks.Homework;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import server.Grader;

/**
 *
 * @author tdgunes
 */
public class Student implements Serializable {
	private String name;
	private String surname;
	private String schoolNumber;
        
        private String privateKey; //a 16 digit random key per student
        
	//FIXME ******************
	private ArrayList<Homework> homeworks = new ArrayList<Homework>();
	//FIXME ******************
	// hwMap -> key: Homework Name, value: HashMap of a Homework
	private Map<String, HomeworkInfo> hwMap =  new HashMap<String, HomeworkInfo>();
	
	// HomeworkInfo contains a HashMap of homework information related to a 
	public class HomeworkInfo{
		
		public HomeworkInfo(){
		}
		
		public HomeworkInfo(String key, String value){
			hwInfo.put(key, value);
		}
		
		private Map<String, String> hwInfo =  new HashMap<String, String>();

		public void setHwInfo(String key, String value) {
			hwInfo.put(key, value);
		}

		public String getHwInfo(String key){
			String value = hwInfo.get(key);
			return value;
		}
	}// end of HomeworkInfo class


	public void setHwNo(String key, HomeworkInfo value) {
		hwMap.put(key, value);
	}

	public HomeworkInfo getHwNo(String key){
		HomeworkInfo value = hwMap.get(key);
		return value;
	}

	/*
	 * Trivial methods
	 */
	public Student(){
		this.name = "Unknown";
		this.surname = "Unknown";
		this.schoolNumber = "Unknown";
	}


	public Student(String name, String surname, String schoolNumber, String homeworkOutput,
                String privateNumber) {
		this.name = name;
		this.surname = surname;
		this.schoolNumber = schoolNumber;
                this.privateKey = privateNumber;
	}
        
	public void grade(int HW){
                //***** FIXME

	}

        @Override
	public String toString(){
		String str;
		str = "\n" + "Student Name: "+ this.name + "\n";

		return str;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(String schoolNumber) {
		this.schoolNumber = schoolNumber;
	}

}
