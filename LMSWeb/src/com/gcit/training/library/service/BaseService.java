package com.gcit.training.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.dao.LibraryBranchDAO;

public class BaseService {
	
	protected Connection getConnection() throws Exception{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/library", "root", "");
			conn.setAutoCommit(false);
			
			return conn;
		} catch(Exception e){			
			e.printStackTrace();
			throw e;
		}
	}
	
	//display library branches
	protected List<LibraryBranch> displayBranches() throws Exception{

		Connection conn = getConnection();
		LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
		List<LibraryBranch> lbList = null;
		try{
			lbList = lbDAO.read();
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

		int i = 0;
		while ( i < lbList.size()){
			LibraryBranch branch = lbList.get(i);
			i++;
			System.out.println(i + ") " + branch.getBname() + " " + branch.getBaddr());
		}
		i++;
		System.out.println(i + ") Quit to previous");
		
		return lbList;

	}
	
	
}
