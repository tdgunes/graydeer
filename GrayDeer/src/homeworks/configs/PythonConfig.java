/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

/**
 *
 * @author tdgunes
 */
public class PythonConfig extends Config {

    public PythonConfig(String storagePath) {
        
        super(storagePath);
        conf.put("Type", "Python");
        conf.put("Extension", ".py");

    }
}
