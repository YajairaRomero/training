package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.Borrower;

public class BorrowerDAO extends BaseDAO{

	public BorrowerDAO(Connection c){
		this.conn = c;
	}

	public void create(Borrower borrower) throws SQLException{

		save("insert into tbl_borrower (name, address, phone) values(?, ?, ?)",
				new Object[] { borrower.getName(),  borrower.getAddress(), borrower.getPhone()});

	}
	
	public void read(Borrower borrower) throws SQLException {

		List<Object> list = saveResultSet("select name, address, phone from tbl_borrower where cardNo = ?",
				new Object [] { borrower.getCardno()});

		for(Object obj : list)
			System.out.print(obj + "  ");
	}

	public void update(Borrower borrower) throws SQLException{

		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo=?",
				new Object[] { borrower.getName(),  borrower.getAddress(), borrower.getPhone(), borrower.getCardno()});
	}

	public void delete(Borrower borrower) throws SQLException{
		save("delete from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getCardno()});


	}
}
