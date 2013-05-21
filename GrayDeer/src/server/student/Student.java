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

/**
 *
 * @author tdgunes
 */
public class Student implements Serializable {

    //Student object consists of four important values
    private String name;
    private String surname;
    private String schoolNumber;
    private String privateKey;

    
    private ArrayList<Homework> homeworks = new ArrayList<Homework>();


    public Student() {
        //this is for testing purposes
        this.name = "Unknown";
        this.surname = "Unknown";
        this.schoolNumber = "Unknown";
    }
    //constructor for student object
    public Student(String name, String surname, String schoolNumber, String privateNumber) {
        this.name = name;
        this.surname = surname;
        this.schoolNumber = schoolNumber;
        this.privateKey = privateNumber;
    }

    public void grade(int HW) {
        
    }

    @Override
    //this is again for debugging
    public String toString() {
        String str;
        str = "\n" + "Student Name: " + this.name + "\n";

        return str;
    }

    //auto generated getter and setters
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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }
    public void addHomework(Homework homework) {
       homework.studentNumber = this.schoolNumber;
       homeworks.add(homework);
    }

    public void setHomeworks(ArrayList<Homework> homeworks) {
        this.homeworks = homeworks;
    }

}
