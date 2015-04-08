<%
	String result = (String) request.getAttribute("result");

%>

<%@ include file="menu.jsp" %>

<body>
	<section style="margin-left: 10px">

		<h2>Librarian</h2>
			<a href="updateLibrary.html" data-target="#myModal" data-toggle="modal">Update the details of the library</a><br /> 
			<a href="updateBookCopies.jsp" data-target="#myModal" data-toggle="modal">Add copies of book to branch</a><br/>

	</section>
</body>