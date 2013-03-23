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
        /*
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("/Users/erensezener/Documents/GrayDeer/GrayDeer/homework.py");
        Executer executer = new Executer("python",arguments);
        ArrayList <String> inputs = new ArrayList<String>();
        inputs.add("4");
        inputs.add("4");
        String output = executer.execute(inputs);
        System.out.println(output);
        */
       
        
        String source = "/* can eren sezener s003777 department of conputer science */\n" +
"import java.util.Scanner;\n" +
"\n" +
"class Echo\n" +
"{\n" +
"  public static void main (String[] args) \n" +
"  {\n" +
"    String inData;\n" +
"    Scanner scan = new Scanner( System.in );\n" +
"\n" +
"    System.out.println(\"Enter the data:\");\n" +
"    inData = scan.nextLine();\n" +
"\n" +
"    System.out.println(\"You entered:\" + inData );\n" +
"  }\n" +
"}\n" +
"\n";
        
        Config config = new Config();
        FileStorage fileStorage = new FileStorage("Echo", source, "/Users/tdgunes"
                + "/homeworks/", ".java");

        fileStorage.buildFile(config);
        fileStorage.runFile(config);
        System.out.println(fileStorage.getStudent());
        
    }
}