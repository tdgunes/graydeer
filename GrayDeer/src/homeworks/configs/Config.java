/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author tdgunes
 * This file should implement the rules of a homework
 * like if it is java file build process is in this way
 * 
 * Directory setting will also be set here.
 * 
 */
public class Config {
	public Map<String, String> conf = new HashMap<String, String>();
//	String StoragePath = "/Users/tdgunes" + "/homeworks/";
	private String storagePath = "/Users/erensezener" + "/homeworks/";

	
	public Config(String storagePath) {
                if (storagePath.equals("")==false){
                    this.storagePath = storagePath;
                }
		//conf.put("Type", "Java");
                //conf.put("Extension", ".java");
	}

	public Config(Map<String, String> conf) {
		this.conf = conf;
		//TODO Create keys and values for configuration
		
	}

	public String getStoragePath() {
		return storagePath;
	}	
	
	
	

	
	
    
}
