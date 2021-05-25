<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("teacher")) { %>
				<div class="profile-box">
					<h1>Profile</h1>
					<p><strong>Teacher Name: </strong>${profile.teacherName}</p>
					<p><strong>Class Name: </strong>${className}</p>
					<p><strong>Subject Name: </strong>${subjectName} </p>
					<p><strong>DOB: </strong>${profile.teacherDoB} </p>
					<p><strong>Address: </strong> ${profile.teacherAddress}</p>
					<p><strong>Phone Number: </strong>${profile.teacherPhoneNo} </p>
					<p><strong>NRIC: </strong> ${profile.teacherNRC} </p>
				</div>
			<% } else { %>
				<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
		