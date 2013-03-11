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
    
    
    public static void main(String[] args) throws IOException, InterruptedException{
        String line;
        Scanner scan = new Scanner(System.in);
        //bin/bash was here
        
        // java -cp ./ Echo
        //ProcessBuilder builder = new ProcessBuilder("java","-cp","./","Echo");
        ProcessBuilder builder = new ProcessBuilder("python","homework.py");
        builder.redirectErrorStream(true);

        Process process = builder.start();
        OutputStream stdin = process.getOutputStream();
        InputStream stderr = process.getErrorStream();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        int x = 0;
        String[] inputs = new String[3];
        inputs[0]="10";
        inputs[1]="b";
        inputs[2]="c";
        
        while (x<3) {
            String input = inputs[x];
            if (input.trim().equals("exit")) {
                // Putting 'exit' amongst the echo --EOF--s below doesn't work.
              //  writer.write("exit\n");
            } else {
                //these are for bash execution /bin/bash
              //  writer.write("((" + input + ") && echo --EOF--) || echo --EOF--\n");
                writer.write(input+"\n");
              //  writer.write(input);
            }
            writer.flush();
            //writer.wait();
          
          
            x++;
        }
        
          line = reader.readLine();
            //process.waitFor();
          while (line != null && !line.trim().equals("--EOF--")) {
                System.out.println(line);
                line = reader.readLine();
          }
   
    }
    
  
    
}