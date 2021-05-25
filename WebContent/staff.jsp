<jsp:include page="include/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<h1 class="table">Staff List</h1>
			<c:if test="${fn:length(staffDataList) > 0}">
			<table>
				<tr>
					
					<th>Staff Name</th>
					<th>Position</th>
					<th>Salary</th>
					<th>Phone number</th>
					<th>Address</th>
					<th>Date of birth</th>
					<th>NRC</th>
					<th>Action</th>
				</tr>
				<c:forEach var="staffInfo" items="${staffDataList}">
					<c:url var="updateLink" value="StaffControlController">
						<c:param name="action" value="loadUpdate" />
						<c:param name="staffID" value="${staffInfo.staffid}" />
					</c:url>
					<c:url var="deleteLink" value="StaffControlController">
						<c:param name="action" value="loadDelete" />
						<c:param name="staffID" value="${staffInfo.staffid}" />
					</c:url>
					<tr>
						
						<td>${staffInfo.staffname}</td>
						<td>${staffInfo.position}</td>
						<td>${staffInfo.salary}</td>
						<td>${staffInfo.phoneno}</td>
						<td>${staffInfo.address}</td>
						<td>${staffInfo.dob}</td>
						<td>${staffInfo.nric}</td>
						<td>
							<a href="${updateLink}">Update</a> | 
							<a href="${deleteLink}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
			<c:if test="${fn:length(staffDataList) <= 0}">
				<b>No data</b>
			</c:if>
			<a href="add-staff.jsp" class="button">New Staff</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
	
	