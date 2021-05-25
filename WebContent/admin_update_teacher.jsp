<jsp:include page="include/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<form action="TeacherControllerServlet" method="post">
				<h1>Update Teacher</h1>
				<input type="hidden" name="command" value="UPDATE" /> 
				<input type="hidden" name="teacherId" value="${the_teacher.teacherId}" />
				<label>Subject:</label>
				<select name="subject">
					<c:forEach var="subjects" items="${subjectList}">
						<c:if test="${subjectidFromTeacher == subjects.subjectid}">
							<option value="${subjects.subjectid}" selected="selected">${subjects.subjectname}</option>
						</c:if>
						<c:if test="${subjectidFromTeacher != subjects.subjectid}">
							<option value="${subjects.subjectid}">${subjects.subjectname}</option>
						</c:if>
					</c:forEach>
				</select>
				<label>Name:</label>
				<input type="text" name="teacherName" value="${the_teacher.teacherName}" />
				<label>DoB(yyyy-mm-dd):</label>
				<input type="text" name="teacherDoB" value="${the_teacher.teacherDoB}" />
				<label>Address:</label>
				<input type="text" name="teacherAddress" value="${the_teacher.teacherAddress}" />
				<label>Phone No.:</label>
				<input type="text" name="teacherPh" value="${the_teacher.teacherPhoneNo}" />
				<label>NRIC:</label>
				<input type="text" name="teacherNRIC" value="${the_teacher.teacherNRC}" />
				<input type="submit" value="Update" class="save" />
			</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	