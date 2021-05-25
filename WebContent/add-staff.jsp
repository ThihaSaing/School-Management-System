<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<form action="StaffControlController?action=loadAddStaff" method="POST">
				<h1>Add Staff</h1>
					<label>Staff Name:</label>
					<input type="text" name="staffName" />
					<label>Position:</label>
					<input type="text" name="position" />
					<label>Salary:</label>
					<input type="text" name="salary" />
					<label>Phone Number:</label>
					<input type="text" name="phno" />
					<label>Address:</label>
					<input type="text" name="address" />
					<label>Date of Birth:</label>
					<input type="text" name="dob" />
					<label>NRC:</label>
					<input type="text" name="nric" />
					<input type="submit" value="Save" class="save" />
			</form>	
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	