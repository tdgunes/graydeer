/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author tdgunes
 */
public class GUIStart {
    //This class is only for making the GUI much beautiful for Mac users :)

    public static void main(String[] args) {

        try {// if not Mac user
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "GrayDeer");
        } 
        catch (Exception e) {
            System.out.println("You are not a mac user?");
        }


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
              //  StudentGUI.createAndShowGUI();
            }
        });
    }
}
