package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.Books;
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
		
		return (List<Publisher>) readResultSet("select * from tbl_publisher");

	}

	public Publisher readOne(int publisherid) throws SQLException {

		List<Publisher> list = (List<Publisher>) readAllResultSet("select * from tbl_publisher where publisherId = ?",
				new Object [] { publisherid});

		if(list != null && list.size()>0)			 
			return list.get(0);

		else
			return null;

	}
	
	public Publisher readOneFirstLevel(int publisherid) throws SQLException {

		List<Publisher> list = (List<Publisher>) readFirstLevel("select * from tbl_publisher where publisherId = ?",
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
		BooksDAO bDAO = new BooksDAO(conn);

		while(rs.next()){
			Publisher p = new Publisher();
			
			p.setPid(rs.getInt("publisherId"));
			p.setPname(rs.getString("publisherName"));
			p.setPaddr(rs.getString("publisherAddress"));
			p.setPphone(rs.getString("publisherPhone"));

			//set the list of books they've published
			List<Books> booklist =  (List<Books>) bDAO.readFirstLevel("select * from tbl_book where pubId = ?",
					new Object[] {p.getPid()});
			p.setBooks(booklist);		

			list.add(p);	
		}
		return list;
	}

	@Override
	public List<Publisher> mapFirstLevelResults(ResultSet rs) throws SQLException {
		List<Publisher> list = new ArrayList<Publisher>(); 

		while(rs.next()){
			Publisher p = new Publisher();

			p.setPid(rs.getInt("publisherId"));
			p.setPname(rs.getString("publisherName"));
			p.setPaddr(rs.getString("publisherAddress"));
			p.setPphone(rs.getString("publisherPhone"));

			list.add(p);	
		}
		
		return list;
	}

	public List<Publisher> getPublishersByName(String publisherToSearch)
			throws SQLException {
		publisherToSearch = "%" + publisherToSearch + "%";
		return (List<Publisher>) readFirstLevel(
				"select * from tbl_publisher where publisherName like ?",
				new Object[] { publisherToSearch });
	}

	public List<Publisher> page(int pageNo) throws SQLException {
		return (List<Publisher>) readResultSet("select * from tbl_publisher LIMIT " + (pageNo-1)*5 + ",5");
	}

	public int count() throws SQLException {
		return count("select count(*) from tbl_publisher");
	}



}
