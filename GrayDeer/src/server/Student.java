/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author tdgunes
 */
public class Student {
    private String name;
    private String surname;
    private String schoolNumber;
    private String homeworkOutput;
    
    public Student(){
   	 this.name = "Unknown";
        this.surname = "Unknown";
        this.schoolNumber = "Unknown";
        this.homeworkOutput = "Unknown";
   }

    public Student(String name, String surname, String schoolNumber, String homeworkOutput) {
        this.name = name;
        this.surname = surname;
        this.schoolNumber = schoolNumber;
        this.homeworkOutput = homeworkOutput;
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
    
    
}
