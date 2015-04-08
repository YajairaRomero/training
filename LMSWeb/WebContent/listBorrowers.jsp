<%@page import="com.gcit.training.library.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	List<Borrower> borrowers = new AdministratorService().displayBorrowers();
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Borrowers</h4>
</div>
<div class="modal-body">
<!-- Default panel contents -->
  	<div class="panel-heading">Borrowers</div>
  	<div class="panel-body">
    	<input type="text" placeholder="Search Borrowers by Name" class="col-lg-6 col-md-12 col-sm-12 col-xs-12"/>
  	</div>
  	<table class="table">
		<tr>
			<th>Id</th>	
			<th>Name</th>
			<th>Address</th>
			<th>Phone Number</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<% for(Borrower b : borrowers) { %>
		<tr>
			<td><%=b.getCardno() %></td>
			<td><%=b.getName() %></td>
			<td><%=b.getAddress() %></td>
			<td><%=b.getPhone() %></td>
			<td>
				<a href="editBorrower.jsp?borrowerIdToEdit=<%=b.getCardno()%>" data-target="#myModal1" data-toggle="modal">
				<i class="fa fa-pencil-square-o"></i></a>
			</td>
			<td><button type="button" class="btn btn-danger btn-circle" 
				onclick="javascript:deleteBorrower('<%=b.getCardno()%>');">
				<i class="fa fa-remove"></i></button></td>
		</tr>   
		<% } %>
	</table>
</div>
<div id="myModal1" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>
<form name="deleteBorrowerFrm" action="deleteBorrower" method="post">
	<input type="hidden" name="borrowerIdToDelete" />
</form>

<script>
	function deleteBorrower(id) {
		document.deleteBorrowerFrm.borrowerIdToDelete.value = id;
		document.deleteBorrowerFrm.submit();
	}

</script>