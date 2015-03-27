package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.Borrower;

public class BorrowerDAOTest {
	
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
		Borrower borrower = new Borrower();
		borrower.setName("Emily");
		borrower.setAddress("112 wacky rd");
		borrower.setPhone("222-2123");
		
		try {
			conn.setAutoCommit(false);
			new BorrowerDAO(conn).create(borrower);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("borrower create failed");
		}
	}

	
	@Test
	public void testRead() throws SQLException {
		
		try {
			conn.setAutoCommit(false);
			new BorrowerDAO(conn).read();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("borrower read failed");
		}
	}
	
	@Test
	public void testReadOne() throws SQLException {
				
		try {
			conn.setAutoCommit(false);
			new BorrowerDAO(conn).readOne(13);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("borrower read failed");
		}
	}
	
	//@Test
	public void testUpdate() throws SQLException {
		Borrower borrower = new Borrower(); 
		borrower.setName("Paisley");
		borrower.setAddress("113 wacky rd");
		borrower.setPhone("222-2123");
		borrower.setCardno(24);
		
		try {
			conn.setAutoCommit(false);
			new BorrowerDAO(conn).update(borrower);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("borrower update failed");
		}
	}

	//@Test
	public void testDelete() throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCardno(25);
		
		try {
			conn.setAutoCommit(false);
			new BorrowerDAO(conn).delete(borrower);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("borrower delete failed");
		}
	}

}
