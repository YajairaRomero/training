package com.gcit.training.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.dao.BookCopiesDAO;
import com.gcit.training.library.dao.LibraryBranchDAO;

public class LibrarianService extends BaseService{
	
	//return list of library branches
	public List<LibraryBranch> displayLibraryBranches() throws Exception{
		return displayBranches();
	}

	
	//update branch info
	public void updateBranch(LibraryBranch newBranch) throws Exception{
		Connection conn = getConnection();
		LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 

		try{
			lbDAO.update(newBranch);
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}
		
	}
	
	//display books available at branch 
	public List<BookCopies> displayBranchCopies(int id) throws Exception{
		Connection conn = getConnection(); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn); 
		 List<BookCopies> copiesList = null;
		
		try{
			copiesList = bcDAO.readByBranchId(id);
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}
		
		
		//display the book titles
		int i = 0;
		while (i < copiesList.size()){
			
			System.out.println((i+1) + ") " + copiesList.get(i).getBooks().getTitle());			
			i++;			
		}
		i++;
		System.out.println(i + ") Quit to previous");
		
		return copiesList;
		
	}
	
	
	//update book copies
	public void setBranchCopies(BookCopies bc) throws Exception{
		Connection conn = getConnection();
		//LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn); 
//		BookCopies bc = copiesList.get(choice - 1);
	//	bc.setNoOfCopies(noOfCopies);
		
		try{
			bcDAO.update(bc);
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}
		
	}

	
}
