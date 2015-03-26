package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.BookLoan;
import com.gcit.training.library.Books;
import com.gcit.training.library.Borrower;
import com.gcit.training.library.LibraryBranch;

public class BookLoanDAOTest {
	
	private Connection conn = null;
	
	@Before
	public void innit(){
		
		try{
			this.conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/library", "root", "");
		} catch(Exception e){
			
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() throws SQLException {
		BookLoan loan = new BookLoan();
		
		//set bookId
		Books b = new Books();
		b.setBookid(7);
		loan.setBook(b);
		
		//set branchid
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchid(4);
		loan.setBranch(lb);
		
		//set cardNo
		Borrower borr = new Borrower();
		borr.setCardno(1);
		loan.setBorrower(borr);
		
		//set dateOut
		Calendar c = new GregorianCalendar();
		Date out = c.getTime();
		loan.setDateOut(out);
		
		//set dueDate		
		c.setTime(out);
		c.add(Calendar.DAY_OF_YEAR, 7);
		Date duedate = c.getTime();
		loan.setDueDate(duedate);
		
		
		try {
			conn.setAutoCommit(false);
			new BookLoanDAO(conn).create(loan);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("bookcopies create failed");
		}
	}

	@Test
	public void testRead() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}
	
	@After
	public void destory() throws SQLException{
		
		conn.close();
		
		conn = null;
	}

}
