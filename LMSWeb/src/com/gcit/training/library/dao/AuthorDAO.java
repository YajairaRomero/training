package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.Author;
import com.gcit.training.library.Books;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection c) {
		this.conn = c;
	}

	public void create(Author author) throws SQLException {

		save("insert into tbl_author(authorName) values(?)",
				new Object[] { author.getName() });

	}

	public List<Author> read() throws SQLException {

		return (List<Author>) readResultSet("select * from tbl_author");

	}

	public Author readOne(int authorid) throws SQLException {

		List<Author> list = (List<Author>) readAllResultSet(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorid });

		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;

	}
	
	public Author readOneFirstLevel(int authorid) throws SQLException {

		List<Author> list = (List<Author>) readFirstLevel(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorid });

		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;

	}
	
	public List<Author> readSearch(String value) throws SQLException {

		return (List<Author>) readFirstLevel(
				"select * from tbl_author where authorName like '%"+ value +"%'",
				new Object[] { });


	}

	public void update(Author author) throws SQLException {

		save("update tbl_author set authorName = ? where authorId=?",
				new Object[] { author.getName(), author.getAuthorid() });

	}

	public void delete(Author author) throws SQLException {

		save("delete from tbl_author where authorId = ?",
				new Object[] { author.getAuthorid() });

	}

	@Override
	public List<Author> mapResults(ResultSet rs) throws SQLException {

		List<Author> list = new ArrayList<Author>();
		BooksDAO bDAO = new BooksDAO(conn);
		while (rs.next()) {
			Author a = new Author();

			a.setAuthorid(rs.getInt("authorId"));
			a.setName(rs.getString("authorName"));

			// set books
			List<Books> booklist = (List<Books>) bDAO.readFirstLevel(
							"select * from tbl_book where bookId in (select bookId from tbl_book_authors where authorId = ?)",
							new Object[] { a.getAuthorid() });
			a.setBooks(booklist);

			list.add(a);
		}
		return list;
	}

	@Override
	public List<Author> mapFirstLevelResults(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<Author>();

		while (rs.next()) {
			Author a = new Author();
			try {
				a.setAuthorid(rs.getInt("authorId"));
			} catch (SQLException e) {

			}
			try {
				a.setName(rs.getString("authorName"));
			} catch (SQLException e) {

			}

			list.add(a);
		}
		return list;
	}
	
	public List<Author> getAuthorsByName(String authorToSearch)
			throws SQLException {
		authorToSearch = "%" + authorToSearch + "%";
		return (List<Author>) readFirstLevel(
				"select * from tbl_author where authorName like ?",
				new Object[] { authorToSearch });
	}

	public List<Author> page(int pageNo) throws SQLException {
		return (List<Author>) readResultSet("select * from tbl_author LIMIT " + (pageNo-1)*5 + ",5");
	}

	public int count() throws SQLException {
		return count("select count(*) from tbl_author");
	}


}
