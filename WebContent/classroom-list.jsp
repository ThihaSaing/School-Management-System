<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("admin")) { %>
			<%! int srl_no = 1; %>
			<h1 class="table">Class List</h1>
			<c:if test="${fn:length(classList) > 0}">
				<table>
					<tr>
						<th>Sr.No</th>
						<th>ClassName</th>
						<th>Action</th>
					</tr>
					<c:forEach var="classList" items="${classList}">
						<c:url var="updateLink" value="ClassroomControlController">
							<c:param name="action" value="loadUpdateClass" />
							<c:param name="classid" value="${classList.classid}" />
						</c:url>
						<c:url var="deleteLink" value="ClassroomControlController">
							<c:param name="action" value="loadDeleteClass" />
							<c:param name="classid" value="${classList.classid}" />
						</c:url>
						<tr>
							<td><%= srl_no++ %></td>
							<td>${classList.classname}</td>
							<td><a href="${updateLink}">Update</a> | <a
								href="${deleteLink}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${fn:length(classList) <= 0}">
				<b>No data</b>
			</c:if>
			<a href="add-class.jsp" class="button">Add Class</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
	