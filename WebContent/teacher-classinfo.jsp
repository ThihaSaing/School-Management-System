<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("teacher")) { %>
			<H1 class="table">Class Info</h1>
			<table>
				<tr>	
					<th>Class</th>
					<th>Students List</th>
				</tr>
				<c:forEach var="teacherClassDataList" items="${teacherClassDataList}">
					<c:url var="studentListLink" value="TeacherClassInfoController">
						<c:param name="action" value="loadStudentList" />
						<c:param name="classid" value="${teacherClassDataList.classid}" />
					</c:url>
					<tr>
						<td>${teacherClassDataList.classname}</td>
						<td><a href="${studentListLink}">List</a></td>
					</tr>
				</c:forEach>
			</table>
			<p style="margin-left: 30px"><strong>Subject: </strong>${teacherClassDataList.get(0).getSubjectname()}</p>
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
