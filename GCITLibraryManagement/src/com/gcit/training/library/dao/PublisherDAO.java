package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{
	
	
	public PublisherDAO(Connection c){
		this.conn = c;
	}

	public void create(Publisher publisher) throws SQLException{

		save("insert into tbl_publisher(publisherName, publisherAddress, publisherPhone) values(?, ?, ?)",
				new Object[] {publisher.getPname(), publisher.getPaddr(),  publisher.getPphone()});
	}
	
	
	
	public List<Publisher> read() throws SQLException {

		 return (List<Publisher>) saveResultSet("select * from tbl_publisher");

	}

	public Publisher readOne(int publisherid) throws SQLException {
			readOne = 1;
		
		 List<Publisher> list = (List<Publisher>) saveResultSet("select publisherName, publisherAddress, publisherPhone from tbl_publisher where publisherId = ?",
					new Object [] { publisherid});
		 
		 if(list != null && list.size()>0)			 
			 return list.get(0);
		 
		 else
			 return null;

	}

	public void update(Publisher publisher) throws SQLException{

		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId=?",
				new Object[] {publisher.getPname(), publisher.getPaddr(),  publisher.getPphone(), publisher.getPid()});
	}

	public void delete(Publisher publisher) throws SQLException{
		
		save("delete from tbl_publisher where publisherId = ?",
				new Object[] {publisher.getPid()});
	}

	@Override
	public List<Publisher> mapResults(ResultSet rs) throws SQLException {
		List<Publisher> list = new ArrayList<Publisher>(); 
		
		while(rs.next()){
			Publisher p = new Publisher();
			
			if(readOne == 0)
				p.setPid(rs.getInt("publisherId"));
			p.setPname(rs.getString("publisherName"));
			p.setPaddr(rs.getString("publisherAddress"));
			p.setPphone(rs.getString("publisherPhone"));
			list.add(p);	
		}
		return list;
	}





}
