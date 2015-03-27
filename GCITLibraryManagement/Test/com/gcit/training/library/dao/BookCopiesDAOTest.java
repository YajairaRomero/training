package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.Books;
import com.gcit.training.library.LibraryBranch;

public class BookCopiesDAOTest {

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
	
	
	//@Test
	public void testCreate() throws SQLException {
		BookCopies bc = new BookCopies();
		
		//set bookid
		Books book = new Books();
		book.setBookid(10);
		bc.setBooks(book);
		
		//set branchId
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchid(4);
		bc.setBranches(lb);
		
		//set noOfCopies
		bc.setNoOfCopies(3);
		
		try {
			conn.setAutoCommit(false);
			new BookCopiesDAO(conn).create(bc);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("bookcopies create failed");
		}
		
	}

	@Test
	public void testRead() throws SQLException {
		
		try {
			conn.setAutoCommit(false);
			new BookCopiesDAO(conn).read();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("bookcopies create failed");
		}
	}

	@Test
	public void testReadOne() throws SQLException {
		
		try {
			conn.setAutoCommit(false);
			new BookCopiesDAO(conn).readOne(1, 10);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("bookcopies create failed");
		}
	}
	
	//@Test
	public void testUpdate() throws SQLException {
	BookCopies bc = new BookCopies();
		
		//set bookid
		Books book = new Books();
		book.setBookid(2);
		bc.setBooks(book);
		
		//set branchId
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchid(3);
		bc.setBranches(lb);
		
		//set noOfCopies
		bc.setNoOfCopies(10);
		
		try {
			conn.setAutoCommit(false);
			new BookCopiesDAO(conn).update(bc);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("bookcopies create failed");
		}
	}

	//@Test
	public void testDelete() throws SQLException {

	BookCopies bc = new BookCopies();
		
		//set bookid
		Books book = new Books();
		book.setBookid(10);
		bc.setBooks(book);
		
		//set branchId
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchid(4);
		bc.setBranches(lb);
		
		
		try {
			conn.setAutoCommit(false);
			new BookCopiesDAO(conn).delete(bc);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("books delete failed");
		}
		
	}

}
