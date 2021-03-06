<%@page import="com.gcit.training.library.Books"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	List<Books> books = new AdministratorService().displayBooks();
%>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">List of Books</h4>
</div>
<div class="modal-body">
	<form action="addBook" name="addBookFrm" method="post">
		Book: 
		<select name="book">
			
			 <%for(Books b : books) { %>
				<option value="<%=b.getBookid()%>"><%=b.getTitle()%></option>
			<% } %> 
		</select>
		
	</form>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	<button type="button" class="btn btn-primary" onclick="javascript:submitAuthor();">Save changes</button>
</div>

<script>
	function submitAuthor() {
		document.addBookFrm.submit();
	}

</script>
