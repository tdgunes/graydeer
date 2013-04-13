/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author tdgunes
 * This file should implement the rules of a homework
 * like if it is java file build process is in this way
 * 
 */
public class Config {
	protected Map<String, String> conf = new HashMap<String, String>();
	
	public Config() {
		super();
	}

	public Config(Map<String, String> conf) {
		super();
		this.conf = conf;
		conf.put("Type", "Java");
		//TODO Create keys and values for configuration
		
	}	
	
	
	

	
	
    
}
