package gui;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args){
		try {
			/*
			 * Create a connection to the server socket on the 
			 * server application
			 */
                    

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

                  	InetAddress host = InetAddress.getLocalHost();
                        //proper IP should be entered here, FIXME
			Socket socket = new Socket(host.getHostName(), 7777);
			
			//Send a message to the client application
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(source);
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			//Read and Display the Response Message sent by server application
			
			String strMessage = (String)ois.readObject();
			System.out.println("Message: " + strMessage);
			oos.close();
			ois.close();
			
		}
		catch (UnknownHostException e) {

			e.printStackTrace();
		}

		catch(IOException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		

	}
}
