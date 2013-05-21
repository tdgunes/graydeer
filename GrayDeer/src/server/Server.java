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


//server class is used for creating a HTTP Server for
//students that they can sumbit,fetch and get their cases from this server.
public final class Server {

    //the studentDB that server will use for its requests is defined here
    private static StudentDB studentDB = new StudentDB(Constants.dbPath);

    //start is for starting the server by given port, all of the handlers are 
    //defined in this method and eventually server is started
    public static void start(int port) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/fetch", new fetchHandler());
        server.createContext("/submit", new submitHandler());
        server.createContext("/verify", new verifyHandler());
        server.createContext("/getcases", new caseHandler());
        server.createContext("/objection", new objectionHandler());
        server.setExecutor(null); // creates a default executor  
        server.start();
    }
    
    static class objectionHandler implements HttpHandler {
        
        //this is for handling the request
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            //IP address is printed for logging (security reasons)
            System.out.println("IP:" + t.getLocalAddress());

            //These are the headers from the request, privateKey is for getting
            //student, homeworkName is for finding this student's homework
            String privateKey = Utils.correctHeader(t.getRequestHeaders().
                    get("privatekey").toString());
            String homeworkName = Utils.correctHeader( t.getRequestHeaders().
                    get("homeworkName").toString());
          
            String objection = Utils.convertStreamToString(t.getRequestBody());
            
            //this key is unique identifier for all of students, can be mailed
            //by their instructor

  
            
            //creates student and his/her homework object for later usage
            Student student = null;
            Homework studentHomework = null;
            try {
                //searchs and gets this student by his/her privatekey
                student = studentDB.getStudentWithKey(privateKey);
                //for checking whether this student is in the database
                if (student != null) {
                    //if yes, it logs and starts searching homework by its name
                    System.out.println("Searching homework: "+homeworkName);
                    //for all of the homework that this student has
                    for (Homework homework : student.getHomeworks()) {
                        //if one of them matches with its name, studentHomework
                        //is changed to be not null
                        if (homework.getHomeworkName().equals(homeworkName)) {
                            //logs with joy that this homework is found
                            System.out.println("Homework is found! :)");
                            //and sets to studentHomework
                            studentHomework = homework;
                            break;
                        }
                    }
                }
                else {
                    //logs that this student is not found
                    System.out.println("Student not found! with key: "+ privateKey);
                }
           
            }
            catch(Exception e){
                //there are some untested errors occurs sometimes for instance 
                //old versions of database file, so this will generate the error
                //here
                e.printStackTrace();
            }

            
            //Setting first delimeter to respone
            String response = "";
            if (studentHomework!=null){
            
                //saving process - two temp lists
                ArrayList<Homework> currentHomeworks = studentDB.getHomeworksOfAStudentByKey(privateKey);
                ArrayList<Homework> finalHomeworks = new ArrayList<Homework>();
                studentHomework.setObjection(objection);
                //Searching homework
                for (Homework homework : currentHomeworks) {
                    if (homework.getHomeworkName().equals(homeworkName)){
                        homework = studentHomework;
                    }
                    finalHomeworks.add(homework);
                }
                //homework is saved, so does the grade as homework has grade and
                //test cases
                student.setHomeworks(finalHomeworks);
                studentDB.saveStudent(student);
            }
            else {
                //this is sent to the student if the homework that requested is 
                //not found in the database
                response = "Something went terribly wrong!";
            }

            //request is answered with the respond here
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    
        
    }
 
    
    //caseHandler returns a homework's history of completion, all of the partial
    //grades and failures can be get by this handler
    static class caseHandler implements HttpHandler {
        
        //this is for handling the request
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            //IP address is printed for logging (security reasons)
            System.out.println("IP:" + t.getLocalAddress());

            //These are the headers from the request, privateKey is for getting
            //student, homeworkName is for finding this student's homework
            String privateKey = Utils.correctHeader(t.getRequestHeaders().
                    get("privatekey").toString());
            String homeworkName = Utils.correctHeader( t.getRequestHeaders().
                    get("homeworkName").toString());
          
            //this key is unique identifier for all of students, can be mailed
            //by their instructor

            //logs
            System.out.println("Fetching cases!");
            
            //creates student and his/her homework object for later usage
            Student student = null;
            Homework studentHomework = null;
            try {
                //searchs and gets this student by his/her privatekey
                student = studentDB.getStudentWithKey(privateKey);
                //for checking whether this student is in the database
                if (student != null) {
                    //if yes, it logs and starts searching homework by its name
                    System.out.println("Searching homework: "+homeworkName);
                    //for all of the homework that this student has
                    for (Homework homework : student.getHomeworks()) {
                        //if one of them matches with its name, studentHomework
                        //is changed to be not null
                        if (homework.getHomeworkName().equals(homeworkName)) {
                            //logs with joy that this homework is found
                            System.out.println("Homework is found! :)");
                            //and sets to studentHomework
                            studentHomework = homework;
                            break;
                        }
                    }
                }
                else {
                    //logs that this student is not found
                    System.out.println("Student not found! with key: "+ privateKey);
                }
           
            }
            catch(Exception e){
                //there are some untested errors occurs sometimes for instance 
                //old versions of database file, so this will generate the error
                //here
                e.printStackTrace();
            }

            
            //Setting first delimeter to respone
            String response = "+=+";
            //if studentHomework is different than null (it is correctly found in the database
             if (studentHomework != null) {
                 //get all of the cases of this homework 
                 ArrayList<Homework.Case> cases = studentHomework.getCases();
                 //for all of the homework cases
                 for (Homework.Case case1 : cases) {
                       //it generates an output for the user
                       response = response + "**" + case1.getInput() + "**"
                             + case1.getOutput() + "**" + case1.getAnswer() + "**" + case1.getGrade() + "+=+";
                 }
             }
              else {
                 //this actually mean that a terrible 'not finding the homework'
                 //error is said to the user
                 response = "Something went terribly wrong!";
             }

            //request is answered with the respond here
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    
        
    }
    
    //fetch handler is used for getting the homeworks of a user
    static class fetchHandler implements HttpHandler {

        //this is for handling the request
        @Override
        public void handle(HttpExchange t) throws IOException {
            

            //the privateKey is gathered from the request header
            String privateKey = Utils.correctHeader(t.getRequestHeaders().get("privatekey").toString());
            
            //this is for logging (security)
            System.out.println("IP:" + t.getLocalAddress());
            System.out.println("Student Key:\n" + privateKey);
             
            
            //homeworks of that students is got in this line
            ArrayList<Homework> homeworks = studentDB.getHomeworksOfAStudentByKey(privateKey);
            
            //using the same delimeter +=+ for parsing it in students' gui (client)
            String response = "+=+"; // +=+ for new lines
            
            //generating the response stinrg
            for (Homework homework: homeworks) {
                //** is for between columns
                response = response + "**"+homework.getHomeworkName()+"**"+
                        homework.getStatus()+"**"+homework.getGrade()+"**"+homework.getActions()+"+=+";
                
            }
    
            //request is answered with the respond here
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    //Submit handler is used when student submits his/her homework
    static class submitHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            
            //logging for security reasons
            System.out.println("IP:" + t.getLocalAddress());

            //this is the homework file (source text) that is sent by the student
            String fileSource = Utils.convertStreamToString(t.getRequestBody());

            //These are the headers from the request, privateKey is for getting
            //student, homeworkName is for finding this student's homework
            String privateKey = Utils.correctHeader(t.getRequestHeaders().
                    get("privatekey").toString());
            String homeworkName = Utils.correctHeader( t.getRequestHeaders().
                    get("homeworkName").toString());
          

            //logs submitting
            System.out.println("Submitting!");
            
             //creates student and his/her homework object for later usage
            Student student = null;
            Homework studentHomework = null;
            try {
                //searchs and gets this student by his/her privatekey
                student = studentDB.getStudentWithKey(privateKey);
                //for checking whether this student is in the database
                if (student != null) {
                    //if yes, it logs and starts searching homework by its name
                    System.out.println("Searching homework: "+homeworkName);
                    //for all of the homework that this student has
                    for (Homework homework : student.getHomeworks()) {
                        //if one of them matches with its name, studentHomework
                        //is changed to be not null
                        if (homework.getHomeworkName().equals(homeworkName)) {
                            //logs with joy that this homework is found
                            System.out.println("Homework is found! :)");
                            //and sets to studentHomework
                            studentHomework = homework;
                            break;
                        }
                    }
                }
                else {
                    //logs that this student is not found
                    System.out.println("Student not found! with key: "+ privateKey);
                }
           
            }
            catch(Exception e){
                //there are some untested errors occurs sometimes for instance 
                //old versions of database file, so this will generate the error
                //here
                e.printStackTrace();
            }

            //response for this request is defined
            String response;
            if (studentHomework!=null){
                //if that homework is found
                //logs tell the steps
                System.out.println("Configuring the homework object!");
                System.out.println("FileSource: " + fileSource); //fileSource is
                //the text file of the homework
                
                //uploaded file is set as a homework source in this line
                studentHomework.setHomeworkSource(fileSource);
                //stating that this homework is ready for building
                System.out.println(".setBuildRead() starts!");
                studentHomework.setBuildReady();
                
                //build process starts
                System.out.println("Building the homework!");
                studentHomework.finalizeHomework();
                
                //the grade is got and stated as a response
                response = "Your grade: " + studentHomework.getGrade();
                
                if (studentHomework.getGrade().equals("0.0")){
                    response = "Your homework has got a problem, you need to"
                            + " upload it again!";
                    studentHomework.setGrade("N/A");
                }
                else {
                    studentHomework.setActions("See Notes");       
                }
                
                //saving process - two temp lists
                ArrayList<Homework> currentHomeworks = studentDB.getHomeworksOfAStudentByKey(privateKey);
                ArrayList<Homework> finalHomeworks = new ArrayList<Homework>();
                
                //Searching homework
                for (Homework homework : currentHomeworks) {
                    if (homework.getHomeworkName().equals(homeworkName)){
                        homework = studentHomework;
                    }
                    finalHomeworks.add(homework);
                }
                //homework is saved, so does the grade as homework has grade and
                //test cases
                student.setHomeworks(finalHomeworks);
                studentDB.saveStudent(student);
            }
            else {
                //this is sent to the student if the homework that requested is 
                //not found in the database
                response = "Something went terribly wrong!";
            }

            //request is answered with the respond here
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    //this handler is used for testing the HTTP server, it just echoes the 
    //things that it writes
    static class verifyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            //Just echos the requestBody to the requester
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