/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import server.student.Student;

/**
 *
 * @author tdgunes
 */
//this class is for global functions that all of the
//java classes
public class Utils {

    //high level reading from file
    public static String readFromFile(String path) {

        // Location of file to read
        File file = new File(path);
        String totalString = "";
        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                totalString += line;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            totalString = "FNF";
        }
        return totalString;

    }

    //high level saving an string to with a name of student school number
    public static void save(String output, Student std) throws IOException {
        String filename = std.getSchoolNumber().toString();
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(output);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //this is from stackoverflow, it makes easier for converting Inputstreams to
    //string
    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    //this method combines a path with a path for instance
    // /home/tdgunes/   .../homeworks/
    // generates /home/tdgunes/homeworks
    public static String combine(String path1, String path2) {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }
    //an ugly way to getting rid of [,] and whitespaces
    public static String correctHeader(String headerString){
        headerString = headerString.replace(']', ' ');
        headerString = headerString.replace('[', ' ');
        headerString = headerString.replaceAll("\\s", "");
        return headerString;
    }
    //just creates a folder with given dirPath
    //dirPath example: /Users/tdgunes/homeworks/MonteCarlo/S002222/
    public static void createTheDir(String dirPath) {
        File dir = new File(dirPath);
        dir.mkdir();
    }

    //writes a string to the given path
    public static void writeAFile(String aFile, String source) throws FileNotFoundException {
        try {
            PrintWriter out = new PrintWriter(aFile);
            out.println(source);
            out.close();
        } catch (Exception e) {
        }
    }
}
