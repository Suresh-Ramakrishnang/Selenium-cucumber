package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelReader 
{

    private static final String path = "src/test/resources/testdata/Dev_Input.xlsx";

    public static Map<String, String> getBlockDataByTC(String sheetName, String tcId)
    {

    	Map<String, String> data = new LinkedHashMap<>();

    	try (FileInputStream fis = new FileInputStream(path); Workbook workbook = WorkbookFactory.create(fis))
    	{

    		Sheet sheet = workbook.getSheet(sheetName);

    			for (int i = 0; i <= sheet.getLastRowNum(); i++)
    			{

    				Row row = sheet.getRow(i);
    				if (row == null) continue;

    				Cell firstCell = row.getCell(0);
    				if (firstCell == null) continue;

    				// Detect header row
    				if (firstCell.toString().trim().equalsIgnoreCase("TC_ID")) 
    				{

						Row headerRow = row;
						Row dataRow = sheet.getRow(i + 1);

						if (dataRow == null) continue;

						String currentTC = dataRow.getCell(0).toString().trim();

						if (currentTC.equalsIgnoreCase(tcId)) 
						{

							for (int j = 0; j < headerRow.getLastCellNum(); j++)
							{

								String key = headerRow.getCell(j).toString().trim();

								Cell valueCell = dataRow.getCell(j);

								String value = (valueCell == null)? "": valueCell.toString().trim();
								data.put(key, value);
							}

							break;
						}
    				}
    			}

    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}

    	return data;
    }
    public static void main(String[] args)
    {
    	System.out.println(getBlockDataByTC("Login","TC_LF_001"));
	}
}


