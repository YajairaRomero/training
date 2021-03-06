package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.BookLoan;
import com.gcit.training.library.LibraryBranch;

public class BookLoanDAO extends BaseDAO<BookLoan>{

	public BookLoanDAO(Connection c) {
		this.conn = c;
	}

	public void create(BookLoan loan) throws SQLException{


		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?, ?, ?, ?, ?) ",
				new Object[] {loan.getBook().getBookid(), loan.getBranch().getBranchid(), loan.getBorrower().getCardno(),
				loan.getDateOut(), loan.getDueDate()});

	}


	public List<BookLoan> read() throws SQLException {

		return (List<BookLoan>) readResultSet("select * from tbl_book_loans");

	}

	public BookLoan readOne(int bookid, int branchid, int cardno) throws SQLException {

		List<BookLoan> list = (List<BookLoan>) readAllResultSet("select * from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] {bookid, branchid, cardno});

		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;

	}

	public List<BookLoan> readMany(int cardno) throws SQLException {

		return (List<BookLoan>) readAllResultSet("select * from tbl_book_loans where cardNo = ?",
				new Object[] {cardno});

	}

	public void update(BookLoan loan) throws SQLException{

		save("update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] {loan.getDueDate(), loan.getBook().getBookid(), loan.getBranch().getBranchid(), loan.getBorrower().getCardno()});

	}

	public void delete(BookLoan loan) throws SQLException{

		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] {loan.getBook().getBookid(), loan.getBranch().getBranchid(), loan.getBorrower().getCardno()});
	}

	@Override
	public List<BookLoan> mapResults(ResultSet rs) throws SQLException {
		List<BookLoan> list = new ArrayList<BookLoan>(); 
		LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
		BorrowerDAO brDAO = new BorrowerDAO(conn);
		BooksDAO bDAO = new BooksDAO(conn);

		while(rs.next()){
			BookLoan bl = new BookLoan();

			bl.setBook(bDAO.readOne(rs.getInt("bookId")));
			bl.setBranch(lbDAO.readOne(rs.getInt("branchId")));
			bl.setBorrower(brDAO.readOne(rs.getInt("cardNo")));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));

			list.add(bl);	
		}
		return list;
	}

	@Override
	public List<BookLoan> mapFirstLevelResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
