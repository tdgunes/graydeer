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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tdgunes
 */
public class Executer {
    
    private String[] argsForProcess;

    
    public Executer(String command, ArrayList<String> argsProcess){
        argsProcess.add(0, command);
        this.argsForProcess= argsProcess.toArray(new String[argsProcess.size()]);

    }
    public void executeWithoutInputs() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(this.argsForProcess);
        builder.redirectErrorStream(true);
        Process process = builder.start();

    }
    
    public String execute(ArrayList<String> inputs) throws IOException,InterruptedException{
        String line;
        Scanner scan = new Scanner(System.in);
        String str = "";
     
        ProcessBuilder builder = new ProcessBuilder(this.argsForProcess);
        builder.redirectErrorStream(true);

        Process process = builder.start();
        OutputStream stdin = process.getOutputStream();
        InputStream stderr = process.getErrorStream();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        
        
        for (String string : inputs) {
            writer.write(string + "\n"); 
            writer.flush();
        }

        line = reader.readLine();
        while (line != null && !line.trim().equals("--EOF--")) {
            str += line + "\n";
            line = reader.readLine();
        }

        return str;
    }

  
    
}