<jsp:include page="include/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<h1 class="table">Student List</h1>
			<c:if test="${fn:length(STUDENT_LIST) > 0}">
			<table>
				<tr>
					<th>StudentID</th>
					<th>RollNo</th>
					<th>Name</th>
					<th>DOB</th>
					<th>NRIC</th>
					<th>PhoneNo</th>
					<th>Address</th>
					<th>Class Name</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="studentId" value="${tempStudent.studentid}" />
					</c:url>

					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="studentId" value="${tempStudent.studentid}" />
					</c:url>
				
				<tr>
				<td>${tempStudent.studentid}</td>
				<td>${tempStudent.rollno}</td>
				<td>${tempStudent.studentname}</td>
				<td>${tempStudent.dob}</td>
				<td>${tempStudent.nric}</td>
				<td>${tempStudent.phoneno}</td>
				<td>${tempStudent.address}</td>
				<td>${tempStudent.classname}</td>
					<td>	
						<a href="${tempLink}">Update</a> | 
						<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>	
					</td>
				</tr>
				</c:forEach>
			</table>
			</c:if>
			<c:if test="${fn:length(STUDENT_LIST) <= 0}">
				<b>No data</b>
			</c:if>
			<a href="StudentControllerServlet?command=LOADADD" class="button">New Student</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
