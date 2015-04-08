<%
	String result = (String) request.getAttribute("result");

%>

<%@ include file="menu.jsp" %>
<body>
	<section style="margin-left: 10px;">
		<% if(result != null)  {%>
			<h4><%=result%></h4>
		<% } %>
		<h2>Authors</h2>
		<a href="addAuthor.html" data-target="#myModal" data-toggle="modal">Add Author</a><br /> 
		<a href="listAuthors.jsp" data-target="#myModal" data-toggle="modal">List Authors</a><br/>
		
		<h2>Books</h2>
		<a href="addBook.jsp" data-target="#myModal" data-toggle="modal">Add Book</a><br /> 
		<a href="listBooks.jsp" data-target="#myModal" data-toggle="modal">List Books</a><br />

		<h2>Library Branches</h2>
		<a href="addLibraryBranch.html" data-target="#myModal" data-toggle="modal">Add Library Branch</a><br /> 
		<a href="listBranches.jsp" data-target="#myModal" data-toggle="modal">List Library Branches</a><br />
		
		<h2>Borrowers</h2>
		<a href="addBorrower.html" data-target="#myModal" data-toggle="modal">Add Borrower</a><br /> 
		<a href="listBorrowers.jsp" data-target="#myModal" data-toggle="modal">List Borrowers</a><br />
		
		<h2>Publishers</h2>
		<a href="addPublisher.html" data-target="#myModal" data-toggle="modal">Add Publisher</a><br /> 
		<a href="listPublishers.jsp" data-target="#myModal" data-toggle="modal">List Publishers</a><br />
		
	</section>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./scripts/jquery.min.js"></script>
	<script src="./scripts/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="./scripts/ie10-viewport-bug-workaround.js"></script>

<script type="text/javascript">

	$('body').on('hidden.bs.modal', '.modal', function() {
		$(this).removeData('bs.modal');
	});
	
</script>


	<!-- Event Modal -->
	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
</body>