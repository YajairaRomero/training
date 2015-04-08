<%@page import="com.gcit.training.library.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	List<Publisher> Publishers = new AdministratorService().displayPublishers();
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Publishers</h4>
</div>
<div class="modal-body">
<!-- Default panel contents -->
  	<div class="panel-heading">Publishers</div>
  	<div class="panel-body">
    	<input type="text" placeholder="Search publishers by Name" id="search" class="col-lg-6 col-md-12 col-sm-12 col-xs-12"/>
  	</div>
  	<table class="table">
		<tr>
			<th>Id</th>	
			<th>Name</th>
			<th>Address</th>
			<th>Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<% for(Publisher p : Publishers) { %>
		<tr>
			<td><%=p.getPid()%></td>
			<td><%=p.getPname() %></td>
			<td><%=p.getPaddr() %></td>
			<td><%=p.getPphone() %></td>
			<td>
				<a href="editPublisher.jsp?publisherIdToEdit=<%=p.getPid()%>" data-target="#myModal1" data-toggle="modal">
				<i class="fa fa-pencil-square-o"></i></a>
			</td>
			<td><button type="button" class="btn btn-danger btn-circle" 
				onclick="javascript:deleteBranch('<%=p.getPid()%>');">
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

<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<form name="deletePublisherFrm" action="deletePublisher" method="post">
	<input type="hidden" name="publisherIdToDelete" />
</form>

<script>
	function deletePublisher(id) {
		document.deletePublisherFrm.publisherIdToDelete.value = id;
		document.deletePublisherFrm.submit();
	}
	
	$("#search").keyup(function(){
		_this = this;
		$.each($(".table").find("tr"), function(){
			if($(this).find("td").eq(1).text().toLowerCase().indexOf($(_this).val().toLowerCase()) == -1)
				$(this).hide();
			else
				$(this).show();
		});
	});

</script>

