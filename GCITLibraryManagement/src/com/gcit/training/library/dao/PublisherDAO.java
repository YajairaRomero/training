package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.Publisher;

public class PublisherDAO extends BaseDAO{
	
	public PublisherDAO(Connection c){
		this.conn = c;
	}

	public void create(Publisher publisher) throws SQLException{

		save("insert into tbl_publisher(publisherName, publisherAddress, publisherPhone) values(?, ?, ?)",
				new Object[] {publisher.getPname(), publisher.getPaddr(),  publisher.getPphone()});
	}
	
	public void read(Publisher publisher) throws SQLException{
		
		List<Object> list = saveResultSet("select publisherName, publisherAddress, publisherPhone from tbl_publisher where publisherId = ?",
				new Object [] { publisher.getPid()});

		for(Object obj : list)
			System.out.print(obj + "  ");
	
	}

	public void update(Publisher publisher) throws SQLException{

		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId=?",
				new Object[] {publisher.getPname(), publisher.getPaddr(),  publisher.getPphone(), publisher.getPid()});
	}

	public void delete(Publisher publisher) throws SQLException{
		
		save("delete from tbl_publisher where publisherId = ?",
				new Object[] {publisher.getPid()});
	}





}
