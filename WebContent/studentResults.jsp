<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<main>
			<% if (role.equals("student")) { %>
				<div class="profile-box">
					<h1>Results</h1>
					<c:forEach var="mark" items="${marks}">
						<p>
							<strong>${mark.getSubjectname()}</strong>:
							${mark.getMark()}
						</p>
					</c:forEach>
				</div>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	


		
