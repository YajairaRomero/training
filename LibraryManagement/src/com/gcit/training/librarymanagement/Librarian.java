package com.gcit.training.librarymanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Librarian {

	public int max = 0;
	private int id = 0, bookid = 0;
	private String libname = "", addr = "";
	
	
	//print out library names and locations
	public void returnLocations(Statement s, Common c) throws SQLException{
		System.out.println("Choose your branch");
		max = c.returnLocations(s, "select * from tbl_library_branch");
			
	}
	
	
	//return the library name
	public void chooseLibrary(int i, Statement s) throws SQLException{
		
		int x = 1;
		
		ResultSet r = s.executeQuery("select * from tbl_library_branch");
		
		while(r.next()){
			
			if(x == i){
				 libname = r.getString("branchName");
				 id = r.getInt("branchId");
				 addr = r.getString("branchAddress");
				 
				break;
			}
			x++;
		}
			
	}
	
	
	//update library branch
	public int update(Statement s, Scanner scan) throws SQLException{
		
		System.out.println("You have chosen to update the Branch with Branch Id: " + id + 
				" and Branch Name: " + libname + ". \nEnter 'quit' at any prompt to cancel operation.\n\n"
				+ "Please enter new branch name or enter N/A for no change.");
		
		scan.nextLine();	//get rid of newline character
		
		String bname = scan.nextLine();
		
		if(bname.equals("N/A")){
			bname = libname;
		}
		else if (bname.equals("quit")){
			return 0;
		}
		
		System.out.println("Please enter new branch address or enter N/A for no change.");
		
		String baddr = scan.nextLine();
		
		if(baddr.equals("N/A")){
			baddr = addr;
		}
		else if (baddr.equals("quit")){
			return 0;
		}

		int result = s.executeUpdate("update tbl_library_branch set branchName = '" + bname +
				"', branchAddress = '" +baddr + "' where branchId = " + id );

		return result;
	}
	
	
	//add copies of book to the branch
		
	public void printBooks(Statement s, Common c) throws SQLException{
		
		System.out.println("Pick the book you want to add copies of to your library");
		
		String q = "select title from tbl_book_copies "
				+ "join tbl_book on tbl_book.bookId= tbl_book_copies.bookId where tbl_book_copies.branchiD = " + id ;
		
		max = c.printBooks(s, q);
	
	}

	public void addBooks(Statement s, int i, Scanner in) throws SQLException{
		
		ResultSet r = s.executeQuery("select tbl_book.bookId, noOfCopies from tbl_book_copies "
				+ "join tbl_book on tbl_book.bookId= tbl_book_copies.bookId where tbl_book_copies.branchiD = " + id );
		
		int x =1;
		
		while(r.next()){
			
			if(x == i){
				System.out.println("Existing number of copies: " + r.getInt("noOfCopies"));
				bookid = r.getInt("bookId");
				
			}
			
			x++;
		}
	
		System.out.println("Enter new number of copies: ");
		int num = in.nextInt();
		
		int res = updateCopies(num,s);
		
		if(res == 1){
			
			System.out.println("Update successful\n");
		}
	}
	
	public int updateCopies(int n, Statement s) throws SQLException{
		
		int result = s.executeUpdate("update tbl_book_copies set noOfCopies = " + n +
				" where branchId = " + id + " and bookId = " + bookid);

		return result;
		
	}

}

