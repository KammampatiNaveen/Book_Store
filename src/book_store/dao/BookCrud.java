package book_store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import book_store.dto.Book;

public class BookCrud {

	public void createTable() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true", "root", "root");
		Statement st = con.createStatement();
		st.execute(
				"create table book(id int primary key,bookName varchar(45),authorName varchar(45),price double,language varchar(45))");
		con.close();
	}

	public void save(Book book) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?,?)");
		ps.setInt(1, book.getId());
		ps.setString(2, book.getBookName());
		ps.setString(3, book.getAuthorName());
		ps.setDouble(4, book.getPrice());
		ps.setString(5, book.getLanguage());
		ps.execute();
		ps.close();
		con.close();
	}

	public void update(int id, String bookName, String authorName, double price, String language) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("update book set bookName=? where id=?");
		ps.setString(1, bookName);
		ps.setInt(2, id);
		PreparedStatement ps1 = con.prepareStatement("update book set authorName=? where id=?");
		ps1.setString(1, authorName);
		ps1.setInt(2, id);
		PreparedStatement ps2 = con.prepareStatement("update book set price=? where id=?");
		ps2.setDouble(1, price);
		ps2.setInt(2, id);
		PreparedStatement ps3 = con.prepareStatement("update book set language=? where id=?");
		ps3.setString(1, language);
		ps3.setInt(2, id);
		ps.executeUpdate();
		ps1.executeUpdate();
		ps2.executeUpdate();
		ps3.executeUpdate();
		ps.close();
		ps1.close();
		ps2.close();
		ps3.close();
		con.close();
	}

	public void delete(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement st = con.prepareStatement("delete from book where id=? ");
		st.setInt(1, id);
		st.execute();
		st.close();
		con.close();
	}

	public int fetch(int id) throws ClassNotFoundException, SQLException {
		int count = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery("select * from book");
		while (res.next()) {
			if (id == res.getInt("id")) {
				System.out.println("id :" + res.getInt(1) + " , " + "Book Name: " + res.getString(2) + " , "
						+ "Author Name: " + res.getString(3) + " , " + "Price: " + res.getDouble(4) + " , "
						+ "Language: " + res.getString(5));
				count++;

			}
		}
		if (count < 1) {
			System.out.println("Fetchinig id is not available..");
		}
		return count;
	}

	public void display() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from book");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDouble(4)
					+ " " + rs.getString(5));
		}
		s.close();
		con.close();
	}

	public double fetchBookPrice(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("select price from book where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getDouble("price");
		} else {
			return 0;
		}
	}

	public void updateWallet(double wallet, int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("update customer set wallet=?");
		double d = fetchWalletById(id);
		double amt = wallet + d;
		ps.setDouble(1, amt);
		ps.execute();
		ps.close();
		con.close();
	}

	public double fetchWalletById(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("select wallet from customer where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getDouble("wallet");
		}
		return 0;
	}

	public void buyBook(int id, int book_id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store", "root", "root");
		PreparedStatement ps = con.prepareStatement("select wallet from customer where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			double wallet = rs.getDouble("wallet");
			double bookPrice = fetchBookPrice(book_id);
			if (wallet >= bookPrice) {
				double balance = wallet - bookPrice;
				PreparedStatement st = con.prepareStatement("update customer set wallet=? where id=?");
				st.setDouble(1, balance);
				st.setInt(2, id);
				st.executeUpdate();
				System.out.println("Purchase Sucessful Wallet Balance: " + balance);
			} else {
				System.out.println("Insufficient balance");
			}
		} else {
			System.out.println("Customer not found");
		}
	}

}
