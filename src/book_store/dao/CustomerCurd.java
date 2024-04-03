package book_store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import book_store.dto.Customer;

public class CustomerCurd {

	public void createTable() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true", "root", "root");
		Statement st = con.createStatement();
		st.execute(
				"create table customer(id int primary key,customerName varchar(45),email varchar(45),password varchar(45),phone Bigint,wallet double,address varchar(45))");
		con.close();
	}

	public void save(Customer customer) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("insert into customer values(?,?,?,?,?,?,?)");
		ps.setInt(1, customer.getId());
		ps.setString(2, customer.getCustomerName());
		ps.setString(3, customer.getEmail());
		ps.setString(4, customer.getPassword());
		ps.setLong(5, customer.getPhone());
		ps.setDouble(6, customer.getWallet());
		ps.setString(7, customer.getAddress());
		ps.execute();
		ps.close();
		con.close();
	}

	public void update(int id, String customerName, String email, String password, long phone, double wallet,
			String address) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("update book set customerName=? where id=?");
		ps.setString(1, customerName);
		ps.setInt(2, id);
		PreparedStatement ps1 = con.prepareStatement("update book set phone=? where id=?");
		ps1.setLong(1, phone);
		ps1.setInt(2, id);
		ps.executeUpdate();
		ps1.executeUpdate();
		ps.close();
		ps1.close();
		con.close();
	}

	public boolean login(String email, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
			PreparedStatement ps = con.prepareStatement("select * from customer where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Login Succesfully..");
				return true;
			} else {
				System.out.println("Invalid email or password");
				return false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void fetchAll() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("select * from customer");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("Customer Id: " + rs.getInt(1) + ", Customer Name: " + rs.getString(2)
					+ ", Customer Wallet: " + rs.getDouble(3));
		}
		ps.close();
		con.close();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement st = con.prepareStatement("delete from customer where id=?");
		st.setInt(1, id);
		st.execute();
		st.close();
		con.close();
	}

}
