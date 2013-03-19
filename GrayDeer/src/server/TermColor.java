/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author tdgune
 * http://stackoverflow.com/questions/5062458/font-settings-for-strings-in-java
 * 0 Normal (clear all)
1 bold
2 dim
4 underline
5 blink
7 reverse
8 blank
9 overstrike
22 normal intensity (cancel bold and blank)
24 underline off
25 blink off
27 reverse off
28 blank off
29 overstrike off
30 black
31 red
32 green
33 yellow
34 blue
35 magenta
36 cyan
37 white
40 black background
41 red background
42 green background
* 
 */
public class TermColor {
    public static String returnRed(String str){
        return (char)27 +"[31m" + str + (char)27 +"[0m";
    }
    public static String returnGreen(String str){
        return (char)27 +"[32m" + str + (char)27 +"[0m";
    }
}
