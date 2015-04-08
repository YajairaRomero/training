package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.LibraryBranch;


public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {

	public LibraryBranchDAO(Connection c){
		this.conn = c;
	}

	public void create(LibraryBranch branch) throws SQLException{

		save("insert into tbl_library_branch (branchName, branchAddress) values(?, ?)",
				new Object[] {branch.getBname(), branch.getBaddr() });

	}

	public List<LibraryBranch> read() throws SQLException {

		return (List<LibraryBranch>) readResultSet("select * from tbl_library_branch");

	}

	public LibraryBranch readOne(int branchid) throws SQLException {

		List<LibraryBranch> list = (List<LibraryBranch>) readAllResultSet("select * from tbl_library_branch where branchId = ?",
				new Object[] {branchid});

		if(list != null && list.size()>0)
			return list.get(0);
		
		else
			return null;

	}
	public void update(LibraryBranch branch) throws SQLException{

		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId=?",
				new Object[] {branch.getBname(), branch.getBaddr(), branch.getBranchid() });

	}

	public void delete(LibraryBranch branch) throws SQLException{

		save("delete from tbl_library_branch where branchId = ?",
				new Object[] {branch.getBranchid() });
	}

	@Override
	public List<LibraryBranch> mapResults(ResultSet rs) throws SQLException {
		List<LibraryBranch> list = new ArrayList<LibraryBranch>(); 

		while(rs.next()){
			LibraryBranch lb = new LibraryBranch();


			lb.setBranchid(rs.getInt("branchId"));
			lb.setBname(rs.getString("branchName"));
			lb.setBaddr(rs.getString("branchAddress"));

			list.add(lb);	
		}
		return list;
	}

	@Override
	public List<?> mapFirstLevelResults(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
