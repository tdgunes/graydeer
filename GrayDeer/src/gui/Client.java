package gui;

import java.io.IOException;
import java.io.PrintWriter;
//import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {

            Scanner userInput = new Scanner(System.in);

            String source = "/* can eren sezener s003777 department of conputer science */\n"
                    + "import java.util.Scanner;\n"
                    + "\n"
                    + "class Echo\n"
                    + "{\n"
                    + "  public static void main (String[] args) \n"
                    + "  {\n"
                    + "    String inData;\n"
                    + "    Scanner scan = new Scanner( System.in );\n"
                    + "\n"
                    + "    System.out.println(\"Enter the data:\");\n"
                    + "    inData = scan.nextLine();\n"
                    + "\n"
                    + "    System.out.println(\"You entered:\" + inData );\n"
                    + "  }\n"
                    + "}\n"
                    + "\n";



            // When the socket object is created,
            // connection is made.
            InetAddress serverIP = InetAddress.getLocalHost();
            int serverPort = 3458;
            Socket socket = new Socket(serverIP, serverPort);

            // create reader and writer
            Scanner reader = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to Server");


        while(true) {
            System.out.print("Enter message to send: ");
            String message = userInput.nextLine();
            if (message.equals("UPLOAD")){
                message = source;
            }
            writer.println(message);
            while (reader.hasNextLine()){
                 String gotMessage = reader.nextLine();
                 System.out.println(gotMessage);
                 if (gotMessage.equals("")){
                     System.out.println("break");
                     break;
                 }
                 System.out.println("---");
            }
           
        }


        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
