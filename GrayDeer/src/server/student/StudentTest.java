/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import java.util.Scanner;

/**
 *
 * @author tdgunes
 */
public class StudentTest {

    public static void main(String[] args) {
        StudentDB studentDB = new StudentDB("/Users/tdgunes/homeworks/");
        System.out.println(studentDB.getStudents().toString());
        while (true) {
            Scanner input = new Scanner(System.in);
            //FIXME ****
            Student student = new Student();
            System.out.println("Enter a key, name, surname and schoolNumber");
            System.out.print(": ");
            while (input.hasNext()) {
                String key = input.next();
                String name = input.next();
                String surname = input.next();
                String schoolNumber = input.next();
                
            }
            //****
            
        }

    }
}
