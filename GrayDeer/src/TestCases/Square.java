package TestCases;

import java.util.Scanner;

/**
 *
 * @author tdgunes
 * This is a homework sample which will be assigned to a student
 * It is not required for the student to write his/her name, student id here.
 * 
 */


public class Square {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        double myNumber = 0;
             
        System.out.print("Enter a number: ");
        myNumber = myScanner.nextDouble();

        
        System.out.println("Output: "+myNumber*myNumber);
    }
}
