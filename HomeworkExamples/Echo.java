import java.util.Scanner;

public class Echo {

	public static void main(String[] args) {
	    Scanner myScanner = new Scanner(System.in);
        String str = null;
             
        str = myScanner.nextLine();
        str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        
        System.out.println(str);
	}
}
