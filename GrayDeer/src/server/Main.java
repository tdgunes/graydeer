/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;

/**
 *
 * @author tdgunes
 */
public class Main {
        
   public static void main(String[] args) throws IOException, InterruptedException, Exception {

        //defines portNum of the HTTP Server
        int portNum = 8000;
        //logs it
        System.out.println("||| GrayDeer Server is started with "+ portNum + " port number in localhost");
        //statts
        Server.start(portNum);
    }
}