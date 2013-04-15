/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

/**
 *
 * @author tdgunes
 */
public class RubyConfig extends Config{
        public RubyConfig(String storagePath){
        super(storagePath);
        conf.put("Type", "Ruby");
        conf.put("Extension", ".rb");
        
    }
}
