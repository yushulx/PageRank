package com.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import main.java.google.pagerank.PageRank;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alexarank.AlexaRank;
import com.common.Utils;

public class ExcelOperator extends Operator {

	public ExcelOperator(String fileName) {
		super(fileName);
	}

	@Override
	public void getPageRankAndAlexaRank() {
		// TODO Auto-generated method stub		
		try {
			InputStream excelFile = new FileInputStream(mFileName);
			XSSFWorkbook wb = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cellPR, cellAR;

			Iterator<Row> rows = sheet.rowIterator();

			int col = 0, colPR = 1, colAR = 2;
			int pageRank = 0, alexaRank = 0;
			String url = null;
			String log = null;
			
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				url = row.getCell(col).getStringCellValue();
				if (url.matches(Utils.REGEX)) { // check whether URL is valid
					System.out.println(url);
					pageRank = PageRank.get(url); // check page rank
					alexaRank = AlexaRank.getAlexaRank(url); // get alexa rank
					
					// write PageRank to excel
					cellPR = row.createCell(colPR);
					cellPR.setCellValue(pageRank);
					
					cellAR = row.createCell(colAR);
					cellAR.setCellValue(alexaRank);
					
					log = "PR = " + pageRank + ", AR = " + alexaRank;
					if (mEventListener != null) {
						mEventListener.log(log);
					}
					System.out.println(log);
				}
				else {
					System.out.println("URL not valid");
				}
				
				System.out.println("--------------------------");
			}
			
			FileOutputStream out = new FileOutputStream(mFileName);
	        wb.write(out);
	        out.flush();
	        out.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
