<%@page import="com.gcit.training.library.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	String borrowerIdToEdit = request.getParameter("borrowerIdToEdit");
	Borrower borrower = new AdministratorService().getBorrower(Integer.parseInt(borrowerIdToEdit));
%>	

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Edit Borrower</h4>
</div>
<div class="modal-body">
	<table>
		<tr>
			<td>Borrower name: </td>
			<td>
				<input type="text" id="borrowerNameToEdit" value="<%=borrower.getName()%>">
				<input type="hidden" id="borrowerIdToEdit" value="<%=borrowerIdToEdit%>">
			</td>
		</tr>
				<tr>
			<td>Borrower address: </td>
			<td>
				<input type="text" id="borrowerAddressToEdit" value="<%=borrower.getAddress()%>">
				<input type="hidden" id="borrowerIdToEdit" value="<%=borrowerIdToEdit%>">
			</td>
		</tr>
				<tr>
			<td>Borrower phone: </td>
			<td>
				<input type="text" id="borrowerPhoneToEdit" value="<%=borrower.getPhone()%>">
				<input type="hidden" id="borrowerIdToEdit" value="<%=borrowerIdToEdit%>">
			</td>
		</tr>
	</table>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	<button type="button" class="btn btn-primary" onclick="submitBorrower();">Save changes</button>
</div>
<form action="editBorrower" name="editBorrowerFrm" method="post">
	<input type="hidden" name="borrowerNameToEdit" />
	<input type="hidden" name="borrowerAddressToEdit" />
	<input type="hidden" name="borrowerPhoneToEdit" />
	<input type="hidden" name="borrowerIdToEdit" />
</form>

<script>
	window.submitBorrower = function() {
		document.editBorrowerFrm.borrowerNameToEdit.value = document.getElementById('borrowerNameToEdit').value;
		document.editBorrowerFrm.borrowerAddressToEdit.value = document.getElementById('borrowerAddressToEdit').value;
		document.editBorrowerFrm.borrowerPhoneToEdit.value = document.getElementById('borrowerPhoneToEdit').value;
		document.editBorrowerFrm.borrowerIdToEdit.value = document.getElementById('borrowerIdToEdit').value;
		document.editBorrowerFrm.submit();
}
</script>
