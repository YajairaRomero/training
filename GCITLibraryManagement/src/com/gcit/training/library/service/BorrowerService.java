package com.gcit.training.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.BookLoan;
import com.gcit.training.library.Books;
import com.gcit.training.library.Borrower;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.dao.BookCopiesDAO;
import com.gcit.training.library.dao.BookLoanDAO;
import com.gcit.training.library.dao.BorrowerDAO;


public class BorrowerService extends BaseService{
	public int max = 0;
	private List<LibraryBranch> lbList = null;
	private LibraryBranch branch = null;
	private List<BookCopies> copiesList = null;
	private List<BookLoan> loanList = null;
	private BookCopies copy = null;
	private Borrower borr = null;

	//make sure that a valid cardNo has been given
	public boolean checkCardNo(int cardno) throws Exception{

		Connection conn = getConnection();
		//LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
		BorrowerDAO bDAO = new BorrowerDAO(conn);

		try{
			borr = bDAO.readOne(cardno);
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

		if(borr == null)		
			return false;
		else 
			return true;

	}


	//CHECK OUT A BOOK

	//display library branches
	public void displayLibraryBranch() throws Exception{
		lbList = displayBranches();
		max = lbList.size() + 1;
	}

	//get info on the branch user chose
	public void chooseBranch(int choice) throws Exception{
		branch = lbList.get(choice-1);		
	}

	//display books available at branch 
	public void displayBranchCopies() throws Exception{
		Connection conn = getConnection(); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn); 

		try{
			copiesList = bcDAO.readHaving(branch.getBranchid());
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

	//get info on the branch user chose
	public void chooseBook(int choice) throws Exception{

		copy =  copiesList.get(choice-1);			 

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
	public void displayBooksCheckedOut() throws Exception{
		Connection conn = getConnection(); 
		BookLoanDAO blDAO = new BookLoanDAO(conn); 

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

		max = i;
	}

	public void returnBook(int choice) throws Exception{			

		Connection conn = getConnection(); 
		BookLoanDAO blDAO = new BookLoanDAO(conn); 
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn);			
		BookLoan bl = loanList.get(choice-1);	
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
