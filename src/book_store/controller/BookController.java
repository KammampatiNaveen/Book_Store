package book_store.controller;

import java.util.Scanner;

import book_store.dao.BookCrud;
import book_store.dao.CustomerCurd;
import book_store.dto.Book;
import book_store.dto.Customer;

public class BookController {

	public static void main(String[] args) throws Throwable {
		Scanner sc = new Scanner(System.in);

		BookCrud crud = new BookCrud();
//		crud.createTable();

//		if u want create a customer table in a database, uncommit the customercrud
		CustomerCurd c = new CustomerCurd();
//		c.createTable();

		System.out.println("...WELCOME TO BOOK STORE...");
		boolean check = true;
		while (check) {
			System.out.println("---SELECT THE OPERATION---");
			System.out.println("1.BOOK\n2.CUSTOMER\n3.EXIT");
			int set = sc.nextInt();
			switch (set) {
			case 1: {
				while (check) {
					System.out.println("===BOOK===");
					System.out.println("SELECT ANY OPERATION");
					System.out.println("1.INSERT BOOK\n2.UPDATE BOOK\n3.DELETE BOOK\n4.FETCH BOOK\n5.EXIT");
					int set1 = sc.nextInt();
					switch (set1) {
					case 1: {
						System.out.println("===INSERTING BOOK DETAILS===");
						System.out.println("ENTER AN ID: ");
						int id = sc.nextInt();
						System.out.println("ENTER BOOK NAME: ");
						String bookName = sc.next();
						System.out.println("ENTER THE BOOK AUTHOR NAME: ");
						String authorName = sc.next();
						System.out.println("ENTER BOOK PRICE: ");
						double price = sc.nextDouble();
						System.out.println("ENTER BOOK LANGAUGE: ");
						String language = sc.next();
						crud.save(new Book(id, bookName, authorName, price, language));
						System.out.println("BOOK DETAILS INSERTED SUCCESSFULLY");
						break;

					}
					case 2: {
						System.out.println("===UPDATING BOOK DETAILS===");
						System.out.println("ENTER AN ID: ");
						int id = sc.nextInt();
						System.out.println("ENTER BOOK NAME: ");
						String bookName = sc.next();
						System.out.println("ENTER AUTHOR NAME: ");
						String authorName = sc.next();
						System.out.println("ENTER BOOK PRICE: ");
						double price = sc.nextDouble();
						System.out.println("ENTER BOOK LANGAUGE: ");
						String language = sc.next();
						crud.update(id, bookName, authorName, price, language);
						System.out.println("UPDATED SUCCESSFULLY");
						break;
					}
					case 3: {
						System.out.println("===DELETE===");
						System.out.println("ENTER AN ID FOR DELETE THE RECORD: ");
						System.out.println("ENTER AN ID: ");
						int id = sc.nextInt();
						crud.delete(id);
						System.out.println("DELETED SUCCESSFULLY");
						break;
					}
					case 4: {
						System.out.println("===FETCHING A BOOK DETAILS===");
						System.out.println("ENTER AN ID: ");
						int id = sc.nextInt();
						int i = crud.fetch(id);
						if (i > 1) {
							System.out.println("Fetchinig Succssfully...");
						}
						break;
					}
					case 5: {
						check = false;
					}
					}

				}
			}
			case 2: {
				while (check) {
					System.out.println("===CUSTOMER===");
					System.out.println("SELECT ANY OPERATION");
					System.out.println("1.REGISTER\n2.LOGIN");
					int value = sc.nextInt();
					switch (value) {
					case 1: {
						System.out.println("---REGISTRATION---");
						System.out.println("ENTER AN ID: ");
						int id = sc.nextInt();
						System.out.println("ENTER CUSTOMER NAME: ");
						String customerName = sc.next();
						System.out.println("ENTER AN EMAIL: ");
						String email = sc.next();
						System.out.println("ENTER PASSWORD: ");
						String password = sc.next();
						System.out.println("ENTER PHONE NUMBER: ");
						long phone = sc.nextLong();
						System.out.println("ENTER WALLET AMOUNT: ");
						double wallet = sc.nextDouble();
						System.out.println("ENTER AN ADDRESS: ");
						String address = sc.next();
						c.save(new Customer(id, customerName, email, password, phone, wallet, address));
						System.out.println("CUSTOMER REGISTRATION DONE");
						break;
					}
					case 2: {
						System.out.println("---LOGIN---");
						System.out.println("ENTER AN EMAIL: ");
						String email = sc.next();
						System.out.println("ENTER PASSWORD: ");
						String password = sc.next();
						if (c.login(email, password) == false) {
							break;
						}
						System.out.println("SELECT ANY OPERATION");
						System.out.println("1.BUY A BOOK\n2.UPDATE WALLET AMOUNT\n3.EXIT");
						int value1 = sc.nextInt();
						switch (value1) {
						case 1: {
							System.out.println(" BUYING A BOOK ");
							crud.display();
							System.out.println("ENTER YOUR USER ID: ");
							int id = sc.nextInt();
							System.out.println("ENTER THE ID OF THE BOOK YOU WANTS TO BUY...");
							int book_id = sc.nextInt();
							crud.buyBook(id, book_id);
							break;
						}
						case 2: {
							System.out.println("ENTER THE AMOUNT: ");
							double wallet = sc.nextDouble();
							System.out.println("ENTER CUSTOMER ID: ");
							int id = sc.nextInt();
							crud.updateWallet(wallet, id);
							System.out.println("WALLET AMOUNT ADDED SUCCESSFULLY...");
							break;
						}
						case 3: {
							check = false;
						}
						}
					}

					}
				}

			}
			case 3: {
				check = false;
			}
			}
		}
		sc.close();
	}

}
