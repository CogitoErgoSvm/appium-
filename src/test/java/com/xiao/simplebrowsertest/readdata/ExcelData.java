package com.xiao.simplebrowsertest.readdata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelData {
    public Workbook workbook;
    public Sheet sheet;
    public Cell cell;
    int totalrows;
    int totalcolumns;
    public String fileName;
    public String caseName;
    public ArrayList<String> arraykey = new ArrayList<String>();
    String sourceFile;

    /**
     * @param fileName   excel文件名
     * @param caseName   sheet名
     */
    public ExcelData() {
       
    }

    /**
     * 获得excel表中的数据
     */
    @SuppressWarnings("unchecked")
	public Object[][] getExcelData(File file) throws BiffException, IOException {

        workbook = Workbook.getWorkbook(file);
        sheet = workbook.getSheet(0);
        totalrows = sheet.getRows();
        totalcolumns = sheet.getColumns();
        // 为了返回值是Object[][],定义一个多行单列的二维数组
        HashMap<String, String>[][] arraymap = new HashMap[totalrows - 1][1];
        // 对数组中所有元素hashmap进行初始化
        if (totalrows > 1) {
            for (int i = 0; i < totalrows - 1; i++) {
                arraymap[i][0] = new HashMap<>();
            }
        } else {
            System.out.println("excel中没有数据");
        }

        // 获得首行的列名，作为hashmap的key值
        for (int c = 0; c < totalcolumns; c++) {
            String cellvalue = sheet.getCell(c, 0).getContents();
            arraykey.add(cellvalue);
        }
        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < totalrows; r++) {
            for (int c = 0; c < totalcolumns; c++) {
                String cellvalue = sheet.getCell(c, r).getContents();
                arraymap[r - 1][0].put(arraykey.get(c), cellvalue);
            }
        }
        return arraymap;
    }

    /**
     * 获得excel文件的路径
     * @return
     * @throws IOException
     */
//    public String getPath() throws IOException {
//        File directory = new File(".");
//        sourceFile = directory.getCanonicalPath() + "\\src\\resources\\"
//                + fileName + ".xls";
//        return sourceFile;
//    }
}
