/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. All rights reserved.
 *
 * Package: homeworks.examples
 *
 * @author tdgunes
 */
package homeworks.examples;

import java.util.ArrayList;
import java.util.HashMap;

public  class AvailableHomeworks {
    
    public static HashMap<String,String> availableHomeworks = new HashMap<String,String>();
    
    public AvailableHomeworks(){
        availableHomeworks.put("Echo", "HW1");
        availableHomeworks.put("Square", "HW2");
        availableHomeworks.put("Reverse", "HW3");
    }
    
}
