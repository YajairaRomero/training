package com.gcit.training.library;

import java.io.Serializable;

public class BookCopiesId implements Serializable{
	
	private int bookId, brancId;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBrancId() {
		return brancId;
	}

	public void setBrancId(int brancId) {
		this.brancId = brancId;
	}
	

}
