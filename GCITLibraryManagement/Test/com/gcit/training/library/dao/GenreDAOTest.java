package com.gcit.training.library.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gcit.training.library.Genre;

public class GenreDAOTest {
	
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
		Genre genre = new Genre();
		genre.setGenre_name("scurry");
		try {
			conn.setAutoCommit(false);
			new GenreDAO(conn).create(genre);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Genre create failed");
		}
	}

	
	@Test
	public void testRead() throws SQLException {

		try {
			conn.setAutoCommit(false);
			new GenreDAO(conn).read();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Genre read failed");
		}
	}
	
	@Test
	public void testReadOne() throws SQLException {

		try {
			conn.setAutoCommit(false);
			new GenreDAO(conn).readOne(2);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Genre readOne failed");
		}
	}
	
	//@Test
	public void testUpdate() throws SQLException {
		Genre genre = new Genre();
		genre.setGenre_name("Bizzy Bills");
		genre.setGenreid(7);
		try {
			conn.setAutoCommit(false);
			new GenreDAO(conn).update(genre);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Genre update failed");
		}
	}

	//@Test
	public void testDelete() throws SQLException {
		Genre genre = new Genre();
		genre.setGenreid(7);
		try {
			conn.setAutoCommit(false);
			new GenreDAO(conn).delete(genre);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			fail("Genre delete failed");
		}
	}
	
	@After
	public void destroy() throws SQLException{
		conn.close();
	}

}
