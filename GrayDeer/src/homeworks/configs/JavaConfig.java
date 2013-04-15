/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

/**
 *
 * @author tdgunes
 */
public class JavaConfig extends Config {
    public JavaConfig(String storagePath){
        super(storagePath);
        conf.put("Type", "Java");
        conf.put("Extension", ".java");
        
    }
}
