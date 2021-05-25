<jsp:include page="include/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ClassroomControlController?action=updateAssignSubjectToClass" method="POST">
					<h1>Update Data</h1>
					<input type="hidden" name="id" value="${subjectClassDataList.id}">	
					<label for="classList">Class List:</label>
					<select name="classList">
						<c:forEach var="classInfo" items="${classList}">
							<c:if test = "${subjectClassClassid == classInfo.classid}">
								<option value = "${classInfo.classid}" selected="selected">${classInfo.classname}</option>
							</c:if>
							<c:if test = "${subjectClassClassid != classInfo.classid}">
								<option value = "${classInfo.classid}">${classInfo.classname}</option>
							</c:if>
						</c:forEach>
					</select>
					<label for="subjectList">Subject List:</label>
					<select name="subjectList">
						<c:forEach var="subjectList" items="${subjectList}">
							<c:if test = "${subjectClassSubjectid == subjectList.subjectid}">
								<option value = "${subjectList.subjectid}" selected="selected">${subjectList.subjectname}</option>
							</c:if>
							<c:if test = "${subjectClassSubjectid != subjectList.subjectid}">
								<option value = "${subjectList.subjectid}">${subjectList.subjectname}</option>
							</c:if>
						</c:forEach>
					</select>
					<input type="submit" value="Update" class="update" />
					<a href="ClassroomControlController?action=loadAssignSubjectToClass">Back to List</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />


	