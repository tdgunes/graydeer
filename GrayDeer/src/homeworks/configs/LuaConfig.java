/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.configs;

/**
 *
 * @author tdgunes
 */
public class LuaConfig extends Config {

    public LuaConfig(String storagePath) {
        super(storagePath);
        conf.put("Type", "Lua");
        conf.put("Extension", ".lua");

    }
}
