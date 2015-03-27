package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.Publisher;

public class PublisherDAOTest {

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
		Publisher publisher = new Publisher();
		publisher.setPname("Wacky wacks");
		publisher.setPaddr("112 wacky rd");
		publisher.setPphone("222-2123");
		
		try {
			conn.setAutoCommit(false);
			new PublisherDAO(conn).create(publisher);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Publisher create failed");
		}
	}

	
	@Test
	public void testRead() throws SQLException {
		
		try {
			conn.setAutoCommit(false);
			new PublisherDAO(conn).read();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Publisher update failed");
		}
	}
	
	@Test
	public void testReadOne() throws SQLException {
		
		try {
			conn.setAutoCommit(false);
			new PublisherDAO(conn).readOne(1);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Publisher update failed");
		}
	}
	
	//@Test
	public void testUpdate() throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPname("Wacky wacks");
		publisher.setPaddr("112 wacky rd");
		publisher.setPphone("222-2123");
		publisher.setPid(15);
		
		try {
			conn.setAutoCommit(false);
			new PublisherDAO(conn).update(publisher);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Publisher update failed");
		}
	}

	//@Test
	public void testDelete() throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPid(14);
		
		try {
			conn.setAutoCommit(false);
			new PublisherDAO(conn).delete(publisher);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Publisher delete failed");
		}
	}
	
	@After
	public void destroy() throws SQLException{
		conn.close();
	}

}
