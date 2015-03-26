package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.Genre;

public class GenreDAO extends BaseDAO {
	
	public GenreDAO(Connection c){
		this.conn = c;
	}

	public void create(Genre genre) throws SQLException{

		save("insert into tbl_genre (genre_name) values(?)",
				new Object[] { genre.getGenre_name()});
			
	}
	
	
	public void read(Genre genre) throws SQLException {
		
		List<Object> list = saveResultSet("select genre_name from tbl_genre where genre_id = ?",
				new Object [] { genre.getGenreid()});

		for(Object obj : list)
			System.out.print(obj + "  ");
	}

	public void update(Genre genre) throws SQLException{

		save("update tbl_genre set genre_name = ? where genre_id=?",
				new Object[] { genre.getGenre_name(), genre.getGenreid()});	
	}
	

	public void delete(Genre genre) throws SQLException{

		save("delete from tbl_genre where genre_id = ?",
				new Object[] {genre.getGenreid()});		
	}
}
