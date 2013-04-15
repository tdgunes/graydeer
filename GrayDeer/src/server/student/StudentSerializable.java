package server.student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class StudentSerializable {
	private ObjectOutputStream output;
	private ObjectInputStream input;


	/*
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */

	public void saveStudent(Student std) throws IOException{
		openFileToWrite();
		addRecords(std);
		closeFile();
	}
	
	public Student readStudent() throws IOException, ClassNotFoundException{
		openFileToRead();
		Student std = readRecords();
		closeFile();
		return std;
	}


	private void openFileToRead() throws FileNotFoundException, IOException {
		input = new ObjectInputStream(new FileInputStream(
				"src/savedfile.ser"));
		
	}

	private Student readRecords() throws IOException, ClassNotFoundException {
		return (Student) input.readObject();
	}

	public void openFileToWrite() throws FileNotFoundException, IOException{
		output = new ObjectOutputStream(new FileOutputStream(
				"src/savedfile.ser"));
	}

	public void addRecords(Student std) throws IOException{
		output.writeObject(std);
		
	}

	private void closeFile() throws IOException {
		if (output != null)
			output.close();		
	}


}
