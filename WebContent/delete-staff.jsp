<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
		<form action="StaffControlController?action=delete" method="POST">
			<h1>Delete Staff</h1>
			<input type="hidden" name="staffID" value="${staff.staffid}">
			<p>Are you sure you want to delete this staff?</p>
			<p>
				<strong>Staff Name: </strong> <span>${staff.staffname}</span>
			</p>
			<p>
				<strong>Position: </strong> <span>${staff.position}</span>
			</p>
			<p>
				<strong>Salary: </strong><span>${staff.salary}</span>
			</p>
			<p>
				<strong>Phone Number: </strong><span>${staff.phoneno}</span>
			</p>
			<p>
				<strong>Address: </strong><span>${staff.address}</span>
			</p>
			<p>
				<strong>Date of Birth: </strong><span>${staff.dob}</span>
			</p>
			<p>
				<strong>NRC: </strong><span>${staff.nric}</span>
			</p>

			<input type="submit" value="Delete"> 
			<a href="staff.jsp">Back to Staff List </a>
		</form>
			
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	