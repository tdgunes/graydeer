/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks;

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
	protected Map<String, String> conf = new HashMap<String, String>();
//	String StoragePath = "/Users/tdgunes" + "/homeworks/";
	String StoragePath = "/Users/erensezener" + "/homeworks/";

	
	public Config() {
		super();
		conf.put("Type", "Java");
	}

	public Config(Map<String, String> conf) {
		super();
		this.conf = conf;
		//TODO Create keys and values for configuration
		
	}

	public String getStoragePath() {
		return StoragePath;
	}	
	
	
	

	
	
    
}
