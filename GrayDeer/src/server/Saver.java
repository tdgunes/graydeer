package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Saves outputs in a file
 */
public class Saver {

    public void save(String output, Student std) throws IOException {
        String filename = std.getSchoolNumber().toString();
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(output);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
