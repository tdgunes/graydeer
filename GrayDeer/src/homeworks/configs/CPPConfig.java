/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

import java.util.ArrayList;

/**
 *
 * @author tdgunes
 */
public class CPPConfig extends Config {

    public CPPConfig(String storagePath) {
        // g++ 
        super(storagePath);
        conf.put("Type", "C++");
        conf.put("Extension", ".cpp");

    }
  
    public void setArgs(String writtenHomeworkFile, String studentFolder, String homeworkName){
        //writtenHomeworkFile is a PATH not SOURCE!
        super.setArgs(writtenHomeworkFile,studentFolder, homeworkName);
        //--- G++ GNU Build ---
        ArrayList<String> bArgs = new ArrayList<String>();
        bArgs.add("g++");
        bArgs.add(writtenHomeworkFile);
        bArgs.add("-d");
        bArgs.add(studentFolder);
        //------------------
              
        //--- Run ---
        ArrayList<String> rArgs = new ArrayList<String>();

        rArgs.add("java");
        rArgs.add("-cp");
        rArgs.add(studentFolder);
        rArgs.add(homeworkName);        
        //----------------
        

        
        this.buildArgs = bArgs;
        this.runArgs = rArgs;
    }
}
