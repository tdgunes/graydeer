/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworks.examples;

import homeworks.Homework;
import homeworks.configs.JavaConfig;
import java.io.FileNotFoundException;

/**
 *
 * @author tdgunes
 */
public class HW2 extends Homework {
    public HW2(String source) throws FileNotFoundException {
        
		super("Square",source,new JavaConfig());

	}
}
