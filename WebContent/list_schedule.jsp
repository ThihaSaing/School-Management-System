<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("admin")) { %>
				<h1 class="table">Schedule List</h1>
				<c:if test="${fn:length(schedule) > 0}">
				<table>
					<tr>
						<th>Subject</th>
						<th>Time From</th>
						<th>Time To</th>
						<th>Day</th>
						<th>Class</th>
						<th>Action</th>
					</tr>
					<c:forEach var="scheduleInfo" items="${schedule}">
						<c:url var="updateLink" value="ScheduleControllerServlet">
							<c:param name="action" value="loadUpdate" />
							<c:param name="scheduleid" value="${scheduleInfo.scheduleId}" />
						</c:url>
						<c:url var="deleteLink" value="ScheduleControllerServlet">
							<c:param name="action" value="loadDelete" />
							<c:param name="scheduleid" value="${scheduleInfo.scheduleId}" />
						</c:url>
						<tr>
							<td>${scheduleInfo.subjectName}</td>
							<td>${scheduleInfo.timeFrom}</td>
							<td>${scheduleInfo.timeTo}</td>
							<td>${scheduleInfo.day}</td>
							<td>${scheduleInfo.className}</td>
							<td>
								<a href="${updateLink}">Update</a> |
								<a href="${deleteLink}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				<c:if test="${fn:length(schedule) <= 0}">
					<b>No data</b>
				</c:if>
				<a href="add_schedule.jsp" class="button">Add Schedule</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	

			