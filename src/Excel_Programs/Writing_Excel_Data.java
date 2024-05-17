package Excel_Programs;

import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Writing_Excel_Data {
	
	private static Logger logger = Logger.getLogger(Writing_Excel_Data.class);

//	static {
//		SimpleLayout layout = new SimpleLayout();
//
//		ConsoleAppender appender = new ConsoleAppender(layout);
//		logger.addAppender(appender);
//	}
	
	static {

		try {
			PropertyConfigurator.configure(
					"C:\\Users\\SKTS_Admin_02\\git\\repository2\\Log4j\\Log4j Properties File\\Loggers.properties");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		logger.info("Enter the First Data :");
		String s1 = sc.nextLine();
		logger.info("Enter the Second Name :");
		String s2 = sc.nextLine();

		Workbook wb = new XSSFWorkbook();
		Sheet s = wb.createSheet("sheet1");

		Row r = s.createRow(0);
		Cell c = r.createCell(1);
		Cell c1 = r.createCell(2);
		

		c.setCellValue(s1);
		c1.setCellValue(s2);

		logger.info("Execute Sucessfully............");

		try (FileOutputStream ops = new FileOutputStream("C:\\Users\\SKTS_Admin_02\\Desktop\\01-05-2024\\Log4j Programs\\Excel Programs\\WritingExcelFile.xlsx")) {
			wb.write(ops);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error Occured........................");
		}
	}
}
