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

//Executer class is used for all of the building and running processes for the
//homeworks. For the implementation of different platforms this class can be
//used as well.

public class Executer {
    
    //Arguments for the process for intance {"python","game.py"}
    private String[] argsForProcess; 

    
    //Executer object requires argProcess in the constructor as a ArrayList
    public Executer(ArrayList<String> argsProcess){
        //ArrayList is converted to String[] for requirement of ProcessBuilder
        //class
        this.argsForProcess= argsProcess.toArray(new String[argsProcess.size()]);

    }
    
    //this method is used for the jobs that does not require a direct entry
    public void executeWithoutInputs() throws IOException, InterruptedException {
        //ProcessBuilder is defiend with the argsForProcess
        ProcessBuilder builder = new ProcessBuilder(this.argsForProcess);
        //it is run and waited until the executed program finishes its runtime
        builder.redirectErrorStream(true);
        Process process = builder.start();
        process.waitFor();

    }
    
    //this method is for executing any terminal commands with inputs 
    //used for building and running the app
    public String execute(ArrayList<String> inputs) 
            throws IOException,
            InterruptedException{
        
        //variables are defined for execution
        String line;
        Scanner scan = new Scanner(System.in);
        String str = "";
        String error = "";
     
        ProcessBuilder builder = new ProcessBuilder(this.argsForProcess);
        builder.redirectErrorStream(true);

        Process process = builder.start();
        //these are used for directly giving inputs to the process that will be
        //run by the process builder and reading back the outputs or errors
        OutputStream stdin = process.getOutputStream();
        InputStream stderr = process.getErrorStream();
        InputStream stdout = process.getInputStream();
 
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        //BufferedReader errorReader = new BufferedReader(new InputStreamReader(stderr));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
      
        //inputs will be written to the process here
        for (String string : inputs) {
            writer.write(string + "\n"); 
            writer.flush();
        }
        
        //reading the output
        line = reader.readLine();
        while (line != null && !line.trim().equals("--EOF--")) {
            str += line + "\n";
            line = reader.readLine();
        }
        
        //output is returned here
        return str;
        
      
    }
 
  
    
}