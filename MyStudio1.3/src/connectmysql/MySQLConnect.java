package connectmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MySQLConnect {
	Connection conn = null;

	public static Connection getConnection() {
		Connection conn;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "mystudio";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "123123";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url + dbName, userName, password);

			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void executeSQLQuery(String query, String message) {
		Connection conn = MySQLConnect.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			if (st.executeUpdate(query) == 1) {
				JOptionPane.showMessageDialog(null, "Data " + message + "Succesully");
			} else {
				JOptionPane.showMessageDialog(null, "Data not " + message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void executeSQLQuery(String query) {
		Connection conn = MySQLConnect.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
