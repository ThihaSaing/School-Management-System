<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<form action="StaffControlController?action=update" method="POST">
				<h1>Update Staff</h1>
				<input type="hidden" name="staffID" value="${staff.staffid}">
				
				<label for="staffName">Staff Name</label>
				<input type="text" name="staffName" value="${staff.staffname}">
	
				<label for="position">Position</label>
				<input type="text" name="position" value="${staff.position}">
				
				<label for="salary">Salary</label>
				<input type="text" name="salary" value="${staff.salary}">
				
				<label for="phno">Phone Number</label>
				<input type="text" name="phno" value="${staff.phoneno}">
				
				<label for="address">Address</label>
				<input type="text" name="address" value="${staff.address}">
				
				<label for="dob">Date of Birth</label>
				<input type="text" name="dob" value="${staff.dob}">
				
				<label for="nrc">NRC</label>
				<input type="text" name="nric" value="${staff.nric}">
				
				<input type="submit" value="Update">
				<a href="staff.jsp">Back to Staff List </a>
			</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	