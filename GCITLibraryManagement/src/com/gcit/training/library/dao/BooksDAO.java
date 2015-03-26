package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;


import java.util.List;

import com.gcit.training.library.Author;
import com.gcit.training.library.Books;

public class BooksDAO extends BaseDAO {
	
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
	
	
	public void read(Books book) throws SQLException{
		

		List<Object> list = saveResultSet("select title from tbl_book where bookId = ?", 
				new Object [] { book.getBookid()});

		for(Object obj : list)
			System.out.print(obj);
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
	
	
}
