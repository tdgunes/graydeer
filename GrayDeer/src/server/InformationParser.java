package server;

import server.student.Student;
import java.util.StringTokenizer;

public class InformationParser {
	//private Student st = new Student();
	
    /*
     * Gets a string and returns the student info
     * Student info is parsed from the comments in a string
     * 
     * String format is assumed to be like /* Can Eren Sezener S003777 Department of Computer Science */

	public static Student parse(String javaString){
                Student st = new Student();
		String name="";
		String surname="";
		String number="";
		StringTokenizer tokenizer = new StringTokenizer(javaString," ");
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken();
			if(token.equals("/*")){
				token = tokenizer.nextToken();
				name = token;
				token = tokenizer.nextToken();
				surname = token;
				token=tokenizer.nextToken();
				if(token.startsWith("S0")){
					number = token;
				}
				else { //FIXME Does not work with 3 word names.
					name = name.concat(" ").concat(surname);
					surname=token;
					number=tokenizer.nextToken();
				}
				
				
			}
			if(token.equals("*/")) break;
		}
		st.setName(name);
		st.setSurname(surname);
		st.setSchoolNumber(number);	
		
		return st;
	}

}
