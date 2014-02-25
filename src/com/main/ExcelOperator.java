package com.main;

import java.io.File;
import main.java.google.pagerank.PageRank;

import com.common.Utils;
import com.smartxls.WorkBook;

public class ExcelOperator extends Operator {

	public ExcelOperator(String fileName) {
		super(fileName);
	}

	@Override
	public void getPageRank() {
		// TODO Auto-generated method stub
		File file =  new File(mFileName);
		if (file.exists()) {
			WorkBook workbook = new WorkBook();
			try {
				workbook.readXLSX(mFileName);
				int row = 0, col = 0, colPR = 1;
				int pageRank = 0;
				String url = null;
				
				while (!(url = workbook.getText(row, col)).equals("")) {
					if (url.matches(Utils.REGEX)) { // check whether URL is valid
						System.out.println(url);
						pageRank = PageRank.get(url); // check page rank
						workbook.setText(row, colPR, String.valueOf(pageRank)); // write PageRank to excel
						System.out.println("PR = " + pageRank);
					}
					else {
						System.out.println("URL not valid");
					}
					
					System.out.println("--------------------------");
					
					++row;
				}
				
				workbook.writeXLSX(mFileName);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
