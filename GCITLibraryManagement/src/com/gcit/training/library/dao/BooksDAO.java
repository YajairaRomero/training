package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.Author;
import com.gcit.training.library.Books;

public class BooksDAO extends BaseDAO<Books> {
	
	public BooksDAO(Connection c){
		this.conn = c;
	}

	public void create(Books book) throws SQLException{
		int bookid = 0;
		
		if(book.getPub() != null)
			bookid = saveReturnGen("insert into tbl_book (title, pubId ) values(?, ?)",
				new Object [] {book.getTitle(), book.getPub().getPid()});
	
		else 
			bookid = saveReturnGen("insert into tbl_book (title, pubId ) values(?, ?)",
					new Object [] {book.getTitle(), null});
		
		//make entry in tbl_book_authors
		if(book.getAuthors() != null && book.getAuthors().size()>0){
			for(Author a : book.getAuthors()){
				save("insert into tbl_book_authors (bookId, authorId ) values(?, ?)", new Object [] { bookid, a.getAuthorid()});
			}
				
		}
		
	}
	
	
	public List<Books> read() throws SQLException{
		
		return (List<Books>) saveResultSet("select * from tbl_book");

	}
	

	public Books readOne(int bookid) throws SQLException {
		
		readOne = 1;
		 List<Books> list = (List<Books>) saveResultSet("select title from tbl_book where bookId = ?",
				 new Object[] {bookid});
		 
		 if(list != null && list.size()>0)
			 return list.get(0);
		 else
			 return null;

	}
	
	
	
	public void update(Books book) throws SQLException{


	
	}
	
	public void delete(Books book) throws SQLException{
		
		//delete from tbl_book_authors first
		if(book.getAuthors() != null && book.getAuthors().size()>0){
			for(Author a : book.getAuthors()){
				save("delete from tbl_book_authors where bookId = ?", new Object [] { book.getBookid()});
			}
				
		}
		
		//delete from tbl_book after
		save("delete from tbl_book where bookId = ?", new Object[] {book.getBookid()});
		
		
	}

	@Override
	public List<Books> mapResults(ResultSet rs) throws SQLException {
		
		List<Books> list = new ArrayList<Books>(); 
		AuthorDAO aDAO = new AuthorDAO(conn);
		PublisherDAO pDAO = new PublisherDAO(conn);
		
		while(rs.next()){
			Books b = new Books();
			
			if(readOne == 0)
				b.setBookid(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			
			List<Author> authorList = (List<Author>) aDAO.saveResultSet("select * from tbl_author where authorId in (select authorId from tbl_book_authors where bookId = ?)",
					new Object[] {b.getBookid()});
			b.setAuthors(authorList);
			
			b.setPub(pDAO.readOne(rs.getInt("pubId")));
			
			list.add(b);	
		}
		return list;
	}
	
	
}
