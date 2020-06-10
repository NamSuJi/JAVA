package proxy;

import java.sql.Statement;
import java.sql.*;

public class AddItemTransaction {
	public void addItem(int orderId, String sku, int qty) {
		try {
			String url = "jdbc:mysql://localhost:3306/jspdb";
			Class.forName("com.mysql.jdbc.Driver");
			Connection itsConnection = DriverManager.getConnection(url,"root","comeng2019");
			Statement s = itsConnection.createStatement();
			s.executeUpdate("insert into soft(orderId,sku,qty) values("+orderId+",'"+sku+"',"+qty+")");
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}
	public void selectItem() {
		try {
			String url = "jdbc:mysql://localhost:3306/jspdb";
			Class.forName("com.mysql.jdbc.Driver");
			Connection itsConnection = DriverManager.getConnection(url,"root","comeng2019");
			Statement s = itsConnection.createStatement();
			ResultSet rs = s.executeQuery("Select orderId,sku,qty from soft");
			while(rs.next()) {
				System.out.println(rs.getInt("orderId")+","+rs.getString("sku")+","+rs.getInt("qty"));
			}
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}
}
