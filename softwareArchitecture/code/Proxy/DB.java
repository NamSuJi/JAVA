package proxytwo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	private static Connection con;
	public static void init() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jspdb","root","comeng2019");
	}
	public static void store(ProductData pd) throws Exception{
		PreparedStatement s = buildInsertionStatement(pd);
		executeStatement(s);
	}
	public static PreparedStatement buildInsertionStatement(ProductData pd) throws SQLException{
		PreparedStatement s = con.prepareStatement("Insert into soft(sku,orderId,qty) values (?,?,?);");
		s.setString(1, pd.sku);
		s.setString(2, pd.name);
		s.setInt(3, pd.price);
		return s;
	}
	public static ProductData getProductData(String sku) throws Exception{
		PreparedStatement s = buildProductQueryStatement(sku);
		ResultSet rs = executeQueryStatement(s);
		ProductData pd = extractProductDataFromResultSet(rs);
		rs.close();
		s.close();
		return pd;
	}
	public static PreparedStatement buildProductQueryStatement(String sku) throws SQLException{
		PreparedStatement s = con.prepareStatement("select sku,orderId,qty from soft where sku=?;");
		s.setString(1, sku);
		return s;
	}
	public static ProductData extractProductDataFromResultSet(ResultSet rs) throws SQLException{
		ProductData pd = new ProductData();
		pd.sku = rs.getString("sku");
		pd.name = rs.getString("orderId");
		pd.price = rs.getInt("qty");
		return pd;
	}
	public static void deleteProductData(String sku) throws Exception{
		executeStatement(buildProductDeleteStatement(sku));
	}
	public static PreparedStatement buildProductDeleteStatement(String sku) throws SQLException{
		PreparedStatement s = con.prepareStatement("delete from soft where sku = ?;");
		s.setString(1, sku);
		return s;
	}
	public static void executeStatement(PreparedStatement s) throws SQLException{
		s.execute();
		s.close();
	}
	private static ResultSet executeQueryStatement(PreparedStatement s) throws SQLException{
		ResultSet rs = s.executeQuery();
		rs.next();
		return rs;
	}
	public static void close() throws Exception{
		con.close();
	}
}
