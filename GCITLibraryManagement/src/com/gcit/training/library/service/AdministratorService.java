package com.gcit.training.library.service;

import java.sql.Connection;
import java.sql.SQLException;

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
	
}
