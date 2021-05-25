<jsp:include page="include/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<h1 class="table">Teacher List</h1>
			<c:if test="${fn:length(teachers) > 0}">
			<table>
				<tr>
					<th>TeacherID</th>
					<th>Name</th>
					<th>Subject</th>
					<th>DoB</th>
					<th>Address</th>
					<th>Phone</th>
					<th>NRC</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempTeacher" items="${teachers}">

					<!-- set up a link for each teacher -->
					<c:url var="tempLink" value="TeacherControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="teacherId" value="${tempTeacher.teacherId}" />
					</c:url>

					<!--  set up a link to delete a teacher -->
					<c:url var="deleteLink" value="TeacherControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="teacherId" value="${tempTeacher.teacherId}" />
					</c:url>
					<tr>
						<td>${tempTeacher.teacherId}</td>
						<td>${tempTeacher.teacherName}</td>
						<c:forEach var="subjectlist" items="${subjectList}">
								<c:if test="${subjectlist.subjectid == tempTeacher.subjectId}">
									<td>${subjectlist.subjectname}</td>
								</c:if>
						</c:forEach>
						<td>${tempTeacher.teacherDoB}</td>
						<td>${tempTeacher.teacherAddress}</td>
						<td>${tempTeacher.teacherPhoneNo}</td>
						<td>${tempTeacher.teacherNRC}</td>
						<td><a href="${tempLink}">Update</a> | <a
							href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this teacher?'))) return false">
								Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
			<c:if test="${fn:length(teachers) <= 0}">
				<b>No data</b>
			</c:if>
			<a href="TeacherControllerServlet?command=LOADS" class="button">Add Teacher</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
