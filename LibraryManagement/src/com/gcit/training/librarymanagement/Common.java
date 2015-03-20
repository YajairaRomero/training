package com.gcit.training.librarymanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Common {

	//print out library locations
	public int returnLocations(Statement s, String query) throws SQLException{
		
		ResultSet r =  s.executeQuery(query);
		int i = 1;
		
		while(r.next()){
			System.out.println(i +") " + r.getString("branchName") + ", " + r.getString("branchAddress"));
			i++;
			
		}
				
		System.out.println(i + ") Quit to previous");		
		
		return i;
	}
	
	//print out books available at that library
	public int printBooks(Statement s, String query) throws SQLException{
			
		ResultSet r = s.executeQuery(query);
		
		int i = 1;
		
		while(r.next()){
			
			System.out.println(i + ") " + r.getString("title"));
			i++;
		}
		
		System.out.println(i+ ") Quit to cancel operation");
		
		return i;
	}
}
