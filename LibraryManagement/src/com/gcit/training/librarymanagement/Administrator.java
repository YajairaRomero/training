package com.gcit.training.librarymanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Administrator {
	
	private String name = "", addr = "", phone="", title =""; 
	private int id = 0, bookid = 0, pid = 0;
	
	public int x=0;
	
	//add for publishers, borrowers
	public int add(String type, Scanner input){		
		
		System.out.print("You're about to enter information for a new "+ type + ". Enter 'quit' at any "
				+ "prompt to cancel. \nEnter new "+ type +" name: ");
		
		String n = input.nextLine();
		n = input.nextLine();

		if(n.equals("quit")){

			return 0;
		}
			
		name = n;

		System.out.print("Enter new "+ type +" address: ");
		String a = input.nextLine();

		if(a.equals("quit")){
			return 0;
		}
		
		addr = a;

		System.out.print("Enter new "+ type+ " phone number: ");
		String p = input.nextLine();

		if(p.equals("quit")){
			return 0;
		}
		
		phone = p;

		return 1;		
		
	}
	
	
	//add for library branch
	public int add2(Scanner input){		
		
		System.out.print("You're about to enter information for a new library branch. Enter 'quit' at any "
				+ "prompt to cancel. \nEnter new branch name: ");
		
		String n = input.nextLine();
		n = input.nextLine();

		if(n.equals("quit")){

			return 0;
		}
			
		name = n;

		System.out.print("Enter new branch address: ");
		String a = input.nextLine();

		if(a.equals("quit")){
			return 0;
		}
		
		addr = a;


		return 1;
	}
	
	
	
	//add for author
	public int add3(Scanner input){		
		
		System.out.print("You're about to enter information for a new author. Enter 'quit' at "
				+ "prompt to cancel. \nEnter new author name: ");
		
		String n = input.nextLine();
		n = input.nextLine();

		if(n.equals("quit")){

			return 0;
		}
			
		name = n;

		return 1;
	}
	
	//add a book
	public int add4(Scanner input){		
		
		System.out.print("You're about to enter information for a new book. Enter or select 'quit' at any "
				+ "prompt to cancel. \nEnter new book name: ");
		
		String t = input.nextLine();
		t = input.nextLine();

		if(t.equals("quit")){

			return 0;
		}
			
		title = t;

		return 1;
	}
	
	public void chooseBook(ResultSet r, int i) throws SQLException{
		x = 1;
		
		while(r.next()){

			if(x == i){
				bookid = r.getInt("bookId");
				title =r.getString("title");	
				pid = r.getInt("pubId");
				break;
			}
			x++;
	
		}
	}
	
	public void chooseAuthor(ResultSet r, int i) throws SQLException{
		x = 1;
		
		while(r.next()){

			if(x == i){
				id = r.getInt("authorId");
				name=r.getString("authorName");	
				break;
			}
			x++;
		}
		
		System.out.println("id is " + id);
		
		
	}
	
	public void chooseAuthor2(ResultSet r) throws SQLException{
	//	x = 1;
		
		while(r.next()){

			//if(x == i){
				id = r.getInt("authorId");
				//name=r.getString("authorName");	
				break;
			//}
		//	x++;
		}
		
		System.out.println("id is " + id);
		
		
	}
	
	public void choosePublisher(ResultSet r, int i) throws SQLException{
		
		x = 1;
		
		while(r.next()){

			if(x == i){
				pid = r.getInt("publisherId");
				name=r.getString("publisherName");
				addr = r.getString("publisherAddress");
				phone = r.getString("publisherPhone");				
				break;
			}
			x++;
		}
		
	}
	
	//choose borrower and record their info
	public void chooseBorrower(ResultSet r, int i) throws SQLException{
		
		x = 1;
		
		while(r.next()){

			if(x == i){
				id = r.getInt("cardNo");
				name=r.getString("name");
				addr = r.getString("address");
				phone = r.getString("phone");				
				break;
			}
			x++;
		}
		
	}
	
	//choose library and record its info
	public void chooseLibrary(ResultSet r, int i) throws SQLException{
		x = 1;
		
		while(r.next()){

			if(x == i){
				id = r.getInt("branchId");
				name=r.getString("branchName");
				addr = r.getString("branchAddress");
						
				break;
			}
			x++;
		}
	}
	
	//update for publishers, borrowers
	public int update(String type, Scanner input){
				
		System.out.print("You're about to update information for a " +type + " with name: " + name +". Enter 'quit' at any "
				+ "prompt to cancel. \nUpdate " + type + " name or N/A for no change: ");
		
		String n = input.nextLine();
		n = input.nextLine();
		
		if(n.equals("quit"))
			return 0;
		else if(!n.equals("N/A")){
			name = n;
		}
		

		System.out.print("Update "+ type + " address: ");
		String a = input.nextLine();

		
		if(a.equals("quit"))
			return 0;
		else if(!a.equals("N/A")){
			addr = a;
		}
		
		System.out.print("Update "+ type +" phone number: ");
		String p = input.nextLine();

		if(!p.equals("N/A")){
			phone = p;
		}
		else if(p.equals("quit"))
			return 0;

		return 1;
	}
	
	//update library branch
	public int updateLB(Scanner input){
		
		System.out.print("You're about to update information for a library branch with name: " + name +" and id: " + id +". "
				+ "Enter 'quit' at any prompt to cancel. \nUpdate branch name or N/A for no change: ");
		
		String n = input.nextLine();
		n = input.nextLine();
		
		if(n.equals("quit"))
			return 0;
		else if(!n.equals("N/A")){
			name = n;
		}
		

		System.out.print("Update branch address: ");
		String a = input.nextLine();

		
		if(a.equals("quit"))
			return 0;
		else if(!a.equals("N/A")){
			addr = a;
		}

		return 1;
	}
	
	
	//update Author
	public int updateAuthor(Scanner input){
		
		System.out.print("You're about to update information for an author with name: " + name +" and id: " + id +". "
				+ "Enter 'quit' at any prompt to cancel. \nUpdate author name or N/A for no change: ");
		
		String n = input.nextLine();
		n = input.nextLine();
		
		if(n.equals("quit"))
			return 0;
		else if(!n.equals("N/A")){
			name = n;
		}

		return 1;
	}
	
	//update book
		public int updateBook(Scanner input){
			
			System.out.print("You're about to update information for a book with title: "
					+ title +  " and bookid: " + bookid + "."
					+ "Enter or select quit at any prompt to exit. \n"
					+ "Enter new title or N/A for no change: ");
			
			String t = input.nextLine();
			t = input.nextLine();
			
			if(t.equals("quit"))
				return 0;
			else if(!t.equals("N/A")){
				title = t;
			}

			return 1;
		}
	

	public int print(ResultSet rs, String column) throws SQLException{
		x=1;
		
		while(rs.next()){
			System.out.println(x +") " + rs.getString(column) );
			x++;
		}
		//System.out.println(x + ") Quit to previous");
		return x;
	}
	
	public int printPublishers(ResultSet rs) throws SQLException{
		x=1;
		
		while(rs.next()){
			System.out.println(x +") " + rs.getString("publisherName") + ", " + rs.getString("publisherAddress") + ", " + rs.getString("publisherPhone"));
			x++;
		}
		//System.out.println(x + ") Quit to previous");
		
		return x;
	}

	
	
	public String getName(){
	
		return name;
	}
	
	public String getAddr(){
		return addr;
	}
	
	public String getPhone(){
		return phone;
	}

	public int getId(){
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public int getBookid(){
		return bookid;
	}
	
	public int getPubid(){
		return pid;
	}
}
