package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.BookCopies;
import com.gcit.training.library.Publisher;

public class BookCopiesDAO extends BaseDAO<BookCopies>{

	public BookCopiesDAO(Connection c){
		this.conn = c;
	}

	public void create(BookCopies bookcopies) throws SQLException{

		//if(bookcopies.getBooks() != null && bookcopies.getBranches() != null)
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?)",
				new Object[] {bookcopies.getBooks().getBookid(), bookcopies.getBranches().getBranchid(), bookcopies.getNoOfCopies() });


	}

	public List<BookCopies> read() throws SQLException {

		return (List<BookCopies>) readResultSet("select * from tbl_book_copies");

	}

	public List<BookCopies> readMany(int branchid) throws SQLException {

		return (List<BookCopies>) readAllResultSet("select * from tbl_book_copies where branchId = ?",
				new Object[] {branchid});

	}
	
	public BookCopies readOne(int bookid, int branchid) throws SQLException {

		List<BookCopies> list = (List<BookCopies>) readAllResultSet("select * from tbl_book_copies where bookId = ? and branchId = ?",
				new Object [] { bookid, branchid});

		if(list != null && list.size()>0)			 
			return list.get(0);

		else
			return null;

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

	@Override
	public List<BookCopies> mapResults(ResultSet rs) throws SQLException {
		List<BookCopies> list = new ArrayList<BookCopies>(); 

		BooksDAO bDAO = new BooksDAO(conn);
		LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);

		while(rs.next()){
			BookCopies bc = new BookCopies();
			
			bc.setBooks(bDAO.readOne(rs.getInt("bookId")));
			bc.setBranches(lbDAO.readOne(rs.getInt("branchId")));
			bc.setNoOfCopies(rs.getInt("noOfCopies"));

			list.add(bc);	
		}
		return list;
	}

	@Override
	public List<?> mapFirstLevelResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}



