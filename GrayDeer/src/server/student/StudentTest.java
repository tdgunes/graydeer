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
        
        StudentDB studentDB = new StudentDB("/Users/tdgunes/homeworks/");
        //adding a student to DB;
        Student student = new Student("Luke", "Skywalker", "S003423", "!4612612315123");
        Student astudent = new Student("Jamie", "Lanister", "S003424", "34563456345");
        Student bstudent = new Student("Ned", "Stark", "S003425", "34563456341");
        Student cstudent = new Student("Sansa", "Stark", "S003426", "34563456342");
        Student dstudent = new Student("Stannis", "Baratheon", "S003427", "34563456349");
        Student estudent = new Student("Arya", "Stark", "S003428", "34563456348");
        
        ArrayList<Student> students = new ArrayList<Student> ();
        students.add(student);
        students.add(astudent);
        students.add(bstudent);
        students.add(cstudent);
        students.add(dstudent);
        students.add(estudent);

        HW2 homework2 = new HW2("");
        HW1 homework1 = new HW1("");

        student.homeworks.add(homework2);
        student.homeworks.add(homework1);
        
        astudent.homeworks.add(homework2);
        astudent.homeworks.add(homework1);
        bstudent.homeworks.add(homework2);
        bstudent.homeworks.add(homework1);
        cstudent.homeworks.add(homework2);
        cstudent.homeworks.add(homework1);
        dstudent.homeworks.add(homework2);
        dstudent.homeworks.add(homework1);
        estudent.homeworks.add(homework2);
        estudent.homeworks.add(homework1);
        
        
        studentDB.setStudents(students);
        System.out.println("Number of students: "+studentDB.getStudents().size());
        
    }
}
