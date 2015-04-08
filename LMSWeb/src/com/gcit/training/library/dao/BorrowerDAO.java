package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.Author;
import com.gcit.training.library.Borrower;
import com.gcit.training.library.Publisher;

public class BorrowerDAO extends BaseDAO<Borrower>{

	public BorrowerDAO(Connection c){
		this.conn = c;
	}

	public void create(Borrower borrower) throws SQLException{

		save("insert into tbl_borrower (name, address, phone) values(?, ?, ?)",
				new Object[] { borrower.getName(),  borrower.getAddress(), borrower.getPhone()});

	}

	public List<Borrower> read() throws SQLException {

		return (List<Borrower>) readResultSet("select * from tbl_borrower");

	}

	public Borrower readOne(int borrowerid) throws SQLException {

		List<Borrower> list = (List<Borrower>) readAllResultSet("select * from tbl_borrower where cardNo = ?",
				new Object [] { borrowerid});

		if(list != null && list.size()>0)
			return list.get(0);
		else
			return null;

	}
	
	

	public void update(Borrower borrower) throws SQLException{

		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo=?",
				new Object[] { borrower.getName(),  borrower.getAddress(), borrower.getPhone(), borrower.getCardno()});
	}

	public void delete(Borrower borrower) throws SQLException{
		save("delete from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getCardno()});


	}

	@Override
	public List<Borrower> mapResults(ResultSet rs) throws SQLException {
		List<Borrower> list = new ArrayList<Borrower>(); 

		while(rs.next()){
			Borrower b = new Borrower();


			b.setCardno(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
			list.add(b);	
		}
		return list;
	}

	@Override
	public List<?> mapFirstLevelResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
