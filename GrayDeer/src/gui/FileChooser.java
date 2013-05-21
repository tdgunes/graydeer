package gui;

import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JComponent implements Accessible {

	/**
	 * A File Chooser for .java files
	 */
	public static void main(String[] args) {
		FileChooser fc = new FileChooser();
		fc.showFileChooser();
	}

	public String showFileChooser(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Java Files", "java");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
                    		chooser.getSelectedFile().getName());
                
                        return chooser.getSelectedFile().getPath();
                }
                else {
                    return "null";
                }


	}
}
