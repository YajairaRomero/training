<%@page import="com.gcit.training.library.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	List<Author> authors = new AdministratorService().displayAuthors();
%>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Add Book</h4>
</div>
<div class="modal-body">
	<div id="authorList">
	  	<table class="table">
			<tr>
				<td> Book Title:</td>
				<td><input type="text" id="title" /></td>
			</tr>
			<tr>
				<td>Author:</td>
				<td>
					<select id="author">
			 			<%for(Author a : authors) { %>
						<option value="<%=a.getAuthorid()%>"><%=a.getName()%></option>
						<% } %> 
					</select>
					</td>	
			</tr>
	</table>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	<button type="button" class="btn btn-primary" onclick="javascript:submitBook();">Save changes</button>
</div>
<form action="addBook" name="addBookFrm" method="post">
	<input type="hidden" name="title" />
	<input type="hidden" name="author" />
</form>

<script>
	window.submitBook = function() {
		document.addBookFrm.title.value = document.getElementById('title').value;
		document.addBookFrm.author.value = document.getElementById('author').value;
		document.addBookFrm.submit();
	}
</script>
