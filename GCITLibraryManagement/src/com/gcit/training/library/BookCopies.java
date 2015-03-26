package com.gcit.training.library;


public class BookCopies extends AbstractDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6796805891016533059L;

	private int noOfCopies = 0;
	
	//foreign keys
	private Books books = null;
	private LibraryBranch branches = null;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result
				+ ((branches == null) ? 0 : branches.hashCode());
		result = prime * result + noOfCopies;
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
		BookCopies other = (BookCopies) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (branches == null) {
			if (other.branches != null)
				return false;
		} else if (!branches.equals(other.branches))
			return false;
		if (noOfCopies != other.noOfCopies)
			return false;
		return true;
	}
	
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}
	public LibraryBranch getBranches() {
		return branches;
	}
	public void setBranches(LibraryBranch branches) {
		this.branches = branches;
	}
	
	
	
	
	
	
	

}
