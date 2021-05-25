<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("admin")) { %>
				<h1 class="table">Data</h1>
				<c:if test="${fn:length(classDataList) > 0}">
					<table>
						<tr>
							<th>Subjects</th>
							<th>Teachers</th>
							<th>Action</th>
						</tr>
						<c:forEach var="classInfo" items="${classDataList}">
							<c:url var="deleteLink" value="ClassroomControlController">
								<c:param name="action" value="deleteData" />
								<c:param name="id" value="${classInfo.id}" />
								<c:param name="teacher" value="${classInfo.teacherid}" />
							</c:url>
							<tr>
								<td>${classInfo.subjectname}</td>
								<td>${classInfo.teachername}</td>
								<td><a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this?'))) return false">Delete</a>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<form action="ClassroomControlController?action=addData" method="POST">
					<h1>Assign Teacher</H1>
					<input type="hidden" name="classID" value="${classid}">
					<c:if test="${fn:length(classDataList) <= 0}">
						<b>No data</b><br>
					</c:if>
					<label>Subject:</label>
					<select name="subject">
						<option value="0">---Select---</option>
						<c:forEach var="subjects" items="${subjectList}">
							<option value="${subjects.subjectid}">${subjects.subjectname}</option>
						</c:forEach>
					</select>
					<label>Teacher:</label>
					<select name="teacher">
						<option value="0">---Select---</option>
						<c:forEach var="teachers" items="${teacherList}">
							<option value="${teachers.teacherId}">${teachers.teacherName}</option>
						</c:forEach>
					</select>
					<input type="submit" value="Add" class="add">
					<a href="ClassroomControlController?action=loadAssignTeacherToClass">Back to List</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />