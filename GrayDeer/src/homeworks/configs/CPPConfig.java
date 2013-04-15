/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

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
}
