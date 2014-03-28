package com.common;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Utils {
	/**
	 * Regular expression
	 */
	public static final String REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	/**
	 * Launches the associated application to open the file.
	 */
	public static boolean openFile(File file) {
		try {
			Desktop.getDesktop().open(file);
			return true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}
	
	public static final void startProcessing(Component component) {
		component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	public static final void stopProcessing(Component component) {
		component.setCursor(Cursor.getDefaultCursor());
	}
}
