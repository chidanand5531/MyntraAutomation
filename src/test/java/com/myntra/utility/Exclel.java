package com.myntra.utility;

import com.myntra.datatable.Datatable;

public class Exclel {
	
	public static void main(String[] args) {
		
		String filepath =System.getProperty("user.dir")+"/excelFile/Testdata.xlsx";
		
		Datatable datatable=new Datatable();
		int rc=datatable.rowCount(filepath, "Contacts");
		System.out.println(rc);
		
		int rc1=datatable.rowCount("Contacts");
		System.out.println(rc1);
		
		String cell=datatable.getCellData(filepath, "Contacts", "NoOfEmployee", 3);
		System.out.println(cell);
		
		String cell6=datatable.getCellData(filepath, "Contacts", "Department", 7);
		System.out.println(cell6);
		
		//datatable.SetCelldata(filepath, "Contacts", "Department", 5, "Human Resourse");
		
		
		//ReportUtil.createReport(FileName, teststarttime, environment);
	}

}
