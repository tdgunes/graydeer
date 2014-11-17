/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

import server.student.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author tdgunes This file should implement the rules of a homework like if it
 * is java file build process is in this way
 *
 * Directory setting will also be set here.
 *
 */
public abstract class Config implements Serializable {
    //after cs102 lecture this week 15 April 2013 :), it is serializable 

    public HashMap<String, String> conf = new HashMap<String, String>();
    private String storagePath = Constants.storagePath;
    public ArrayList<String> buildArgs = new ArrayList<String>();
    public ArrayList<String> runArgs = new ArrayList<String>();

    public Config(String storagePath) {
        if (storagePath.equals("") == false) {
            this.storagePath = storagePath;
        }
    }
    public Config(HashMap<String, String> conf) {
        this.conf = conf;

    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setArgs(String writtenHomeworkFile, String studentFolder, String homeworkName) {
    }
}
