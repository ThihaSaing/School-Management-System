<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ScheduleControllerServlet?action=add" method="post">
					<h1>Add Schedule</h1>
					<label for="subject">Subject</label>
					<input type="text" name="subject">
					<label for="timefrom">Time From</label>
					<input type="text" name="timefrom">
					<label for="timeto">Time To</label>
					<input type="text" name="timeto">
					<label for="day">Day</label>
					<input type="text" name="day">
					<label for="class">Class</label>
					<input type="text" name="class">
					<input type="submit" value="Add">
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />
		