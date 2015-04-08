<%@page import="com.gcit.training.library.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	String branchIdToEdit = request.getParameter("branchIdToEdit");
	LibraryBranch branch = new AdministratorService().getLibraryBranch(Integer.parseInt(branchIdToEdit));
%>	

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Edit LibraryBranch</h4>
</div>
<div class="modal-body">
	<table>
		<tr>
			<td>Library Branch name: </td>
			<td>
				<input type="text" id="branchNameToEdit" value="<%=branch.getBname()%>">
				<input type="hidden" id="branchIdToEdit" value="<%=branchIdToEdit%>">
			</td>
		</tr>
				<tr>
			<td>Library Branch address: </td>
			<td>
				<input type="text" id="branchAddressToEdit" value="<%=branch.getBaddr()%>">
				<input type="hidden" id="branchIdToEdit" value="<%=branchIdToEdit%>">
			</td>
		</tr>

	</table>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	<button type="button" class="btn btn-primary" onclick="submitLibraryBranch();">Save changes</button>
</div>
<form action="editBranch" name="editLibraryBranchFrm" method="post">
	<input type="hidden" name="branchNameToEdit" />
	<input type="hidden" name="branchAddressToEdit" />
	<input type="hidden" name="branchIdToEdit" />
</form>

<script>
	window.submitLibraryBranch = function() {
		document.editLibraryBranchFrm.branchNameToEdit.value = document.getElementById('branchNameToEdit').value;
		document.editLibraryBranchFrm.branchAddressToEdit.value = document.getElementById('branchAddressToEdit').value;
		document.editLibraryBranchFrm.branchIdToEdit.value = document.getElementById('branchIdToEdit').value;
		document.editLibraryBranchFrm.submit();
}
</script>
