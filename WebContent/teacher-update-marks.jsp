<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("teacher")) { %>
			<c:if test="${fn:length(studentMarks) > 0}">
				<form action="TeacherClassInfoController?action=updateMark" method="POST">
					<h1>Marks of ${studentName}</h1>
					<input type="hidden" name="teacherSubjectID" value="${teacherSubjectId}">	
					<input type="hidden" name="teacherMarkID" value="${teacherMarkId}">	
					<input type="hidden" name="classid" value="${classid}">	
					<c:set var = "flag" value = "${false}"/>
					<c:forEach var="studentMarks" items="${studentMarks}">
						<input type="hidden" name="studentid" value="${studentMarks.studentid}">
						<label>${studentMarks.subjectname}</label>
						<c:if test = "${teacherSubjectId == studentMarks.subjectid}">
							<c:set var = "flag" value = "${true}"/>
							<input type="text" name="enableMarks" id="enableMarks" value = "${studentMarks.marks}"/>
						</c:if>
						<c:if test = "${teacherSubjectId != studentMarks.subjectid}">
							<input type="text" name="enableMarks" id="enableMarks" value = "${studentMarks.marks}" disabled="disabled"/>
						</c:if>
					</c:forEach>
					<c:if test = "${flag == false}">
						<label>${subjectname}</label>
						<input type="text" name="enableMarks" id="enableMarks" value = "">
					</c:if>
					<input type="submit" value="Update"/>
					<a href="TeacherClassInfoController?action=loadStudentList&classid=${classid}">Back to List</a>
				</form>
			</c:if>
			<c:if test="${fn:length(studentMarks) <= 0}">
				<form action="TeacherClassInfoController?action=addMark" method="POST">
					<input type="hidden" name="subjectId" value="${teacherSubjectId}">	
					<input type="hidden" name="classid" value="${classid}">	
					<input type="hidden" name="studentid" value="${studentid}">	
					<h1>Add Marks </h1>
					<label>${subjectname}</label>
					<input type="text" name="enableMarks" id="enableMarks" value = "">
					<input type="submit" value="Add" class="add" />
					<a href="TeacherClassInfoController?action=loadStudentList&classid=${classid}">Back to List</a>
				</form>
			</c:if>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	

			