package PDF_Programs;

import java.io.FileOutputStream;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import Excel_Programs.Writing_Excel_Data;

public class Writing_PDF_Data {

	private static Logger logger = Logger.getLogger(Writing_Excel_Data.class);

	static {
		SimpleLayout layout = new SimpleLayout();

		ConsoleAppender appender = new ConsoleAppender(layout);
		logger.addAppender(appender);
	}
	

	public static void pdfWriter() {
		
		Scanner sc=new Scanner(System.in);
		try {
			PDDocument doc = new PDDocument();

			PDPage pp = new PDPage();
			System.out.println("Enter the Data :");
			String data=sc.nextLine();

			doc.addPage(pp);

			PDPageContentStream pcs = new PDPageContentStream(doc, pp);

			pcs.setFont(PDType1Font.HELVETICA_BOLD, 14);
			pcs.beginText();
			pcs.newLineAtOffset(100, 700);
			pcs.showText(data);
			pcs.endText();
			pcs.close();
			doc.save(new FileOutputStream("C:\\Users\\SKTS_Admin_02\\Desktop\\01-05-2024\\Log4j Programs\\PDF Programs\\Writing PDF File.pdf"));

			doc.close();
			System.out.println("Successfully Written..........");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		pdfWriter();
	}
}
