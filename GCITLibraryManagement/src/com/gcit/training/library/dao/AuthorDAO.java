package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.Author;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection c){
		this.conn = c;
	}


	public void create(Author author) throws SQLException {

		save("insert into tbl_author(authorName) values(?)", new Object [] {author.getName()});

	}

	
	public List<Author> read() throws SQLException {

		 return (List<Author>) saveResultSet("select * from tbl_author");

	}

	public Author readOne(int authorid) throws SQLException {
		readOne = 1;
		 List<Author> list = (List<Author>) saveResultSet("select authorName from tbl_author where authorId = ?",
				 new Object[] {authorid});
		 
		 if(list != null && list.size()>0)
			 return list.get(0);
		 else
			 return null;

	}
	
	public void update(Author author) throws SQLException {

		save("update tbl_author set authorName = ? where authorId=?", 
				new Object [] {author.getName(), author.getAuthorid()});
	

	}

	public void delete(Author author) throws SQLException {

		save("delete from tbl_author where authorId = ?",
				new Object [] {author.getAuthorid()});
	

	}


	@Override
	public List<Author> mapResults(ResultSet rs) throws SQLException {
		
		List<Author> list = new ArrayList<Author>(); 
		
		while(rs.next()){
			Author a = new Author();
			if(readOne == 0)
				a.setAuthorid(rs.getInt("authorId"));
			a.setName(rs.getString("authorName"));
			list.add(a);	
		}
		return list;
	}

}
