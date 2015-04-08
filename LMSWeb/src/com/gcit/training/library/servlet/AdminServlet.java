package com.gcit.training.library.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.library.Author;
import com.gcit.training.library.Books;
import com.gcit.training.library.Borrower;
import com.gcit.training.library.LibraryBranch;
import com.gcit.training.library.Publisher;
import com.gcit.training.library.service.AdministratorService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/addBook", "/deleteAuthor", "/editAuthor",
		"/searchAuthors", "/pageAuthor", "/addBorrower", "/deleteBorrower", "/editBorrower",
		"/addBranch", "/deleteBranch", "/editBranch","/addPublisher", "/deletePublisher", "/editPublisher"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String function = request.getRequestURI().substring(
				request.getContextPath().length());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");

		try {

			switch (function) {
			case "/addAuthor": {
				addAuthor(request, response, rd);
				break;
			}

			case "/editAuthor": {
				editAuthor(request, response, rd);
				break;
			}

			case "/deleteAuthor": {
				deleteAuthor(request, response, rd);
				break;
			}

			case "/searchAuthors": {
				searchAuthors(request, response);
				break;
			}
			case "/pageAuthor": {
				pageAuthor(request, response);
				break;
			}

			case "/addBook": {
				addBook(request, response, rd);
				break;
			}
			case "/addBorrower": {
				addBorrower(request, response, rd);
				break;
			}
			case "/deleteBorrower": {
				deleteBorrower(request, response, rd);
				break;
			}
			case "/editBorrower": {
				editBorrower(request, response, rd);
				break;
			}
			case "/addBranch": {
				addBranch(request, response, rd);
				break;
			}
			case "/deleteBranch": {
				deleteBranch(request, response, rd);
				break;
			}
			case "/editBranch": {
				editBranch(request, response, rd);
				break;
			}
			case "/addPublisher": {
				addPublisher(request, response, rd);
				break;
			}
			case "/deletePublisher": {
				deletePublisher(request, response, rd);
				break;
			}
			case "/editPublisher": {
				editPublisher(request, response, rd);
				break;
			}
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Operation Failed: " + e.getMessage());
			rd.forward(request, response);
		}
	}

	private void editBorrower(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException{
		String borrowerName = request.getParameter("borrowerNameToEdit");
		String borrowerAddr = request.getParameter("borrowerAddressToEdit");
		String borrowerPhone = request.getParameter("borrowerPhoneToEdit");
		String borrowerId = request.getParameter("borrowerIdToEdit");
		Borrower b = new Borrower();
		b.setName(borrowerName);
		b.setAddress(borrowerAddr);
		b.setPhone(borrowerPhone);
		b.setCardno(Integer.parseInt(borrowerId));

		new AdministratorService().UpdateInventory(b);
		request.setAttribute("result", "Borrower Updated Succesfully!");
		rd.forward(request, response);
		
	}

	private void editPublisher(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException{
		String publisherName = request.getParameter("publisherNameToEdit");
		String publisherAddr = request.getParameter("publisherAddressToEdit");
		String publisherPhone = request.getParameter("publisherPhoneToEdit");
		String publisherId = request.getParameter("publisherIdToEdit");
		Publisher p = new Publisher();
		p.setPname(publisherName);
		p.setPaddr(publisherAddr);
		p.setPphone(publisherPhone);
		p.setPid(Integer.parseInt(publisherId));		

		new AdministratorService().UpdateInventory(p);
		request.setAttribute("result", "Publisher Updated Succesfully!");
		rd.forward(request, response);
		
	}

	private void editBranch(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException{
		String branchName = request.getParameter("branchNameToEdit");
		String branchAddr = request.getParameter("branchAddressToEdit");
		String branchId = request.getParameter("branchIdToEdit");
		LibraryBranch lb = new LibraryBranch();
		lb.setBname(branchName);
		lb.setBaddr(branchAddr);
		lb.setBranchid(Integer.parseInt(branchId));

		new AdministratorService().UpdateInventory(lb);
		request.setAttribute("result", "Library Branch Updated Succesfully!");
		rd.forward(request, response);
		
	}

	private void deletePublisher(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException{
		String publisherId = request.getParameter("publisherIdToDelete");		
		Publisher p = new Publisher();
		p.setPid(Integer.parseInt(publisherId));
		new AdministratorService().deleteInventory(p);
		request.setAttribute("result", "Publisher Deleted Succesfully!");
		rd.forward(request, response);
	
		
	}

	private void addPublisher(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException{
		String publisherName = request.getParameter("publisherNameToAdd");
		String publisherAddr = request.getParameter("publisherAddrToAdd");		
		String publisherPhone = request.getParameter("publisherPhoneToAdd");		
		Publisher p = new Publisher();
		p.setPname(publisherName);
		p.setPaddr(publisherAddr);
		p.setPphone(publisherPhone);
		new AdministratorService().createInventory(p);
		request.setAttribute("result", "Publisher Added Succesfully!");
		rd.forward(request, response);
		
	}

	private void deleteBranch(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException{
		String branchId = request.getParameter("branchIdToDelete");		
		LibraryBranch lb = new LibraryBranch();
		lb.setBranchid(Integer.parseInt(branchId));
		new AdministratorService().deleteInventory(lb);
		request.setAttribute("result", "Branch Deleted Succesfully!");
		rd.forward(request, response);
		
	}

	private void addBranch(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException{
		String branchName = request.getParameter("branchNameToAdd");
		String branchAddr = request.getParameter("branchAddrToAdd");		
		LibraryBranch lb = new LibraryBranch();
		lb.setBname(branchName);
		lb.setBaddr(branchAddr);
		new AdministratorService().createInventory(lb);
		request.setAttribute("result", "Library Branch Added Succesfully!");
		rd.forward(request, response);
		
	}

	private void deleteBorrower(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException {
		String borrId = request.getParameter("borrowerIdToDelete");		
		Borrower b = new Borrower();
		b.setCardno(Integer.parseInt(borrId));
		new AdministratorService().deleteInventory(b);
		request.setAttribute("result", "Borrower Deleted Succesfully!");
		rd.forward(request, response);
		
	}

	private void addBorrower(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd) throws Exception, ServletException, IOException {
		String borrName = request.getParameter("borrowerNameToAdd");
		String borrAddr = request.getParameter("borrowerAddrToAdd");
		String borrPhone = request.getParameter("borrowerPhoneToAdd");		
		Borrower b = new Borrower();
		b.setName(borrName);
		b.setAddress(borrAddr);
		b.setPhone(borrPhone);
		new AdministratorService().createInventory(b);
		request.setAttribute("result", "Borrower Added Succesfully!");
		rd.forward(request, response);
		
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd)
			throws Exception, ServletException, IOException {
		String title = request.getParameter("title");
		String aId = request.getParameter("author");

		Author a = new Author();
		a.setAuthorid(Integer.parseInt(aId));

		Books b = new Books();
		b.setTitle(title);

		b.setAuthors(new ArrayList<Author>());
		b.getAuthors().add(a);

		new AdministratorService().createInventory(b);
		request.setAttribute("result", "Book Added Succesfully!");
		rd.forward(request, response);
	}

	private void searchAuthors(HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		String authorToSearch = request.getParameter("authorToSearch");
		List<Author> authors = new AdministratorService()
				.searchAuthorByName(authorToSearch);

		response.getWriter().write(getTableResponseAuthors(authors, response));
	}

	private void pageAuthor(HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		String pageNo = request.getParameter("pageNo");
		List<Author> authors = new AdministratorService()
				.pageAuthors(Integer.parseInt(pageNo));

		response.getWriter().write(getTableResponseAuthors(authors, response));
	}

	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd)
			throws Exception, ServletException, IOException {
		String authorId = request.getParameter("authorIdToDelete");
		Author a = new Author();
		a.setAuthorid(Integer.parseInt(authorId));
		new AdministratorService().deleteInventory(a);
		request.setAttribute("result", "Author Deleted Succesfully!");
		rd.forward(request, response);
	}

	private void editAuthor(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd)
			throws Exception, ServletException, IOException {
		String authorName = request.getParameter("authorNameToEdit");
		String authorId = request.getParameter("authorIdToEdit");
		Author a = new Author();
		a.setName(authorName);
		a.setAuthorid(Integer.parseInt(authorId));

		new AdministratorService().UpdateInventory(a);
		request.setAttribute("result", "Author Updated Succesfully!");
		rd.forward(request, response);
	}

	private void addAuthor(HttpServletRequest request,
			HttpServletResponse response, RequestDispatcher rd)
			throws Exception, ServletException, IOException {
		String authorName = request.getParameter("authorName");
		Author a = new Author();
		a.setName(authorName);
		new AdministratorService().createInventory(a);
		request.setAttribute("result", "Author Added Succesfully!");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getTableResponseAuthors(List<Author> authors, HttpServletResponse response ) {
		if(authors == null || authors.size() == 0) {
			return "<p>No Authors found!</p><input type='hidden' id='noOfAuthorsInPage' value='"+authors.size()+"'";
		} else {
			StringBuilder responseString = new StringBuilder(
					"<table class=\"table\">");
			responseString
					.append("<tr><th>Id</th><th>Name</th><th>Edit</th><th>Delete</th></tr>");
	
			for (Author a : authors) {
				responseString.append("<tr>");
				responseString.append("<td>").append(a.getAuthorid())
						.append("</td>");
				responseString.append("<td>").append(a.getName())
						.append("</td>");
				responseString
						.append("<td>")
						.append("<a href=\"editAuthor.jsp?authorIdToEdit="+a.getAuthorid()+" data-target=\"#myModal1\" data-toggle=\"modal\">");
				responseString.append(
						"<i class=\"fa fa-pencil-square-o\"></i>").append(
						"</a></td><td>");
				responseString
						.append("<button type=\"button\" class=\"btn btn-danger btn-circle\"onclick=\"javascript:deleteAuthor('"+a.getAuthorid()+"');\">");
				responseString
						.append("<i class=\"fa fa-remove\"></i></button></td></tr>");
			}
			responseString.append("</table>");
			responseString.append("<input type='hidden' id='noOfAuthorsInPage' value='"+authors.size()+"'");
			
			return responseString.toString();
		}
	}

}
