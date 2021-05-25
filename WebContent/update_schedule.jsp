<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ScheduleControllerServlet?action=update" method="post">
					<h1>Update Schedule</h1>
					<input type="hidden" name="scheduleid" value="${schedule.scheduleId}">
					<label for="subject">Subject</label>
					<input type="text" name="subject" value="${schedule.subjectName}">
					<label for="timefrom">Time From</label>
					<input type="text" name="timefrom" value="${schedule.timeFrom}">
					<label for="timeto">Time To</label>
					<input type="text" name="timeto" value="${schedule.timeTo}">
					<label for="day">Day</label>
					<input type="text" name="day" value="${schedule.day}">
					<label for="class">Class</label>
					<input type="text" name="class" value="${schedule.className}">
					<input type="submit" value="Update">
					<a href="ScheduleControllerServlet">Back to schedule list</a>				
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
			
			


