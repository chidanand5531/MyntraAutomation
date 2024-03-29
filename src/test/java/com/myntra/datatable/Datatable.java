package com.myntra.datatable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datatable {
	public static Workbook wb;
	public static Sheet sh;
	public static Row row;
	public static Cell cell;
	public static FileInputStream fin;

	public int rowCount(String FileName, String SheetName) {
		int rc = 0;
		try {
			fin = new FileInputStream(FileName);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(SheetName);
			if (sh == null) {
				return -1;
			}
			rc = sh.getPhysicalNumberOfRows();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rc - 1;
	}

	public void importExcelFile(String FileName) {
		try {
			fin = new FileInputStream(FileName);
			wb = new XSSFWorkbook(fin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int rowCount(String SheetName) {
		Sheet sh = null;
		int rc = 0;
		try {
			sh = wb.getSheet(SheetName);
			if (sh == null) {
				return -1;
			}
			rc = sh.getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rc;
	}

	public String getCellData(String FileName, String SheetName, String colName, int rownum) {
		String cellValue = null;
		int colNum = 0;
		int c;
		try {
			fin = new FileInputStream(FileName);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(SheetName);
			row = sh.getRow(0);
			for (c = 0; c < row.getLastCellNum(); c++) {
				cell = row.getCell(c);
				String cValue = cell.getStringCellValue();
				if (cValue.trim().equalsIgnoreCase(colName.trim())) {
					colNum = c;
					break;
				}
			}
			row = sh.getRow(rownum - 1);
			cell = row.getCell(c);
			if (cell == null || cell.getCellType() == CellType.BLANK) {
				cellValue = "";
			} else if (cell.getCellType() == CellType.STRING) {
				cellValue = String.valueOf(cell.getStringCellValue());
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				cellValue = String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == CellType.FORMULA || cell.getCellType() == CellType.NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellValue = String.valueOf(cal.get(Calendar.MONTH) + 1) + "/"
							+ String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "/"
							+ String.valueOf(cal.get(Calendar.YEAR));
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cellValue;
	}

	public String getCellData(String SheetName, String colName, int rownum) {
		String cellValue = null;
		int colNum = 0;
		int c;
		try {
			sh = wb.getSheet(SheetName);
			row = sh.getRow(0);
			for (c = 0; c < row.getLastCellNum(); c++) {
				cell = row.getCell(c);
				String cValue = cell.getStringCellValue();
				if (cValue.trim().equalsIgnoreCase(colName.trim())) {
					colNum = c;
					break;
				}
			}
			row = sh.getRow(rownum - 1);
			cell = row.getCell(c);
			if (cell == null || cell.getCellType() == CellType.BLANK) {
				cellValue = "";
			} else if (cell.getCellType() == CellType.STRING) {
				cellValue = String.valueOf(cell.getStringCellValue());
			} else if (cell.getCellType() == CellType.BOOLEAN) {
				cellValue = String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == CellType.FORMULA || cell.getCellType() == CellType.NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellValue = String.valueOf(cal.get(Calendar.MONTH) + 1) + "/"
							+ String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + "/"
							+ String.valueOf(cal.get(Calendar.YEAR));
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}

	public void setCellData(String FileName, String SheetName, String colName, int rownum, String data) {
		int colNum = 0;
		int c;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream(FileName);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(SheetName);
			row = sh.getRow(0);
			for (c = 0; c < row.getLastCellNum(); c++) {
				cell = row.getCell(c);
				String cValue = cell.getStringCellValue();
				if (cValue.trim().equalsIgnoreCase(colName.trim())) {
					colNum = c;
					break;
				}
			}
			row = sh.getRow(rownum - 1);
			if (row == null) {
				row = sh.createRow(rownum - 1);
			}
			cell = row.getCell(c);
			if (cell == null) {
				cell = row.createCell(c);
			}
			cell.setCellValue(data);
			fout = new FileOutputStream(FileName);
			wb.write(fout);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
