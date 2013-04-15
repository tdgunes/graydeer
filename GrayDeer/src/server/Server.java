package server;

import homeworks.configs.Config;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

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
            String requestBody = Utils.convertStreamToString(t.getRequestBody());
            System.out.println("IP:" + t.getLocalAddress());
            System.out.println("Request Body:\n" + requestBody);
            System.out.println("Request Header:\n" + t.getRequestHeaders().toString());
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
            System.out.println("Request Header:\n" + t.getRequestHeaders().toString());
            
            
            Config config = new Config();
            
            String storagePath = config.getStoragePath();
            String extension  = config.conf.get("Extension");
            
            FileStorage fileStorage = new FileStorage("Echo", requestBody, storagePath, extension);
//            FileStorage fileStorage = new FileStorage("Echo", requestBody, "/Users/tdgunes"
//                    + "/homeworks/", ".java");
            System.out.println("Submitting!");
            fileStorage.buildFile(config);
            
            while(fileStorage.isBuild == false){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            fileStorage.runFile(config);
            System.out.println(""+fileStorage.getStudent());
            String response = fileStorage.getStudent().getHomeworkOutput();
            
 
            
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