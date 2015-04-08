<%@page import="com.gcit.training.library.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	List<LibraryBranch> LibraryBranches = new AdministratorService().displayLibraryBranches();
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Library Branches</h4>
</div>
<div class="modal-body">
<!-- Default panel contents -->
  	<div class="panel-heading">Library Branches</div>
  	<div class="panel-body">
    	<input type="text" placeholder="Search branches by Name" class="col-lg-6 col-md-12 col-sm-12 col-xs-12"/>
  	</div>
  	<table class="table">
		<tr>
			<th>Id</th>	
			<th>Name</th>
			<th>Address</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<% for(LibraryBranch lb : LibraryBranches) { %>
		<tr>
			<td><%=lb.getBranchid()%></td>
			<td><%=lb.getBname() %></td>
			<td><%=lb.getBaddr() %></td>
			<td>
				<a href="editBranch.jsp?branchIdToEdit=<%=lb.getBranchid()%>" data-target="#myModal1" data-toggle="modal">
				<i class="fa fa-pencil-square-o"></i></a>
			</td>
			<td><button type="button" class="btn btn-danger btn-circle" 
				onclick="javascript:deleteBranch('<%=lb.getBranchid()%>');">
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
<form name="deleteBranchFrm" action="deleteBranch" method="post">
	<input type="hidden" name="branchIdToDelete" />
</form>

<script>
	function deleteBranch(id) {
		document.deleteBranchFrm.branchIdToDelete.value = id;
		document.deleteBranchFrm.submit();
	}

</script>
