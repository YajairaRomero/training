package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.Genre;

public class GenreDAO extends BaseDAO<Genre> {
	
	public GenreDAO(Connection c){
		this.conn = c;
	}

	public void create(Genre genre) throws SQLException{

		save("insert into tbl_genre (genre_name) values(?)",
				new Object[] { genre.getGenre_name()});
			
	}
	
	
	public List<Genre> read() throws SQLException {

		 return (List<Genre>) saveResultSet("select * from tbl_genre");

	}

	public Genre readOne(int genreid) throws SQLException {
		readOne = 1;
		 List<Genre> list = (List<Genre>) saveResultSet("select genre_name from tbl_genre where genre_id = ?",
				 new Object[] {genreid});
		 
		 if(list != null && list.size()>0)
			 return list.get(0);
		 else
			 return null;

	}

	public void update(Genre genre) throws SQLException{

		save("update tbl_genre set genre_name = ? where genre_id=?",
				new Object[] { genre.getGenre_name(), genre.getGenreid()});	
	}
	

	public void delete(Genre genre) throws SQLException{

		save("delete from tbl_genre where genre_id = ?",
				new Object[] {genre.getGenreid()});		
	}

	@Override
	public List<Genre> mapResults(ResultSet rs) throws SQLException {
		List<Genre> list = new ArrayList<Genre>(); 
		
		while(rs.next()){
			Genre g = new Genre();
			
			if(readOne == 0)
				g.setGenreid(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));
	
			list.add(g);	
		}
		return list;
	}
}
