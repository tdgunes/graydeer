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
public class JavaConfig extends Config {

    
    public JavaConfig(){
        super(""); //default value
        conf.put("Type", "Java");
        conf.put("Extension", ".java");

    }
    @Override
    public void setArgs(String writtenHomeworkFile, String studentFolder, String homeworkName){
        
        super.setArgs(writtenHomeworkFile,studentFolder, homeworkName);
        //--- Java Build ---
        ArrayList<String> bArgs = new ArrayList<String>();
        bArgs.add("javac");
        bArgs.add(writtenHomeworkFile);
        bArgs.add("-d");
        bArgs.add(studentFolder);
        //------------------
              
        //--- Java Run ---
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
