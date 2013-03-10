/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author tdgunes
 */
public class Executer {
    
    // I got this from  http://stackoverflow.com/questions/3643939/java-process-with-input-output-stream
    
    public static void main(String[] args) throws IOException{
        String line;
        Scanner scan = new Scanner(System.in);
        //bin/bash was here
        ProcessBuilder builder = new ProcessBuilder("python","homework.py");
        
        builder.redirectErrorStream(true);

        Process process = builder.start();
        OutputStream stdin = process.getOutputStream();
        InputStream stderr = process.getErrorStream();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));


        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.trim().equals("exit")) {
                // Putting 'exit' amongst the echo --EOF--s below doesn't work.
                writer.write("exit\n");
            } else {
                //these are for bash execution /bin/bash
                writer.write("((" + input + ") && echo --EOF--) || echo --EOF--\n");
            }
            writer.flush();

            line = reader.readLine();
            while (line != null && !line.trim().equals("--EOF--")) {
                System.out.println("Stdout: " + line);
                line = reader.readLine();
            }
            if (line == null) {
                break;
            }
        }
    }
    
  
    
}
