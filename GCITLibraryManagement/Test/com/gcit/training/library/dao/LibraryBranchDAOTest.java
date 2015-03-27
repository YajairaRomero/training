package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.LibraryBranch;

public class LibraryBranchDAOTest {
	
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
		LibraryBranch branch = new LibraryBranch();
		branch.setBname("Emilstein");
		branch.setBaddr("112 wacky rd");
				
		try {
			new LibraryBranchDAO(conn).create(branch);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("library branch create failed");
		}
	}
	
	
	@Test
	public void testRead() {
				
		try {
			conn.setAutoCommit(false);
			new LibraryBranchDAO(conn).read();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail("library branch update failed");
		}
	}

	@Test
	public void testReadOne() {
	
		try {
			conn.setAutoCommit(false);
			new LibraryBranchDAO(conn).readOne(4);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			fail("library branch update failed");
		}
	}

	//@Test
	public void testUpdate() {
		LibraryBranch branch = new LibraryBranch();
		branch.setBname("Emilsteinzee");
		branch.setBaddr("112 wacky rd");
		branch.setBranchid(11);
				
		try {
			new LibraryBranchDAO(conn).update(branch);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("library branch update failed");
		}
	}

	//@Test
	public void testDelete() {
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchid(11);
				
		try {
			new LibraryBranchDAO(conn).delete(branch);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("library branch delete failed");
		}
	}
	
	@After
	public void destroy() throws SQLException{
		conn.close();
	}

}
