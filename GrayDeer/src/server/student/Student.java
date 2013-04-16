/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import homeworks.Homework;
import java.io.Serializable;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.HashMap;
import java.util.Map;

>>>>>>> Implement nested HashMap for Students and their Homeworks
import server.Grader;

/**
 *
 * @author tdgunes
 */
public class Student implements Serializable {
    private String name;
    private String surname;
    private String schoolNumber;
<<<<<<< HEAD
    private String homeworkOutput;
    //FIXME ******************
    private ArrayList<Homework> homeworks = new ArrayList<Homework>();
    //FIXME ******************
    private double grade;
=======
    // hwMap -> key: Homework Name, value: HashMap of a Homework
    private Map<String, HomeworkInfo> hwMap =  new HashMap<String, HomeworkInfo>();

    // HomeworkInfo contains a HashMap of homework information related to a 
    public class HomeworkInfo{
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
>>>>>>> Implement nested HashMap for Students and their Homeworks
    
	/*
	 * Trivial methods
	 */
    public Student(){
    	this.name = "Unknown";
        this.surname = "Unknown";
        this.schoolNumber = "Unknown";
   }
   
<<<<<<< HEAD
    public String toString(){
        String str;
        str = "\n" + "Student Name: "+ this.name + "\n";
        //str += "Homework Output" + "\n" + this.homeworkOutput;
        
        return str;
    }

=======
>>>>>>> Implement nested HashMap for Students and their Homeworks
    public Student(String name, String surname, String schoolNumber, String homeworkOutput) {
        this.name = name;
        this.surname = surname;
        this.schoolNumber = schoolNumber;
    }
    
    public void grade(int HW){
    	Grader grader = new Grader(HW); //Grader is initialized with rules for a homework.
        grader.grade(this);

    }
    
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
