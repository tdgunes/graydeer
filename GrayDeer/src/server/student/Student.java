/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import java.io.Serializable;
import server.Grader;

/**
 *
 * @author tdgunes
 */
public class Student implements Serializable {
    private String name;
    private String surname;
    private String schoolNumber;
    private String homeworkOutput;
    private double grade;
    
    public Student(){
   	this.name = "Unknown";
        this.surname = "Unknown";
        this.schoolNumber = "Unknown";
        this.homeworkOutput = "Unknown";
   }
   
    public String toString(){
        String str;
        str = "\n" + "Student Name: "+ this.name + "\n";
        str += "Homework Output" + "\n" + this.homeworkOutput;
        
        return str;
    }

    public Student(String name, String surname, String schoolNumber, String homeworkOutput) {
        this.name = name;
        this.surname = surname;
        this.schoolNumber = schoolNumber;
        this.homeworkOutput = homeworkOutput;
    }
    
    public void grade(int hw){
    	Grader grader = new Grader(hw); //Grader is initialized with rules for a homework.
        grader.grade(this);

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

	public String getHomeworkOutput() {
		return homeworkOutput;
	}

	public void setHomeworkOutput(String homeworkOutput) {
		this.homeworkOutput = homeworkOutput;
	}
	
	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	

    
}
