/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

/**
 *
 * @author tdgunes
 */
public class HTTPLib {
    
    private String requestURL;
    private String privateKey;
    
    public HTTPLib(String requestURL, String privateKey) {
        this.requestURL = requestURL;
        this.privateKey = privateKey;
    }
    
    //****// private key 
    
    
    
    //****// 
    
    //This is from one of my CS101 homeworks, https://github.com/tdgunes/ozucourses/blob/master/CS101/Homeworks/My%20Solutions/MineField.java
    //lines between 220-233
    //*********************
    // This is for parsing the response, for example homework list
    public static String[] tokenizerToStringArray(StringTokenizer tokenizer) {
        String[] myArray = new String[tokenizer.countTokens()];
        int count = 0;
        while (tokenizer.hasMoreTokens()) {
            myArray[count] = tokenizer.nextToken();
            count++;
        }
        return (myArray);
    }

    public static String[] splitItWithString(String thisString, String separator) {
        StringTokenizer tokenizer = new StringTokenizer(thisString, separator);
        return (tokenizerToStringArray(tokenizer));
    }
    //*********************
    
    
  
    
    
    // returns the response
    public String postData(String path,String sourceData,String homeworkName) throws MalformedURLException, IOException {
        String urlParameters = sourceData;
        
        String request;
        request = requestURL+path;
        System.out.println(request);
        //*** Requests
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //connection.addRequestProperty("key", this.privateKey);
        System.out.println("Sending a post with this key: "+this.privateKey);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
        
        connection.setRequestProperty("privatekey", this.privateKey);
        connection.setRequestProperty("homeworkName", homeworkName);
        connection.setUseCaches(false);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
      


        BufferedReader rd = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        String line;
        String response = "";
        while ((line = rd.readLine()) != null) {
            // Process line...
            response = response + line;
        }
        wr.close();
        rd.close();
        connection.disconnect();
        return response;
    }
}
