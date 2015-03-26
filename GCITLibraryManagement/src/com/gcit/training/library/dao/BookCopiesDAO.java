package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.BookCopies;

public class BookCopiesDAO extends BaseDAO{

	public BookCopiesDAO(Connection c){
		this.conn = c;
	}
	
	public void create(BookCopies bookcopies) throws SQLException{
		
		//if(bookcopies.getBooks() != null && bookcopies.getBranches() != null)
			save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?)",
				new Object[] {bookcopies.getBooks().getBookid(), bookcopies.getBranches().getBranchid(), bookcopies.getNoOfCopies() });
	
	
	}
	
	public void read(BookCopies bookcopies) throws SQLException{
		List<Object> list = null;
		
		//if(bookcopies.getBooks() != null && bookcopies.getBranches() != null)
			list = saveResultSet("select noOfCopies from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] {bookcopies.getBooks().getBookid(), bookcopies.getBranches().getBranchid() });
		
		for(Object obj : list)
			System.out.print(obj + "    ");
	}
	
	public void update(BookCopies bookcopies) throws SQLException{
		
		//if(bookcopies.getBooks() != null && bookcopies.getBranches() != null)
			save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] {bookcopies.getNoOfCopies(), bookcopies.getBooks().getBookid(), bookcopies.getBranches().getBranchid() });
	
	}
	
	public void delete(BookCopies bookcopies) throws SQLException{
		
		//if(bookcopies.getBooks() != null && bookcopies.getBranches() != null)
			save("delete from tbl_book_copies where bookId = ? and branchId = ?",
					new Object[] {bookcopies.getBooks().getBookid(), bookcopies.getBranches().getBranchid()});

	}
}



