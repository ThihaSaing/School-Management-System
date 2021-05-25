<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("teacher")) { %>
				<h1 class="table">Student List</h1>
				<c:if test="${fn:length(studentList) > 0}">
				<table>
					<tr>
						<th>Roll No</th>
						<th>Student Name</th>
						<th>DOB</th>
						<th>Phone No</th>
						<th>NRIC</th>
						<th>Address</th>
						<th>Action</th>
					</tr>
					<c:forEach var="studentList" items="${studentList}">
						<c:url var="markLink" value="TeacherClassInfoController">
							<c:param name="action" value="loadAddMarks" />
							<c:param name="classid" value="${studentList.classid}" />
							<c:param name="studentid" value="${studentList.studentid}" />
						</c:url>
						<tr>
							<td>${studentList.rollno}</td>
							<td>${studentList.studentname}</td>
							<td>${studentList.dob}</td>
							<td>${studentList.phoneno}</td>
							<td>${studentList.nric}</td>
							<td>${studentList.address}</td>
							<td><a href = "${markLink}">Marks</a></td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				<c:if test="${fn:length(studentList) <= 0}">
					<p><b>No Data</b>
				</c:if>
				<a href="TeacherClassInfoController?action=loadClassInfo" class="button">Back to List</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	

			