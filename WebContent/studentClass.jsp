<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("student")) { %>
				<div class="profile-box">
					<h1>Class Info</h1>
					<p><strong>Class Room: </strong> ${student.getClassName()}</p>
					<p><strong>Teachers: </strong></p>
					<ol>
						<c:forEach var="teacher" items="${teachers}">
						<li>
							${teacher.getTeacherName()}
							(${teacher.getSubjectName()})
						</li>
						</c:forEach>
					</ol>
					<p><strong>Students: </strong></p>
					<ol>
						<c:forEach var="student" items="${students}">
						<li>${student.getName()}</li>
						</c:forEach>
					</ol>
				</div>
				<h1 class="table">Class Schedule</h1>
				<c:if test="${fn:length(schedule) > 0}">
					<table style="margin-bottom: 30px">
						<tr>
							<th>Subject</th>
							<th>Time From</th>
							<th>Time To</th>
							<th>Day</th>
							<th>Class</th>
						</tr>
						<c:forEach var="scheduleInfo" items="${schedule}">
							<tr>
								<td>${scheduleInfo.subjectName}</td>
								<td>${scheduleInfo.timeFrom}</td>
								<td>${scheduleInfo.timeTo}</td>
								<td>${scheduleInfo.day}</td>
								<td>${scheduleInfo.className}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${fn:length(schedule) <= 0}">
					<b>No data</b>
				</c:if>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	


		
