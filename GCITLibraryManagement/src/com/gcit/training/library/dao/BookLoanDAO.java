package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.gcit.training.library.BookLoan;

public class BookLoanDAO extends BaseDAO{

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


	public void read(BookLoan loan){

	}

	public void update(BookLoan loan){

	}

	public void delete(BookLoan loan){

	}

}
