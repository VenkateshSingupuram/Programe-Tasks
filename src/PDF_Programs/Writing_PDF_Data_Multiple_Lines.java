package PDF_Programs;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Writing_PDF_Data_Multiple_Lines {

	private static Logger loggers = Logger.getLogger(Writing_PDF_Data_Multiple_Lines.class);

	static {
		try {
			PropertyConfigurator.configure(
					"C:\\Users\\SKTS_Admin_02\\git\\repository2\\Log4j\\Log4j Properties File\\Loggers.properties");
		} catch (Exception e) {
			e.printStackTrace();
			loggers.error("Exception Occured.....................................");

		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String arr[] = new String[2];
		for (int i = 0; i < arr.length; i++) {
			System.out.println("Enter  the   name :");
			arr[i] = sc.nextLine();
		}

		String fileName = "C:\\Users\\SKTS_Admin_02\\Desktop\\01-05-2024\\Log4j Programs\\PDF Programs\\Writing PDF File.pdf";

		try {
			createPDF(arr, fileName);
			loggers.info("PDF file created successfully.");
			
		} catch (IOException e) {
			loggers.error("Error creating PDF: " + e.getMessage());
		}
	}

	public static void createPDF(String[] lines, String fileName) throws IOException {
		try (PDDocument document = new PDDocument()) {
			PDPage page = new PDPage();
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				contentStream.beginText();
				contentStream.setFont(PDType1Font.HELVETICA, 12);
				contentStream.newLineAtOffset(100, 700); // Starting position for text

				// Add each line of text
				for (String line : lines) {
					contentStream.showText(line);
					contentStream.newLineAtOffset(0, -20); // Move to the next line
				}

				contentStream.endText();
			}

			document.save(fileName);
		}
	}
}