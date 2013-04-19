package server;

import homeworks.configs.Config;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import homeworks.Homework;
import homeworks.configs.JavaConfig;
import homeworks.examples.HW1;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.student.StudentDB;


/*
        TEST CASE HERE PLEASE DON'T DELETE

/* can eren sezener s003777 department of conputer science */
/*
import java.util.Scanner;

class Echo{  
public static void main (String[] args)
   {   
 String inData;    
Scanner scan = new Scanner( System.in ); 
   System.out.println("Enter a text here: ");    
inData = scan.nextLine();   
 System.out.println("Lenght: " + inData.length()  + 99999); 
 }}


*/
public final class Server {

    //this is scary by the way, since if there is no database there, some requests may kill the server :)
    private static StudentDB studentDB = new StudentDB("/Users/tdgunes/homeworks/");
    public static void start(int port) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/fetch", new fetchHandler());
        server.createContext("/submit", new submitHandler());
        server.createContext("/verify", new verifyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class fetchHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            
            //this will be gathered from the studentDB
            
            String requestBody = Utils.convertStreamToString(t.getRequestBody());
            String privateKey = t.getRequestHeaders().get("key").toString();
            
            System.out.println("IP:" + t.getLocalAddress());
            System.out.println("Request Body:\n" + requestBody);
            System.out.println("Student Key:\n" + privateKey);
             
            
            //NOT TEST since, we don't have a database yet! (physically no, programmatically yes) 
            ArrayList<Homework> homeworks = studentDB.getHomeworksOfAStudentByKey(privateKey);
            
            
            
            
            String response = "Monte Carlo Integral**Homework Uploaded**See Notes**5.0**+=+"
                    + "**Echo**Deadline: 14/04/13/**Upload**0.0+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+"
                    + "**ArrayList**TBA**-**0.0**+=+";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class submitHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String requestBody = Utils.convertStreamToString(t.getRequestBody());
            System.out.println("IP:" + t.getLocalAddress());
            System.out.println("Request Body:\n" + requestBody);
            System.out.println("Request Header:\n" + t.getRequestHeaders().get("key").toString());
            
            String privateKey = t.getRequestHeaders().get("key").toString();
            //this key is special for every student, should be mailed all of them
           
             //// FIXME *******************
            /// Automatic homework selection part here!
            System.out.println("Submitting!");
            Homework homework = new HW1(requestBody);
            //////////
            
            
            //directly sending homework to fileStorage

    

            //build and run simple :)
            homework.finalizeHomework();
            
            //"FIXME - Not implemented yet!"- UGLY UGLY
            String response ="Done?";
           //homework.fileStorage.student.getHwNo("HW1").getHwInfo("Output");
           
            //= fileStorage.getStudent().getHomeworkOutput();
            
 
            
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class verifyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String requestBody = Utils.convertStreamToString(t.getRequestBody());
            System.out.println("IP:" + t.getLocalAddress());
            //System.out.println("Request Body:\n" + requestBody);
            //System.out.println("Request Header:\n" + t.getRequestHeaders().toString());
            System.out.println("||| GrayDeer(verifyHandler) - "+ requestBody);
            String response = "GrayDeer VerifyHandler echoes you: " +requestBody;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}