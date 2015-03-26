package com.gcit.training.librarymanagement;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Borrower {

	public int max = 0;
	private int id = 0, bookid = 0, cardno = 0, x =1, origCopies = 0;

	public boolean checkUser(ResultSet rs, int num) throws SQLException{

		cardno = num; 

		boolean user = false;

		while(rs.next()){
			if(rs.getInt("cardNo") == num){
				user = true;
				cardno = num;
			}
		}


		return user;
	}

	
	//choose the library id
	public void chooseLibrary(int i, ResultSet r) throws SQLException{

		x=1; 

		while(r.next()){

			if(x == i){
				id = r.getInt("branchId");

				break;
			}
			x++;
		}

	}


	public void getBookId(int i, ResultSet rs) throws SQLException{

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
	
	//select book to return
	public void selectBook(ResultSet r, int i) throws SQLException{
		
		x =1;
	
		while(r.next()){

			if(x == i){
				id=r.getInt("branchId");
				bookid = r.getInt("bookId");
				break;
			}
			x++;
		}
		
	}

	//return branchid
	public int getId(){
		return id;
	}
	
	//return card number
	public int getCardNo(){
		return cardno;
	}
	
	//return bookid
	public int getbookId(){
		return bookid;
	}
}
