package com.xiao.simplebrowsertest.readdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelOperater {

	/** 总行数 */
	private int totalRows = 0;

	/** 总列数 */
	private int totalColumns = 0;

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalCells() {
		return totalColumns;
	}

	/**
	 * 
	 * @param data
	 * @return data---每个单元格内容
	 * @throws IOException
	 * @throws BiffException
	 */
	public String[][] readFile(File file) throws BiffException, IOException {

		Workbook workbook = null;

		// 直接从本地文件创建Workbook
		InputStream instream = new FileInputStream(file.getAbsolutePath());
		workbook = Workbook.getWorkbook(instream);

		// 获取第一张Sheet表 ,Sheet的下标是从0开始
		Sheet sheet = workbook.getSheet(0);
		totalRows = sheet.getRows();
		System.out.println("行数：" + totalRows);
		totalColumns = sheet.getColumns();
		System.out.println("列数：" + totalColumns);
		String[][] data = new String[totalColumns][totalRows];
		// 获取指定单元格的对象引用
		for (int i = 0; i < totalColumns; i++) {
			for (int j = 0; j < totalRows; j++) {
				Cell cell = sheet.getCell(i, j);
				data[i][j] = cell.getContents();
			}
			System.out.println();
		}

		return data;
	}
}
