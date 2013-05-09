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

    /**
     *
     * @param source
     * @throws FileNotFoundException
     */
    public void setResults() {
        System.out.println("Setting the results");
        GradeMap gm = new GradeMap();
        
        gm.setOutputGradePair("9.0", 2.0);
        gm.setOutputGradePair("-9.0", 1.0);
        inputToOutputMap.put("-3.0", gm);

        GradeMap gm2 = new GradeMap();
        gm2.setOutputGradePair("16.0", 3.0);
        inputToOutputMap.put("4.0", gm2);

        //wrong test case
        GradeMap gm3 = new GradeMap();
        gm3.setOutputGradePair("16.0", 3.0);
        inputToOutputMap.put("2.0", gm3);
        
        transformResults();
    }

    public HW2(String source) throws FileNotFoundException {
    
        super("Square", source, new JavaConfig());
       // System.out.println("Source\n"+source+"\n--------");
        this.status = "May 13, 2013";
        this.actions = "See Notes";
        this.grade = "N/A";

    }
}
