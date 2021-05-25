<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<main>
			<% if (role.equals("admin")) { %>

			<form action="StudentControllerServlet" method="post">
				<h1>Update Student</h1>
				<input type="hidden" name="command" value="UPDATE" />
				<input type="hidden" name="studentid" value="${THE_STUDENT.studentid}" />
				<label>Class:</label>
				<select name="classid">
				<c:forEach var="classroom" items="${classList}">
						<c:if test = "${studentClassid == classroom.classid}">
							<option value="${classroom.classid}" selected="selected">${classroom.classname}</option>
						</c:if>
						<c:if test = "${studentClassid != classroom.classid}">
							<option value="${classroom.classid}">${classroom.classname}</option>
						</c:if>
					
				</c:forEach>
				</select>
				<label>RollNo:</label>
				<input type="text" name="rollno" value="${THE_STUDENT.rollno}" />
				<label>StudentName:</label>
				<input type="text" name="studentname" value="${THE_STUDENT.studentname}" />
				<label>DOB:</label>
				<input type="text" name="dob" value="${THE_STUDENT.dob}" />
				<label>Address:</label>
				<input type="text" name="address" value="${THE_STUDENT.address}" />
				<label>PhoneNo:</label>
				<input type="text" name="phoneno" value="${THE_STUDENT.phoneno}" />
				<label>NRIC:</label>
				<input type="text" name="nric" value="${THE_STUDENT.nric}" />
				<input type="submit" value="Save" class="save" />
				<a href="StudentControllerServlet">Back to List</a>
			</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
