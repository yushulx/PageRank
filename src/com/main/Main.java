package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import main.java.google.pagerank.PageRank;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("urls.txt"); 
		if (file.exists()) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				try {
					String url = null;
					int pageRank = 0;
					
					// URL regular expression
					String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

					while ((url = reader.readLine()) != null) {
						if (url.matches(regex)) { // check whether URL is valid
							System.out.println(url);
							pageRank = PageRank.get(url); // check page rank
							System.out.println("PR = " + pageRank);
						}
						else {
							System.out.println("URL not valid");
						}
						
						System.out.println("--------------------------");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
