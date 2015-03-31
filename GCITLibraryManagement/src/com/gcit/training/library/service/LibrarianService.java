package com.gcit.training.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.dao.BookCopiesDAO;
import com.gcit.training.library.dao.LibraryBranchDAO;

public class LibrarianService extends BaseService{

	private LibraryBranch branch = null;
	private List<BookCopies> copiesList = null;
	private List<LibraryBranch> lbList = null;
	public int max = 0;
	
	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	//display library branches
	public void displayLibraryBranches() throws Exception{

		lbList = displayBranches();
		max = lbList.size() + 1;

	}
	
	//get info on the branch user chose
	public void chooseBranch(int choice) throws Exception{
		
		branch = lbList.get(choice-1);
		
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
	public void displayBranchCopies() throws Exception{
		Connection conn = getConnection(); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn); 
		
		try{
			copiesList = bcDAO.readMany(branch.getBranchid());
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
		max = i;
		
	}
	
	//user chose a book
	
	public int numBranchCopies(int choice){
		return copiesList.get(choice - 1).getNoOfCopies();	
		
	}
	
	//update book copies
	public void setBranchCopies(int choice, int noOfCopies) throws Exception{
		Connection conn = getConnection();
		//LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn); 
		BookCopies bc = copiesList.get(choice - 1);
		bc.setNoOfCopies(noOfCopies);
		
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
