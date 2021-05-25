<jsp:include page="include/header.jsp" />
<% 
	String role = (String) session.getAttribute("role"); 
	String email = (String) session.getAttribute("email");
%>
		<main>
			<% if (role.equals("student")) { %>
				<div class="profile-box">
					<h1>Profile</h1>
					<p><strong>Name: </strong>${student.getName()}</p>
					<p><strong>Email: </strong>${email}</p>
					<p><strong>Class Room: </strong>${student.getClassName()}</p>
					<p><strong>Roll No: </strong>${student.getRollno()}</p>
					<p><strong>Date of Birth: </strong>${student.getDob()}</p>
					<p><strong>Address: </strong>${student.getAddress()}</p>
					<p><strong>Phone: </strong>${student.getPhone()}</p>
					<p><strong>NRIC: </strong>${student.getNric()}</p>
				</div>
				<div class="profile-box">
					<h1>Actions</h1>
					<ul>
						<li><a href="editAccount.jsp">Change Email/Password</a>
						<li><a href="StudentController?action=results">View Results</a>
					</ul>
				</div>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />		