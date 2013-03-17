package server;

import java.util.StringTokenizer;

public class InformationParser {
	private Student st = new Student();
	
    /*
     * Gets a string and returns the student info
     * Student info is parsed from the comments in a string
     */
	public Student parse(String javaString){
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
