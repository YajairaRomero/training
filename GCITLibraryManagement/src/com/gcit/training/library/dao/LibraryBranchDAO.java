package com.gcit.training.library.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.LibraryBranch;;

public class LibraryBranchDAO extends BaseDAO {
	
	public LibraryBranchDAO(Connection c){
		this.conn = c;
	}
	
	public void create(LibraryBranch branch) throws SQLException{

		save("insert into tbl_library_branch (branchName, branchAddress) values(?, ?)",
				new Object[] {branch.getBname(), branch.getBaddr() });
		
	}
	
	public void read(LibraryBranch branch) throws SQLException{
		List<Object> list = saveResultSet("select branchName, branchAddress from tbl_library_branch where branchId = ?",
				new Object [] {branch.getBranchid()});

		for(Object obj : list)
			System.out.print(obj + "  ");
}

	public void update(LibraryBranch branch) throws SQLException{
		
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId=?",
				new Object[] {branch.getBname(), branch.getBaddr(), branch.getBranchid() });

	}

	public void delete(LibraryBranch branch) throws SQLException{
		
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] {branch.getBranchid() });
	}
	

}
