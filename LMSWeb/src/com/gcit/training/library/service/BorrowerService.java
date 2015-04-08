package com.gcit.training.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.BookLoan;
import com.gcit.training.library.Borrower;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.dao.BookCopiesDAO;
import com.gcit.training.library.dao.BookLoanDAO;
import com.gcit.training.library.dao.BorrowerDAO;


public class BorrowerService extends BaseService{

	//make sure that a valid cardNo has been given
	public Borrower checkCardNo(int cardno) throws Exception{

		Connection conn = getConnection();
		//LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
		BorrowerDAO bDAO = new BorrowerDAO(conn);
		Borrower borr = null;
		
		try{
			borr = bDAO.readOne(cardno);
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

	return borr;

	}

	//CHECK OUT A BOOK

	//display library branches
	public List<LibraryBranch> displayLibraryBranch() throws Exception{
		return displayBranches();
		
	}

	//display books available at branch 
	public List<BookCopies> displayBranchCopies(LibraryBranch branch) throws Exception{
		Connection conn = getConnection(); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn); 
		 List<BookCopies> copiesList = null;
		 
		try{
			copiesList = bcDAO.readBooksGreaterThanZero(branch.getBranchid());
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

	//get info on the branch user chose
	public void chooseBook(BookCopies copy, Borrower borr, LibraryBranch branch) throws Exception{

		Connection conn = getConnection(); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn);

		BookLoanDAO blDAO = new BookLoanDAO(conn);
		BookLoan bl = new BookLoan();
		bl.setBorrower(borr);
		bl.setBranch(branch);
		bl.setBook(copy.getBooks());

		//set dateOut
		Calendar c = new GregorianCalendar();
		Date out = c.getTime();
		bl.setDateOut(out);

		//set dueDate		
		c.setTime(out);
		c.add(Calendar.DAY_OF_YEAR, 7);
		Date duedate = c.getTime();
		bl.setDueDate(duedate);

		//subtract from noOfCopies in tbl_book_copies
		int newCopies = copy.getNoOfCopies() - 1;	
		copy.setNoOfCopies(newCopies);

		try{
			blDAO.create(bl);	//add to book loans table
			bcDAO.update(copy);	   
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

	}



	//RETURN A BOOK

	//display books user has checked out
	public List<BookLoan> displayBooksCheckedOut(Borrower borr) throws Exception{
		Connection conn = getConnection(); 
		BookLoanDAO blDAO = new BookLoanDAO(conn); 
		List<BookLoan> loanList = null;
		
		try{
			loanList = blDAO.readMany(borr.getCardno());
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

		//display the book titles
		int i = 0;
		while (i < loanList.size()){
			System.out.println((i+1) + ") " + loanList.get(i).getBook().getTitle() + " " + loanList.get(i).getDueDate());			
			i++;			
		}
		i++;
		System.out.println(i + ") Quit to previous");

		return loanList;
	}

	public void returnBook(BookLoan bl) throws Exception{			

		Connection conn = getConnection(); 
		BookLoanDAO blDAO = new BookLoanDAO(conn); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn);				
		BookCopies bc = null;				

		try{

			//increase number of copies in tbl_book_copies				
			bc = bcDAO.readOne(bl.getBook().getBookid(), bl.getBranch().getBranchid());	
			int newCopies = bc.getNoOfCopies() + 1;
			bc.setNoOfCopies(newCopies);
			bcDAO.update(bc);
			blDAO.delete(bl);
			
			conn.commit();

		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

	}

}
