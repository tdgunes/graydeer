/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import homeworks.Homework;
import homeworks.examples.HW1;
import homeworks.examples.HW2;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author tdgunes
 */
public class StudentTest {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        System.out.println("StudentTest.java");
        
        StudentDB studentDB = new StudentDB("/Users/erensezener/homeworks/");
        //adding a student to DB;
        Student student = new Student("Luke", "Skywalker", "S003423", "!4612612315123");
        
        ArrayList<Student> students = new ArrayList<Student> ();
        students.add(student);
        
        HW2 homework2 = new HW2("");
        HW1 homework1 = new HW1("");

        student.homeworks.add(homework2);
        student.homeworks.add(homework1);
        
        studentDB.setStudents(students);
        System.out.println("Number of students: "+studentDB.getStudents().size());
        
    }
}
