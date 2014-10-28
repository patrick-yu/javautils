/***
 *	Excel file의 contents를 JsonObject로 변환한다.
 *	Excel sheet의 형식은 다음과 같아야 한다.
 *	row 0: key,   key,    key, ..., lang1, lang2 ...
 *	row 1: key11, key12,            val11, val12
 *  row 2:        key22,            val21, val22
 *  row 3:        key32,  key33,    val31, val32
 *  row 4: key41, key42,  key43,    val41, val42
 *    :
 */
package org.patrickyu.util;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.vertx.java.core.json.JsonObject;

public class ExcelMessageUtils {

	public static JsonObject excelFileToJson(File excelFile) {
		Workbook workbook;

		String ext = FileUtils.getExtension(excelFile.getName());
		try {
			if (StringUtils.equalsIgnoreCase(ext, "xlsx"))
				workbook = new XSSFWorkbook(new FileInputStream(excelFile));
			else
				workbook = new HSSFWorkbook(new FileInputStream(excelFile));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		JsonObject json = new JsonObject();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			JsonObject sheetJson = sheetToJson(sheet);
			json.putObject(sheet.getSheetName(), sheetJson);
		}
		return json;
	}

	public static JsonObject sheetToJson(Sheet sheet) {
		JsonObject json = new JsonObject();

		// header 정보
		int keyCols = 0;
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		Row headRow = sheet.getRow(0);
		for (int c = 0; c < cols; c++) {
			String value = headRow.getCell(c).getStringCellValue();
			value = value.trim().toLowerCase();
			if (value.equals("key"))
				keyCols++;
			else
				break;
		}

		// languages
		String[] langs = new String[cols - keyCols];
		for (int c = keyCols; c < cols; c++) {
			String lang = headRow.getCell(c).getStringCellValue();
			langs[c-keyCols] = lang;
			json.putObject(lang, new JsonObject());
		}

		// keys
		String[] keys = new String[keyCols];
		for (int c = 0; c < keys.length; c++)
			keys[c] = "";

		// main
		for (int r = 1; r < rows; r++) {
			// key
			boolean keyStart = false;
			int rowKeyCols = 0;
			Row row = sheet.getRow(r);
			for (int c = 0; c < keyCols; c++) {
				String key = row.getCell(c).getStringCellValue().trim();
				if (StringUtils.isEmpty(key) && !keyStart)
					continue;
				keys[c] = key;
				keyStart = true;
				if (StringUtils.isNotEmpty(key))
					rowKeyCols = c+1;
			}

			if (rowKeyCols == 0)
				continue;

			for (int i = 0; i < langs.length; i++) {
				// prepare json
				JsonObject root = json.getObject(langs[i]);
				for (int c = 0; c < rowKeyCols-1; c++) {
					String key = keys[c];
					if (StringUtils.isEmpty(key))
						break;
					JsonObject value = root.getValue(key);
					if (value == null) {
						root.putObject(key, new JsonObject());
						value = root.getObject(key);
					}
					root = value;
				}

				// set value
				String key = keys[rowKeyCols-1];
				root.putString(key, row.getCell(keyCols+i).getStringCellValue());
			}
		}

		return json;
	}

}
