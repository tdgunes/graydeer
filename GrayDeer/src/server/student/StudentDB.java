/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.student;

import server.Utils;

/**
 *
 * @author tdgunes
 */
public class StudentDB {
    public String workDir = "";
    
    // constant studentDB name which will be placed inside
    //workdir
    private String dbName = "students.db";
    private String dbFilePath = "";
    //this will be initialized
    
    public StudentDB(String workdir){
        this.workDir = workdir;
        this.dbFilePath = Utils.combine(workdir, this.dbName);
        
    }
    
}
