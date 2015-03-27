package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.Author;
import com.gcit.training.library.dao.AuthorDAO;

public class AuthorDAOTest {
	
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
	public void testCreate() {
		Author author = new Author();
		author.setName("Seymore Skinner");
		try {
			new AuthorDAO(conn).create(author);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Author create failed");
		}
		
		
	}

	@Test
	public void testReadOne() {
		Author author = new Author();
		author.setAuthorid(1);
		try {
			new AuthorDAO(conn).readOne(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Author read failed");
		}
		
		
	}
	
	
	@Test
	public void testRead() {
		
		try {
			new AuthorDAO(conn).read();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Author read failed");
		}
		
		
	}
	
	//@Test
	public void testUpdate() {

		Author author = new Author();
		author.setAuthorid(11);
		author.setName("Lizzy Biz");
		try {
			new AuthorDAO(conn).update(author);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Author create failed");
		}
	}

	//@Test
	public void testDelete() {

		Author author = new Author();
		author.setAuthorid(11);
		try {
			new AuthorDAO(conn).delete(author);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Author create failed");
		}
	}
	
	@After
	public void destory() throws SQLException{
		
		conn.close();
		
		conn = null;
	}

}
