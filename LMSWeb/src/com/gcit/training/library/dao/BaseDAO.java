package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;



public abstract class BaseDAO<T> {
	
	protected Connection conn = null;

	public BaseDAO() {
		super();
	}

	public void save(String query, Object [] vals) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(query);
		loopStatement(vals, ps);
		ps.execute();

	}

	public int saveReturnGen(String query, Object [] vals) throws SQLException {

		PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		loopStatement(vals, ps);
		ps.execute();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		if(rs != null && rs.next())
			return rs.getInt(1);
		
		else 
			return -1;

	}
	
	public int count(String query) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) 
			return rs.getInt(1);
		else
			return 0;
	}

	public List<?> readResultSet(String query) throws SQLException{
		return readAllResultSet(query, null); 
	
	}
	
	public List<?> readAllResultSet(String query,  Object [] vals) throws SQLException{
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		if(vals != null)
			loopStatement(vals, ps);
		
		ResultSet rs = ps.executeQuery();
		return mapResults(rs);
	}
	
	public List<?> readFirstLevel(String query,  Object [] vals) throws SQLException{
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		if(vals != null)
			loopStatement(vals, ps);
		
		ResultSet rs = ps.executeQuery();
		return mapFirstLevelResults(rs);
	}

	public abstract List<?> mapResults(ResultSet rs) throws SQLException;
	
	public abstract List<?> mapFirstLevelResults(ResultSet rs) throws SQLException;

	private void loopStatement(Object[] vals, PreparedStatement ps)
			throws SQLException {
		int loop = 1;

		for(Object obj : vals){
			if(obj == null)
				ps.setNull(loop, Types.NULL);
			else
				ps.setObject(loop, obj);
			loop++;
		}
	}
	
	


}