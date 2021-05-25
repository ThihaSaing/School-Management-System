<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<main>
			<% if (role.equals("admin")) { %>
		<form action="StudentControllerServlet" method="post">
			<h1>Add New Student</h1>
			<input type="hidden" name="command" value="ADD" />
			<label>Class:</label>
			<select name="classid">
				<c:forEach var="CLASS" items="${CLASS_LIST}">
					<option value="${CLASS.classid}">${CLASS.classname}</option>
				</c:forEach>
			</select>
			<label>RollNo:</label>
			<input type="text" name="rollno" />
			<label>StudentName:</label>
			<input type="text" name="studentname" value="${THE_STUDENT.studentname}" />
			<label>DOB:</label>
			<input type="text" name="dob"/>
			<label>Address:</label>
			<input type="text" name="address" />
			<label>PhoneNo:</label>
			<input type="text" name="phoneno" />
			<label>NRIC:</label>
			<input type="text" name="nric"/>
			<input type="submit" value="Add"/>
		</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	