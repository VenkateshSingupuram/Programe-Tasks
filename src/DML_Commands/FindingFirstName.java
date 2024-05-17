package DML_Commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

public class FindingFirstName {

	private static Logger loggers = LogManager.getLogger(Data_Inserted.class);

//	static {
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
			preparedStatement = connection.prepareStatement("select *from stdDetails where fname like ?");

			loggers.info("Enter the First name :");
			String fname = scanner.next() + "%";

			preparedStatement.setString(1, fname);

			ResultSet resultSet = preparedStatement.executeQuery();

//			 while(resultSet.next()) {					
//					System.out.println(resultSet.getString("fname"));
//					}

			if (resultSet.next()) {

				loggers.info(" Name is :" + resultSet.getString("fname"));

			} else {
				loggers.error("This Data Not-Available in Our DataBase......");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Closing Statements........
			
			scanner.close();
			preparedStatement.close();
			connection.close();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			calling();
		} catch (SQLException | IOException e) {

			e.printStackTrace();
			loggers.debug("Exception throwing.................");
		}

	}
}
