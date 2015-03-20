package com.gcit.training.librarymanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Borrower {

	public int max = 0;
	private int id = 0, bookid = 0, cardno = 0, x =1;

	public boolean checkUser(Statement s, int num) throws SQLException{

		cardno = num; 

		ResultSet rs = s.executeQuery("select cardNo from tbl_borrower");

		boolean user = false;

		while(rs.next()){
			if(rs.getInt("cardNo") == num){
				user = true;
				cardno = num;
			}
		}


		return user;
	}


	public void returnLocations(Statement s, Common c) throws SQLException{

		System.out.println("Pick the branch you want to check out from");
		max = c.returnLocations(s, "select * from tbl_library_branch");
	}

	//return the library name
	public void chooseLibrary(int i, Statement s) throws SQLException{

		ResultSet r = s.executeQuery("select branchId from tbl_library_branch");

		x=1; 

		while(r.next()){

			if(x == i){
				id = r.getInt("branchId");

				break;
			}
			x++;
		}

	}

	//display books available at chosen branch
	public void chooseBook(Statement s, Common c, Scanner in) throws SQLException{

		String query = "select title from tbl_book_copies "
				+ "join tbl_book on tbl_book.bookId= tbl_book_copies.bookId where tbl_book_copies.branchiD = " + id 
				+ " and tbl_book_copies.noOfCopies>0";

		max = c.printBooks(s, query);

	}


	public void getBookId(int i, Statement s) throws SQLException{

		String query = "select bookid from tbl_book_copies where branchId = " + id + " and noOfCopies>0";

		ResultSet rs = s.executeQuery(query);
		x = 1;

		while(rs.next()){

			if(x == i){
				bookid = rs.getInt("bookId");
				break;
			}
			x++;
		}

	}
	
	public String returnQuery(){
		
		return "insert into tbl_book_loans values (" + bookid + ", " + id + ", " + cardno+ ", " + "?, ? )" ;

	}

	//check out the book. Create an entry in tbl_book_loans
	public void checkOut(Statement s) throws SQLException{

		String query = "insert into tbl_book_loans values (" + bookid + ", " + id + ", " + cardno+ ", " + "null, null)" ;

		int res = s.executeUpdate(query);

		if(res == 1)
			System.out.println("Check out complete");
	}
}
