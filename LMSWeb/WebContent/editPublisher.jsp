<%@page import="com.gcit.training.library.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%
	String publisherIdToEdit = request.getParameter("publisherIdToEdit");
	Publisher publisher = new AdministratorService().getPublisher(Integer.parseInt(publisherIdToEdit));
%>	

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title">Edit Publisher</h4>
</div>
<div class="modal-body">
	<table>
		<tr>
			<td>Publisher name: </td>
			<td>
				<input type="text" id="publisherNameToEdit" value="<%=publisher.getPname()%>">
				<input type="hidden" id="publisherIdToEdit" value="<%=publisherIdToEdit%>">
			</td>
		</tr>
				<tr>
			<td>Publisher address: </td>
			<td>
				<input type="text" id="publisherAddressToEdit" value="<%=publisher.getPaddr()%>">
				<input type="hidden" id="publisherIdToEdit" value="<%=publisherIdToEdit%>">
			</td>
		</tr>
				<tr>
			<td>Publisher phone: </td>
			<td>
				<input type="text" id="publisherPhoneToEdit" value="<%=publisher.getPphone()%>">
				<input type="hidden" id="publisherIdToEdit" value="<%=publisherIdToEdit%>">
			</td>
		</tr>
	</table>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	<button type="button" class="btn btn-primary" onclick="submitPublisher();">Save changes</button>
</div>
<form action="editPublisher" name="editPublisherFrm" method="post">
	<input type="hidden" name="publisherNameToEdit" />
	<input type="hidden" name="publisherAddressToEdit" />
	<input type="hidden" name="publisherPhoneToEdit" />
	<input type="hidden" name="publisherIdToEdit" />
</form>

<script>
	window.submitPublisher = function() {
		document.editPublisherFrm.publisherNameToEdit.value = document.getElementById('publisherNameToEdit').value;
		document.editPublisherFrm.publisherAddressToEdit.value = document.getElementById('publisherAddressToEdit').value;
		document.editPublisherFrm.publisherPhoneToEdit.value = document.getElementById('publisherPhoneToEdit').value;
		document.editPublisherFrm.publisherIdToEdit.value = document.getElementById('publisherIdToEdit').value;
		document.editPublisherFrm.submit();
}
</script>
