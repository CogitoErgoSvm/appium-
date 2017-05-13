package com.xiao.simplebrowsertest;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		// TODO debug
		
		// 获取Excel文件路径
		File classpathRoot = new File(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.dir"));
		File excelDir = new File(classpathRoot, "data");
		File excel = new File(excelDir, "testData.xls");
		String path = excel.getAbsolutePath();
		System.out.println(path);

	}

}
