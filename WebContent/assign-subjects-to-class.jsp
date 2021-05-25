<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("admin")) { %>
				<%! int srl_no = 1; %>
				<h1 class="table">Data List</h1>
				<c:if test="${fn:length(classDataList) > 0}">
				<table id="liststyle">
					<tr>
						<th>Sr.No</th>
						<th>Class</th>
						<th>Subject</th>
						<th>Action</th>
					</tr>
					<c:forEach var="classInfo" items="${classDataList}">
						<c:url var="updateLink" value="ClassroomControlController">
							<c:param name="action" value="loadUpdateAssignSubjectToClass" />
							<c:param name="id" value="${classInfo.id}" />
						</c:url>
						<c:url var="deleteLink" value="ClassroomControlController">
							<c:param name="action" value="loadDeleteAssignSubjectToClass" />
							<c:param name="id" value="${classInfo.id}" />
						</c:url>
						<tr>
							<td><%= srl_no++ %></td>
							<td>${classInfo.classname}</td>
							<td>${classInfo.subjectname}</td>
							<td><a href="${updateLink}">Update</a> | <a
								href="${deleteLink}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				<c:if test="${fn:length(classDataList) <= 0}">
				 <b>No data</b>
				</c:if>
			<form action="ClassroomControlController?action=addAssignSubjectToClass" method="POST">
				<h1>Assign Subject to Class</h1>
				<c:if test="${fn:length(classList) > 0 && fn:length(subjectList) > 0}">
					<label for="classList">Class List:</label>
					<select name="classList">
						<c:forEach var="classInfo" items="${classList}">
							<option value = "${classInfo.classid}">${classInfo.classname}</option>
						</c:forEach>
					</select>
					<label for="subjectList">Subject List:</label>
					<select name="subjectList">
						<c:forEach var="subjectList" items="${subjectList}">
							<option value = "${subjectList.subjectid}">${subjectList.subjectname}</option>
						</c:forEach>
					</select>
					<input type="submit" value="Assign" class="save" />
				</c:if>
			</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />