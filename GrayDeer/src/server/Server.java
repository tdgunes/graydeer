package server;

import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

//Multi threaded server
public class Server {
    // server port number

    //default port
    public static int PORT = 3458;

    public static void start(int port) throws Exception {
        // create socket and bind to port
        PORT = port;
        ServerSocket sock = new ServerSocket(PORT);
        System.out.println("Server is waiting for clients to connect,\nupload their homeworks.");

        while (true) {
            // Listen to new connection.
            Socket clientSocket = sock.accept();
            System.out.println(clientSocket.getInetAddress() + " client has connected.");
            // Create a thread that handles the client connection in parallel
            // so that the server is not blocked.
            // Then go back to the beginning of the loop to continue listening
            // to the new connections.
            new Thread(new ChatHandler(clientSocket)).start();
        }
    }
}

class ChatHandler implements Runnable {

    private Socket clientSocket;

    public ChatHandler(Socket s) {
        clientSocket = s;
    }

    @Override
    public void run() {
        try {
            Scanner reader = new Scanner(clientSocket.getInputStream());
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                
                // read msg from client, and print to terminal.
                try {
                    String msg = " ";

                    String source = "";
                    
                    while (reader.hasNextLine()) {
                        msg = reader.nextLine();
                        source += msg;
                        System.out.println(source);
         
                        // System.out.println("---");
                    }
                    
                    if(source.equals("")==false){
                         System.out.println(clientSocket.getInetAddress() + " > " + source);
                    }
                   

                    if (source.equals("LIST")) {
                        writer.println("1. Echo\n2.MonteCarlo\n3.ArrayList\n");
                    } 
                    else if (source.equals("")==false) {
                        //------------ FIXME -----------
                        //The problem is when client sends the file(message)
                        //The message should be gathered in this loop however
                        //it does not work as it is intended :) 

                        //(because of the "evil java")

                        //------------ FIXME -----------

                        //running the code
                        Config config = new Config();
                        FileStorage fileStorage = new FileStorage("Echo", source, "/Users/tdgunes"
                                + "/homeworks/", ".java");

                        fileStorage.buildFile(config);
                        fileStorage.runFile(config);
                        System.out.println(fileStorage.getStudent());
                        //System.out.println("Enter message to send to the client: ");
                        writer.println("Your homework is seems fine");
                  
                    }

                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                    //client disconnected FIXME
                    System.out.println("Seems like connection has been interrupted!");
                    break;
                }

            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
