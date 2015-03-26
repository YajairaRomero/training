package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;



public abstract class BaseDAO {

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
	
	public List saveResultSet(String query, Object [] vals) throws SQLException{
		
		PreparedStatement ps = conn.prepareStatement(query);
		loopStatement(vals, ps);
		ResultSet rs = ps.executeQuery();
		
		//get number of columns
		ResultSetMetaData metadata = rs.getMetaData();
		int numColumns = metadata.getColumnCount();
		
		
		List<Object> result = new ArrayList<Object>();
		
		while(rs.next()){
			int i = 1;
			while(i <= numColumns){
				result.add(rs.getObject(i));
				i++;
			}
		}
		
		return result;
	}

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