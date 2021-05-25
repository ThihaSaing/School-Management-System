<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<div class="admin-panel">
				<section>
					<h1>Manage Accounts</h1>
					<ul>
						<li><a href="createAccount.jsp">Create New Account</a></li>
						<li><a href="AccountController?action=list">View Accounts</a></li>
						<li><a href="changePassword.jsp">Change Admin Password</a></li>
					</ul>
				</section>
				<section>
					<h1>Manage Staff</h1>
					<ul>
						<li><a href="add-staff.jsp">Add Staff</a></li>
						<li><a href="StaffControlController">View Staffs</a></li>
					</ul>
				</section>
				<section>
					<h1>Manage News</h1>
					<ul>
						<li><a href="add_news.jsp">Add News</a></li>
						<li><a href="NewsControllerServlet">View News</a></li>
					</ul>
				</section>
				<section>
					<h1>Manage Students</h1>
					<ul>
						<li><a href="StudentControllerServlet?command=LOADADD">Add Student</a></li>
						<li><a href="StudentControllerServlet">View Students</a></li>
						
					</ul>
				</section>
				<section>
					<h1>Manage Teachers</h1>
					<ul>
						<li><a href="TeacherControllerServlet?command=LOADS">Add Teachers</a></li>
						<li><a href="TeacherControllerServlet">View Teachers</a></li>
						
					</ul>
				</section>
				<section>
					<h1>Manage Classrooms</h1>
					<ul>
						<li><a href="add-class.jsp">Add Classroom</a></li>
						<li><a href="ClassroomControlController">View Classrooms</a></li>
					</ul>
				</section>
				<section>
					<h1>Manage Subjects</h1>
					<ul>
						<li><a href="add-subject.jsp">Add Subject</a></li>
						<li><a href="ClassroomControlController?action=loadSubject">View Subjects</a></li>
					</ul>
				</section>
				<section>
					<h1>Data Assignment</h1>
					<ul>
						<li><a href="ClassroomControlController?action=loadAssignSubjectToClass">Assign Subjects to Class</a></li>
						<li><a href="ClassroomControlController?action=loadAssignTeacherToClass">Assign Teachers to Class</a></li>
					</ul>
				</section>
				<section>
					<h1>Manage Schedule</h1>
					<ul>
						<li><a href="add_schedule.jsp">Add Schedule</a></li>
						<li><a href="ScheduleControllerServlet">View Schedule</a></li>
					</ul>
				</section>
			</div>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />		