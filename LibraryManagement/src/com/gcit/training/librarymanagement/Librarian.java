package com.gcit.training.librarymanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Librarian {

	public int max = 0;
	private int id = 0, bookid = 0;
	private String libname = "", addr = "";
	
	
	//return the library name
	public void chooseLibrary(int i, ResultSet r) throws SQLException{
		
		int x = 1;
		
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
	public int update(Scanner scan) throws SQLException{
		
		System.out.println("You have chosen to update the Branch with Branch Id: " + id + 
				" and Branch Name: " + libname + ". \nEnter 'quit' at any prompt to cancel operation.\n\n"
				+ "Please enter new branch name or enter N/A for no change.");
		
		scan.nextLine();	//get rid of newline character
		
		String bname = scan.nextLine();
		
		if (bname.equals("quit")){
			return 0;
		}
		else if(!bname.equals("N/A")){
			libname = bname;
		}		 
				
		System.out.println("Please enter new branch address or enter N/A for no change.");
		
		String baddr = scan.nextLine();
		
	
		if (baddr.equals("quit")){
			return 0;
		}
		else if(!baddr.equals("N/A")){
			addr = baddr;
		}
		
		return 1;
	
	}
	

	public void addBooks(ResultSet r, int i, Scanner in) throws SQLException{
		
		int x =1;
		
		while(r.next()){
			
			if(x == i){
				System.out.println("Existing number of copies: " + r.getInt("noOfCopies"));
				bookid = r.getInt("bookId");
				
			}
			
			x++;
		}

	}

	

	public String getBranchName(){
		return libname;
	}
	
	public String getAddress(){
		return addr;
	}
	
	public int getId(){
		return id;
	}
	
	public int getBookId(){
		return bookid;
	}
}

