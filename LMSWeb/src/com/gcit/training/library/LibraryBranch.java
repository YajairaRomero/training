package com.gcit.training.library;


public class LibraryBranch extends AbstractDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5459970176429757824L;
	//non-FK elements
	private int branchid = 0;
	private String bname = "", baddr = "";
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baddr == null) ? 0 : baddr.hashCode());
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + branchid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryBranch other = (LibraryBranch) obj;
		if (baddr == null) {
			if (other.baddr != null)
				return false;
		} else if (!baddr.equals(other.baddr))
			return false;
		if (bname == null) {
			if (other.bname != null)
				return false;
		} else if (!bname.equals(other.bname))
			return false;
		if (branchid != other.branchid)
			return false;
		return true;
	}
	
	public int getBranchid() {
		return branchid;
	}
	public String getBname() {
		return bname;
	}
	public String getBaddr() {
		return baddr;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public void setBaddr(String baddr) {
		this.baddr = baddr;
	}
	
	
}
