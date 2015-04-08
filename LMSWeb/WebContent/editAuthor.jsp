<%@page import="com.gcit.training.library.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	String authorIdToEdit = request.getParameter("authorIdToEdit");
	Author author = new AdministratorService().getAuthor(Integer.parseInt(authorIdToEdit));
%>	

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Edit Author</h4>
</div>
<div class="modal-body">
	<table>
		<tr>
			<td>Author name: </td>
			<td>
				<input type="text" id="authorNameToEdit" value="<%=author.getName()%>">
				<input type="hidden" id="authorIdToEdit" value="<%=authorIdToEdit%>">
			</td>
		</tr>
	</table>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	<button type="button" class="btn btn-primary" onclick="submitAuthor();">Save changes</button>
</div>
<form action="editAuthor" name="editAuthorFrm" method="post">
	<input type="hidden" name="authorNameToEdit" />
	<input type="hidden" name="authorIdToEdit" />
</form>

<script>
	window.submitAuthor = function() {
		document.editAuthorFrm.authorNameToEdit.value = document.getElementById('authorNameToEdit').value;
		document.editAuthorFrm.authorAddrToEdit.value = document.getElementById('authorIdToEdit').value;
		document.editAuthorFrm.submit();
}
</script>
