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

       
        int portNum = 8000;
        System.out.println("||| GrayDeer Server is started with "+ portNum + " port number in localhost");
        Server.start(portNum);
    }
}