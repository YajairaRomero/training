package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.Author;
import com.gcit.training.library.Books;
import com.gcit.training.library.Publisher;

public class BooksDAOTest {

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
		Books book = new Books();
		book.setTitle("Billy and the Clonasaurus");
		
		Author author1 = new Author();
		author1.setAuthorid(1);
		
		Author author2 = new Author();
		author2.setAuthorid(2);
		List<Author> authors = new ArrayList<Author>();
		authors.add(author1);
		authors.add(author2);
		
		book.setAuthors(authors);
		
		Publisher pub = new Publisher();
		pub.setPid(4);
		
		book.setPub(pub);
		
		try {
			conn.setAutoCommit(false);
			new BooksDAO(conn).create(book);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("books create failed");
		}
		
	}
	
	
	@Test
	public void testRead() throws SQLException {
		Books book = new Books();
		book.setBookid(3);
		
		try {
			conn.setAutoCommit(false);
			new BooksDAO(conn).read(book);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("books delete failed");
		}
		
	}


	//@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	//@Test
	public void testDeleteBooks() throws SQLException {
		Books book = new Books();
		book.setBookid(14);
		
		Author author1 = new Author();
		author1.setAuthorid(1);
		
		Author author2 = new Author();
		author2.setAuthorid(2);
		List<Author> authors = new ArrayList<Author>();
		authors.add(author1);
		authors.add(author2);
		
		book.setAuthors(authors);
		
		try {
			conn.setAutoCommit(false);
			new BooksDAO(conn).delete(book);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("books delete failed");
		}
		
	}

	@After
	public void destroy() throws SQLException{
		conn.close();
	}


}
