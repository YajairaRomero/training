package com.gcit.training.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.training.library.Author;
import com.gcit.training.library.Books;
import com.gcit.training.library.Borrower;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.Publisher;
import com.gcit.training.library.dao.AuthorDAO;
import com.gcit.training.library.dao.BooksDAO;
import com.gcit.training.library.dao.BorrowerDAO;
import com.gcit.training.library.dao.LibraryBranchDAO;
import com.gcit.training.library.dao.PublisherDAO;

public class AdministratorService extends BaseService {

	public void createInventory(Object obj) throws Exception{

		if(obj instanceof Author){
			//call a private member that takes an Author
			createAuthor((Author) obj);

		}

		else if(obj instanceof Books){
			//call a private member that takes a book
			createBook((Books) obj);
		}
		else if(obj instanceof Publisher){
			//call a private member that takes a publisher
			createPublisher((Publisher) obj);
		}
		else if(obj instanceof LibraryBranch){
			//call a private member that takes a publisher
			createLibraryBranch((LibraryBranch) obj);
		}
		else if(obj instanceof Borrower){
			//call a private member that takes a publisher
			createBorrower((Borrower) obj);
		}
	}

	private void createBorrower(Borrower obj) throws Exception {
		if(obj != null){
			/*
			if(obj.getName() == null || obj.getName().length() > 45)
				throw new Exception("Borrower name cannot be blank or more than 45 characters");
			 */
			Connection conn = getConnection();
			BorrowerDAO bDAO = new BorrowerDAO(conn); 
			try{
				bDAO.create(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Borrower cannot be null");

	}

	private void createLibraryBranch(LibraryBranch obj) throws Exception {
		if(obj != null){
			if(obj.getBname() == null || obj.getBname().length() > 45)
				throw new Exception("Library Branch name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
			try{
				lbDAO.create(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Library Branch cannot be null");

	}

	private void createPublisher(Publisher obj) throws Exception {
		if(obj != null){
			if(obj.getPname() == null || obj.getPname().length() > 45)
				throw new Exception("Publisher name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			PublisherDAO pDAO = new PublisherDAO(conn); 
			try{
				pDAO.create(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Publisher cannot be null");
	}



	private void createBook(Books obj) throws Exception {
		if(obj != null){
			if(obj.getTitle() == null || obj.getTitle().length() > 45)
				throw new Exception("Title cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			BooksDAO bDAO = new BooksDAO(conn); 
			try{
				bDAO.create(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Book cannot be null");
	}

	private void createAuthor(Author obj) throws Exception {
		if(obj != null){
			if(obj.getName() == null || obj.getName().length() > 45)
				throw new Exception("Author name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			AuthorDAO aDAO = new AuthorDAO(conn); 
			try{
				aDAO.create(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Author cannot be null");
	}



	public void UpdateInventory(Object obj) throws Exception{

		if(obj instanceof Author){
			//call a private member that takes an Author
			UpdateAuthor((Author) obj);
		}

		else if(obj instanceof Books){
			//call a private member that takes a book
			UpdateBook((Books) obj);
		}
		else if(obj instanceof Publisher){
			//call a private member that takes a publisher
			UpdatePublisher((Publisher) obj);
		}
		else if(obj instanceof LibraryBranch){
			//call a private member that takes a publisher
			UpdateLibraryBranch((LibraryBranch) obj);
		}
		else if(obj instanceof Borrower){
			//call a private member that takes a publisher
			UpdateBorrower((Borrower) obj);
		}
	}



	private void UpdateAuthor(Author obj) throws Exception {
		if(obj != null){
			if(obj.getName() == null || obj.getName().length() > 45)
				throw new Exception("Author name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			AuthorDAO aDAO = new AuthorDAO(conn); 
			try{
				aDAO.update(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Author cannot be null");

	}

	private void UpdateBook(Books obj) throws Exception {
		if(obj != null){
			if(obj.getTitle() == null || obj.getTitle().length() > 45)
				throw new Exception("Title cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			BooksDAO bDAO = new BooksDAO(conn); 
			try{
				bDAO.update(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Book cannot be null");

	}

	private void UpdatePublisher(Publisher obj) throws Exception {
		if(obj != null){
			if(obj.getPname() == null || obj.getPname().length() > 45)
				throw new Exception("Publisher name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			PublisherDAO pDAO = new PublisherDAO(conn); 
			try{
				pDAO.update(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Publisher cannot be null");

	}

	private void UpdateLibraryBranch(LibraryBranch obj) throws Exception {
		if(obj != null){
			if(obj.getBname() == null || obj.getBname().length() > 45)
				throw new Exception("Library Branch name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
			try{
				lbDAO.update(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Library Branch cannot be null");

	}

	private void UpdateBorrower(Borrower obj) throws Exception {
		if(obj != null){
			Connection conn = getConnection();
			BorrowerDAO bDAO = new BorrowerDAO(conn); 
			try{
				bDAO.update(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Borrower cannot be null");

	}


	public void deleteInventory(Object obj) throws Exception{

		if(obj instanceof Author){
			//call a private member that takes an Author
			deleteAuthor((Author) obj);
		}

		else if(obj instanceof Books){
			//call a private member that takes a book
			deleteBook((Books) obj);
		}
		else if(obj instanceof Publisher){
			//call a private member that takes a publisher
			deletePublisher((Publisher) obj);
		}
		else if(obj instanceof LibraryBranch){
			//call a private member that takes a publisher
			deleteLibraryBranch((LibraryBranch) obj);
		}
		else if(obj instanceof Borrower){
			//call a private member that takes a publisher
			deleteBorrower((Borrower) obj);
		}
	}

	private void deleteAuthor(Author obj) throws Exception {
		if(obj != null){
			if(obj.getName() == null || obj.getName().length() > 45)
				throw new Exception("Author name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			AuthorDAO aDAO = new AuthorDAO(conn); 
			try{
				aDAO.delete(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Author cannot be null");

	}

	private void deleteBook(Books obj) throws Exception {
		if(obj != null){
			if(obj.getTitle() == null || obj.getTitle().length() > 45)
				throw new Exception("Title cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			BooksDAO bDAO = new BooksDAO(conn); 
			try{
				bDAO.delete(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Book cannot be null");


	}

	private void deletePublisher(Publisher obj) throws Exception {
		if(obj != null){
			if(obj.getPname() == null || obj.getPname().length() > 45)
				throw new Exception("Publisher name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			PublisherDAO pDAO = new PublisherDAO(conn); 
			try{
				pDAO.delete(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Publisher cannot be null");

	}

	private void deleteLibraryBranch(LibraryBranch obj) throws Exception {
		if(obj != null){
			if(obj.getBname() == null || obj.getBname().length() > 45)
				throw new Exception("Library Branch name cannot be blank or more than 45 characters");

			Connection conn = getConnection();
			LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn); 
			try{
				lbDAO.delete(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Library Branch cannot be null");

	}

	private void deleteBorrower(Borrower obj) throws Exception {
		if(obj != null){
			Connection conn = getConnection();
			BorrowerDAO bDAO = new BorrowerDAO(conn); 
			try{
				bDAO.delete(obj);
				conn.commit();
			} catch(SQLException e){
				conn.rollback();
			} finally{
				conn.close();
				conn = null;
			}
		}
		else
			throw new Exception("Borrower cannot be null");

	}

	public List<Author> displayAuthors() throws Exception {
		Connection conn = getConnection();
		AuthorDAO aDAO = new AuthorDAO(conn);
		List<Author> aList = null;

		try{
			aList = aDAO.read();
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}
		return aList;
	}

	public List<Books> displayBooks() throws Exception {
		Connection conn = getConnection();
		BooksDAO bDAO = new BooksDAO(conn);
		List<Books> bList = null;
		try{
			bList = bDAO.read();
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}
		return bList;


	}

	public List<Publisher> displayPublishers() throws Exception {
		Connection conn = getConnection();
		PublisherDAO pDAO = new PublisherDAO(conn);
		List<Publisher> pList = null;

		try{
			pList = pDAO.read();
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

		return pList;
	}

	public List<LibraryBranch> displayLibraryBranches() throws Exception {
		Connection conn = getConnection();
		LibraryBranchDAO lbDAO = new LibraryBranchDAO(conn);
		List<LibraryBranch> lbList = null;

		try{
			lbList = lbDAO.read();
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

		return lbList;
	}

	public List<Borrower> displayBorrowers() throws Exception {
		Connection conn = getConnection();
		BorrowerDAO borrDAO = new BorrowerDAO(conn);
		List<Borrower> borrList = null;

		try{
			borrList = borrDAO.read();
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}

		return borrList;
	}

	public Author getAuthor(int authorid) throws Exception{
		Connection conn = getConnection();
		AuthorDAO aDAO = new AuthorDAO(conn);
		Author a = new Author();

		try{
			a = aDAO.readOne(authorid);
			return a;
		} catch(SQLException e){
			throw e;
		} finally{
			conn.close();
			conn = null;
		}	
	}
	
	public Books getBook(int bookid) throws Exception{
		Connection conn = getConnection();
		BooksDAO aDAO = new BooksDAO(conn);
		Books a = new Books();

		try{
			a = aDAO.readOne(bookid);
			return a;
		} catch(SQLException e){
			throw e;
		} finally{
			conn.close();
			conn = null;
		}	
	}
	
	public Publisher getPublisher(int publisherid) throws Exception{
		Connection conn = getConnection();
		PublisherDAO aDAO = new PublisherDAO(conn);
		Publisher a = new Publisher();

		try{
			a = aDAO.readOne(publisherid);
			return a;
		} catch(SQLException e){
			throw e;
		} finally{
			conn.close();
			conn = null;
		}	
	}
	
	public Borrower getBorrower(int borrowerid) throws Exception{
		Connection conn = getConnection();
		BorrowerDAO aDAO = new BorrowerDAO(conn);
		Borrower a = new Borrower();

		try{
			a = aDAO.readOne(borrowerid);
			return a;
		} catch(SQLException e){
			throw e;
		} finally{
			conn.close();
			conn = null;
		}	
	}

	public LibraryBranch getLibraryBranch(int branchid) throws Exception{
		Connection conn = getConnection();
		LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);
		LibraryBranch a = new LibraryBranch();

		try{
			a = aDAO.readOne(branchid);
			return a;
		} catch(SQLException e){
			throw e;
		} finally{
			conn.close();
			conn = null;
		}	
	}
	
	public List<Author> searchAuthor(String value) throws Exception{
		Connection conn = getConnection();
		AuthorDAO aDAO = new AuthorDAO(conn);
		List<Author> aList = null;

		try{
			aList = aDAO.readSearch(value);
			conn.commit();
		} catch(SQLException e){
			conn.rollback();
		} finally{
			conn.close();
			conn = null;
		}
		return aList;
	}
	
	
	public List<Author> pageAuthors(int pageNo) throws Exception {
		Connection conn = getConnection();
		try {
			AuthorDAO aDAO = new AuthorDAO(conn);
			return aDAO.page(pageNo);
		} finally {
			conn.close();
			conn = null;
		}
	}

	public int countAuthors() throws Exception {
		Connection conn = getConnection();
		try {
			AuthorDAO aDAO = new AuthorDAO(conn);
			return aDAO.count();
		} finally {
			conn.close();
			conn = null;
		}
	}

	public List<Author> searchAuthorByName(String authorToSearch) throws Exception{
		Connection conn = getConnection();
		try {
			AuthorDAO aDAO = new AuthorDAO(conn);
			return aDAO.getAuthorsByName(authorToSearch);
		} finally {
			conn.close();
			conn = null;
		}
	}

	public List<Publisher> pagePublishers(int pageNo) throws Exception {
		Connection conn = getConnection();
		try {
			PublisherDAO aDAO = new PublisherDAO(conn);
			return aDAO.page(pageNo);
		} finally {
			conn.close();
			conn = null;
		}
	}

	public int countPublishers() throws Exception {
		Connection conn = getConnection();
		try {
			PublisherDAO aDAO = new PublisherDAO(conn);
			return aDAO.count();
		} finally {
			conn.close();
			conn = null;
		}
	}

	public List<Publisher> searchPublisherByName(String publisherToSearch) throws Exception{
		Connection conn = getConnection();
		try {
			PublisherDAO aDAO = new PublisherDAO(conn);
			return aDAO.getPublishersByName(publisherToSearch);
		} finally {
			conn.close();
			conn = null;
		}
	}
	
	public List<Borrower> pageBorrowers(int pageNo) throws Exception {
		Connection conn = getConnection();
		try {
			BorrowerDAO aDAO = new BorrowerDAO(conn);
			return aDAO.page(pageNo);
		} finally {
			conn.close();
			conn = null;
		}
	}

	public int countBorrowers() throws Exception {
		Connection conn = getConnection();
		try {
			BorrowerDAO aDAO = new BorrowerDAO(conn);
			return aDAO.count();
		} finally {
			conn.close();
			conn = null;
		}
	}

	public List<Borrower> searchBorrowerByName(String BorrowerToSearch) throws Exception{
		Connection conn = getConnection();
		try {
			BorrowerDAO aDAO = new BorrowerDAO(conn);
			return aDAO.getBorrowersByName(BorrowerToSearch);
		} finally {
			conn.close();
			conn = null;
		}
	}
	
	public List<LibraryBranch> pageLibraryBranchs(int pageNo) throws Exception {
		Connection conn = getConnection();
		try {
			LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);
			return aDAO.page(pageNo);
		} finally {
			conn.close();
			conn = null;
		}
	}

	public int countLibraryBranches() throws Exception {
		Connection conn = getConnection();
		try {
			LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);
			return aDAO.count();
		} finally {
			conn.close();
			conn = null;
		}
	}

	public List<LibraryBranch> searchLibraryBranchByName(String LibraryBranchToSearch) throws Exception{
		Connection conn = getConnection();
		try {
			LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);
			return aDAO.getLibraryBranchesByName(LibraryBranchToSearch);
		} finally {
			conn.close();
			conn = null;
		}
	}
	
	public List<Books> pageBooks(int pageNo) throws Exception {
		Connection conn = getConnection();
		try {
			BooksDAO aDAO = new BooksDAO(conn);
			return aDAO.page(pageNo);
		} finally {
			conn.close();
			conn = null;
		}
	}

	public int countBooks() throws Exception {
		Connection conn = getConnection();
		try {
			BooksDAO aDAO = new BooksDAO(conn);
			return aDAO.count();
		} finally {
			conn.close();
			conn = null;
		}
	}

	public List<Books> searchBooksByName(String BooksToSearch) throws Exception{
		Connection conn = getConnection();
		try {
			BooksDAO aDAO = new BooksDAO(conn);
			return aDAO.getBooksByName(BooksToSearch);
		} finally {
			conn.close();
			conn = null;
		}
	}




}
