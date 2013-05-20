/**
 * Copyright (c) 2013 by Taha Doğan Güneş and Eren Sezener. 
 * All rights reserved.
 *
 * Package: homeworks.examples
 * @author tdgunes
 */

package homeworks.examples;

import homeworks.Homework;
import homeworks.configs.JavaConfig;
import java.io.FileNotFoundException;


public class HW3 extends Homework{

    
    @Override
	public void setResults() {
        System.out.println("Setting the results");
        
        GradeMap gm = new GradeMap();
        gm.setOutputGradePair("54321", 2.0);
        inputToOutputMap.put("12345", gm);

        GradeMap gm2 = new GradeMap();
        gm2.setOutputGradePair("GFEDCBA", 2.0);
        inputToOutputMap.put("ABCDEFG", gm2);


        
        transformResults();
    }
    
    
    public HW3(String source) throws FileNotFoundException {
    
        super("Reverse", source, new JavaConfig());
       // System.out.println("Source\n"+source+"\n--------");
        this.setStatus( "May 13, 2013");
        this.setActions("Upload");
        this.setGrade("N/A");

    }

}
