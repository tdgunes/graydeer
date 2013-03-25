package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


// it is not a proper TCPServer, I believe it requires
//a multi-threaded TCP Server FIXME

public class Server {

    private ServerSocket server;
    private int port = 7777;

    public Server() {
        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        //Server example = new Server();
        //example.handleConnection(); 
    }

    public void handleConnection() {
        System.out.println("Waiting for client homework uploads");
        while (true) {
            try {
                Socket socket = server.accept();
                ConnectionHandler connectionHandler = new ConnectionHandler(socket);
            } catch (Exception e) {
            }
        }
    }
}

class ConnectionHandler implements Runnable {

    private Socket socket;

    public ConnectionHandler(Socket socket) {

        this.socket = socket;
        Thread t = new Thread(this);

        //Thread Start
        t.start();
    }

    @Override
    public void run() {
        try {
            //Read a message sent by client application
            System.out.println("Server is working...");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String strMessage = (String) ois.readObject();
            //System.out.println("Message Recieved: " + strMessage);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Processing...");

            Config config = new Config();
            FileStorage fileStorage = new FileStorage("Echo", strMessage, "/Users/tdgunes"
                    + "/homeworks/", ".java");

            fileStorage.buildFile(config);
            fileStorage.runFile(config);
            System.out.println(fileStorage.getStudent());
            oos.writeObject("Compiled, seems working!");
            ois.close();
            oos.close();
            socket.close();
          
            System.out.println("Waiting for client message...");

        } catch (Exception e) {
        }

    }
}
