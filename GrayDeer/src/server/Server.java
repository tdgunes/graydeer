package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import homeworks.Homework;
import java.util.ArrayList;
import server.student.Student;
import server.student.StudentDB;



public final class Server {

 //this is scary by the way, since if there is no database there, some requests may kill the server :)
//private static StudentDB studentDB = new StudentDB("/Users/tdgunes/homeworks/");
    private static StudentDB studentDB = new StudentDB(Constants.dbPath);

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
            
            System.out.println("fetch!");
            //this will be gathered from the studentDB
            //System.out.println("YES");
           // String requestBody = Utils.convertStreamToString(t.getRequestBody());
            
            String privateKey = Utils.correctHeader(t.getRequestHeaders().get("privatekey").toString());
            //String homeworkName = Utils.correctHeader( t.getRequestHeaders().get("homeworkName").toString());
            
            System.out.println("IP:" + t.getLocalAddress());
           // System.out.println("Request Body:\n" + requestBody);
            System.out.println("Student Key:\n" + privateKey);
             
            
            //NOT TEST since, we don't have a database yet! (physically no, programmatically yes) 
            ArrayList<Homework> homeworks = studentDB.getHomeworksOfAStudentByKey(privateKey);
            String response = "+=+";
            for (Homework homework: homeworks) {
                response = response + "**"+homework.getHomeworkName()+"**"+
                        homework.getStatus()+"**"+homework.getGrade()+"**"+homework.getActions()+"+=+";
                
            }
    
            
           
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class submitHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            
            System.out.println("IP:" + t.getLocalAddress());
           // System.out.println("Request Body:\n" + requestBody);
           // System.out.println("Request Header:\n" + t.getRequestHeaders().get("privatekey").toString());
            
            
            String fileSource = Utils.convertStreamToString(t.getRequestBody());

            
            String privateKey = Utils.correctHeader(t.getRequestHeaders().get("privatekey").toString());
            String homeworkName = Utils.correctHeader( t.getRequestHeaders().get("homeworkName").toString());
          
            //this key is special for every student, should be mailed all of them
           
             //// FIXME *******************
            /// Automatic homework selection part here!
            System.out.println("Submitting!");
            Student student = null;
            Homework studentHomework = null;
            try {
                student = studentDB.getStudentWithKey(privateKey);
                if (student != null) {
                    System.out.println("Searching homework: "+homeworkName);
                    for (Homework homework : student.getHomeworks()) {
                        if (homework.getHomeworkName().equals(homeworkName)) {
                            System.out.println("Homework is found! :)");
                            studentHomework = homework;
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Student not found! with key: "+ privateKey);
                }
           
            }
            catch(Exception e){
                e.printStackTrace();
            }
            //////////
            

            //directly sending homework to fileStorage
            String response;
            if (studentHomework!=null){
                System.out.println("Configuring the homework object!");
                System.out.println("FileSource: " + fileSource);
                studentHomework.setHomeworkSource(fileSource);
                System.out.println(".setBuildRead() starts!");
                studentHomework.setBuildReady();
                System.out.println("Building the homework!");
                studentHomework.finalizeHomework();
                response = "Your grade: "+studentHomework.getGrade();
                
                
                ArrayList<Homework> currentHomeworks = studentDB.getHomeworksOfAStudentByKey(privateKey);
                ArrayList<Homework> finalHomeworks = new ArrayList<Homework>();
                
                //Searching homework
                for (Homework homework : currentHomeworks) {
                    if (homework.getHomeworkName().equals(homeworkName)){
                        homework = studentHomework;
                    }
                    finalHomeworks.add(homework);
                }
                student.setHomeworks(finalHomeworks);
                studentDB.saveStudent(student);
            }
            else {
                response = "Something went terribly wrong!";
            }

            
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
            
       
            System.out.println("||| GrayDeer(verifyHandler) - "+ requestBody);
            String response = "GrayDeer VerifyHandler echoes you: " +requestBody;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}