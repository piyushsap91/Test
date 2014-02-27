package package1;

//File Name SendEmail.java
import java.sql.*;

import javax.swing.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.internet.InternetAddress;

public class SendEmail {

	public Connection con;

	public Connection getConnection() {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/eventplanner";
		// Database credentials
		String USER = "root";
		String PASS = "root";

		Connection conn = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public SendEmail() {
	}
	
	

	public boolean send(Connection con) throws SQLException {
		// Sender's email ID needs to be mentioned
		String from = "piyush.dureja@sap.com";
		String pass = "Creativity3";

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		


		Session session = Session.getDefaultInstance(properties,
			    new Authenticator() {
			        protected PasswordAuthentication  getPasswordAuthentication() {
			        return new PasswordAuthentication(
			        		"anshulitdce@gmail.com", "anshulch");
			                }
			    });
		
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			//message.setText(body);

			PreparedStatement ps = con
					.prepareStatement("select * from addresslist");
			ResultSet rs = ps.executeQuery();
			ArrayList mailList = new ArrayList();
			while (rs.next()) {
				
				mailList.add(rs.getString("EmailID"));
				System.out.println(rs.getString("EmailID"));
				InternetAddress[] address = new InternetAddress[mailList.size()];
				for (int i = 0; i < mailList.size(); i++) {
					address[i] = new InternetAddress(mailList.get(i).toString());
				}
				message.setRecipients(Message.RecipientType.TO, address);

				// Set Subject: header field
				message.setSubject("This is the Subject Line!");

				// Now set the actual message
				message.setText("This is actual message");

				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully....");
			}
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) throws SQLException {

		SendEmail mail = new SendEmail();
		mail.send(mail.getConnection());
	}

}