package gui;

/*
 * SimpleTableDemo.java requires no other files.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StudentGUI extends JPanel {

    private boolean DEBUG = true;

    private Object[][] fetchData(Object[][] data, String hostName)  {

        try {
            HTTPLib httpLib = new HTTPLib("http://" + hostName);
            int listNum = 0;
            String response = httpLib.postData("list?");//sending list? does not required
            System.out.println("||| GrayDeer POST response: " + response);
            String[] lines = HTTPLib.splitItWithString(response, "+=+");
            //fetched data

            listNum = lines.length;
            System.out.println("ListNum: " + listNum);
            data = new Object[listNum][4];

            for (int i = 0; i < lines.length; i++) {
                //aaa**aaa**aaaa**aaa
                String string = lines[i];
                System.out.println(string);
                String[] parsedItems = HTTPLib.splitItWithString(string, "**");
                System.arraycopy(parsedItems, 0, data[i], 0, 4);
                /*       for (int j = 0; j < 4; j++) {
                 data[i][j] = parsedItems[j];
                 }*/
            }
            return data;
        } catch (ConnectException er) {
            //unable to get the list, server is unavailable

            int reply = JOptionPane.showConfirmDialog(null, "Unable to access to the host(\"" + hostName + ")\"\n"
                    + "Do you want to try to connect again ?", "Warning!", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                //looping :)
                return this.fetchData(data, hostName);
            } else {
                System.exit(0);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(StudentGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    public StudentGUI() {
        super(new GridLayout(1, 0));

        String[] columnNames = {"Assignment Name",
            "Status",
            "Actions",
            "Grade"
        };

        //*******************************

        Object[][] data = {};
        String hostName = "localhost:8000/fetch/";
     
        //starting the loop
        data = this.fetchData(data,hostName);
    
        //*******************************


        final JTable table = new JTable(data, columnNames) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
                
                
       
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //httplib for sending, getting homework/list


        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    HTTPLib httpLib = new HTTPLib("http://localhost:8000/verify/");
                    //FIXME deleted for checking httplib
                    //printDebugData(table);
                    try {
                        String response = httpLib.postData("hello");
                        System.out.println("||| GrayDeer POST response: " + response);
                    } catch (Exception er) {
                    }
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

        //createAndShowGUI();
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GrayDeer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(600, 500);

        //Create and set up the content pane.
        StudentGUI newContentPane = new StudentGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);


        //Menubar exampless
        JMenuBar mb = new JMenuBar();
        frame.setJMenuBar(mb);
        JMenu m = new JMenu("File");
        mb.add(m);

        addItem(m, "Open", KeyEvent.VK_O);
        addItem(m, "Save", KeyEvent.VK_S);
        addItem(m, "Save As", KeyEvent.VK_A);
        addItem(m, "Import", KeyEvent.VK_I);
        addItem(m, "Export", KeyEvent.VK_E);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static void addItem(JMenu m, String name, int accelerator) {
        JMenuItem mi = new JMenuItem(name);
        mi.setAccelerator(KeyStroke.getKeyStroke(accelerator,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        m.add(mi);
    }
}
