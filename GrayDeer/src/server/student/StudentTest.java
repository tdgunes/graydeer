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
import server.Constants;


/**
 *
 * @author tdgunes
 */
public class StudentTest {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //StudentDB is defined with the database path from Constants.dbPath
        StudentDB studentDB = new StudentDB(Constants.dbPath);

        //these students are defined for testing purposes by a Game of Thrones Fan :)
        Student student = new Student("Luke", "Skywalker", "S003423", "!4612612315123");
        Student astudent = new Student("Jamie", "Lanister", "S003424", "34563456345");
        Student bstudent = new Student("Ned", "Stark", "S003425", "34563456341");
        Student cstudent = new Student("Sansa", "Stark", "S003426", "34563456342");
        Student dstudent = new Student("Stannis", "Baratheon", "S003427", "34563456349");
        Student estudent = new Student("Arya", "Stark", "S003428", "34563456348");
        
        //in order to overwrite the current database records a list is defined
        ArrayList<Student> students = new ArrayList<Student> ();
        //all of the test cases are added to this list
        students.add(student);
        students.add(astudent);
        students.add(bstudent);
        students.add(cstudent);
        students.add(dstudent);
        students.add(estudent);

        //two different homewors are defined 
        HW2 homework2 = new HW2(""); //Homework 'Square'
        HW1 homework1 = new HW1(""); //Homework 'Echo'
        
        //one of the homeworks has a history of a case (a homeworks partial grade)
        //'cases' list is defined
        ArrayList<Homework.Case> cases = new ArrayList<Homework.Case>();
        //a new case is added to this list
        cases.add(homework1.new Case("abc", "cba", "cba", 1.0) );
        //these case(s) are set to the homework
        homework1.setCases(cases);
        
        //these two homeworks are added to the students,
        //so student objects has their homeworks
        student.getHomeworks().add(homework2);
        student.getHomeworks().add(homework1);
        astudent.getHomeworks().add(homework2);
        astudent.getHomeworks().add(homework1);
        bstudent.getHomeworks().add(homework2);
        bstudent.getHomeworks().add(homework1);
        cstudent.getHomeworks().add(homework2);
        cstudent.getHomeworks().add(homework1);
        dstudent.getHomeworks().add(homework2);
        dstudent.getHomeworks().add(homework1);
        estudent.getHomeworks().add(homework2);
        estudent.getHomeworks().add(homework1);
        
        //all of these test values are set to the database
        studentDB.setStudents(students);
        
        //in order to check whether it is correctly set to the DB, it generates a print
        System.out.println("Number of students: "+studentDB.getStudents().size());
        
    }
}
