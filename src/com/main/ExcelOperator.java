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

import com.common.Utils;

public class ExcelOperator extends Operator {

	public ExcelOperator(String fileName) {
		super(fileName);
	}

	@Override
	public void getPageRank() {
		// TODO Auto-generated method stub		
		try {
			InputStream excelFile = new FileInputStream(mFileName);
			XSSFWorkbook wb = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;

			Iterator<Row> rows = sheet.rowIterator();

			int col = 0, colPR = 1;
			int pageRank = 0;
			String url = null;
			
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				url = row.getCell(col).getStringCellValue();
				if (url.matches(Utils.REGEX)) { // check whether URL is valid
					System.out.println(url);
					pageRank = PageRank.get(url); // check page rank
					
					// write PageRank to excel
					cell = row.createCell(colPR);
					cell.setCellValue(pageRank);
					
					System.out.println("PR = " + pageRank);
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
