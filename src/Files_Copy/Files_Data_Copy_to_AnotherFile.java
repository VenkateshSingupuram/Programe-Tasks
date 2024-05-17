package Files_Copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import PDF_Programs.Writing_PDF_Data_Multiple_Lines;

public class Files_Data_Copy_to_AnotherFile {


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


	public static void copiedData() {
		File sourceFile = new File(
				"C:\\Users\\SKTS_Admin_02\\Desktop\\01-05-2024\\Log4j Programs\\Files Data Transfer\\Source.txt");
		File destinationFile = new File(
				"C:\\Users\\SKTS_Admin_02\\Desktop\\01-05-2024\\Log4j Programs\\Files Data Transfer\\Destination.txt");

		try {

			copyFile(sourceFile, destinationFile);
			loggers.info("File Data copied to Another File  successfully...........");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void copyFile(File source, File dest) throws IOException {
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} finally {
			if (sourceChannel != null)
				sourceChannel.close();
			if (destChannel != null)
				destChannel.close();
		}
	}

	public static void main(String[] args) {
		copiedData();
	}

}
