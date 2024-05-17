package DML_Commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

public class Data_Inserted {

	private static Logger loggers = LogManager.getLogger(Data_Inserted.class);

//	static {               //       Hard Code
//		SimpleLayout layout = new SimpleLayout();
//		ConsoleAppender appender = new ConsoleAppender(layout);
//		loggers.addAppender(appender);
//	}

	static {
		try {
			PropertyConfigurator.configure(
					"C:\\Users\\SKTS_Admin_02\\eclipse-workspace\\Log4j\\Log4j Properties File\\Loggers.properties");
		} catch (Exception e) {
			loggers.debug("Exception Occurred....");
		}
	}

	public static String calling() throws SQLException, IOException {

		File file = new File("./DBDetails.properties");

		FileInputStream fileInput = new FileInputStream(file);

		Properties properties = new Properties();
		properties.load(fileInput);
		String dbDriver = properties.getProperty("dbDriver");
		String url = properties.getProperty("url");
		String userName = properties.getProperty("username");
		String passWord = properties.getProperty("password");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = new Scanner(System.in);

		try {

			Class.forName(dbDriver);
			connection = DriverManager.getConnection(url, userName, passWord);

			preparedStatement = connection.prepareStatement("insert into stdDetails values (?,?,?)");

			loggers.info("How Many Students Data You Want to Insert ?");
			int number = scanner.nextInt();

			for (int i = 0; i < number; i++) {

				loggers.info("Enter the First Name :");
				String firstname = scanner.next();

				loggers.info("Enter the Last Name :");
				String lastname = scanner.next();

				loggers.info("Enter the Mobile Number :");
				long phno = scanner.nextLong();

				preparedStatement.setString(1, firstname);
				preparedStatement.setString(2, lastname);
				preparedStatement.setLong(3, phno);

				int reference = preparedStatement.executeUpdate(); // The Data Stored into Based on Prepared Statement

				if (reference > 0) {
					loggers.info("Student Details Inserted  Successfully............");
				}
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			loggers.error("An Error Occured :" + e.getMessage());
		} finally {

			connection.close(); // Connection Close
			preparedStatement.close();
			scanner.close(); // Scanner Close.
			loggers.info("Programe Execution Completed  And Connections Close ......");

		}

		return null;
	}

	public static void main(String[] args) throws SQLException, IOException {

		calling(); // Calling the Method

	}
}
