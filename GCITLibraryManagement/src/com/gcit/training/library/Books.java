package com.gcit.training.library;

//import java.io.Serializable;
import java.util.List;


public class Books extends AbstractDomain {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4030878723518268780L;
	//non-FK elements
	private int bookid = 0;
	private String title = "";
	private Publisher pub = null;
	private List<Author> authors = null;
	private List <Genre> genres;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + bookid;
		result = prime * result + ((pub == null) ? 0 : pub.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Books other = (Books) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (bookid != other.bookid)
			return false;
		if (pub == null) {
			if (other.pub != null)
				return false;
		} else if (!pub.equals(other.pub))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPub(Publisher pub) {
		this.pub = pub;
	}
	
	public int getBookid() {
		return bookid;
	}
	public String getTitle() {
		return title;
	}
	public Publisher getPub() {
		return pub;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
