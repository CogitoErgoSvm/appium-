package com.xiao.simplebrowsertest.readdata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GetExcelData {

	private String path;
	private String sheetName;

	public GetExcelData(String path, String sheetName) {
		this.path = path;
		this.sheetName = sheetName;
	}

	public Object[][] getExcelData() throws BiffException, IOException {
		// 获取文件流
		InputStream iStream = new FileInputStream(path);
		Workbook wBook = Workbook.getWorkbook(iStream);
		Sheet sheet = wBook.getSheet(sheetName);

		// 定义存放表格数据的对象
		Object[][] content;
		List<Object> ls = new ArrayList<Object>();

		// 获取表格内容长宽
		int tColu = sheet.getColumns();
		int tRow = sheet.getRows();

		// 遍历表格内容 保存到数组中
		if (sheet != null) {
			if (tRow > 0 && tColu > 0) {
				content = new Object[tRow - 1][tColu];

				// 取出表格中的数据，放在list中
				for (int i = 1; i < tRow; i++) {
					for (int j = 0; j < tColu; j++) {
						ls.add(sheet.getCell(j, i).getContents());
					}
				}

				// 取出list中的数据，放在数组中
				int key = -1;
				for (int i = 0; i < tRow - 1; i++) {
					for (int j = 0; j < tColu; j++) {
						key++;
						content[i][j] = ls.get(key);
					}
				}
				return content;
			}
		}
		return null;
	}

}
