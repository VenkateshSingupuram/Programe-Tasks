package Excel_Programs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Read_Excel_Data {

	// Create the Logger

	private static Logger logger = Logger.getLogger(Read_Excel_Data.class);

//	static {
//		SimpleLayout layout = new SimpleLayout();
//
//		ConsoleAppender appender = new ConsoleAppender(layout);
//		
//		logger.addAppender(appender);
//	}

	static {
		try {
			PropertyConfigurator.configure(
					"C:\\Users\\SKTS_Admin_02\\eclipse-workspace\\Log4j\\Log4j Properties File\\Loggers.properties");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void readExcel() throws EncryptedDocumentException, InvalidFormatException, IOException {

		try {
			FileInputStream file = new FileInputStream(
					"C:\\Users\\SKTS_Admin_02\\Desktop\\01-05-2024\\Log4j Programs\\Excel Programs\\ReadingExcelFile.xlsx");

			Workbook workbook = new WorkbookFactory().create(file);

			Sheet sheet = workbook.getSheet("Sheet1");

			for (Row row : sheet) {

				for (Cell cell : row) {
					// logger.info(row.getCell(2));
					// logger.info(cell.getColumnIndex());

					logger.info(cell.toString() + "\t");

					//System.out.println(cell.getStringCellValue());
				}
			}

			//System.out.println();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

			logger.error("Error Occured could you Plz Check Once........." + e.getMessage());
		}

		finally {
			logger.info("Programme Execution Done..........");
		}
	}

	public static void main(String[] args) { // MAIN Method
		try {

			Read_Excel_Data readData = new Read_Excel_Data();
			readData.readExcel();
		} catch (Exception e) {
			logger.info("Debug Occurance....");
		}
	}

}