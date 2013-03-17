/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tdgunes
 */
public class Main {
        
   public static void main(String[] args) throws IOException, InterruptedException {
        //Testing Classes
        
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("/Users/erensezener/Documents/GrayDeer/GrayDeer/homework.py");
        Executer executer = new Executer("python",arguments);
        ArrayList <String> inputs = new ArrayList<String>();
        inputs.add("4");
        inputs.add("4");
        String output = executer.execute(inputs);
        System.out.println(output);
        
    }
}
