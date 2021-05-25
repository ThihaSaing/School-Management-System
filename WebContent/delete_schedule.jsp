<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ScheduleControllerServlet?action=delete" method="post">
					<h1>Delete Schedule</h1>
					<input type="hidden" name="scheduleid" value="${schedule.scheduleId}">
					<p>Are you sure to delete this schedule?</p>
					<p><strong>Subject: </strong><span>${schedule.subjectName}</span></p>
					<p><strong>Time From: </strong><span>${schedule.timeFrom}</span></p>
					<p><strong>Time To: </strong><span>${schedule.timeTo}</span></p>
					<p><strong>Day: </strong><span>${schedule.day}</span></p>
					<p><strong>Class: </strong><span>${schedule.className}</span></p>
					<input type="submit" value="Delete">
					<a href="ScheduleControllerServlet">Back to Schedule list</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
			
			
		