package com.ui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import main.java.google.pagerank.PageRank;

import com.main.ExcelOperator;
import com.main.TextOperator;
 
/*
 * FileChooserDemo.java uses these files:
 * http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java
 */
public class FileChooserDemo extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton mOpenButton, mCheckButton;
    JTextArea log;
    JFileChooser fc;
    JEditorPane mEditText;
 
    public FileChooserDemo() {
        super(new BorderLayout());
 
        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
 
        //Create a file chooser
        fc = new JFileChooser();
 
        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
 
        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        mOpenButton = new JButton("Open a File...");
        mOpenButton.addActionListener(this);

        mCheckButton = new JButton("Check PR");
        mCheckButton.addActionListener(this);
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        mEditText = new JEditorPane();

        buttonPanel.add(mEditText);
        buttonPanel.add(mCheckButton);
		buttonPanel.add(mOpenButton);
        
        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
    public void actionPerformed(ActionEvent e) {
 
        //Handle open button action.
        if (e.getSource() == mOpenButton) {
            int returnVal = fc.showOpenDialog(FileChooserDemo.this);
 
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                String fileName = file.getName();
                log.append("Opening: " + fileName + "." + newline);
                
                // only text files or excel files
                if (fileName.endsWith("txt")) {
                	 TextOperator text = new TextOperator("urls.txt");
            		 text.getPageRank();
                }
                else if (fileName.endsWith("xlsx")) {
                	ExcelOperator excel = new ExcelOperator("urls.xlsx"); 
            		excel.getPageRank();
                }
                else {
                	JOptionPane.showMessageDialog(null, "Not Supported !");
                }
                
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        } 
        else if (e.getSource() == mCheckButton) {
        	String url = mEditText.getText();
        	String tmpURL = url;
        	if (url != null && !url.equals("")) {
        		if (!url.startsWith("http")) {
        			tmpURL = "http://" + url;
        		}
        		
        		int pageRank = PageRank.get(tmpURL);
        		log.append(url + ": PR = " + pageRank + newline);
        	}
        }
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooserDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new FileChooserDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
