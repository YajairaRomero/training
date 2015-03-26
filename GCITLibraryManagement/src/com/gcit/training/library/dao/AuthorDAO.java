package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.Author;

public class AuthorDAO extends BaseDAO {

	public AuthorDAO(Connection c){
		this.conn = c;
	}


	public void create(Author author) throws SQLException {

		save("insert into tbl_author(authorName) values(?)", new Object [] {author.getName()});

	}

	
	public void read(Author author) throws SQLException {

		List<Object> list = saveResultSet("select authorName from tbl_author where authorId = ?", new Object [] {author.getAuthorid()});

		for(Object obj : list)
			System.out.print(obj);
	}
	
	public void update(Author author) throws SQLException {

		save("update tbl_author set authorName = ? where authorId=?", 
				new Object [] {author.getName(), author.getAuthorid()});
	

	}

	public void delete(Author author) throws SQLException {

		save("delete from tbl_author where authorId = ?",
				new Object [] {author.getAuthorid()});
	

	}

}
