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
		
		if(loan.getDateOut() == null){
			
			save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?, ?, ?, ?, ?) ",
					new Object[] {loan.getBook().getBookid(), loan.getBranch().getBranchid(), loan.getBorrower().getCardno(),
					null, loan.getDueDate()});
		}
		else if(loan.getDueDate() == null){
			save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?, ?, ?, ?, ?) ",
					new Object[] {loan.getBook().getBookid(), loan.getBranch().getBranchid(), loan.getBorrower().getCardno(),
					loan.getDateOut(), null});
		}
		else if( loan.getDateOut() == null && loan.getDueDate() == null){
			save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?, ?, ?, ?, ?) ",
					new Object[] {loan.getBook().getBookid(), loan.getBranch().getBranchid(), loan.getBorrower().getCardno(),
					null, null});
		}
		else	
			save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?, ?, ?, ?, ?) ",
					new Object[] {loan.getBook().getBookid(), loan.getBranch().getBranchid(), loan.getBorrower().getCardno(),
					loan.getDateOut(), loan.getDueDate()});

	}

	
	public List<BookLoan> read() throws SQLException {

		return (List<BookLoan>) saveResultSet("select * from tbl_book_loans");

	}

	public BookLoan readOne(int bookid, int branchid, int cardno) throws SQLException {
		readOne = 1;
		List<BookLoan> list = (List<BookLoan>) saveResultSet("select dateOut, dueDate from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] {bookid, branchid, cardno});

		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;

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
			
			if(readOne == 0){
				bl.setBook(bDAO.readOne(rs.getInt("branchId")));
				bl.setBranch(lbDAO.readOne(rs.getInt("branchId")));
				bl.setBorrower(brDAO.readOne(rs.getInt("cardNo")));
			}
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
	
			list.add(bl);	
		}
		return list;
	}

}
